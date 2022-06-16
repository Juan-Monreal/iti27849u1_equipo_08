package com.upv.pm_2022.iti_27849_u1_equipo_08.adapters;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.upv.pm_2022.iti_27849_u1_equipo_08.Customer;
import com.upv.pm_2022.iti_27849_u1_equipo_08.DbHandler;
import com.upv.pm_2022.iti_27849_u1_equipo_08.Inventory;
import com.upv.pm_2022.iti_27849_u1_equipo_08.Loan;
import com.upv.pm_2022.iti_27849_u1_equipo_08.R;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.LoanController;

import java.util.ArrayList;

public class LoansListAdapter extends BaseAdapter {

    private Activity context;
    private ArrayList<Loan> loans;
    private PopupWindow popupWindow;
    private DbHandler dbHandler;
    private BaseAdapter baseAdapter;
    private ArrayList<Customer> customers;
    private ArrayList<Inventory> inventories;

    public LoansListAdapter(Activity context, ArrayList<Loan> loans, DbHandler dbHandler, ArrayList<Customer> customers, ArrayList<Inventory> inventories) {
        this.context = context;
        this.loans = loans;
        this.dbHandler = dbHandler;
        this.customers = customers;
        this.inventories = inventories;
    }

    public static class LoansListContainer {
        TextView textViewId;
        TextView textViewCustomer;
        TextView textViewDate;
        TextView textViewItem;
        Switch swStatus;
        ImageButton btnDelete;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return loans.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return position;
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        LoansListContainer container;
        if (convertView == null){
            container = new LoansListContainer();
            row = inflater.inflate(R.layout.loans_list_item, null, true);
            container.textViewId = row.findViewById(R.id.textViewId);
            container.textViewCustomer = row.findViewById(R.id.textViewCustomer);
            container.textViewDate = row.findViewById(R.id.textViewDate);
            container.textViewItem = row.findViewById(R.id.textViewItem);
            container.swStatus = row.findViewById(R.id.swStatus);
            container.btnDelete = row.findViewById(R.id.btnDelete);
            row.setTag(container);
        } else {
            container = (LoansListContainer) convertView.getTag();
        }

        container.textViewId.setText(String.valueOf(loans.get(position).getId()));
        container.textViewCustomer.setText(getCustomerName(loans.get(position).getCustomer_id()));
        container.textViewDate.setText(String.valueOf(loans.get(position).getLoan_datetimeAsString()));
        container.textViewItem.setText(getItemName(loans.get(position).getInventory_id()));
        container.swStatus.setChecked(loans.get(position).isStatus());



        final int positionPopup = position;
        container.btnDelete.setOnClickListener( v -> {
            SQLiteDatabase database = dbHandler.getWritableDatabase();
            LoanController controller = new LoanController(database);
            controller.deleteLoan(loans.get(positionPopup));
            Toast.makeText(context, "Loan deleted!", Toast.LENGTH_SHORT).show();
            controller.setDb(dbHandler.getReadableDatabase());
            loans = (ArrayList<Loan>) controller.getAll();
            notifyDataSetChanged();
        });

        container.swStatus.setOnClickListener( v -> {
            SQLiteDatabase database = dbHandler.getWritableDatabase();
            LoanController controller = new LoanController(database);
            loans.get(position).setStatus( container.swStatus.isChecked());
            controller.updateLoan( loans.get(positionPopup));
            Toast.makeText(context, "Loan Updated!", Toast.LENGTH_SHORT).show();
            controller.setDb(dbHandler.getReadableDatabase());
            loans = (ArrayList<Loan>) controller.getAll();
            notifyDataSetChanged();
        });

        return row;
    }

    private String getCustomerName(int customer_id){
        for (Customer customer: customers) {
            if (customer.getId() == customer_id)
                return customer.getName();
        }
        return "";
    }
    private String getItemName(int inventory_id){
        for (Inventory inventory: inventories) {
            if (inventory.getId() == inventory_id)
                return inventory.getName();
        }
        return "";
    }
}
