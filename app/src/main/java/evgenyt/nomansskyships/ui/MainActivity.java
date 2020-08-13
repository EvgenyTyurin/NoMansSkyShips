package evgenyt.nomansskyships.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.MatrixCursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import evgenyt.nomansskyships.R;
import evgenyt.nomansskyships.data.AppData;
import evgenyt.nomansskyships.data.NMSShip;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private AppData appData;
    private final int SHIP_NUM = 2;
    private Button[] shipButtons = new Button[SHIP_NUM];
    private ImageView[] shipImages = new ImageView[SHIP_NUM];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appData = AppData.getInstance(this);
        shipButtons[0] = findViewById(R.id.ship1_button);
        shipButtons[1] = findViewById(R.id.ship2_button);
        shipImages[0] = findViewById(R.id.ship1_image);
        shipImages[1] = findViewById(R.id.ship2_image);
        for (int buttonIdx = 0; buttonIdx < SHIP_NUM; buttonIdx++) {
            final int finalButtonIdx = buttonIdx;
            shipButtons[buttonIdx].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shipButtonPressed(finalButtonIdx);
                }
            });
        }
        updateParamList();
    }

    /** User want to select a ship */
    private void shipButtonPressed(int buttonIdx) {
        Intent intent = new Intent(this, ShipListActivity.class);
        startActivityForResult(intent, buttonIdx);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;
        String shipName = data.getStringExtra(ShipListActivity.EXTRA_SHIP_SELECTED);
        shipButtons[requestCode].setText(shipName);
        updateParamList();
    }

    /** Show ships parameters */
    private void updateParamList() {
        NMSShip ship1 = appData.getShipByName(shipButtons[0].getText().toString());
        NMSShip ship2 = appData.getShipByName(shipButtons[1].getText().toString());
        // Load images
        Picasso.get().load("https://gamepedia.cursecdn.com/nomanssky_gamepedia/thumb/1/14/36487677_10156330598231153_4991233876102742016_o.jpg/525px-36487677_10156330598231153_4991233876102742016_o.jpg?version=7ce62bf5e64e44d79d6fd72bb36fcf92").into(shipImages[0]);
        Picasso.get().load(ship2.getImageUrl()).into(shipImages[1]);
        // Prepare ship parameters matrix
        final String[] matrix = {"_id", "param", "ship1", "ship2"};
        final String[] columns = {"param", "ship1", "ship2"};
        MatrixCursor cursor = new MatrixCursor(matrix);
        int key = 0;
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