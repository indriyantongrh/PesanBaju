package com.example.pesanbaju.Api;

import com.google.gson.annotations.SerializedName;

public class ModelTanggal {

    @SerializedName("id_transaksi") //ini yg ada di json
    private String id_transaksi;

    @SerializedName("tanggal_booking") //ini yg ada di json
    private String tanggal_booking;

    @SerializedName("nama_sekolah") //ini yg ada di json
    private String nama_sekolah;

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getTanggal_booking() { return tanggal_booking; }

    public void setTanggal_booking(String tanggal_booking) { this.tanggal_booking = tanggal_booking; }

    public String getNama_sekolah() { return nama_sekolah; }

    public void setNama_sekolah(String nama_sekolah) { this.nama_sekolah = nama_sekolah; }

}
