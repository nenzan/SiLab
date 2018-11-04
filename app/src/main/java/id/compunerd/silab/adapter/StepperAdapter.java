package id.compunerd.silab.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import id.compunerd.silab.R;
import id.compunerd.silab.fragment.ConfirmOrderFragment;
import id.compunerd.silab.fragment.OrderFragment;

public class StepperAdapter extends AbstractFragmentStepAdapter {
    public static final String CURRENT_STEP_POSITION_KEY = "STEP";

    public StepperAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }


    @Override
    public Step createStep(int position) {
        switch (position) {
            case 0:
                final OrderFragment step1 = new OrderFragment();
                Bundle b1 = new Bundle();
                b1.putInt(CURRENT_STEP_POSITION_KEY, position);
                step1.setArguments(b1);
                return step1;
            case 1:
                final ConfirmOrderFragment step2 = new ConfirmOrderFragment();
                Bundle b2 = new Bundle();
                b2.putInt(CURRENT_STEP_POSITION_KEY, position);
                step2.setArguments(b2);
                return step2;
        }
        return null;

    }

    @Override
    public int getCount() {
        return 2;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        return new StepViewModel.Builder(context)
                .setTitle(R.string.tab_title)
                .create();
    }
}
