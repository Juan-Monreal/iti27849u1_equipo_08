package com.upv.pm_2022.iti_27849_u1_equipo_08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ImportExport extends AppCompatActivity {

    private Button btnImport;
    private Button btnExport;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_export);

        btnImport = findViewById(R.id.btnImport);
        btnExport = findViewById(R.id.btnExport);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(view -> onBackPressed());

    }
}