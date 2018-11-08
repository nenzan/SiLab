package id.compunerd.silab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import id.compunerd.silab.adapter.ItemAdapter;

import static id.compunerd.silab.fragment.OrderFragment.KODE_BARANG;
import static id.compunerd.silab.fragment.OrderFragment.NAMA_BARANG;
import static id.compunerd.silab.fragment.OrderFragment.ORDER_PREFERENCES;

public class ListItemActivity extends AppCompatActivity {

    ListView lvRes;
    public final static String[][] dataMineral = new String[][]{
            {"KTK", "Mineral", "grid_mineral","BG00000001"},
            {"Nikel", "Mineral", "grid_mineral","BG00000002"},
            {"Besi", "Mineral", "grid_mineral","BG00000003"},
            {"Bauksit", "Mineral", "grid_mineral","BG00000004"},
            {"Kaolin", "Mineral", "grid_mineral","BG00000005"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        String strExtra;
        lvRes = (ListView) findViewById(R.id.lvResult);


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
                SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences(ORDER_PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(NAMA_BARANG, dataMineral[position][0]);
                editor.putString(KODE_BARANG, dataMineral[position][3]);
                editor.apply();
                startActivity(new Intent(getBaseContext(), StepOrderActivity.class));
            }
        });

    }
}
