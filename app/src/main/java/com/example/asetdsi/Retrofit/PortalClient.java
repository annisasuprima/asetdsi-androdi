package com.example.asetdsi.Retrofit;

import com.example.asetdsi.model.AuthClass;
import com.example.asetdsi.model.BuktiFotoOngoingResponse;
import com.example.asetdsi.model.BuktiFotoResponse;
import com.example.asetdsi.model.ChangePasswordClass;
import com.example.asetdsi.model.DaftarBarangResponse;
import com.example.asetdsi.model.DetailHistoryPeminjamanResponse;
import com.example.asetdsi.model.DetailHistoryPengusulanMaintenenceResponse;
import com.example.asetdsi.model.DetailHistoryPengusulanResponse;
import com.example.asetdsi.model.DetailOngoingPeminjamanResponse;
import com.example.asetdsi.model.DetailOngoingPengusulanMaintenenceResponse;
import com.example.asetdsi.model.DetailOngoingPengusulanResponse;
import com.example.asetdsi.model.EditProfileClass;
import com.example.asetdsi.model.FormPeminjamanBangunanResponse;
import com.example.asetdsi.model.HistoryPeminjamanResponse;
import com.example.asetdsi.model.HistoryPengusulanResponse;
import com.example.asetdsi.model.HomeBarangResponse;
import com.example.asetdsi.model.ListPeminjamanBangunanResponse;
import com.example.asetdsi.model.ListPeminjamanBarangResponse;
import com.example.asetdsi.model.OngoingPeminjamanResponse;
import com.example.asetdsi.model.OngoingPengusulanResponse;
import com.example.asetdsi.model.PJPengusulanResponse;
import com.example.asetdsi.model.PJResponse;
import com.example.asetdsi.model.PengusulanBarang;
import com.example.asetdsi.model.PengusulanBarangItem;
import com.example.asetdsi.model.PengusulanBarangResponse;
import com.example.asetdsi.model.RegisterClass;
import com.example.asetdsi.model.SettingClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PortalClient {

    String API_BASE_URL = "http://192.168.0.105:8000/api/";

    @FormUrlEncoded
    @POST("auth/login")
    Call<AuthClass> checkLogin(@Field("username") String username,
                               @Field("password") String password,
                                @Field("remember_token") String remember_token);

    @FormUrlEncoded
    @POST("auth/register")
    Call<RegisterClass> registerResponse(
            @Field("nim") String nim,
            @Field("name") String name,
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password
//            @Field("remember_token") String remember_token
    );

    @GET("setting")
    Call<SettingClass> getSetting(@Header("Authorization") String token);

    @FormUrlEncoded
    @PUT("editprofile")
    Call<EditProfileClass> getEditProfile(
            @Header("Authorization") String token,
            @Field("name") String name,
            @Field("nim") String nim,
            @Field("email") String email,
            @Field("username") String username
    );

    @FormUrlEncoded
    @PUT("changepassword")
    Call<ChangePasswordClass> getChangePassword(
            @Header("Authorization") String token,
            @Field("passwordlama") String passwordlama,
            @Field("passwordbaru1") String passwordbaru1,
            @Field("passwordbaru2") String passwordbaru2
    );

    @GET("barang")
    Call<HomeBarangResponse> getHomeBarang(@Header("Authorization") String token);

    @GET("daftarbarang")
    Call<DaftarBarangResponse> getDaftarBarang(@Header("Authorization") String token);


    @GET("pj-peminjaman")
    Call<PJResponse> getPJ(@Header("Authorization") String token);

    @GET("pj-pengusulan")
    Call<PJPengusulanResponse> getPJPengusulan(@Header("Authorization") String token);

    @Headers({"Accept: application/json", "Content-Type: multipart/form-data"})
    @GET("daftarpeminjaman-barang/{id}")
    Call<ListPeminjamanBarangResponse> getPeminjamanBarang (@Header("Authorization") String token, @Path("id") Integer id);

    @GET("daftarpeminjaman-bangunan/{id}")
    Call<ListPeminjamanBangunanResponse> getPeminjamanBangunan (@Header("Authorization") String token, @Path("id") Integer id);


    @GET("history")
    Call<HistoryPeminjamanResponse> getHistoryPeminjaman(@Header("Authorization") String token);

    @GET("ongoing")
    Call<OngoingPeminjamanResponse> getOngoingPeminjaman(@Header("Authorization") String token);

    @GET("historypengusulan")
    Call<HistoryPengusulanResponse> getHistoryPengusulan(@Header("Authorization") String token);

    @GET("ongoingpengusulan")
    Call<OngoingPengusulanResponse> getOngoingPengusulan(@Header("Authorization") String token);

    @GET("showpeminjaman/{id}")
    Call<DetailHistoryPeminjamanResponse> getDetailHistoryPeminjaman (@Header("Authorization") String token, @Path("id") Integer id);

    @GET("showongoingpeminjaman/{id}")
    Call<DetailOngoingPeminjamanResponse> getDetailOngoingPeminjaman (@Header("Authorization") String token, @Path("id") Integer id);

    @GET("showhistorypengusulan/{id}")
    Call<DetailHistoryPengusulanResponse> getDetailHistoryPengusulan (@Header("Authorization") String token, @Path("id") Integer id);

    @GET("showhistorypengusulanmt/{id}")
    Call<DetailHistoryPengusulanMaintenenceResponse> getDetailHistoryPengusulanMaintenence (@Header("Authorization") String token, @Path("id") Integer id);

    @GET("showbukti/{id}")
    Call<BuktiFotoResponse> getBuktiFoto (@Header("Authorization") String token, @Path("id") Integer id);

    @GET("showongoingpengusulan/{id}")
    Call<DetailOngoingPengusulanResponse> getDetailOngoingPengusulan (@Header("Authorization") String token, @Path("id") Integer id);

    @GET("showongoingpengusulanmt/{id}")
    Call<DetailOngoingPengusulanMaintenenceResponse> getDetailOngoingPengusulanMaintenence (@Header("Authorization") String token, @Path("id") Integer id);

    @GET("showbuktiongoing/{id}")
    Call<BuktiFotoOngoingResponse> getBuktiFotoMt (@Header("Authorization") String token, @Path("id") Integer id);


    @Headers({"Accept: application/json"})
    @Multipart
    @POST("storepengusulan/{id}")
    Call<PengusulanBarangResponse> getPengusulanbarang(
            @Header("Authorization") String token,
            @Path("id") Integer id,
            @Query("proposal_description") String proposal_description,
            @Part("data[]") ArrayList<PengusulanBarang> listData
//            @Field("spesification_detail") String spesification_detail,
//            @Field("amount") Integer amount,
//            @Field("unit_price") Integer unit_price,
//            @Field("source_shop") String source_shop
    );



//    @Multipart
//    @POST("storepengusulan/{id}")
//    Call<PengusulanBarangResponse> getPengusulanbarang(
//            @Header("Authorization") String token,
//            @Path("id") Integer id,
//            @Query("proposal_description") String proposal_description,
//            @Query() ArrayList<PengusulanBarangItem> aset
//
//    );


    @FormUrlEncoded
    @POST("storeBangunan/{id}")
    Call<FormPeminjamanBangunanResponse> getFormPeminjamanBangunan(
            @Header("Authorization") String token,
            @Path("id") Integer id,
            @Field("building_id") Integer building_id,
            @Field("building_name") String building_name,
            @Field("photo") String photo,
            @Field("loan_date") String loan_date,
            @Field("loan_time") String loan_time,
            @Field("loan_description") String loan_description
    );




}