package com.example.photo_edit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button button, buttl, buttr;
    SeekBar seekh, seekw, seeka, seekr, seekg, seekb;

    public int rotate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imgv);

        button = findViewById(R.id.buttshoot);
        buttl = findViewById(R.id.buttleft);
        buttr = findViewById(R.id.buttright);

        seekh = findViewById(R.id.seekheight);
        seekw = findViewById(R.id.seekwidth);
        seeka = findViewById(R.id.seekalpha);
        seekr = findViewById(R.id.seekred);
        seekg = findViewById(R.id.seekgreen);
        seekb = findViewById(R.id.seekblue);

        buttl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotate -= 90;
                imageView.setRotation(rotate);
            }
        });
        buttr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotate -= 90;
                imageView.setRotation(rotate);
            }
        });

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }
}