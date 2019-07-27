package com.example.pesanbaju.Api;

import com.google.gson.annotations.SerializedName;

public class ModelMenu {

    @SerializedName("menu_id") //ini yg ada di json
    private String menu_id;

    @SerializedName("nama_menu") //ini yg ada di json
    private String nama_menu;

    @SerializedName("harga") //ini yg ada di json
    private String harga;

    @SerializedName("stok") //ini yg ada di json
    private String stok;

    @SerializedName("kategori") //ini yg ada di json
    private String kategori;

    @SerializedName("gambar_menu") //ini yg ada di json
    private String gambar_menu;

    private String id_transaksi;
    private String jumlah;
    private Integer total;

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getNama_menu() {
        return nama_menu;
    }

    public void setNama_menu(String nama_menu) {
        this.nama_menu = nama_menu;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getGambar_menu() {
        return gambar_menu;
    }

    public void setGambar_menu(String gambar_menu) {
        this.gambar_menu = gambar_menu;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
