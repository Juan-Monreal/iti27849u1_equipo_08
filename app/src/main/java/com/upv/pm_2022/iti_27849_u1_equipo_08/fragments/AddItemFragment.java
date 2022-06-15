package com.upv.pm_2022.iti_27849_u1_equipo_08.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.upv.pm_2022.iti_27849_u1_equipo_08.DbHandler;
import com.upv.pm_2022.iti_27849_u1_equipo_08.Inventory;
import com.upv.pm_2022.iti_27849_u1_equipo_08.Owner;
import com.upv.pm_2022.iti_27849_u1_equipo_08.R;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.InventoryController;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.OwnerController;
import com.upv.pm_2022.iti_27849_u1_equipo_08.databinding.AddItemBinding;

public class AddItemFragment extends Fragment {

    private AddItemBinding binding;
    private DbHandler dbHandler;
    private SQLiteDatabase write,read;
    private ImageButton btnBack;
    private Button btnSave;
    private EditText txtItemName;
    private Owner owner;

    public AddItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // DIFERENTE FORMA PARA SACAR LOS DATOS DE LA INTERFAZ
        binding = AddItemBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        dbHandler = new DbHandler(view.getContext());
        read = dbHandler.getReadableDatabase();
        write = dbHandler.getWritableDatabase();
        btnBack = view.findViewById(R.id.btn_back);
        btnSave = view.findViewById(R.id.btnSave);
        txtItemName = view.findViewById(R.id.txtItemName);

        OwnerController ownerController = new OwnerController(read);
        owner = ownerController.getOwner(1);
        InventoryController controller = new InventoryController(write);
        // Botón para regresar al fragment items
        btnBack.setOnClickListener(view1 -> NavHostFragment.findNavController(AddItemFragment.this).navigate(R.id.action_add_item_to_items));

        btnSave.setOnClickListener( v -> {
            String name = txtItemName.getText().toString();
            if (name.isEmpty()){
                Toast.makeText(getContext(), "Please input a valid name", Toast.LENGTH_SHORT).show();
            }else {
                Inventory inventory = new Inventory(name, owner.getId());
                controller.addItem(inventory);
                Toast.makeText(getContext(), "Item Added sucessfully!", Toast.LENGTH_SHORT).show();
                txtItemName.setText("");
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
