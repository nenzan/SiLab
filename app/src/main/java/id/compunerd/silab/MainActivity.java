package id.compunerd.silab;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

import id.compunerd.silab.fragment.ContactUsFragment;
import id.compunerd.silab.fragment.HomeFragment;
import id.compunerd.silab.fragment.NotificationFragment;
import id.compunerd.silab.fragment.ProfileFragment;
import id.compunerd.silab.fragment.ProfileLoginFragment;
import id.compunerd.silab.utils.SharedPrefManager;
import pl.aprilapps.easyphotopicker.EasyImage;

import static id.compunerd.silab.fragment.VerticalStepperFragment.REQUEST_CODE_GALLERY;

public class MainActivity extends AppCompatActivity {

    ImageView imageHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPrefManager sharedPrefManager = new SharedPrefManager(this);
        Fragment fragment = new HomeFragment();
        loadFragment(fragment);
        imageHolder = (ImageView) findViewById(R.id.imageHolder);


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
        builder.setMessage(R.string.msg_exit)
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("onActivityResult", "requestCode = " + requestCode);
        EasyImage.handleActivityResult(requestCode, resultCode, data, MainActivity.this, new EasyImage.Callbacks() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                Toast.makeText(MainActivity.this, "Error Mengambil Gambar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                switch (type) {
                    case REQUEST_CODE_GALLERY:
                        Glide.with(MainActivity.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageHolder);
                        break;
                }
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                Toast.makeText(MainActivity.this, "Tidak Jadi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}