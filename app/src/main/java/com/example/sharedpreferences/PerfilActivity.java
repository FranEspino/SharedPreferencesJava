package com.example.sharedpreferences;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharedpreferences.context.DataProccessor;

public class PerfilActivity extends AppCompatActivity {
    DataProccessor dataProccessor = new DataProccessor(this);
    private TextView tv_correo;
    private TextView tv_nombre;
    private TextView tv_apellidos;
    private TextView tv_edad;
    private TextView tv_ciclo;

    private Button btn_editarperfil;
    private Button btn_cerrarsesion;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        boolean estadoCheckBox = dataProccessor.getBool("isLogin");
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            boolean inicioSesion = bundle.getBoolean("inicioDeSesion",false);
            if(!inicioSesion && !estadoCheckBox) {
                Intent intent = new Intent(PerfilActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        } else {
            if(!estadoCheckBox){
                Intent intent = new Intent(PerfilActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }

        tv_correo = findViewById(R.id.tv_correo);
        tv_nombre = findViewById(R.id.tv_nombre);
        tv_apellidos = findViewById(R.id.tv_apellidos);
        tv_edad = findViewById(R.id.tv_edad);
        tv_ciclo = findViewById(R.id.tv_ciclo);
        btn_editarperfil = findViewById(R.id.btn_editarperfil);
        btn_cerrarsesion = findViewById(R.id.btn_cerrarsesion);

        btn_editarperfil.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this,EditarPerfilActivity.class);
                startActivity(intent);
            }
        });

        btn_cerrarsesion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dataProccessor.setBool("isLogin",false);
                Intent intent = new Intent(PerfilActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

        tv_correo.setText("Correo: "+dataProccessor.getStr("correo"));
        tv_nombre.setText("Nombre: "+dataProccessor.getStr("nombre"));
        tv_apellidos.setText("Apellidos: "+dataProccessor.getStr("apellidos"));
        tv_edad.setText("Edad: "+dataProccessor.getStr("edad"));
        tv_ciclo.setText("Ciclo: "+dataProccessor.getStr("ciclo"));
    }
}