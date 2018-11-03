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

import id.compunerd.silab.R;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment implements BlockingStep {

    public TextView tvBarang, tvTotalHarga, tvJumlahBarang;
    private Button btnPlus, btnMin;

    private long hargaSatuan = 450000;

    public OrderFragment() {
        // Required empty public constructor
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
                String currJml = (String) tvJumlahBarang.getText();
                Integer doneJml = Integer.valueOf(currJml) - 1;
                if (doneJml <= 0) {
                    tvJumlahBarang.setText(String.valueOf(doneJml));
                    btnMin.setEnabled(false);
                } else {
                    tvJumlahBarang.setText(String.valueOf(doneJml));
                }
                tvTotalHarga.setText("Rp. " + hargaSatuan * doneJml);
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMin.setEnabled(true);
                String currJml = (String) tvJumlahBarang.getText();
                Integer doneJml = Integer.valueOf(currJml) + 1;
                tvJumlahBarang.setText(String.valueOf(doneJml));
                tvTotalHarga.setText("Rp. " + hargaSatuan * doneJml);
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

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onNextClicked(final StepperLayout.OnNextClickedCallback callback) {
        String namaBarang = "";
        namaBarang= (String) tvBarang.getText();
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("APPS", MODE_PRIVATE).edit();
        editor.putString("NAMABARANG", "Gehu");
        editor.apply();
        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

    }


//    @Override
//    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

//    }
//
//    @Override
//    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
//
//    }
//
//    @Override
//    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
//
//    }
}
