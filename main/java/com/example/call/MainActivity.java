package com.example.call;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE=1;
    EditText phone;
    Button call;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting the edittext and button instance
        phone=(EditText)findViewById(R.id.et_number);
        call=(Button)findViewById(R.id.bt_call);

        //Performing action on button click
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber = phone.getText().toString();
                /*if(phoneNumber.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Please Enter Number",Toast.LENGTH_SHORT).show();
                }
                else {
                intentcall.setData(Uri.parse("tel:" + phoneNumber));
                }*/
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CODE);
                }
                else {
                    Intent intentcall = new Intent(Intent.ACTION_CALL);
                    intentcall.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(intentcall);
                    finish();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode== REQUEST_CODE){
            if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                Intent intentcall = new Intent(Intent.ACTION_CALL);
                intentcall.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intentcall);
                finish();
            }
        }
    }
}

