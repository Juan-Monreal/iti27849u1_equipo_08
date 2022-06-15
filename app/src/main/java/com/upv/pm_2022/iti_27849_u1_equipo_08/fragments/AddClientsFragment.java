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

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.upv.pm_2022.iti_27849_u1_equipo_08.Customer;
import com.upv.pm_2022.iti_27849_u1_equipo_08.DbHandler;
import com.upv.pm_2022.iti_27849_u1_equipo_08.Owner;
import com.upv.pm_2022.iti_27849_u1_equipo_08.R;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.CustomerController;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.OwnerController;
import com.upv.pm_2022.iti_27849_u1_equipo_08.databinding.AddClientBinding;

public class AddClientsFragment extends Fragment {

    private AddClientBinding binding;

    private ImageButton btnBack;
    private DbHandler dbHandler;
    private SQLiteDatabase write,read;
    private Button btnSave;
    private EditText lastName, firstName;
    private Owner owner;

    public AddClientsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // DIFERENTE FORMA PARA SACAR LOS DATOS DE LA INTERFAZ
        binding = AddClientBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        dbHandler = new DbHandler(view.getContext());
        read = dbHandler.getReadableDatabase();
        write = dbHandler.getWritableDatabase();

        btnSave = view.findViewById(R.id.btnSave);
        btnBack = view.findViewById(R.id.btn_back);
        lastName = view.findViewById(R.id.lastName);
        firstName = view.findViewById(R.id.firstName);

        OwnerController ownerController = new OwnerController(read);
        owner = ownerController.getOwner(1);

        CustomerController controller = new CustomerController(write);

        // Botón para regresar al fragent clients
        btnBack.setOnClickListener(view1 -> NavHostFragment.findNavController(AddClientsFragment.this).navigate(R.id.action_add_clients_to_clients));

        // TODO:
        //  falta la conexión entre fragments de formulario
        btnSave.setOnClickListener( v -> {
            String name = firstName.getText().toString();
            String last = lastName.getText().toString();
            if (name.isEmpty() || last.isEmpty()){
                Toast.makeText(getContext(), "Please input valid data", Toast.LENGTH_SHORT).show();
            } else {
                Customer customer = new Customer(name, last);
                controller.addCustomer(customer);
                Toast.makeText(getContext(), "Customer Added successfully!", Toast.LENGTH_SHORT).show();
                firstName.setText("");
                lastName.setText("");
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
