package id.compunerd.silab.rest;

import java.util.List;

import id.compunerd.silab.model.Item;
import id.compunerd.silab.model.ResultItem;
import id.compunerd.silab.model.UserResult;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

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

}
