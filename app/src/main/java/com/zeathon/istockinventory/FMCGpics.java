package com.zeathon.istockinventory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class FMCGpics extends AppCompatActivity implements View.OnClickListener {
    ImageView img, imageView1, imageView2, imageView3;
    private static final int IMAGE_REQUEST = 777;
    private Bitmap bitmap, bitmap2, bitmap3;
    Button choseImg1, choseImg2, choseImg3, uploadOGImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmcgpics);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);

        choseImg1 = findViewById(R.id.choseImg1);
        choseImg2 = findViewById(R.id.choseImg2);
        choseImg3 = findViewById(R.id.choseImg3);
        uploadOGImg = findViewById(R.id.uploadOGImg);

        uploadOGImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FMCGpics.this, FmcgStock.class);
                startActivity(intent);
            }
        });

        if (null == imageView1) {
            Log.e("Error", "Ouh! there is no there is no child view with R.id.imageView ID within my parent view View.");
        }
        choseImg1.setOnClickListener(this);
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    /*private void selectImage2() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    private void selectImage3() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {

            Uri picture = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), picture);
                imageView1.setImageBitmap(bitmap);
                /*bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), picture);
                bitmap3 = MediaStore.Images.Media.getBitmap(getContentResolver(), picture);

                imageView2.setImageBitmap(bitmap2);
                imageView3.setImageBitmap(bitmap3);*/
                // Img.setVisibility(View.VISIBLE);
                //btnChoose.setEnabled(false);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.choseImg1:
                selectImage();
                break;
        }
    }
}