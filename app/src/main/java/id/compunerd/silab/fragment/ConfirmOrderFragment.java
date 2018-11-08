package id.compunerd.silab.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.compunerd.silab.MainActivity;
import id.compunerd.silab.R;
import id.compunerd.silab.rest.ApiInterface;
import id.compunerd.silab.rest.UtilsApi;
import id.compunerd.silab.utils.SharedPrefManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static id.compunerd.silab.fragment.OrderFragment.JUMLAH_BARANG;
import static id.compunerd.silab.fragment.OrderFragment.NAMA_BARANG;
import static id.compunerd.silab.fragment.OrderFragment.ORDER_PREFERENCES;
import static id.compunerd.silab.fragment.OrderFragment.TOTAL_HARGA;
import static id.compunerd.silab.fragment.OrderFragment.formatRupiah;


public class ConfirmOrderFragment extends Fragment implements BlockingStep {


    private TextView tvNama, tvJumlah, tvTotalHarga, tvTotalHargaBank;
    ApiInterface mApiService;
    String idPerusahaan = "PR00000001";
    String idBarang = "BG00000002";
    int tot;

    public ConfirmOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_confirm_order, container, false);
        tvNama = (TextView) rootView.findViewById(R.id.tvNama);
        tvJumlah = (TextView) rootView.findViewById(R.id.tvJumlah);
        tvTotalHarga = (TextView) rootView.findViewById(R.id.tvTotalHarga);
        tvTotalHargaBank = (TextView) rootView.findViewById(R.id.tvTotalHargaBank);
        mApiService = UtilsApi.getAPIService();

        return rootView;
    }


    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {
        SharedPreferences prefs = getActivity().getSharedPreferences(ORDER_PREFERENCES, MODE_PRIVATE);
        if (prefs != null) {
            tot = Integer.parseInt(prefs.getString(TOTAL_HARGA, null));
            tvNama.setText(prefs.getString(NAMA_BARANG, null));
            tvJumlah.setText(prefs.getString(JUMLAH_BARANG, null));
            tvTotalHarga.setText(formatRupiah.format((double)tot));
            tvTotalHargaBank.setText(formatRupiah.format((double)tot));
        }

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }


    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

        if (tvNama.getText().equals("KTK")){
            idBarang = "BG00000001";
        }else if (tvNama.getText().equals("Nike")){
            idBarang = "BG00000002";
        }else if (tvNama.getText().equals("Besi")){
            idBarang = "BG00000003";
        }

        // TODO: 06/11/2018 POST Order Barang
        orderBarang(idPerusahaan, idBarang, tvJumlah.getText().toString(), Integer.toString(tot));

        //TODO POST-Order

//        getActivity().finish();
//        startActivity(new Intent(getActivity(),MainActivity.class));
        //TODO untuk menghapus pref ORDER_PREFERENCES
        SharedPreferences prefs = getActivity().getSharedPreferences(ORDER_PREFERENCES, Context.MODE_PRIVATE);
        prefs.edit().remove("NAMA_BARANG").commit();
        prefs.edit().remove("JUMLAH_BARANG").commit();
        prefs.edit().remove("TOTAL_HARGA").commit();
    }

    private void orderBarang(String idPerusahaan, String idBarang, String jumlahBarang, String totalHarga) {
        mApiService.orderPengujian(idPerusahaan, idBarang, jumlahBarang, totalHarga).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULT = new JSONObject(response.body().string());
                        if (jsonRESULT.getString("success").isEmpty()){
                            Toast.makeText(getActivity(), "Gagal Order Pengujian", Toast.LENGTH_SHORT).show();
                        }else {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(getActivity(), "Berhasil Order Pengujian", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getActivity(), "Silahkan cek notifikasi untuk informasi", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity(), "Koneksi Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}
