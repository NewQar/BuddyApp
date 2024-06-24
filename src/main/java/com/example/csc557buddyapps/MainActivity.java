package com.example.csc557buddyapps;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    SearchFragment searchFragment = new SearchFragment();
    AddFragment addFragment = new AddFragment();

    SendFragment sendFragment = new SendFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, homeFragment).commit();
                    return true;
                } else if (itemId == R.id.search) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, searchFragment).commit();
                    return true;
                } else if (itemId == R.id.add) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, addFragment).commit();
                    return true;
                } else if (itemId == R.id.send) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, sendFragment).commit();
                    return true;
                }

                return false;
            }
        });
    }


}
