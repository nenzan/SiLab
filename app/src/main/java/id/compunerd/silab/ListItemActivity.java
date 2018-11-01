package id.compunerd.silab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import id.compunerd.silab.adapter.ItemAdapter;

public class ListItemActivity extends AppCompatActivity {

    ListView lvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        String strExtra;
        lvRes = (ListView) findViewById(R.id.lvResult);

        String[][] dataMineral = new String[][]{{"KTK", "Mineral", "grid_mineral"},
                {"Nikel (Ni)", "Mineral", "grid_mineral"},
                {"Besi (Fe)", "Mineral", "grid_mineral"}};

        String[][] dataBatuBara = new String[][]{{"Batu", "Batu Bara", "grid_batubara"},
                {"Arang", "Batu Bara", "grid_batubara"},
                {"Sulfur", "Batu Bara", "grid_batubara"}};

        String[][] dataMigas = new String[][]{{"Gas Hitam", "Migas", "grid_migas"},
                {"Gas Putih", "Migas", "grid_migas"},
                {"Gas Abu", "Migas", "grid_migas"}};


        Bundle extras = getIntent().getExtras();
        strExtra = extras.getString("jenis");
        if (strExtra.equals("mineral")) {
            ItemAdapter adapter = new ItemAdapter(ListItemActivity.this, dataMineral);
            lvRes.setAdapter(adapter);
        } else if (strExtra.equals("batu bara")) {
            ItemAdapter adapter = new ItemAdapter(ListItemActivity.this, dataBatuBara);
            lvRes.setAdapter(adapter);
        } else if (strExtra.equals("migas")) {
            ItemAdapter adapter = new ItemAdapter(ListItemActivity.this, dataMigas);
            lvRes.setAdapter(adapter);
        }

        lvRes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(ListItemActivity.this, Integer.toString(position), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ListItemActivity.this, StepOrderActivity.class));
            }
        });

    }
}
