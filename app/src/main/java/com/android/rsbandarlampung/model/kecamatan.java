package com.android.rsbandarlampung.model;

public class kecamatan {
    private final String id_kec;
    private final String nama_kec;

    public kecamatan(String id_kec, String nama_kec) {
        this.id_kec = id_kec;
        this.nama_kec = nama_kec;
    }

    public String getId_kec() {
        return id_kec;
    }

    public String getNama_kec() {
        return nama_kec;
    }

}
