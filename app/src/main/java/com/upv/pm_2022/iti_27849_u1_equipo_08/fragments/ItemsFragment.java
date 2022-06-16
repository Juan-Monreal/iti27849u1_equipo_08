package com.upv.pm_2022.iti_27849_u1_equipo_08.fragments;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.upv.pm_2022.iti_27849_u1_equipo_08.DbHandler;
import com.upv.pm_2022.iti_27849_u1_equipo_08.Inventory;
import com.upv.pm_2022.iti_27849_u1_equipo_08.R;
import com.upv.pm_2022.iti_27849_u1_equipo_08.adapters.InventoryListAdapter;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.InventoryController;
import com.upv.pm_2022.iti_27849_u1_equipo_08.databinding.ItemsBinding;

import java.util.ArrayList;

public class ItemsFragment extends Fragment {

    private ItemsBinding binding;
    private Button btnAddItems;

    private ListView itemsList;
    private DbHandler dbHandler;
    private SQLiteDatabase write, read;
    private ArrayList<Inventory> inventories;

    public ItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // DIFERENTE FORMA PARA SACAR LOS DATOS DE LA INTERFAZ
        binding = ItemsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        dbHandler = new DbHandler(view.getContext());
        read = dbHandler.getReadableDatabase();
        write = dbHandler.getWritableDatabase();

        InventoryController controller = new InventoryController(read);
        inventories = (ArrayList<Inventory>) controller.getAllItems();

        InventoryListAdapter adapter = new InventoryListAdapter(
                (Activity) view.getContext(),
                inventories,
                dbHandler
        );

        itemsList = view.findViewById(R.id.itemsList);
        itemsList.setAdapter(adapter);

        btnAddItems = view.findViewById(R.id.btnAddItem);

        btnAddItems.setOnClickListener(view1 -> NavHostFragment.findNavController(ItemsFragment.this).navigate(R.id.action_items_to_add_item));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
