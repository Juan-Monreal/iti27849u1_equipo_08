package com.upv.pm_2022.iti_27849_u1_equipo_08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.OwnerController;

import java.security.PrivateKey;

import fragments.HomeFragment;
import fragments.LoansFragment;
import fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationn;
    private Switch sw1;
    private FrameLayout frameLayout;
    private SQLiteDatabase read, write;
    private DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationn =(BottomNavigationView) findViewById(R.id.bottomNavegation);
        sw1=findViewById(R.id.id_switch);
        frameLayout = findViewById(R.id.frameLayout);
        mBottomNavigationn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if(menuItem.getItemId() == R.id.menu_home ) {
                   showSelectedFragment(new HomeFragment());
                }
                if(menuItem.getItemId() == R.id.menu_loans ) {
                    showSelectedFragment(new LoansFragment());
                }
                if(menuItem.getItemId() == R.id.menu_profile ) {
                    showSelectedFragment(new ProfileFragment());

                }



                return false;
            }
        });

        this.read = dbHandler.getReadableDatabase();
        this.write = dbHandler.getWritableDatabase();
    }
    private void showSelectedFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public void validarSwitch(View view) {
        if (sw1.isChecked()) {
            Toast.makeText(this, "Activo", Toast.LENGTH_SHORT).show();
        } else {
            if (sw1.isChecked())
                Toast.makeText(this, " no activo", Toast.LENGTH_SHORT).show();
        }
    }

    private void todo(){
        OwnerController ownerController = new OwnerController(read);
        Owner owner = ownerController.getOwner(1);
        System.out.println(owner.toString());
        ownerController.setDb(write);
        owner.setName("Owner Name");
        owner.setLast_name("Owner LastName");
        ownerController.updateOwner(owner);
        ownerController.setDb(read);
        Owner owner1 = ownerController.getOwner(1);
        System.out.println("Owner editado \n" + owner1.toString());
    }
}