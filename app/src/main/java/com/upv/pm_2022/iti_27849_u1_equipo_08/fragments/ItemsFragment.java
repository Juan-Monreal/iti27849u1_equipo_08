package com.upv.pm_2022.iti_27849_u1_equipo_08.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.upv.pm_2022.iti_27849_u1_equipo_08.R;
import com.upv.pm_2022.iti_27849_u1_equipo_08.databinding.ItemsBinding;

public class ItemsFragment extends Fragment {

    private ItemsBinding binding;
    private Button btnAddItems;

    public ItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // DIFERENTE FORMA PARA SACAR LOS DATOS DE LA INTERFAZ
        binding = ItemsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        btnAddItems = view.findViewById(R.id.btnAddItem);

        btnAddItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ItemsFragment.this).navigate(R.id.action_items_to_add_item);
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
