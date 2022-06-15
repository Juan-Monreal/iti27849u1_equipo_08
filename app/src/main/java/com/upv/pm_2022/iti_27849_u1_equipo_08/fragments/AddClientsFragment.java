package com.upv.pm_2022.iti_27849_u1_equipo_08.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.upv.pm_2022.iti_27849_u1_equipo_08.R;
import com.upv.pm_2022.iti_27849_u1_equipo_08.databinding.AddClientBinding;

public class AddClientsFragment extends Fragment {

    private AddClientBinding binding;

    private ImageButton btnBack;

    public AddClientsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // DIFERENTE FORMA PARA SACAR LOS DATOS DE LA INTERFAZ
        binding = AddClientBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        btnBack = view.findViewById(R.id.btn_back);

        // Botón para regresar al fragent clients
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AddClientsFragment.this).navigate(R.id.action_add_clients_to_clients);
            }
        });

        // TODO:
        //  falta la conexión entre fragments de formulario

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
