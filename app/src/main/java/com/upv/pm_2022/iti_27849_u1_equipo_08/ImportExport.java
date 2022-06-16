package com.upv.pm_2022.iti_27849_u1_equipo_08;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ImportExport extends AppCompatActivity {

    private Button btnImport;
    private Button btnExport;
    private Button btnBack;
    private DbHandler dbHandler;
    private ActivityResultLauncher<Intent> resultLauncher;

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
        btnImport.setOnClickListener( v -> {
            searchFile();
        });

        resultLauncher = registerForActivityResult(
                new ActivityResultContracts
                        .StartActivityForResult(),
                result -> {
                    // Initialize result data
                    Intent data = result.getData();
                    // check condition
                    if (data != null) {
                        Uri sUri = data.getData();
                        File file = new File(sUri.getPath());//create path from uri
                        String respaldo = "";
                        try {
                            respaldo = LeerArchivoTexto(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (checkFile(respaldo)){
                            dbHandler.importDb(respaldo);
                            Toast.makeText(getBaseContext(),"Data has been successfully imported!",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Error! Invalid file content", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void searchFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        resultLauncher.launch(intent);
    }

    private String LeerArchivoTexto (File file) throws IOException {
        String fileContent = "";
        if (file.exists()) { // Checar si el archivo Existe
            if (!file.getName().endsWith(".txt")){
                Toast.makeText(getBaseContext(), "Only .txt file are allowed", Toast.LENGTH_SHORT).show();
            }else{
                FileInputStream fIn = new FileInputStream(file);
                BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                String aDataRow = "";
                String aBuffer = "";
                while ((aDataRow = myReader.readLine()) != null) {
                    aBuffer += aDataRow + "\n";
                }
                fileContent += (aBuffer);
                myReader.close();
                Toast.makeText(getBaseContext(),"The file was read",Toast.LENGTH_SHORT).show();
                if (checkFile(fileContent)){
                    Toast.makeText(getApplicationContext(), "File is Valid!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error! Invalid file content", Toast.LENGTH_SHORT).show();
                    return null;
                }
            }
            }else {
                Toast.makeText(getApplicationContext(),"There IS NO SUCH FILE"+ file.getName(),Toast.LENGTH_SHORT).show();
            }
        return fileContent;
    }

    private boolean checkFile(String fileContent) {
        if(fileContent.contains("INSERT INTO")){
            return true;
        }else {
            return false;
        }
    }

}