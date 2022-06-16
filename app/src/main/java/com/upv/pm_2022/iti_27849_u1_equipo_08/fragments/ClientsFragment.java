package com.upv.pm_2022.iti_27849_u1_equipo_08.fragments;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.upv.pm_2022.iti_27849_u1_equipo_08.Customer;
import com.upv.pm_2022.iti_27849_u1_equipo_08.DbHandler;
import com.upv.pm_2022.iti_27849_u1_equipo_08.Owner;
import com.upv.pm_2022.iti_27849_u1_equipo_08.R;
import com.upv.pm_2022.iti_27849_u1_equipo_08.adapters.CustomerListAdapter;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.CustomerController;
import com.upv.pm_2022.iti_27849_u1_equipo_08.databinding.ClientsBinding;

import java.util.ArrayList;

public class ClientsFragment extends Fragment {

    private ClientsBinding binding;

    private Button btnAddClient;
    private ListView clientsList;
    private DbHandler dbHandler;
    private SQLiteDatabase write,read;
    private Owner owner;
    private ArrayList<Customer> customers;

    public ClientsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // DIFERENTE FORMA PARA SACAR LOS DATOS DE LA INTERFAZ
        binding = ClientsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        dbHandler = new DbHandler(view.getContext());
        read = dbHandler.getReadableDatabase();
        write = dbHandler.getWritableDatabase();

        CustomerController controller = new CustomerController(read);
        customers = (ArrayList<Customer>) controller.getAll();

        CustomerListAdapter adapter = new CustomerListAdapter(
                (Activity) view.getContext(),
                customers,
                dbHandler
        );
        clientsList = view.findViewById(R.id.clientsList);
        clientsList.setAdapter(adapter);

        btnAddClient = view.findViewById(R.id.btnAddClient);
        clientsList = view.findViewById(R.id.clientsList);

        btnAddClient.setOnClickListener(view1 -> NavHostFragment.findNavController(ClientsFragment.this).navigate(R.id.action_clients_to_add_clients));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
