package id.compunerd.silab.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
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
import id.compunerd.silab.RegisterActivity;
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
public class ProfileFragment extends Fragment {


    TextView mToRegister;
    Button buttonSignIn;
    TextInputEditText emailInput, passwordInput;
    ApiInterface mApiService;
    SharedPrefManager sharedPrefManager;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        mToRegister = (TextView) v.findViewById(R.id.toRegister);
        buttonSignIn = (Button) v.findViewById(R.id.buttonSignIn);
        emailInput = (TextInputEditText) v.findViewById(R.id.emailInput);
        passwordInput = (TextInputEditText) v.findViewById(R.id.passwordInput);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(getActivity());

//        if (sharedPrefManager.getSPSudahLogin()){
//            startActivity(new Intent(getActivity(), ProfileActivity.class)
//                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//        }

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);

            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSignIn();
            }
        });
    }

    private void requestSignIn() {
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setTitle(getString(R.string.loading));
        pd.setMessage(getString(R.string.please_wait));
        pd.show();
        mApiService.loginUser(emailInput.getText().toString(),
                passwordInput.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonRESULT = new JSONObject(response.body().string());
                        if (jsonRESULT.getString("success").isEmpty()) {
                            pd.dismiss();
                            Toast.makeText(getActivity(), R.string.failed_login, Toast.LENGTH_SHORT).show();
                        } else {
                            pd.dismiss();
                            String token = jsonRESULT.getJSONObject("success").getString("token");
                            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_TOKEN, token);
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.putExtra("token", token);
                            startActivity(intent);
                            Toast.makeText(getActivity(), R.string.success_login, Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        }

                    } catch (JSONException e) {
                        pd.dismiss();
                        e.printStackTrace();
                    } catch (IOException e) {
                        pd.dismiss();
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getActivity(), R.string.failed_login, Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                pd.dismiss();
                t.printStackTrace();
                Toast.makeText(getActivity(), R.string.connection_problem, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
