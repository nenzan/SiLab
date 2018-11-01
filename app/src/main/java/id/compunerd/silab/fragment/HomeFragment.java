package id.compunerd.silab.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import id.compunerd.silab.CustomGridViewActivity;
import id.compunerd.silab.ListItemActivity;
import id.compunerd.silab.R;

/**
 * A simple {@link Fragment} subclass.
 */


public class HomeFragment extends Fragment {

    GridView androidGridView;

    String[] gridViewString = {
            "Mineral", "Batubara", "Migas"
    };

    int[] gridViewImageId = {R.drawable.grid_mineral, R.drawable.grid_batubara, R.drawable.grid_migas};

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(getActivity(), gridViewString, gridViewImageId);
        androidGridView = (GridView) v.findViewById(R.id.gridViewImageText);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                if (i == 0) {
                    Toast.makeText(getActivity(), "Mineral", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), ListItemActivity.class);
                    intent.putExtra("jenis", "mineral");
                    startActivity(intent);
                } else if (i == 1) {
                    Toast.makeText(getActivity(), R.string.menu_belum_tersedia, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getActivity(), "Batu bara", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getActivity(), ListItemActivity.class);
//                    intent.putExtra("jenis", "batu bara");
//                    startActivity(intent);
                } else if (i == 2) {
                    Toast.makeText(getActivity(), R.string.menu_belum_tersedia, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getActivity(), "Migas", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getActivity(), ListItemActivity.class);
//                    intent.putExtra("jenis", "migas");
//                    startActivity(intent);
                }
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

}
