package com.android.rsbandarlampung.model;

public class rumahsakit {
    String id, nama, alamat, lat, lon, jenis, kelas, tenaga_medis, tenaga_perawat, telp, id_kec;

    public rumahsakit(String id, String nama, String alamat, String lat, String lon, String jenis, String kelas, String tenaga_medis, String tenaga_perawat, String telp, String id_kec) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.lat = lat;
        this.lon = lon;
        this.jenis = jenis;
        this.kelas = kelas;
        this.tenaga_medis = tenaga_medis;
        this.tenaga_perawat = tenaga_perawat;
        this.telp = telp;
        this.id_kec = id_kec;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getTenaga_medis() {
        return tenaga_medis;
    }

    public void setTenaga_medis(String tenaga_medis) {
        this.tenaga_medis = tenaga_medis;
    }

    public String getTenaga_perawat() {
        return tenaga_perawat;
    }

    public void setTenaga_perawat(String tenaga_perawat) {
        this.tenaga_perawat = tenaga_perawat;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getId_kec() {
        return id_kec;
    }

    public void setId_kec(String id_kec) {
        this.id_kec = id_kec;
    }
}
