package com.pu.anonymous.mobileinformationcenter.model;

/**
 * Created by Firdaus on 9/22/2014.
 */
public class KomentarModel {
    int id;
    String pid,uid, message, tanggal;

    public KomentarModel(int id, String pid, String uid, String message, String tanggal) {
        this.id = id;
        this.pid = pid;
        this.uid = uid;
        this.message = message;
        this.tanggal = tanggal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
