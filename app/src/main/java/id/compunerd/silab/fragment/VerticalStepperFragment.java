package id.compunerd.silab.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.compunerd.silab.MainActivity;
import id.compunerd.silab.R;
import moe.feng.common.stepperview.VerticalStepperItemView;


/**
 * A simple {@link Fragment} subclass.
 */
public class VerticalStepperFragment extends Fragment {

    private VerticalStepperItemView mSteppers[] = new VerticalStepperItemView[7];
    private Button btnUploadPaymentProof, btnCancelPayment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vertical_stepper, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mSteppers[0] = view.findViewById(R.id.stepper_0);
        mSteppers[1] = view.findViewById(R.id.stepper_1);
        mSteppers[2] = view.findViewById(R.id.stepper_2);
        mSteppers[3] = view.findViewById(R.id.stepper_3);
        mSteppers[4] = view.findViewById(R.id.stepper_4);
        mSteppers[5] = view.findViewById(R.id.stepper_5);
        mSteppers[6] = view.findViewById(R.id.stepper_6);

        VerticalStepperItemView.bindSteppers(mSteppers);

        btnUploadPaymentProof = view.findViewById(R.id.btnUploadPaymentProof);
        btnUploadPaymentProof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnCancelPayment = view.findViewById(R.id.btnCancelPayment);
        btnCancelPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Cancel Order")
                        .setMessage("Are you sure to cancel this order ?")
                        .setPositiveButton(R.string.ya, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                                startActivity(new Intent(getActivity(),MainActivity.class));
                            }
                        })
                        .setNegativeButton(R.string.tidak, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });
    }


    private void changeStepper1() {
        mSteppers[0].setSummary("");
    }

    private void changeStepper2() {
        mSteppers[1].setSummary("");
    }

    private void changeStepper3() {
        mSteppers[2].setSummary("");
    }

    private void changeStepper4() {
        mSteppers[3].setSummary("");
    }

    private void changeStepper5() {
        mSteppers[4].setSummary("");
    }

    private void changeStepper6() {
        mSteppers[5].setSummary("");
    }

    private void changeStepper7() {
        mSteppers[6].setSummary("");
    }

}

