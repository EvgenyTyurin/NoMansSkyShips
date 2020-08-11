package evgenyt.nomansskyships.ui;

import android.database.MatrixCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import evgenyt.nomansskyships.R;
import evgenyt.nomansskyships.data.AppData;
import evgenyt.nomansskyships.data.NMSShip;

public class MainActivity extends AppCompatActivity {

    private AppData appData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appData = AppData.getInstance();
        updateParamList();
    }

    /** Show ships parameters */
    private void updateParamList() {
        // Prepare ship parameters matrix
        final String[] matrix = {"_id", "param", "ship1", "ship2"};
        final String[] columns = {"param", "ship1", "ship2"};
        MatrixCursor cursor = new MatrixCursor(matrix);
        int key = 0;
        NMSShip ship1 = appData.getShipByName("Test ship");
        NMSShip ship2 = appData.getShipByName("Test ship");
        cursor.addRow(new Object[] {key++, "- Galaxy:", "", ""});
        cursor.addRow(new Object[] {key++, "", ship1.getGalaxy(), ship2.getGalaxy()});
        // Show parameters in ListView
        final int[] layouts = {R.id.text1, R.id.text2, R.id.text3};
        SimpleCursorAdapter cursorAdapter =
                new SimpleCursorAdapter(this, R.layout.viewlist_two_items,
                        cursor, columns, layouts);
        ListView paramListView = findViewById(R.id.param_list);
        paramListView.setAdapter(cursorAdapter);
    }
}