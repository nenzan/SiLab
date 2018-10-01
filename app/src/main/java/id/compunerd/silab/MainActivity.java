package id.compunerd.silab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.compunerd.silab.view.KeranjangActivity;
import id.compunerd.silab.view.KontakActivity;
import id.compunerd.silab.view.OrderActivity;
import id.compunerd.silab.view.ProfilActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.cvProfil)
    CardView cvProfil;
    @BindView(R.id.cvOrder)
    CardView cvOrder;
    @BindView(R.id.cvKeranjang)
    CardView cvKeranjang;
    @BindView(R.id.cvKontak)
    CardView cvKontak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.cvProfil, R.id.cvOrder, R.id.cvKeranjang, R.id.cvKontak})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cvProfil:
                Toast.makeText(this, "Profil", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ProfilActivity.class));
                break;
            case R.id.cvOrder:
                Toast.makeText(this, "Order", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, OrderActivity.class));
                break;
            case R.id.cvKeranjang:
                Toast.makeText(this, "Keranjang", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, KeranjangActivity.class));
                break;
            case R.id.cvKontak:
                Toast.makeText(this, "Kontak", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, KontakActivity.class));
                break;
        }
    }
}
