package com.upv.pm_2022.iti_27849_u1_equipo_08;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.CustomerController;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.InventoryController;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.LoanController;
import com.upv.pm_2022.iti_27849_u1_equipo_08.controllers.OwnerController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

public class DbHandler extends SQLiteOpenHelper {
    private String PATH = Environment.getExternalStorageDirectory().toString();
    private static final String DATABASE_NAME = "loansData";
    private final String ownerCreate = "CREATE TABLE Owners (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT," +
            "last_name TEXT," +
            "email TEXT" +
            ")";
    private final String inventoryCreate = "CREATE TABLE Inventory ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT NOT NULL," +
            "owner_id INTEGER," +
            "FOREIGN KEY(owner_id) REFERENCES Owner(id)" +
            ")";
    private final String customersCreate = "CREATE TABLE Customers(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT," +
            "last_name TEXT" +
            ")";
    private final String loansCreate = "CREATE TABLE Loans (" +
            "inventory_id INTEGER," +
            "customer_id INTEGER," +
            "status INTEGER default 0," +
            "loan_datetime datetime default current_timestamp," +
            "FOREIGN KEY(inventory_id) REFERENCES Inventory(id)," +
            "FOREIGN KEY(customer_id) REFERENCES Customer(id)" +
            ")";

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use for locating paths to the the database
     * @param name    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public DbHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }
    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use for locating paths to the the database
     */
    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     @Override
     * @param db The database.
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ownerCreate);
        db.execSQL(inventoryCreate);
        db.execSQL(customersCreate);
        db.execSQL(loansCreate);
        db.execSQL("INSERT INTO Owners (name, last_name) values ('Name', 'Last name')");
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Loans");
        db.execSQL("DROP TABLE IF EXISTS Inventory");
        db.execSQL("DROP TABLE IF EXISTS Owners");
        db.execSQL("DROP TABLE IF EXISTS Customers");
        onCreate(db);
    }

    /**
     * Utility function to delete all rows from the tables in the database
     * Using the clause DELETE
     */
    private void cleanTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Loans");
        db.execSQL("DELETE FROM Inventory");
        db.execSQL("DELETE FROM Owners");
        db.execSQL("DELETE FROM Customers");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String exportDatabaseRows(){
        String output = "";
        CustomerController customerController = new CustomerController(this.getReadableDatabase());
        InventoryController inventoryController = new InventoryController(this.getReadableDatabase());
        OwnerController ownerController = new OwnerController(this.getReadableDatabase());
        LoanController loanController = new LoanController(this.getReadableDatabase());


        String query = "INSERT INTO ";
        String values = " VALUES ";
        String queryCustomer = query + "Customers" + values;
        for (Customer customer: customerController.getAll()) {
            queryCustomer += customer.toExport() + ",";
        }
        queryCustomer = queryCustomer.substring(0, queryCustomer.length()-1) + "\n";

        String queryOwner = query + "Owners" + values;
        queryOwner += ownerController.getOwner(1).toExport() + "\n";

        String queryInventory = query + "Inventory" + values;
        for (Inventory inventory: inventoryController.getAllItems()) {
            queryInventory += inventory.toExport() + ",";
        }
        queryInventory = queryInventory.substring(0, queryInventory.length()-1) + "\n";

        String queryLoans = query + "Loans" + values;
        for (Loan loan: loanController.getAll()) {
            queryLoans += loan.toExport() + ",";
        }
        queryLoans = queryLoans.substring(0, queryLoans.length()-1) + "\n";
        return createBackUpFile(queryOwner, queryCustomer, queryInventory, queryLoans);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String createBackUpFile(String queryOwner, String queryCustomer, String queryInventory, String queryLoans) {
        File file = null;
        try {
            file = new File(PATH + "/" + DATABASE_NAME + "_backup_" +
                    LocalTime.now().toString() + ".txt");
            if (!file.exists())
                file.createNewFile();
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(queryOwner);
            bufferedWriter.write(queryCustomer);
            bufferedWriter.write(queryInventory);
            bufferedWriter.write(queryLoans);
            bufferedWriter.close();
        }catch (IOException ioException){
            //TODO: HANDLE
            ioException.printStackTrace();
        }
        return file.getAbsolutePath().toString();
    }

    public void importDb(String content){
        cleanTables();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] contents = content.split("\\r?\\n");
        for (String cont: contents) {
            db.execSQL(cont);
        }
        db.close();

    }
}
