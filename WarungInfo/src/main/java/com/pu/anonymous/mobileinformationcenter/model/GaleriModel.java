package com.pu.anonymous.mobileinformationcenter.model;

/**
 * Created by Firdaus on 9/22/2014.
 */
public class GaleriModel {
    int id;
    String judul, deskripsi, gambar, tanggal;

    public GaleriModel(int id, String judul, String deskripsi, String gambar, String tanggal) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
        this.tanggal = tanggal;
    }


}
