package id.compunerd.silab;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import id.compunerd.silab.fragment.ContactUsFragment;
import id.compunerd.silab.fragment.HomeFragment;
import id.compunerd.silab.fragment.NotificationFragment;
import id.compunerd.silab.fragment.ProfileFragment;
import id.compunerd.silab.fragment.ProfileLoginFragment;
import id.compunerd.silab.fragment.VerticalStepperFragment;
import id.compunerd.silab.utils.SharedPrefManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPrefManager sharedPrefManager = new SharedPrefManager(this);
        Fragment fragment = new HomeFragment();
        loadFragment(fragment);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                android.support.v4.app.Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        //Toast.makeText(MainActivity.this, "Ini Home", Toast.LENGTH_SHORT).show();
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.action_notification:
                        //Toast.makeText(MainActivity.this, "Ini Cart", Toast.LENGTH_SHORT).show();
                        fragment = new NotificationFragment();
                        loadFragment(fragment);

//                        fragment = new VerticalStepperFragment();
//                        loadFragment(fragment);
                        return true;
                    case R.id.action_register:
                        //Toast.makeText(MainActivity.this, "Ini Profile", Toast.LENGTH_SHORT).show();
                        if (sharedPrefManager.getSPSudahLogin()) {
                            fragment = new ProfileLoginFragment();
                        } else {
                            fragment = new ProfileFragment();
                        }
                        loadFragment(fragment);
                        return true;
                    case R.id.action_more:
                        //Toast.makeText(MainActivity.this, "Ini Contact", Toast.LENGTH_SHORT).show();
                        fragment = new ContactUsFragment();
                        loadFragment(fragment);
                        return true;
                    default:
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        return true;
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Anda Yakin akan keluar dari aplikasi?")
                .setCancelable(false)
                .setPositiveButton(R.string.ya, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton(R.string.tidak, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_right);
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

}