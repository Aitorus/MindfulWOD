package com.agb24.mindfulwod.Connetion;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public class ConnetionBD {
    @SuppressLint("NewApi")
    public Connection connect() {
        Connection connection = null;
        String connectionURL;
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://192.168.1.139:1433;databaseName=DBMAINFULWOD;user=adminwod;password=adminwod";
            connection = DriverManager.getConnection(connectionURL);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Error de conexión SQL", Objects.requireNonNull(e.getMessage()));
        }
        return connection;
    }
}

