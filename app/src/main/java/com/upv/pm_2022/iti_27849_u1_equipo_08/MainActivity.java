package com.upv.pm_2022.iti_27849_u1_equipo_08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.upv.pm_2022.iti_27849_u1_equipo_08.fragments.HomeFragment;
import com.upv.pm_2022.iti_27849_u1_equipo_08.fragments.LoansFragment;
import com.upv.pm_2022.iti_27849_u1_equipo_08.fragments.ProfileFragment;

import com.upv.pm_2022.iti_27849_u1_equipo_08.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationn;
    private Switch sw1;
    private FrameLayout frameLayout;
    private SQLiteDatabase read, write;
    private DbHandler dbHandler;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_loans, R.id.navigation_profile,
                R.id.navigation_clients, R.id.navigation_items)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        /*
        mBottomNavigationn =(BottomNavigationView) findViewById(R.id.bottomNavegation);
        sw1=findViewById(R.id.id_switch);
        //frameLayout = findViewById(R.id.frameLayout);
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
        */
        dbHandler = new DbHandler(this, "", null, 1);
        this.read = dbHandler.getReadableDatabase();
        this.write = dbHandler.getWritableDatabase();
        // showSelectedFragment(new HomeFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent intent = new Intent(this, AboutUs.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_import_export) {
            Intent intent = new Intent(this, Import_Export.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
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
    } */

}