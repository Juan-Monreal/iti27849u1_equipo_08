package com.upv.pm_2022.iti_27849_u1_equipo_08;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ImportExport extends AppCompatActivity {

    private Button btnImport;
    private Button btnExport;
    private Button btnBack;
    private DbHandler dbHandler;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_export);

        btnImport = findViewById(R.id.btnImport);
        btnExport = findViewById(R.id.btnExport);
        btnBack = findViewById(R.id.btnBack);
        dbHandler = new DbHandler(this);

        btnBack.setOnClickListener(view -> onBackPressed());
        btnExport.setOnClickListener( v -> {
            String path = dbHandler.exportDatabaseRows();
            Toast.makeText(getApplicationContext(), "File created check the path: \n " + path, Toast.LENGTH_SHORT).show();
        });

    }
}