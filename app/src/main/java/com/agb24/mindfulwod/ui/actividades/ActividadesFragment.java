package com.agb24.mindfulwod.ui.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.agb24.mindfulwod.R;


import java.util.ArrayList;

public class ActividadesFragment extends Fragment {

    private CustomListViewAdapter customListViewAdapter;

    private ArrayList<ActivityWODItem> activityWodStackList = new ArrayList<>();
    private ExpandableListView expandableLV;

    ImageView yoga_yt;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_actividades, container, false);
        expandableLV = root.findViewById(R.id.idExpandableListView);
        customListViewAdapter = new CustomListViewAdapter(activityWodStackList);
        expandableLV.setAdapter(customListViewAdapter);

        yoga_yt = root.findViewById(R.id.yoga_entrar);
        yoga_yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yog = new Intent(getActivity(), Yoga.class);
                startActivity(yog);
            }
        });

        ArrayList<String> r_consciente = new ArrayList<>();
        r_consciente.add("Ejercicio 1RC - Respira ");
        ArrayList<String> r_consciente_descriptions = new ArrayList<>(); // Lista de descripciones de ejercicios
        r_consciente_descriptions.add("Duración 5-10 minutos" +
                "1. Respira por la nariz y suelta el aire por la boca." +
                "2. Después de exhalar, espera tranquilamente hasta que tu cuerpo comience a inhalar de nuevo." +
                "3. Haz que cada inhalación por la nariz sea lenta y calmada." +
                "4. Cuando alcances la máxima inhalación, exhala lentamente por la boca abierta." +
                "5. Mantén la boca abierta, relaja la mandíbula y espera conscientemente hasta que sientas la necesidad de volver a inhalar." +
                "6. Después de dos o tres respiraciones, permite que el tiempo entre cada inhalación sea un momento de relajación total para tu cuerpo." +
                "7. Luego, respira hacia una parte específica de tu cuerpo que necesite relajarse especialmente." +
                "8. Repite este ritmo de respiración varias veces.");

        activityWodStackList.add(new ActivityWODItem("Respiración Consciente", r_consciente, r_consciente_descriptions));

        ArrayList<String> mindful = new ArrayList<>();
        mindful.add("Ejercicio 1MM -  Conecta con la música");
        ArrayList<String> mindful_descriptions = new ArrayList<>(); // Lista de descripciones de ejercicios
        mindful_descriptions.add("Duración 5-10 min" +
                "La música instrumental es genial para este ejercicio, pero si prefieres otros tipos de música, también está bien." +
                "Antes de empezar, tómate un momento para sentir cómo te encuentras." +
                "Mientras escuchas la música, presta atención a cómo te hace sentir. No juzgues tus emociones, solo obsérvalas." +
                "Recuerda que la música nos afecta a todos, ya sea que lo notemos o no." +
                "Presta atención a cómo tu cuerpo reacciona al ritmo de la música." +
                "Si estás en un lugar donde te sientes cómodo y tienes intimidad, ¡intenta bailar al ritmo de la música! Bailar puede hacerte sentir aún mejor y más conectado con la música.\\n\n" +
                "Cuando termines, piensa en las emociones que experimentaste mientras escuchabas la música.");
        activityWodStackList.add(new ActivityWODItem("Mindfulness", mindful, mindful_descriptions));

        customListViewAdapter.notifyDataSetChanged();

        // Agregar listeners para el click en los elementos
        expandableLV.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            ActivityWODItem activityWODItem = activityWodStackList.get(groupPosition);
            String programmingLanguageItem = activityWODItem.getProgrammingLanguages().get(childPosition);
            String exerciseDescription = activityWODItem.getExerciseDescriptions().get(childPosition); // Obtener la descripción del ejercicio
            Toast.makeText(getContext(), activityWODItem.getTechStack() + "/" + programmingLanguageItem + "\n" + exerciseDescription, Toast.LENGTH_LONG).show();
            return false;
        });

        expandableLV.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            ActivityWODItem activityWODItem = activityWodStackList.get(groupPosition);
            Toast.makeText(getContext(), activityWODItem.getTechStack(), Toast.LENGTH_LONG).show();
            return false;
        });

        return root;
    }
}