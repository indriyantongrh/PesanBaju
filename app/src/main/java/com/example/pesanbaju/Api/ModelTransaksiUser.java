package com.example.pesanbaju.Api;

import com.google.gson.annotations.SerializedName;

public class ModelTransaksiUser {

    @SerializedName("id_transaksi") //ini yg ada di json
    private String id_transaksi;

    @SerializedName("iduser") //ini yg ada di json
    private String iduser;

    @SerializedName("nama_lengkap") //ini yg ada di json
    private String nama_lengkap;


    @SerializedName("jumlah_baju") //ini yg ada di json
    private String jumlah_baju;

    @SerializedName("total_bayar") //ini yg ada di json
    private String total_bayar;

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getJumlah_baju() {
        return jumlah_baju;
    }

    public void setJumlah_baju(String jumlah_baju) {
        this.jumlah_baju = jumlah_baju;
    }

    public String getTotal_bayar() {
        return total_bayar;
    }

    public void setTotal_bayar(String total_bayar) {
        this.total_bayar = total_bayar;
    }
}
