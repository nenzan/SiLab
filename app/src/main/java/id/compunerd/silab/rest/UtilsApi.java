package id.compunerd.silab.rest;

public class UtilsApi {
    public static final String BASE_URL_API = "https://silab-beta.000webhostapp.com/";

    // Mendeklarasikan Interface BaseApiService
    public static ApiInterface getAPIService(){
        return ApiClient.getClient(BASE_URL_API).create(ApiInterface.class);
    }
}
