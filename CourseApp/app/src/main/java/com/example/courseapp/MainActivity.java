package com.example.courseapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragement = new HomeFragment();

            switch (item.getItemId()){
                case R.id.nav_newsfeed:
                    selectedFragement = new HomeFragment();
                    break;
                case R.id.nav_search:
                    selectedFragement = new SearchFragment();
                    break;

                case R.id.nav_add:
                    selectedFragement = null;
                    startActivity(new Intent(MainActivity.this,PostActivity.class));
                    break;
                case R.id.nav_chat:
                    selectedFragement = new ChatFragment();
                    break;
                case R.id.nav_profile:
                    selectedFragement = new ProfileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragement).commit();
            return true;
        }
    };
}
