package id.compunerd.silab.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import id.compunerd.silab.CustomGridViewActivity;
import id.compunerd.silab.MineralActivity;
import id.compunerd.silab.R;

/**
 * A simple {@link Fragment} subclass.
 */


public class HomeFragment extends Fragment {

    GridView androidGridView;

    String[] gridViewString = {
            "Mineral", "Batubara", "Migas"
    } ;

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
        androidGridView=(GridView)v.findViewById(R.id.gridViewImageText);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                if (i == 0){
                    Toast.makeText(getActivity(), "GridView Item: " + i, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), MineralActivity.class);
                    startActivity(intent);
                }else if (i == 1){
                    Toast.makeText(getActivity(), "Batu bara", Toast.LENGTH_SHORT).show();

                }else if (i == 2){
                    Toast.makeText(getActivity(), "Migas", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return  v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

}
