package com.example.pesanbaju.Api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestInterface {

   @GET("mykopi/user/list_menu.php")
    Call<JSONResponse> getMenu(@Query("kategori") String kategori);

    //insert data transaksi
    @FormUrlEncoded
    @POST("mykopi/user/insert_transaksi.php")
    Call<ValueMessage>insertTransaksi(@Field("id_transaksi") String id_transaksi,
                                      @Field("id_user") String id_user,
                                      @Field("nama_user") String nama_user,
                                      @Field("telepon_user") String telepon_user,
                                      @Field("tgl_pesanan") String tgl_pesanan,
                                      @Field("jam_pesanan") String jam_pesanan,
                                      @Field("catatan") String catatan,
                                      @Field("grand_total") String grand_total);

    //insert data transaksi
    @FormUrlEncoded
    @POST("mykopi/user/insert_menu_transaksi.php")
    Call<ValueMessage>insertMenuTransaksi(@Field("id_transaksi") String id_transaksi,
                                          @Field("menu_id") String menu_id,
                                          @Field("nama_menu") String nama_menu,
                                          @Field("gambar_menu") String gambar_menu,
                                          @Field("harga") String harga,
                                          @Field("jumlah") String jumlah);

 //detail profil
 @GET("pesanbaju/detailuser.php")
 Call<JSONResponse> getProfilUser(@Query("iduser") String id);

 //detail transaksi
 @GET("pesanbaju/detailtransaksi.php")
 Call<JSONResponse> getTransaksi(@Query("iduser") String iduser);

 //detail tanggalbooking
 @GET("fieldtripkandri/User/get_tanggal_booking.php")
 Call<JSONResponse> getData_tanggal(@Query("date") String date);

}
