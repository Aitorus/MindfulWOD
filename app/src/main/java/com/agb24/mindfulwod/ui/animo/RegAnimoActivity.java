package com.agb24.mindfulwod.ui.animo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.agb24.mindfulwod.Connetion.ConnetionBD;
import com.agb24.mindfulwod.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegAnimoActivity extends AppCompatActivity {

    EditText fecha;
    Spinner etiqueta;
    Button registrarAnimo, volver;

    Connection con;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_animo);

        fecha = findViewById(R.id.txt_fecha);
        etiqueta = findViewById(R.id.sel_animo);
        registrarAnimo = findViewById(R.id.bt_registrar);
        volver = findViewById(R.id.bt_volver);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String usuarioActual = sharedPreferences.getString("username", "");

        ConnetionBD instanceConnection = new ConnetionBD();
        con = instanceConnection.connect();
        // Selector Etiquetas
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.etiquetas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etiqueta.setAdapter(adapter);

        // Botón Registrar Animo
        registrarAnimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerIdUsuario(usuarioActual);
            }
        });
        //Botón volver
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void obtenerIdUsuario(String usuarioActual) {
        try {
            if (con == null) {
                Toast.makeText(RegAnimoActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                return;
            }

            PreparedStatement stmt = con.prepareStatement("SELECT id_usuario FROM usuario WHERE usuario = ?");
            stmt.setString(1, usuarioActual);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                registrarEstadoAnimo(idUsuario);
            } else {
                Toast.makeText(RegAnimoActivity.this, "No se encontró el usuario", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Log.e("Error de conexión", e.getMessage());
        }
    }

    private void registrarEstadoAnimo(int idUsuario) {
        try {
            String fechaSeleccionada = fecha.getText().toString();
            String etiquetaSeleccionada = etiqueta.getSelectedItem().toString();

            PreparedStatement insertStmt = con.prepareStatement("INSERT INTO animo (id_usuario, fecha, etiqueta) VALUES (?,?,?)");
            insertStmt.setInt(1, idUsuario);
            insertStmt.setString(2, fechaSeleccionada);
            insertStmt.setString(3, etiquetaSeleccionada);
            insertStmt.executeUpdate();

            fecha.setText("");
            etiqueta.setSelection(0); // Para reiniciar la selección del Spinner

            Toast.makeText(RegAnimoActivity.this, "Registro de ánimo exitoso", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e("Error de conexión", e.getMessage());
        }
    }
}


