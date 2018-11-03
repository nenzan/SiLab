package id.compunerd.silab.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import id.compunerd.silab.R;

import static android.content.Context.MODE_PRIVATE;


public class ConfirmOrderFragment extends Fragment implements Step {

    public TextView tvHel;

    public ConfirmOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_confirm_order, container, false);
        tvHel = (TextView) rootView.findViewById(R.id.tvHel);
        return rootView;
    }



    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {
        SharedPreferences prefs = getActivity().getSharedPreferences("APPS", MODE_PRIVATE);
        String restoredText = prefs.getString("NAMABARANG", null);
        if (restoredText != null) {
            String name = prefs.getString("NAMABARANG", "No name defined");
            tvHel.setText(name);
        }

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }


}
