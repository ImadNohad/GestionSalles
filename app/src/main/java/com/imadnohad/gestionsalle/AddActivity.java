package com.imadnohad.gestionsalle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.imadnohad.gestionsalle.models.Salle;
import com.imadnohad.gestionsalle.service.SalleService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddActivity extends AppCompatActivity {

    boolean isEdit = false;
    int salleId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        TextView txtCode = findViewById(R.id.txtCode);
        TextView txtLibele = findViewById(R.id.txtLibele);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        Button btnSave = (Button) findViewById(R.id.btnSave);


        Bundle extras = getIntent().getExtras();

        SalleService salleService;
        ArrayList<Salle> salles = (ArrayList<Salle>) getIntent().getSerializableExtra("salles");

        if (salles == null) { salleService = new SalleService(); }
        else { salleService = new SalleService(salles); }

        if(getIntent().hasExtra("salleId")){
            salleId = extras.getInt("salleId");
            //Salle salle = new SalleService(SalleApplication.getSalleList()).findById(salleId);
            Salle salle = salleService.findById(salleId);
            txtCode.setText(salle.getCode());
            txtLibele.setText(salle.getLibele());
            isEdit = true;
        }

        btnSave.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);

            //SalleService salleService = new SalleService(SalleApplication.getSalleList());

            String code = txtCode.getText().toString();
            String libele = txtLibele.getText().toString();

            if("".equals(code) || "".equals(libele)){
                Toast.makeText(this,"Code et libelé obligatoire !!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isEdit) {
                Salle salle = new Salle(txtCode.getText().toString(), txtLibele.getText().toString());
                salleService.create(salle);
            } else {
                Salle salle = salleService.findById(salleId);
                salle.setCode(code);
                salle.setLibele(libele);
                salleService.update(salle);
            }

            intent.putExtra("salles", (Serializable)salleService.findAll());
            startActivity(intent);
        });

        btnCancel.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}