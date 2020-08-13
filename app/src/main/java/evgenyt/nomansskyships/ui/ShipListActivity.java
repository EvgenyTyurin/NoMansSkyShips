package evgenyt.nomansskyships.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import evgenyt.nomansskyships.R;
import evgenyt.nomansskyships.data.AppData;
import evgenyt.nomansskyships.data.NMSShip;

public class ShipListActivity extends AppCompatActivity {

    public static final String EXTRA_SHIP_SELECTED = "nms_ships.ship_selected";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiplist);
        List<NMSShip> shipList = AppData.getInstance(this).getShipList();
        List<String> shipNameList = new ArrayList<>();
        for (NMSShip ship : shipList) {
            shipNameList.add(ship.getName());
        }
        final ListAdapter arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item,
                shipNameList);
        ListView shipListView = findViewById(R.id.ship_list);
        shipListView.setAdapter(arrayAdapter);
        shipListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                intent.putExtra(EXTRA_SHIP_SELECTED, String.valueOf(arrayAdapter.getItem(position)));
                finish();
            }
        });
    }
}
