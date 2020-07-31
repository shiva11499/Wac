package com.example.recyclerviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    RecyclerView recyclerView;

    ArrayList names = new ArrayList<>(Arrays.asList("Shivansh", "Ayush", "Sanjana", "Divya", "Dharmendra", "Vartika", "Faisal"));
    ArrayList images = new ArrayList<>(Arrays.asList(R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        CustomAdapter adapter = new CustomAdapter(this,names,images);
        recyclerView.setAdapter(adapter);

    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.home:
//                Toast.makeText(this,"Open Home",Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.settings:
//                Toast.makeText(this,"Open Settings",Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.page_2:
//                Toast.makeText(this,"Open Music",Toast.LENGTH_SHORT).show();
//                return true;
//        }
//        return false;
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                return true;
            case R.id.settings:
                return true;
            case R.id.page_2:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
