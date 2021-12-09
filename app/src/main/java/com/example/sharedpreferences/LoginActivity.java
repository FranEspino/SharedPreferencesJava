package com.example.sharedpreferences;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharedpreferences.context.DataProccessor;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText et_correo;
    private EditText et_password;
    private CheckBox cb_guardarsesion;
    private Button btn_ingresar;
    DataProccessor dataProccessor = new DataProccessor(this);

    @Override
    protected void onStart() {
        super.onStart();
        if(dataProccessor.getBool("isLogin")){
            Intent intent = new Intent(this,PerfilActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_correo = findViewById(R.id.et_correo);
        et_password = findViewById(R.id.et_password);
        cb_guardarsesion = findViewById(R.id.cb_guardarsesion);
        btn_ingresar = findViewById(R.id.btn_iniciarsesion);

        cb_guardarsesion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                boolean check = cb_guardarsesion.isChecked();
                if ( isChecked )
                {
                    dataProccessor.setBool("isLogin",check);
                }else{
                    dataProccessor.setBool("isLogin",check);
                }

            }
        });

        dataProccessor.setStr("correopordefecto","Yosip.urquizo@gmail.com");
        dataProccessor.setStr("clavepordefecto","@123456");
        dataProccessor.setStr("correo","");

        dataProccessor.setStr("nombre","");
        dataProccessor.setStr("apellidos","");
        dataProccessor.setStr("edad","");
        dataProccessor.setStr("ciclo","");

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = et_correo.getText().toString();
                String password = et_password.getText().toString();

                boolean correoValido = Patterns.EMAIL_ADDRESS.matcher(correo).matches();

                if(correo.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Correo o contraseña vacia.", Toast.LENGTH_LONG).show();
                }else{

                    if(correoValido){
                        if( correo.equals(dataProccessor.getStr("correopordefecto"))
                            && password.equals(dataProccessor.getStr("clavepordefecto"))){
                            Intent intent = new Intent(LoginActivity.this,PerfilActivity.class);
                            intent.putExtra("inicioDeSesion",true);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "Dirección de correo incorrectas.", Toast.LENGTH_LONG).show();

                        }

                    }else{
                        Toast.makeText(LoginActivity.this, "Dirección de correo inválido.", Toast.LENGTH_LONG).show();

                    }

                }
            }
        });
    }
}