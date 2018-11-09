package id.compunerd.silab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import id.compunerd.silab.model.UserResult;
import id.compunerd.silab.rest.ApiInterface;
import id.compunerd.silab.rest.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button buttonRegister;
    ApiInterface mApiService;
    TextInputEditText RegistName, RegistEmail, RegistPassword, RegistConfirmPassword;
    TextView toLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestRegister();
            }
        });
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
    }

    private void requestRegister() {
        final ProgressDialog dialog = ProgressDialog.show(RegisterActivity.this, "Loading",
                "Loading. Please wait...", true);
        dialog.show();
        mApiService.registerUser(RegistName.getText().toString(),
                RegistEmail.getText().toString(),
                RegistPassword.getText().toString(),
                RegistConfirmPassword.getText().toString()).enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                dialog.dismiss();
                Toast.makeText(RegisterActivity.this, R.string.registration_success, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(RegisterActivity.this, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

        });
    }

    private void init() {
        mApiService = UtilsApi.getAPIService();
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        RegistName = (TextInputEditText) findViewById(R.id.RegistName);
        RegistEmail = (TextInputEditText) findViewById(R.id.RegistEmail);
        RegistPassword = (TextInputEditText) findViewById(R.id.RegistPassword);
        RegistConfirmPassword = (TextInputEditText) findViewById(R.id.RegistConfirmPassword);
        toLogin = (TextView) findViewById(R.id.toLogin);

    }
}
