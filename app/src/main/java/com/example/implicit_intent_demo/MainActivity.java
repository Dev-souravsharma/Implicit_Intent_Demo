package com.example.implicit_intent_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtURL, edtLocation, editText;
    Button urlButton, locationButton, shareButton, cameraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtURL = findViewById(R.id.editTextTextPersonName);
        edtLocation = findViewById(R.id.editTextLocation);
        editText = findViewById(R.id.editTextSHARE);
        urlButton = findViewById(R.id.edtButton);
        shareButton = findViewById(R.id.edtShare);
        cameraButton = findViewById(R.id.edtCamera);
        locationButton = findViewById(R.id.edtButtonLocation);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            urlButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openWebsite(urlButton);
                }
            });

        }
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLocation(locationButton);
            }
        });
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareText(shareButton);
            }
        });
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera(cameraButton);
            }
        });
    }

    public void openWebsite(View view) {
        String url = edtURL.getText().toString().trim();
        Uri data = Uri.parse("https://" + url);
        Intent intent = new Intent(Intent.ACTION_VIEW, data);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Toast.makeText(this, "Can't Handle", Toast.LENGTH_SHORT).show();
    }

    public void openLocation(View view) {
        String locationGeo = edtLocation.getText().toString().trim();
        Uri geo = Uri.parse("geo:0,0?q=" + locationGeo);
        Intent intent = new Intent(Intent.ACTION_VIEW, geo);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Toast.makeText(this, "Can't Handle", Toast.LENGTH_SHORT).show();
    }

    public void shareText(View view) {
        String share = editText.getText().toString().trim();
        String mime = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mime)
                .setChooserTitle("Share")
                .setText(share)
                .startChooser();
    }

    private void takePhotoUsingCamera() {
        Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePhoto.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePhoto, 100);
        }
    }

    public void openCamera(View view) {
        takePhotoUsingCamera();
    }
}