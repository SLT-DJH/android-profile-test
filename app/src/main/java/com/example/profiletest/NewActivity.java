package com.example.profiletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        ImageView profileImage = (ImageView) findViewById(R.id.profileNewImage);

        SharedPreferences myimage = getSharedPreferences("image", MODE_PRIVATE);
        Log.d("Tag", "SharedPrefernece : image => " + myimage.getString("image",""));
        if(myimage.getString("image","") == ""){
            Bitmap basebitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.profile_default);
            profileImage.setImageBitmap(basebitmap);
        } else{
            String bitmapString = myimage.getString("image", "");
            profileImage.setImageBitmap(StringToBitMap(bitmapString));
        }

    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e){
            e.getMessage();
            return null;
        }
    }
}
