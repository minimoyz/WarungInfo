package com.pu.anonymous.mobileinformationcenter.model;

/**
 * Created by Anonymous on 15/09/2014.
 */
public class NewsItem {

    private String judul;
    private String tanggal;
    private String isiberita;
    private int image;

    public NewsItem() {

    }

    public NewsItem(String judul, String tanggal, int image) {
        this.judul = judul;
        this.tanggal = tanggal;
        this.isiberita = isiberita;
        this.image = image;
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

    public String getIsiberita() {
        return isiberita;
    }

    public void setIsiberita(String isiberita) {
        this.isiberita = isiberita;
    }

    public int getIcon() {
        return image;
    }

    public void setIcon(int icon) {
        this.image = icon;
    }


}
