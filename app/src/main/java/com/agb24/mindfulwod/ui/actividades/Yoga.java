package com.agb24.mindfulwod.ui.actividades;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.agb24.mindfulwod.HomeActivity;
import com.agb24.mindfulwod.R;
import com.agb24.mindfulwod.ui.actividades.ActividadesFragment;

public class Yoga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga);

        Button playButton = findViewById(R.id.play_yt1);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // URL del video de YouTube
                String videoId = "FU4OTllcOXM";

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
                intent.putExtra("VIDEO_ID", videoId);
                startActivity(intent);
            }
        });

        Button backButton = findViewById(R.id.back_yt);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yt = new Intent(Yoga.this, HomeActivity.class);
                startActivity(yt);
                finish(); // Terminar la actividad actual
            }
        });
    }
}
