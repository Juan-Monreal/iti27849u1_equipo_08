package com.upv.pm_2022.iti_27849_u1_equipo_08.fragments;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
import com.upv.pm_2022.iti_27849_u1_equipo_08.databinding.LoansBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoansFragment} factory method to
 * create an instance of this fragment.
 *
 */
public class LoansFragment extends Fragment {

    private LoansBinding binding;

    private ListView loansList;
    private DbHandler dbHandler;
    private ArrayList<Loan> loans;
    private ArrayList<Customer> customers;
    private ArrayList<Inventory> inventories;
    private SQLiteDatabase write,read;

    public LoansFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.loans, container, false);

        binding = LoansBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
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

        loansList.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    
}