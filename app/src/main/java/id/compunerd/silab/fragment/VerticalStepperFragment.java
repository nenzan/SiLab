package id.compunerd.silab.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import id.compunerd.silab.MainActivity;
import id.compunerd.silab.R;
import id.compunerd.silab.rest.ApiInterface;
import id.compunerd.silab.rest.UtilsApi;
import id.compunerd.silab.utils.SharedPrefManager;
import moe.feng.common.stepperview.VerticalStepperItemView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class VerticalStepperFragment extends Fragment {

    private VerticalStepperItemView mSteppers[] = new VerticalStepperItemView[5];
    Button btnUploadPaymentProof, btnCancelPayment, btnDownload, btnGallery;
    ImageView imageHolder;
    TextView tvTotal;
    String idPengujian, tglBayar, tglOrder, tglVerifikasi, tglBarangDiterima, tglBarangSelesai, totalHarga;
    ApiInterface mApiService;
    String mediaPath;
    final int REQUEST_GALLERY = 9544;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vertical_stepper, parent, false);

        idPengujian = getArguments().getString("idPengujian");
        tglBayar = getArguments().getString("tglBayar");
        tglOrder = getArguments().getString("tglOrder");
        tglVerifikasi = getArguments().getString("tglVerifikasi");
        tglBarangDiterima = getArguments().getString("tglBarangDiterima");
        tglBarangSelesai = getArguments().getString("tglBarangSelesai");
        totalHarga = getArguments().getString("totalHarga");
        mApiService = UtilsApi.getAPIService();
        return v;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mSteppers[0] = view.findViewById(R.id.stepper_0);
        mSteppers[1] = view.findViewById(R.id.stepper_1);
        mSteppers[2] = view.findViewById(R.id.stepper_2);
        mSteppers[3] = view.findViewById(R.id.stepper_3);
        mSteppers[4] = view.findViewById(R.id.stepper_4);

        VerticalStepperItemView.bindSteppers(mSteppers);
        imageHolder = view.findViewById(R.id.imageHolder);
        btnDownload = view.findViewById(R.id.btnDownload);
        btnGallery = view.findViewById(R.id.btnGallery);
        tvTotal = view.findViewById(R.id.tvTotal);
        btnUploadPaymentProof = view.findViewById(R.id.btnUploadPaymentProof);

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "open gallery"), REQUEST_GALLERY);
            }
        });


        btnUploadPaymentProof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), idPengujian, Toast.LENGTH_SHORT).show();
                uploadFile(idPengujian);
            }
        });

        btnCancelPayment = view.findViewById(R.id.btnCancelPayment);
        btnCancelPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Cancel Order")
                        .setMessage("Are you sure to cancel this order ?")
                        .setPositiveButton(R.string.ya, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                                startActivity(new Intent(getActivity(), MainActivity.class));
                            }
                        })
                        .setNegativeButton(R.string.tidak, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });

//        changeStepper0("tanggal ordernyah", true);
//        changeStepper1("tanggal pembayaran  telah di vefipikasi ");
//        changeStepper2("tanggal barang sample diterima");
//        changeStepper3("tanggal barang sedang diproses");

        stepperCondition(idPengujian, tglOrder, tglBayar, tglVerifikasi, tglBarangDiterima, tglBarangSelesai, totalHarga);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_GALLERY) {
                Uri dataimage = data.getData();
                String[] imageprojection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().getContentResolver().query(dataimage, imageprojection, null, null, null);

                if (cursor != null) {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
                    mediaPath = cursor.getString(indexImage);

                    if (mediaPath != null) {
                        File image = new File(mediaPath);
                        imageHolder.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));
                    }
                }
            }
        }
    }

    private void uploadFile(String idPengujian) {

        // Map is used to multipart the file using okhttp3.RequestBody
        File file = new File(mediaPath);

        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        mApiService.uploadFile(fileToUpload, idPengujian).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonRESULT = new JSONObject(response.body().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
            }
        });
    }

    private void stepperCondition(String idPengujian, String tglOrder, String tglBayar, String tglVerifikasi, String tglBarangDiterima, String tglBarangSelesai, String totalHarga) {

        if ((idPengujian != null) && (tglOrder != null) && (tglBayar == null) && (tglVerifikasi == null) && (tglBarangDiterima == null) && (tglBarangSelesai == null)) {
            changeStepper0(tglOrder, false);
            tvTotal.setText(totalHarga);
        } else if ((idPengujian != null) && (tglOrder != null) && (tglBayar != null) && (tglVerifikasi == null) && (tglBarangDiterima == null) && (tglBarangSelesai == null)) {
            changeStepper0(tglOrder, true);
            changeStepper1(tglBayar);
        } else if ((idPengujian != null) && (tglOrder != null) && (tglBayar != null) && (tglVerifikasi != null) && (tglBarangDiterima == null) && (tglBarangSelesai == null)) {
            changeStepper0(tglOrder, true);
            changeStepper1(tglBayar);
            changeStepper2(tglVerifikasi);
        } else if ((idPengujian != null) && (tglOrder != null) && (tglBayar != null) && (tglVerifikasi != null) && (tglBarangDiterima != null) && (tglBarangSelesai == null)) {
            changeStepper0(tglOrder, true);
            changeStepper1(tglBayar);
            changeStepper2(tglVerifikasi);
            changeStepper3(tglBarangDiterima);
        } else if ((idPengujian != null) && (tglOrder != null) && (tglBayar != null) && (tglVerifikasi != null) && (tglBarangDiterima != null) && (tglBarangSelesai != null)) {
            changeStepper0(tglOrder, true);
            changeStepper1(tglBayar);
            changeStepper2(tglVerifikasi);
            changeStepper3(tglBarangDiterima);
            changeStepper4(tglBarangSelesai);
        } else {
            Toast.makeText(getActivity(), "Terjadi Kesalahan pada Database", Toast.LENGTH_SHORT).show();
        }


    }


    private void changeStepper0(String tgl, boolean isDone) {
        //tittle diisi dengan tgl
        mSteppers[0].setTitle(tgl);
        if (!isDone) {
            mSteppers[0].setState(VerticalStepperItemView.STATE_SELECTED);
            mSteppers[0].setIsLastStep(true);
        } else {
            mSteppers[0].setState(VerticalStepperItemView.STATE_DONE);
            mSteppers[0].setIsLastStep(false);
            mSteppers[1].setVisibility(View.VISIBLE);
        }
    }

    private void changeStepper1(String tglBeres) {
        mSteppers[1].setTitle(tglBeres);
        mSteppers[1].setState(VerticalStepperItemView.STATE_DONE);
        mSteppers[1].setIsLastStep(false);
        mSteppers[2].setVisibility(View.VISIBLE);
    }

    private void changeStepper2(String tglBeres) {
        mSteppers[2].setTitle(tglBeres);
        mSteppers[2].setState(VerticalStepperItemView.STATE_DONE);
        mSteppers[2].setIsLastStep(false);
        mSteppers[3].setVisibility(View.VISIBLE);
    }

    private void changeStepper3(String tglBeres) {
        mSteppers[3].setTitle(tglBeres);
        mSteppers[3].setState(VerticalStepperItemView.STATE_DONE);
        mSteppers[3].setIsLastStep(false);
        mSteppers[4].setVisibility(View.VISIBLE);
    }

    private void changeStepper4(String tglBeres) {
        mSteppers[4].setTitle(tglBeres);
        mSteppers[4].setIsLastStep(true);
        btnDownload.setVisibility(View.VISIBLE);
    }


}

