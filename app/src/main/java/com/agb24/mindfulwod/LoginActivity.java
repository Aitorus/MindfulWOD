package com.agb24.mindfulwod;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    EditText usuario, clave;
    TextView regist;
    Button btlogin;
    Connection con;
    SharedPreferences sharedPreferences;

    public LoginActivity() {
        ConnetionBD instanceConnetion = new ConnetionBD();
        con = instanceConnetion.connect();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usuario = findViewById(R.id.lk_usuario);
        clave = findViewById(R.id.lk_contrasena);
        regist = findViewById(R.id.link_registro);
        btlogin = findViewById(R.id.bt_login);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new login().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, usuario.getText().toString());
                //revisar lin 57
            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(reg);
            }
        });

    }

    public class login extends AsyncTask<String, String, Boolean> {
        Boolean exito = false;
        String userName;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            if (success) {
                Toast.makeText(LoginActivity.this, "Has iniciado sesión", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", userName);
                editor.apply();
                Intent menu = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(menu);

            } else {
                Toast.makeText(LoginActivity.this, "Error de usuario/contraseña", Toast.LENGTH_SHORT).show();
            }
        }


        @Override
        protected Boolean doInBackground(String... strings) {
            if (con == null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                try {
                    String sql = "SELECT * FROM usuario WHERE usuario='" + strings[0] + "' AND clave ='" + clave.getText() + "'";
                    Statement stm = con.createStatement();
                    ResultSet rs = stm.executeQuery(sql);
                    if (rs.next()) {
                        exito = true;
                        userName = rs.getString("usuario");
                        usuario.setText("");
                        clave.setText("");
                    } else {
                        exito = false;
                        usuario.setText("");
                        clave.setText("");
                    }
                } catch (Exception e) {
                    Log.e("Error de conexión", Objects.requireNonNull(e.getMessage()));
                }
            }
            return exito;
        }
    }
}
