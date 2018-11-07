package id.compunerd.silab.fragment;


import android.content.Intent;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileLoginFragment extends Fragment {

    TextView profileName, profileEmail, profileAddress;
    ApiInterface mApiService;
    Button buttonLogout;
    SharedPrefManager sharedPrefManager;

    public ProfileLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile_login, container, false);

        profileEmail = (TextView) v.findViewById(R.id.txtProfileEmail);
        profileName = (TextView) v.findViewById(R.id.txtProfileName);
        profileAddress = (TextView) v.findViewById(R.id.txtProfileAddress);
        buttonLogout = (Button) v.findViewById(R.id.buttonLogout);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(getActivity());

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle extra = getActivity().getIntent().getExtras();
        if (extra != null) {
//            token.setText(extra.getString("token"));
            String token = extra.getString("token");
            sharedPrefManager.saveSPString(SharedPrefManager.SP_TOKEN,token);

            String getToken = sharedPrefManager.getSPToken();
            mApiService.detailUser("application/x-www-form-urlencoded",
                    "application/json",
                    "Bearer " + getToken).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        try {
                            JSONObject jsonRESULT = new JSONObject(response.body().string());
                            if (jsonRESULT.getString("success").isEmpty()) {
                                Toast.makeText(getActivity(), "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                            } else {
                                String email = jsonRESULT.getJSONObject("success").getString("email");
                                String name = jsonRESULT.getJSONObject("success").getString("name");

                               // Save Data Nama dan Email User Login di SharedPreference
                                saveDataSP(name, email);


//                                Toast.makeText(ProfileActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
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

                }
            });
        }

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutProcess();
            }
        });
    }

    private void saveDataSP(String nama, String email) {
        sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA,nama);
        sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL,email);

        profileName.setText(sharedPrefManager.getSPNama());
        profileEmail.setText(sharedPrefManager.getSPEmail());
    }

    private void logoutProcess() {
        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
        startActivity(new Intent(getActivity(), MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        getActivity().finish();
    }
}
