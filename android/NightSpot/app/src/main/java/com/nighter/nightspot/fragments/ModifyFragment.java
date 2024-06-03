package com.nighter.nightspot.fragments;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nighter.nightspot.MainActivity;
import com.nighter.nightspot.R;
import com.nighter.nightspot.databinding.FragmentModifyBinding;
import com.nighter.nightspot.databinding.FragmentUserBinding;
import com.nighter.nightspot.models.UploadImageRequest;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.retrofit.RetrofitService;
import com.nighter.nightspot.retrofit.SpotApi;
import com.nighter.nightspot.retrofit.UserApi;
import com.nighter.nightspot.sharedpreferences.SharedPref;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Response;


public class ModifyFragment extends Fragment {

    private FragmentModifyBinding binding;
    private ModifyFragmentArgs args;

    private byte[] readBytes(InputStream inputStream) throws IOException {
        // this dynamically extends to take the bytes you read
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        // this is storage overwritten on each iteration with bytes
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        // we need to know how may bytes were read to write them to the byteBuffer
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        // and then we can return your byte array.
        return byteBuffer.toByteArray();
    }

    private File f;

    private byte[] bytes;

    private UploadImageRequest uploadImageRequest = new UploadImageRequest();

    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                // Callback is invoked after the user selects a media item or closes the
                // photo picker.
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: " + uri);

                    ContentResolver contentResolver = getContext().getContentResolver();



                    try (Cursor cursor = contentResolver.query(uri, null, null, null, null)) {
                        if (cursor != null && cursor.moveToFirst()) {
                            int displayNameColumnIndex = cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME);
                            int mimeTypeColumnIndex = cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME);

                            String fileName = cursor.getString(displayNameColumnIndex);
                            String mimeType = cursor.getString(mimeTypeColumnIndex);
                            uploadImageRequest.setFileName(fileName);
                            uploadImageRequest.setMimeTipe(mimeType);


                        }
                    }

                    try (InputStream is= contentResolver.openInputStream(uri)){


                        bytes = readBytes(is);
                        String s = Base64.getEncoder().encodeToString(bytes);
                        uploadImageRequest.setImageData(s);

                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                        // Set the Bitmap to your ImageView
                        binding.profileImage.setImageBitmap(bitmap);



                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    Log.d("PhotoPicker", "No media selected");
                }
            });

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    pickMedia.launch(new PickVisualMediaRequest.Builder()
                            .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                            .build());
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            });


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = ModifyFragmentArgs.fromBundle(getArguments());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentModifyBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        User user = args.getUser();
        binding.email.setText(user.getEmail());
        binding.instagram.setText(user.getInstagramHandle());
        Picasso.get()
                .load("http://192.168.1.62:8080/"+ user.getPhoto()).placeholder(R.drawable.giphy)
                .into(binding.profileImage);

        binding.save.setOnClickListener(v->{
            String email = binding.email.getText().toString().trim();
            String instagram = binding.instagram.getText().toString().trim();
            user.setEmail(email);
            user.setInstagramHandle(instagram);
            String token = SharedPref.loadToken(getContext());

            System.out.println(user);

            userApi.updateUser(user,"Bearer "+token).enqueue(new retrofit2.Callback<Void>(){

                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    System.out.println(response.code());


                    userApi.uploadPhoto(uploadImageRequest ,user.getUsername()).enqueue(new retrofit2.Callback<Void>(){

                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            System.out.println(response.code() + " inserimento foto");
                            NavController navController = Navigation.findNavController(view);
                            NavDirections toUser = ModifyFragmentDirections.modifyToUser(user);
                            navController.navigate(toUser);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getContext(), "save failed", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
                        }
                    });

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getContext(), "Load failed", Toast.LENGTH_SHORT).show();
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
                }
            });

        });
        binding.profileImage.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(
                    getContext(), Manifest.permission.READ_MEDIA_IMAGES) ==
                    PackageManager.PERMISSION_GRANTED) {
                // You can use the API that requires the permission.
                pickMedia.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build());


            } else if (ActivityCompat.shouldShowRequestPermissionRationale(
                    getActivity(), Manifest.permission.READ_MEDIA_IMAGES)) {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected, and what
                // features are disabled if it's declined. In this UI, include a
                // "cancel" or "no thanks" button that lets the user continue
                // using your app without granting the permission.
                //showInContextUI(...);
            } else {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requestPermissionLauncher.launch(
                            Manifest.permission.READ_MEDIA_IMAGES);
                } else {
                    pickMedia.launch(new PickVisualMediaRequest.Builder()
                            .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                            .build());
                }
            }

        });
    }
}