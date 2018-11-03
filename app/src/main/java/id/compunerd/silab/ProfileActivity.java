package id.compunerd.silab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.compunerd.silab.rest.ApiInterface;
import id.compunerd.silab.rest.UtilsApi;
import id.compunerd.silab.utils.SharedPrefManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    TextView token;
    ApiInterface mApiService;
    Button buttonLogout;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
//            token.setText(extra.getString("token"));
            mApiService.detailUser("application/x-www-form-urlencoded",
                    "application/json",
                    "Bearer " + extra.getString("token")).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        try {
                            JSONObject jsonRESULT = new JSONObject(response.body().string());
                            if (jsonRESULT.getString("success").isEmpty()) {
                                Toast.makeText(ProfileActivity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                            } else {
                                String result = jsonRESULT.getJSONObject("success").getString("name");
                                token.setText(result);
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

    private void logoutProcess() {
        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
        startActivity(new Intent(ProfileActivity.this, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }

    private void init() {
        token = (TextView) findViewById(R.id.textToken);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);
    }
}
