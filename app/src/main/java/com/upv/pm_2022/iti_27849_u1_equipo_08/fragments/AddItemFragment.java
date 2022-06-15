package com.upv.pm_2022.iti_27849_u1_equipo_08.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.upv.pm_2022.iti_27849_u1_equipo_08.R;
import com.upv.pm_2022.iti_27849_u1_equipo_08.databinding.AddItemBinding;

public class AddItemFragment extends Fragment {

    private AddItemBinding binding;

    private ImageButton btnBack;

    public AddItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // DIFERENTE FORMA PARA SACAR LOS DATOS DE LA INTERFAZ
        binding = AddItemBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        btnBack = view.findViewById(R.id.btn_back);

        // Botón para regresar al fragment items
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AddItemFragment.this).navigate(R.id.action_add_item_to_items);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
