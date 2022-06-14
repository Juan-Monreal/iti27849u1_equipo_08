package fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.upv.pm_2022.iti_27849_u1_equipo_08.DbHandler;
import com.upv.pm_2022.iti_27849_u1_equipo_08.Owner;
import com.upv.pm_2022.iti_27849_u1_equipo_08.R;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.OwnerController;


public class ProfileFragment extends Fragment {

    private EditText inputName, inputLastName, inputEmail;
    private Button btnSave;
    private SQLiteDatabase read, write;
    private OwnerController ownerController;
    private Owner owner;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.profile, container, false);

        inputName = view.findViewById(R.id.firstName);
        inputLastName = view.findViewById(R.id.lastName);
        inputEmail = view.findViewById(R.id.email);
        btnSave = view.findViewById(R.id.saveButton);

        read = new DbHandler(getActivity()).getReadableDatabase();
        write = new DbHandler(getActivity()).getWritableDatabase();

        ownerController = new OwnerController(read);

        owner = ownerController.getOwner(1);

        inputName.setText(owner.getName());
        inputLastName.setText(owner.getLast_name());
        inputEmail.setText(owner.getEmail());

        btnSave.setOnClickListener( v -> {
            String name = inputName.getText().toString();
            String lastName = inputLastName.getText().toString();
            String email = inputEmail.getText().toString();

            if (name.isEmpty() || lastName.isEmpty() || email.isEmpty()){
//                Toast.makeText(getContext(), "Please fill the profile", Toast.LENGTH_SHORT).show();
                name = "";
                lastName = "";
                email = "";
            }
            saveData(name, lastName, email);
        });

        return view;

    }

    /**
     * Saves the Data of the actual Owner
     *
     * @param name taken from the editText
     * @param lastName taken from the editText
     * @param email taken from the editText
     */
    private void saveData(String name, String lastName, String email) {
        owner.setName(name);
        owner.setLast_name(lastName);
        owner.setEmail(email);
        ownerController.setDb(write);
        ownerController.updateOwner(owner);
        Toast.makeText(getContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
    }

}