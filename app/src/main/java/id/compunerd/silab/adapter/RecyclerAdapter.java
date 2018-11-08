package id.compunerd.silab.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.compunerd.silab.R;
import id.compunerd.silab.model.ResultItem;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> {

    List<ResultItem> resultItems;

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
        ResultItem resultItem = resultItems.get(i);
        holder.namaPengujian.setText(resultItem.getNamaBarang());
        holder.statusPengujian.setText(resultItem.getStatusPengujian());
        holder.tanggalOrder.setText(resultItem.getCreatedAt());
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
