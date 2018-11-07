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

    private VerticalStepperItemView mSteppers[] = new VerticalStepperItemView[5];
    private Button btnUploadPaymentProof, btnCancelPayment, btnDownload;


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

        changeStepper0("tanggal ordernyah",true);
        changeStepper1("tanggal pembayaran  telah di vefipikasi ");
//        changeStepper2("tanggal barang sample diterima");
//        changeStepper3("tanggal barang sedang diproses");
    }


    private void changeStepper0(String tgl,boolean isDone) {
        //tittle diisi dengan tgl
        mSteppers[0].setTitle(tgl);
        if(!isDone){
            mSteppers[0].setState(VerticalStepperItemView.STATE_SELECTED);
            mSteppers[0].setIsLastStep(true);
        }else{
            mSteppers[0].setState(VerticalStepperItemView.STATE_DONE);
            mSteppers[0].setIsLastStep(false);
            mSteppers[1].setVisibility(View.VISIBLE);
        }
    }

    private void changeStepper1(String tglBeres) {
        mSteppers[1].setTitle(tglBeres);
        mSteppers[1].setState(VerticalStepperItemView.STATE_DONE);
        mSteppers[1].setIsLastStep(false);
        mSteppers[2].setVisibility(View.VISIBLE);
    }

    private void changeStepper2(String tglBeres) {
        mSteppers[2].setTitle(tglBeres);
        mSteppers[2].setState(VerticalStepperItemView.STATE_DONE);
        mSteppers[2].setIsLastStep(false);
        mSteppers[3].setVisibility(View.VISIBLE);
    }

    private void changeStepper3(String tglBeres) {
        mSteppers[3].setTitle(tglBeres);
        mSteppers[3].setState(VerticalStepperItemView.STATE_DONE);
        mSteppers[3].setIsLastStep(false);
        mSteppers[4].setVisibility(View.VISIBLE);
    }

    private void changeStepper4(String tglBeres) {
        mSteppers[4].setTitle(tglBeres);
        mSteppers[4].setIsLastStep(true);
        btnDownload.setVisibility(View.VISIBLE);
    }



}

