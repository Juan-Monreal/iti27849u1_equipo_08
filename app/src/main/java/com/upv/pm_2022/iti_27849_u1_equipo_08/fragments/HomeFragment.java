package com.upv.pm_2022.iti_27849_u1_equipo_08.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.upv.pm_2022.iti_27849_u1_equipo_08.DbHandler;
import com.upv.pm_2022.iti_27849_u1_equipo_08.R;
import com.upv.pm_2022.iti_27849_u1_equipo_08.databinding.FragmentHomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private Button btnAddLoan;
    private SQLiteDatabase read, write;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /* View view = inflater.inflate(R.layout.fragment_home, container, false);

        addLoan = view.findViewById(R.id.addLoan);

        read = new DbHandler(getActivity()).getReadableDatabase();
        write = new DbHandler(getActivity()).getWritableDatabase();

        addLoan.setOnClickListener( v ->{
            Toast.makeText(getContext(), "TODO:ADD LOAN", Toast.LENGTH_SHORT).show();
        });

        return  view; */

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

        read = new DbHandler(getActivity()).getReadableDatabase();
        write = new DbHandler(getActivity()).getWritableDatabase();

        /* btnAddLoan.setOnClickListener( v ->{
            Toast.makeText(getContext(), "TODO:ADD LOAN", Toast.LENGTH_SHORT).show();
        }); */


        return view;

    }
    //TODO: PreLoad Outstanding Loans

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}