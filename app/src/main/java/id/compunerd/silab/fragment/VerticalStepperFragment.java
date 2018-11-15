package id.compunerd.silab.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

import id.compunerd.silab.ItemTrackingActivity;
import id.compunerd.silab.R;
import id.compunerd.silab.rest.ApiInterface;
import id.compunerd.silab.rest.UtilsApi;
import moe.feng.common.stepperview.VerticalStepperItemView;
import pl.aprilapps.easyphotopicker.EasyImage;

import static id.compunerd.silab.fragment.OrderFragment.formatRupiah;


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
    public static final int REQUEST_CODE_CAMERA = 0012;
    public static final int REQUEST_CODE_GALLERY = 0013;

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
//        imageHolder = (ImageView) view.findViewById(R.id.imageHolder);
        btnDownload = view.findViewById(R.id.btnDownload);
//        btnGallery = view.findViewById(R.id.btnGallery);
        tvTotal = view.findViewById(R.id.tvTotal);
        btnUploadPaymentProof = view.findViewById(R.id.btnUploadPaymentProof);

//        btnGallery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent();
////                intent.setType("image/*");
////                intent.setAction(Intent.ACTION_GET_CONTENT);
////                startActivityForResult(Intent.createChooser(intent, "open gallery"), REQUEST_CODE_GALLERY);
////
////                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                startActivityForResult(intent, REQUEST_CAMERA);
//            }
//        });


        btnUploadPaymentProof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mApiService.uploadFileText("image.jpg", idPengujian).enqueue(new Callback() {
//                    @Override
//                    public void onResponse(Call call, Response response) {
//                        if (response.isSuccessful()) {
//                            try {
//                                JSONObject jsonRESULT = new JSONObject(response.body().toString());
//                                Log.d("json", String.valueOf(jsonRESULT));
//                                Toast.makeText(getActivity(), "Berhasil Upload gambar", Toast.LENGTH_SHORT).show();
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }else {
//                            Toast.makeText(getActivity(), "Gagal upload", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call call, Throwable t) {
//                        Log.d("RETRO", "ON FAILURE : " + t.getMessage());
//                    }
//                });
//                uploadFile(idPengujian);
                sendDatatoActivity(idPengujian);
            }
        });

        stepperCondition(idPengujian, tglOrder, tglBayar, tglVerifikasi, tglBarangDiterima, tglBarangSelesai, totalHarga);
    }

    private void uploadFile(String idPengujian) {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "open gallery"), REQUEST_GALLERY);
        EasyImage.openGallery(getActivity(), REQUEST_CODE_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Log.d("onActivityResult", "requestCode = " + requestCode);

        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new EasyImage.Callbacks() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                Toast.makeText(getActivity(), R.string.failed_get_picture, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                switch (type) {
                    case REQUEST_CODE_GALLERY:
                        Glide.with(getActivity())
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageHolder);
                        break;
                }
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                Toast.makeText(getActivity(), R.string.cancel, Toast.LENGTH_SHORT).show();
            }
        });

//        if (resultCode != RESULT_CANCELED) {
//            if (requestCode == REQUEST_GALLERY) {
//                Uri dataimage = data.getData();
//                String[] imageprojection = {MediaStore.Images.Media.DATA};
//                Cursor cursor = getActivity().getContentResolver().query(dataimage, imageprojection, null, null, null);
//
//                if (cursor != null) {
//                    cursor.moveToFirst();
//                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
//                    mediaPath = cursor.getString(indexImage);
//
//                    if (mediaPath != null) {
////                        File image = new File(mediaPath);
//                        Bitmap bitmap = BitmapFactory.decodeFile(mediaPath);
//                        imageHolder.setImageBitmap(bitmap);
//
//                        InputStream imageStream = null;
//                        try {
//                            imageStream = getActivity().getContentResolver().openInputStream(dataimage);
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        }
//                        Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
//                        encodeTobase64(yourSelectedImage);
//
//                        String base64 = encodeTobase64(yourSelectedImage);
//                        mApiService.uploadFile(base64, idPengujian).enqueue(new Callback<ResponseBody>() {
//                            @Override
//                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                Toast.makeText(getActivity(), "Sukses", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                            }
//                        });
//                    }
//                }else {
//                    Toast.makeText(getActivity(), "Gambar Tidak Dipilih", Toast.LENGTH_SHORT).show();
//                }
//
//                int columnIndex = cursor.getColumnIndex(imageprojection[0]);
//                String picturePath = cursor.getString(columnIndex);
//                cursor.close();
//
//                Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
//                imageHolder.setImageBitmap(bitmap);

//            }
//        }else {
//            Toast.makeText(getActivity(), "Gambar Tidak Masuk", Toast.LENGTH_SHORT).show();
//        }
    }

    private void stepperCondition(String idPengujian, String tglOrder, String tglBayar, String tglVerifikasi, String tglBarangDiterima, String tglBarangSelesai, String totalHarga) {

        if ((idPengujian != null) && (tglOrder != null) && (tglBayar == null) && (tglVerifikasi == null) && (tglBarangDiterima == null) && (tglBarangSelesai == null)) {
            changeStepper0(tglOrder, false);
            tvTotal.setText(formatRupiah.format((double) Integer.valueOf(totalHarga)));
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
            Toast.makeText(getActivity(), R.string.something_wrong_wDB, Toast.LENGTH_SHORT).show();
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

    private void sendDatatoActivity(String idPengujian) {
        Intent i = new Intent(getContext(), ItemTrackingActivity.class);
        i.putExtra("idPengujian", idPengujian);
        getContext().startActivity(i);

    }


}

