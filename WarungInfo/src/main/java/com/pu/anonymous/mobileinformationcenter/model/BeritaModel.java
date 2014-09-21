package com.pu.anonymous.mobileinformationcenter.model;

/**
 * Created by Firdaus on 9/22/2014.
 */
public class BeritaModel {
    int id;
    String judul, tanggal, gambar, like;

    public BeritaModel(int id, String judul, String tanggal, String gambar, String like) {
        this.id = id;
        this.judul = judul;
        this.tanggal = tanggal;
        this.gambar = gambar;
        this.like = like;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}
