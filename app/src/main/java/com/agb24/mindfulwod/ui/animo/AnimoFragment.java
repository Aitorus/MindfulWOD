package com.agb24.mindfulwod.ui.animo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.agb24.mindfulwod.R;
import com.agb24.mindfulwod.databinding.FragmentAnimoBinding;

public class AnimoFragment extends Fragment {

    private Button registrar_animo; // Cambio de Button a private
    private FragmentAnimoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AnimoViewModel animoViewModel =
                new ViewModelProvider(this).get(AnimoViewModel.class);

        binding = FragmentAnimoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        animoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Buscar el botón dentro de la vista root
        registrar_animo = root.findViewById(R.id.reg_estado);
        registrar_animo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresar = new Intent(requireActivity(), RegAnimoActivity.class);
                startActivity(ingresar);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
