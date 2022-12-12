package com.imadnohad.gestionsalle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.imadnohad.gestionsalle.models.Salle;
import com.imadnohad.gestionsalle.service.SalleService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SalleService salleService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);

        //SalleService salleService = new SalleService(SalleApplication.getSalleList());
        salleService = new SalleService();

        if(getIntent().hasExtra("salles")){
            ArrayList<Salle> salles = (ArrayList<Salle>) getIntent().getSerializableExtra("salles");
            salleService = new SalleService(salles);
        }


        RecyclerView rvSalles = (RecyclerView) findViewById(R.id.rvSalles);
        SallesAdapter adapter = new SallesAdapter(salleService.findAll(), this);
        rvSalles.setAdapter(adapter);
        rvSalles.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddActivity.class);
            intent.putExtra("salles", (Serializable)salleService.findAll());
            startActivity(intent);
        });
    }
}