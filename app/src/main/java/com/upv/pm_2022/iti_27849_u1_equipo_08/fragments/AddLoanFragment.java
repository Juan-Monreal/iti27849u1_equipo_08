package com.upv.pm_2022.iti_27849_u1_equipo_08.fragments;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.upv.pm_2022.iti_27849_u1_equipo_08.Customer;
import com.upv.pm_2022.iti_27849_u1_equipo_08.DbHandler;
import com.upv.pm_2022.iti_27849_u1_equipo_08.Inventory;
import com.upv.pm_2022.iti_27849_u1_equipo_08.Loan;
import com.upv.pm_2022.iti_27849_u1_equipo_08.R;
import com.upv.pm_2022.iti_27849_u1_equipo_08.adapters.CustomersAdapter;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.CustomerController;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.InventoryController;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.LoanController;
import com.upv.pm_2022.iti_27849_u1_equipo_08.databinding.AddLoanBinding;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddLoanFragment extends Fragment {

    private AddLoanBinding binding;

    private ImageButton btnBack;
    private Button btnSave;

    private EditText txtStatus, txtDate;
    private ListView lvInventory, lvCustomers;
    private DbHandler dbHandler;
    private ArrayList<Customer> customers;
    private ArrayList<Inventory> inventories;
    private SQLiteDatabase write, read;


    public AddLoanFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // DIFERENTE FORMA PARA SACAR LOS DATOS DE LA INTERFAZ
        binding = AddLoanBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        dbHandler = new DbHandler(view.getContext());

        write = dbHandler.getWritableDatabase();
        read = dbHandler.getReadableDatabase();

        btnBack = view.findViewById(R.id.btn_back);
        txtDate = view.findViewById(R.id.txtDate);
        txtStatus = view.findViewById(R.id.txtStatus);
        btnSave = view.findViewById(R.id.btnSave);

        lvCustomers = view.findViewById(R.id.lvCustomers);
        lvInventory = view.findViewById(R.id.lvInventory);

        CustomerController customerController = new CustomerController(read);
        customers = (ArrayList<Customer>) customerController.getAll();
        CustomersAdapter customersAdapter = new CustomersAdapter(
                                                                (Activity) view.getContext(),
                                                                customers,
                                                                dbHandler);
        //lvCustomers.setAdapter(customersAdapter);



        InventoryController inventoryController = new InventoryController(read);
        inventories = (ArrayList<Inventory>) inventoryController.getAllItems();



        txtDate.setText(LocalDate.now().toString());
        // Botón para regresar al fragent loans
        btnBack.setOnClickListener(view1 -> NavHostFragment.findNavController(AddLoanFragment.this).navigate(R.id.action_add_loan_to_home));
        btnSave.setOnClickListener( v -> {
            String status = txtStatus.getText().toString();
            status = String.valueOf(1);
            String date_loan = txtDate.getText().toString();
            if (status.isEmpty() || date_loan.isEmpty())
                Toast.makeText(getContext(), "Please enter valid data", Toast.LENGTH_SHORT).show();
            else  {
                LoanController controller = new LoanController(write);
                date_loan = LocalDateTime.now().toString();
                Loan loan = new Loan();
                loan.setStatus(Integer.parseInt(status));
                loan.setCustomer_id(1);
                loan.setInventory_id(1);
                loan.setLoan_datetime(LocalDateTime.now());
                controller.addLoan(loan);
                Toast.makeText(getContext(), "Item Added successfully!", Toast.LENGTH_SHORT).show();
                txtStatus.setText("");
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
