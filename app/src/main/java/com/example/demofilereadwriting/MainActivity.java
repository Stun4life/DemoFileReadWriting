package com.example.demofilereadwriting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity {

    String folderLocation;
    Button btnRead, btnWrite;
    TextView textViewToDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);
        textViewToDisplay = findViewById(R.id.textViewToDisplay);

        askPermission();
        checkPermission();
        Log.i("permission", String.valueOf(checkPermission()));
        if (checkPermission()) {
            folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyFolder2";
            Log.i("folderLocation", String.valueOf(folderLocation));
            File folder = new File(folderLocation);
            if (folder.exists() == false) {
                boolean results = folder.mkdir();
                if (results == true) {
                    Log.i("results", String.valueOf(results));
                    Log.d("File Read/Write", "Folder Created");
                }
            }
        }
        else {
            Toast.makeText(this, "Folder not created", Toast.LENGTH_SHORT).show();
        }

        //FOLDER CREATION
    }
    private void askPermission(){
        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 103);
    }

    private boolean checkPermission(){
        int permissionCheck_read= ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionCheck_write = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck_read == PermissionChecker.PERMISSION_GRANTED || permissionCheck_write == PermissionChecker.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }

    }
}