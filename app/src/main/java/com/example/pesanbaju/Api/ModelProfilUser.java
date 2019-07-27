package com.example.pesanbaju.Api;

import com.google.gson.annotations.SerializedName;

public class ModelProfilUser {
    @SerializedName("iduser") //ini yg ada di json
    private String iduser;

    @SerializedName("nama_lengkap") //ini yg ada di json
    private String nama_lengkap;

    @SerializedName("email") //ini yg ada di json
    private String email;

    @SerializedName("password") //ini yg ada di json
    private String password;




    public String getId_user() { return iduser; }

    public void setId_user(String iduser) { this.iduser = iduser; }

    public String getNama_user() {
        return nama_lengkap;
    }

    public void setNama_user(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getEmail_user() {
        return email;
    }

    public void setEmail_user(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
