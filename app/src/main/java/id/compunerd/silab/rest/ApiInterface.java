package id.compunerd.silab.rest;

import java.util.List;
import java.util.Map;

import id.compunerd.silab.model.Item;
import id.compunerd.silab.model.ResultItem;
import id.compunerd.silab.model.UserResult;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("/api/register")
    Call<UserResult> registerUser(@Field("name") String name,
                                  @Field("email") String email,
                                  @Field("password") String password,
                                  @Field("c_password") String c_password);

    @FormUrlEncoded
    @POST("/api/login")
    Call<ResponseBody> loginUser(@Field("email") String email,
                                 @Field("password") String password);

    @POST("/api/details")
    Call<ResponseBody> detailUser(@Header("Content-Type") String content,
                                  @Header("Accept") String acc,
                                  @Header("Authorization") String auth);

    @FormUrlEncoded
    @POST("/api/pengujian/simpan")
    Call<ResponseBody> orderPengujian(@Field("id_perusahaan") String id_perusahaan,
                                      @Field("id_barang") String id_barang,
                                      @Field("jumlah_barang") String jumlah_barang,
                                      @Field("total_harga") String total_harga);


    @GET("/api/pengujian/lihat/PR00000001")
    Call<Item> getDataPengujian();

    @FormUrlEncoded
    @Multipart
    @POST("/api/pengujian/buktibayar/{idPengujian}")
    Call<ResponseBody> uploadFile(@Part MultipartBody.Part file,
                                  @Part("bukti_pembayaran") RequestBody bukti_pembayaran,
                                  @Path("idPengujian") String idPengujian);

    @FormUrlEncoded
    @POST("/api/pengujian/buktibayar/{idPengujian}")
    Call<ResponseBody> uploadFileText(@Field("bukti_pembayaran") String bukti_pembayaran,
                        @Path("id") String idPengujian);
}
