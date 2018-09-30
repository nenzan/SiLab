package id.compunerd.silab;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import id.compunerd.silab.view.CartFragment;
import id.compunerd.silab.view.HomeFragment;
import id.compunerd.silab.view.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                android.support.v4.app.Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.action_home:
                        Toast.makeText(MainActivity.this, "Ini Home", Toast.LENGTH_SHORT).show();
                        fragment = new HomeFragment();
                        break;
                    case R.id.action_cart:
                        Toast.makeText(MainActivity.this, "Ini Cart", Toast.LENGTH_SHORT).show();
                        fragment = new CartFragment();
                        break;
                    case R.id.action_register:
                        Toast.makeText(MainActivity.this, "Ini Profile", Toast.LENGTH_SHORT).show();
                        fragment = new ProfileFragment();
                        break;
                }

                android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.commit();
                return true;
            }
        });
    }


}
