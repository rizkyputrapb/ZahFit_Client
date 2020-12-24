package com.example.zahfitclient.ui.editprofile;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zahfitclient.MainActivity;
import com.example.zahfitclient.R;
import com.example.zahfitclient.UserMainActivity;
import com.example.zahfitclient.databinding.EditProfileFragmentBinding;
import com.example.zahfitclient.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.base.Splitter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static android.app.Activity.RESULT_OK;

public class EditProfileFragment extends Fragment {

    EditProfileFragmentBinding binding;
    Map<String, Object> usermodel;
    private FirebaseUser user;
    private DatabaseReference mDatabase;
    private Uri filePath;
    FirebaseStorage storage;
    StorageReference storageReference;
    private final int PICK_IMAGE_REQUEST = 71;

    public static EditProfileFragment newInstance() {
        return new EditProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        binding = DataBindingUtil.inflate(inflater, R.layout.edit_profile_fragment, container, false);
        View view = binding.getRoot();
        user = FirebaseAuth.getInstance().getCurrentUser();
        String getUserId = user.getUid();
        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
        mStorageRef.child(getUserId + ".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(view.getContext()).load(uri).into(binding.imageView4);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String uri = "@drawable/ic_baseline_person_pin_24";  // where myresource (without the extension) is the file
                int imageResource = getResources().getIdentifier(uri, null, getActivity().getPackageName());
                Drawable res = getActivity().getDrawable(imageResource);
                binding.imageView4.setImageDrawable(res);
            }
        });
        binding.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
        binding.btnConfirmEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usermodel = new HashMap<>();
                String email, name, username, img;
                name = binding.txtEditName.getText().toString();
                email = binding.txtEditEmail.getText().toString();
                username = binding.txtEditUsername.getText().toString();
                if (!name.isEmpty()) {
                    usermodel.put("name", name);
                }
                if (!email.isEmpty()) {
                    usermodel.put("email", email);
                }
                if (!username.isEmpty()) {
                    usermodel.put("username", username);
                }

                // age, weight, height must be integer or long

                if (!binding.txtEditAge.getText().toString().isEmpty()) {
                    usermodel.put("age", Long.parseLong(binding.txtEditAge.getText().toString()));
                }
                if (!binding.txtEditWeight.getText().toString().isEmpty()) {
                    usermodel.put("weight", Long.parseLong(binding.txtEditWeight.getText().toString()));
                }
                if (!binding.txtEditHeight.getText().toString().isEmpty()) {
                    usermodel.put("height", Long.parseLong(binding.txtEditHeight.getText().toString()));
                }
                img = user.getUid() + ".png";
                usermodel.put("img", img);
                storage = FirebaseStorage.getInstance();
                storageReference = storage.getReference();
                if (filePath != null) {
                    final ProgressDialog progressDialog = new ProgressDialog(getContext());
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();
                    StorageReference ref = storageReference.child(img);
                    ref.putFile(filePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                                    mDatabase.child("user").child(user.getUid()).updateChildren(usermodel);
//                                    Log.w("UPDATE", "Update Success " + convertWithGuava("email"));
                                    Intent i = new Intent(getContext(), UserMainActivity.class);
                                    startActivity(i);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                            .getTotalByteCount());
                                    progressDialog.setMessage("Uploaded " + (int) progress + "%");
                                }
                            });
                } else {
                    mDatabase.child("user").child(user.getUid()).updateChildren(usermodel);
//                    Log.w("UPDATE", "Update Success " + convertWithGuava("email"));
                    Intent i = new Intent(getContext(), UserMainActivity.class);
                    startActivity(i);
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                binding.imageView4.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<String, String> convertWithGuava(String mapAsString) {
        return Splitter.on(',').withKeyValueSeparator('=').split(mapAsString);
    }
}