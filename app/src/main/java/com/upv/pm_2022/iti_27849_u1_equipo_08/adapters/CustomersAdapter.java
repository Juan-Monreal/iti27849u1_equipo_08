package com.upv.pm_2022.iti_27849_u1_equipo_08.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.upv.pm_2022.iti_27849_u1_equipo_08.Customer;
import com.upv.pm_2022.iti_27849_u1_equipo_08.DbHandler;
import com.upv.pm_2022.iti_27849_u1_equipo_08.R;

import java.util.ArrayList;

public class CustomersAdapter extends BaseAdapter {

    private Activity context;
    private ArrayList<Customer> customers;
    private PopupWindow popupWindow;
    private DbHandler dbHandler;
    private BaseAdapter baseAdapter;

    public CustomersAdapter(Activity context, ArrayList<Customer> customers, DbHandler dbHandler) {
        this.context = context;
        this.customers = customers;
        this.dbHandler = dbHandler;
    }

    public class CustomerContainer{
        TextView textViewId;
        TextView textViewName;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return customers.size();
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
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        CustomerContainer customerContainer;
        if (convertView == null){
            customerContainer = new CustomerContainer();
            row = inflater.inflate(R.layout.customer_item, null, true);
            customerContainer.textViewId = row.findViewById(R.id.textViewId);
            customerContainer.textViewName = row.findViewById(R.id.textViewName);
            row.setTag(customerContainer);
        } else {
            customerContainer = (CustomerContainer) convertView.getTag();
        }

        customerContainer.textViewId.setText(String.valueOf(customers.get(position).getId()));
        customerContainer.textViewName.setText(String.valueOf(customers.get(position).getName()));
        notifyDataSetChanged();
        return null;
    }
}
