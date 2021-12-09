package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sharedpreferences.context.DataProccessor;

public class EditarPerfilActivity extends AppCompatActivity {
    DataProccessor dataProccessor = new DataProccessor(this);

    private EditText et_correo;
    private EditText et_nombre;
    private EditText et_apellidos;
    private EditText et_edad;
    private EditText et_ciclo;
    private Button btn_guardar;
    private Button btn_salir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
        et_correo = findViewById(R.id.et_correo);
        et_nombre = findViewById(R.id.et_nombre);
        et_apellidos = findViewById(R.id.et_apellidos);
        et_edad = findViewById(R.id.et_edad);
        et_ciclo = findViewById(R.id.et_ciclo);

        btn_guardar = findViewById(R.id.btn_guardar);
        btn_salir = findViewById(R.id.btn_salir);

        btn_guardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dataProccessor.setStr("correo",et_correo.getText().toString());
                dataProccessor.setStr("nombre",et_nombre.getText().toString());
                dataProccessor.setStr("apellidos",et_apellidos.getText().toString());
                dataProccessor.setStr("edad",et_edad.getText().toString());
                dataProccessor.setStr("ciclo", et_ciclo.getText().toString());
                Intent intent = new Intent(EditarPerfilActivity.this,PerfilActivity.class);
                startActivity(intent);
            }
        });

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditarPerfilActivity.this,PerfilActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        et_correo.setText(dataProccessor.getStr("correo"));
        et_nombre.setText(dataProccessor.getStr("nombre"));
        et_apellidos.setText(dataProccessor.getStr("apellidos"));
        et_edad.setText(dataProccessor.getStr("edad"));
        et_ciclo.setText(dataProccessor.getStr("ciclo"));

    }
}
