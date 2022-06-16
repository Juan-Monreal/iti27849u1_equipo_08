package com.upv.pm_2022.iti_27849_u1_equipo_08.fragments;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.upv.pm_2022.iti_27849_u1_equipo_08.Customer;
import com.upv.pm_2022.iti_27849_u1_equipo_08.DbHandler;
import com.upv.pm_2022.iti_27849_u1_equipo_08.Inventory;
import com.upv.pm_2022.iti_27849_u1_equipo_08.Loan;
import com.upv.pm_2022.iti_27849_u1_equipo_08.R;
import com.upv.pm_2022.iti_27849_u1_equipo_08.adapters.LoansListAdapter;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.CustomerController;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.InventoryController;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.LoanController;
import com.upv.pm_2022.iti_27849_u1_equipo_08.databinding.FragmentHomeBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private Button btnAddLoan;

    private ListView loansList;
    private DbHandler dbHandler;
    private ArrayList<Loan> loans;
    private ArrayList<Customer> customers;
    private ArrayList<Inventory> inventories;
    private SQLiteDatabase write,read;

    public HomeFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // DIFERENTE FORMA PARA SACAR LOS DATOS DE LA INTERFAZ
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        btnAddLoan = view.findViewById(R.id.btnAddLoan);

        btnAddLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_home_to_add_loan);
            }
        });

        dbHandler = new DbHandler(view.getContext());
        loansList = view.findViewById(R.id.loansList);
        read = dbHandler.getReadableDatabase();
        write = dbHandler.getWritableDatabase();
        CustomerController controller = new CustomerController(read);
        customers = (ArrayList<Customer>) controller.getAll();

        InventoryController inventoryController = new InventoryController(read);
        inventories = (ArrayList<Inventory>) inventoryController.getAllItems();

        LoanController loanController = new LoanController(read);
        loans = (ArrayList<Loan>) loanController.getAll();

        LoansListAdapter adapter = new LoansListAdapter(
                (Activity) view.getContext(),
                loans,
                dbHandler,
                customers,
                inventories
        );

        loansList.setAdapter(adapter);;

        return view;

    }
    //TODO: PreLoad Outstanding Loans

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}