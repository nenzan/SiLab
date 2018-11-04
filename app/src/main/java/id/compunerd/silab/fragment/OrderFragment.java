package id.compunerd.silab.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.text.NumberFormat;
import java.util.Locale;

import id.compunerd.silab.R;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment implements BlockingStep {

    public final static NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));

    public final static String ORDER_PREFERENCES = "ORDER_PREFERENCES";
    public final static String NAMA_BARANG = "NAMA_BARANG";
    public final static String JUMLAH_BARANG = "JUMLAH_BARANG";
    public final static String TOTAL_HARGA = "TOTAL_HARGA";

    private TextView tvBarang, tvTotalHarga, tvJumlahBarang;
    private Button btnPlus, btnMin;

    private String namaBarang, jumlahBarang, totalHarga;

    private int currJml = 1;
    private int doneJml = 1;
    private long total = 450000;
    private long hargaSatuan = 450000;


    public OrderFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);


        tvBarang = (TextView) rootView.findViewById(R.id.tvBarang);
        tvTotalHarga = (TextView) rootView.findViewById(R.id.tvTotalHarga);
        tvJumlahBarang = (TextView) rootView.findViewById(R.id.tvJumlahBarang);
        btnPlus = (Button) rootView.findViewById(R.id.btnPlus);
        btnMin = (Button) rootView.findViewById(R.id.btnMin);
        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doneJml = currJml - 1;
                currJml = doneJml;
                if (doneJml <= 1) {
                    tvJumlahBarang.setText(String.valueOf(doneJml));
                    btnMin.setEnabled(false);
                } else {
                    tvJumlahBarang.setText(String.valueOf(doneJml));
                }
                total = hargaSatuan * doneJml;
                tvTotalHarga.setText(formatRupiah.format((double) total));
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMin.setEnabled(true);
                doneJml = currJml + 1;
                currJml = doneJml;
                tvJumlahBarang.setText(String.valueOf(doneJml));
                total = hargaSatuan * doneJml;
                tvTotalHarga.setText(formatRupiah.format((double) total));
            }
        });
        return rootView;
    }


    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {
        SharedPreferences prefs = getActivity().getSharedPreferences(ORDER_PREFERENCES, MODE_PRIVATE);
        tvBarang.setText(prefs.getString(NAMA_BARANG, "--"));
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onNextClicked(final StepperLayout.OnNextClickedCallback callback) {
        jumlahBarang = String.valueOf(tvJumlahBarang.getText());
        totalHarga = String.valueOf(total);

        SharedPreferences.Editor editor = getActivity().getSharedPreferences(ORDER_PREFERENCES, MODE_PRIVATE).edit();
        editor.putString(JUMLAH_BARANG, jumlahBarang);
        editor.putString(TOTAL_HARGA, totalHarga);
        editor.apply();
        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}
