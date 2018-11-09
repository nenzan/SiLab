package id.compunerd.silab.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.compunerd.silab.MainActivity;
import id.compunerd.silab.R;
import id.compunerd.silab.fragment.VerticalStepperFragment;
import id.compunerd.silab.model.ResultItem;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> {

    List<ResultItem> resultItems;
    private Context context;

    public  RecyclerAdapter(List<ResultItem> resultItems){
        this.resultItems = resultItems;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int i) {
        final ResultItem resultItem = resultItems.get(i);
        holder.namaPengujian.setText(resultItem.getNamaBarang());
        holder.statusPengujian.setText(resultItem.getStatusPengujian());
        holder.tanggalOrder.setText(resultItem.getCreatedAt());

        final String idPengujian = resultItem.getIdPengujian();
        final String tglBayar = (String) resultItem.getTglBayar();
        final String tglOrder = resultItem.getCreatedAt();
        final String tglBarangDiterima = (String) resultItem.getTglBarangDiterima();
        final String tglBarangSelesai = (String) resultItem.getTglBarangDiterima();
        final String tglVerifikasi = (String) resultItem.getTglVerifikasi();
        final String totalHarga = resultItem.getTotalHarga();

        holder.namaPengujian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new VerticalStepperFragment();
                Bundle bundle = new Bundle();
                bundle.putString("idPengujian", idPengujian);
                bundle.putString("tglBayar", tglBayar);
                bundle.putString("tglOrder", tglOrder);
                bundle.putString("tglVerifikasi", tglVerifikasi);
                bundle.putString("tglBarangDiterima", tglBarangDiterima);
                bundle.putString("tglBarangSelesai", tglBarangSelesai);
                bundle.putString("totalHarga", totalHarga);
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_right);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, myFragment).addToBackStack(null).commit();


            }
        });
    }


    @Override
    public int getItemCount() {
        return resultItems.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView namaPengujian, statusPengujian, tanggalOrder;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            namaPengujian = (TextView) itemView.findViewById(R.id.txtNamaBarangUji);
            statusPengujian = (TextView) itemView.findViewById(R.id.txtStatusPengujian);
            tanggalOrder = (TextView) itemView.findViewById(R.id.txtTanggalOrder);
        }
    }


}
