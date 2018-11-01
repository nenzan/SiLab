package id.compunerd.silab.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import id.compunerd.silab.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment implements Step {

    private Button btnIncrease, btnDecrease;
    private TextView tvQuantity, tvHargaTotal;

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);

        tvQuantity = (TextView) rootView.findViewById(R.id.tvQuantity);
        tvHargaTotal = (TextView) rootView.findViewById(R.id.tvHargaTotal);
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


}
