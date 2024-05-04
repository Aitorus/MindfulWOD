package com.agb24.mindfulwod;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.agb24.mindfulwod.Connetion.ConnetionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegistroActivity extends AppCompatActivity {

    EditText nomapellidos,email,telefono,usuario,clave;
    Button registrar;
    TextView ingresar;
    Connection con;

    public RegistroActivity(){
        ConnetionBD instanceConnection = new ConnetionBD();
        con = instanceConnection.connect();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    nomapellidos = findViewById(R.id.lk_nombre);
    email = findViewById(R.id.lk_correo);
    telefono = findViewById(R.id.lk_telf);
    usuario = findViewById(R.id.lk_usuario);
    clave = findViewById(R.id.lk_contrasena);
    registrar = findViewById(R.id.bt_registrar);
    ingresar = findViewById(R.id.bt_iniciar);

    registrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RegistrarUser();
        }
    });
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresar = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(ingresar);
                finish();
            }
        });
    }
    public void RegistrarUser(){
        try {
            if (con==null){
                Toast.makeText(RegistroActivity.this,"Error de conexión",Toast.LENGTH_SHORT).show();
                return;
            }else{
                PreparedStatement stm = con.prepareStatement("INSERT INTO usuario VALUES(?,?,?,?,?)");
                stm.setString(1,nomapellidos.getText().toString());
                stm.setString(2,email.getText().toString());
                stm.setString(3,telefono.getText().toString());
                stm.setString(4,usuario.getText().toString());
                stm.setString(5,clave.getText().toString());
                stm.executeUpdate();
                Toast.makeText(RegistroActivity.this,"Registro completado",Toast.LENGTH_SHORT).show();
                nomapellidos.setText("");
                email.setText("");
                telefono.setText("");
                usuario.setText("");
                clave.setText("");
            }
        }catch (Exception e){
            Log.e("Error de conexión",e.getMessage());

        }
    }
}