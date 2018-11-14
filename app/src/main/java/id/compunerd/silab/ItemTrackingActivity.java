package id.compunerd.silab;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import id.compunerd.silab.rest.ApiInterface;
import id.compunerd.silab.rest.UtilsApi;
import okhttp3.ResponseBody;
import pl.aprilapps.easyphotopicker.EasyImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemTrackingActivity extends AppCompatActivity {

    // TODO: 14/11/2018 Ini Textview asal aja buat tampilin ukuran file percobaan
    TextView text10, text11, text12, text13;

    // TODO: 14/11/2018 Ini button buka gallery masih auto upload
    Button openGallery;
    ImageView imageViewUpload;
    ApiInterface mApiService;
    String idPengujian;

    final static int REQUEST_CODE_GALLERY = 001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_tracking);

        mApiService = UtilsApi.getAPIService();
        Intent i = getIntent();
        idPengujian = i.getStringExtra("idPengujian");

        text10 = findViewById(R.id.text10);
        text11 = findViewById(R.id.text11);
        text10.setText(idPengujian);

        openGallery = findViewById(R.id.btnSelectImage);
        imageViewUpload = findViewById(R.id.imageViewUpload);
        openGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyImage.openGallery(ItemTrackingActivity.this, REQUEST_CODE_GALLERY);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new EasyImage.Callbacks() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                // TODO: 14/11/2018 Tambahin kondisi error ketika pick image error
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                int file_size = Integer.parseInt(String.valueOf(imageFile.length()/1024));

                if (file_size >= 2048){
                    Toast.makeText(ItemTrackingActivity.this, "Ukuran file terlalu besar", Toast.LENGTH_SHORT).show();
                }else {

                    // TODO: 14/11/2018 Ini buat tampilan ke imageview
                    Glide.with(ItemTrackingActivity.this)
                            .load(imageFile)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imageViewUpload);

                    // TODO: 14/11/2018 Encode Base64
                    Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                    String encodedImage = encodeToBase64(myBitmap, Bitmap.CompressFormat.JPEG, 50);

                    // TODO: 14/11/2018 proses upload
                    uploadFile(encodedImage, idPengujian);
                }
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                // TODO: 14/11/2018 Tambahin kondisi cancel ambil image
            }
        });
    }

    private void uploadFile(String encodedImage, String idPengujian) {
        mApiService.uploadFile(encodedImage, idPengujian).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // TODO: 14/11/2018 Tambahin progress bar kalo bisa
                Toast.makeText(ItemTrackingActivity.this, "Berhasil Upload", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {

        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.NO_WRAP);
    }

}
