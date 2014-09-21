package com.pu.anonymous.mobileinformationcenter.model;

/**
 * Created by Anonymous on 15/09/2014.
 */
public class AgendaItem {

    private String judul_agenda;
    private String tanggal_agenda;
    private int image_agenda;

    public AgendaItem(String judul_agenda, String tanggal_agenda, int image_agenda) {
        this.judul_agenda = judul_agenda;
        this.tanggal_agenda = tanggal_agenda;
        this.image_agenda = image_agenda;

    }

    public String getJudulAgenda() {
        return judul_agenda;
    }

    public void setJudulAgenda(String judul_agenda) {
        this.judul_agenda = judul_agenda;
    }


    public String getTanggalAgenda() {
        return tanggal_agenda;
    }

    public void setTanggal_agenda(String tanggal_agenda) {
        this.tanggal_agenda = tanggal_agenda;
    }


    public int getIcon() {
        return image_agenda;
    }

    public void setIcon(int icon) {
        this.image_agenda = icon;
    }


}
