package id.compunerd.silab;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.compunerd.silab.fragment.VerticalStepperAdapterFragment;
import id.compunerd.silab.fragment.VerticalStepperFragment;

public class StatusActivity extends AppCompatActivity {

    private Fragment mVerticalStepperDemoFragment = new VerticalStepperFragment(),
            mVerticalStepperAdapterDemoFragment = new VerticalStepperAdapterFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        if (savedInstanceState == null) {
            replaceFragment(mVerticalStepperDemoFragment);
        }
    }
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.flStatus, fragment).commit();
    }
}
