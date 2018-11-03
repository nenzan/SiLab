package id.compunerd.silab.rest;

import id.compunerd.silab.model.UserResult;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
}
