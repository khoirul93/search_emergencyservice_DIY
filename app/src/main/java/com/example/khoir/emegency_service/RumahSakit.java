package com.example.khoir.emegency_service;

/**
 * Created by khoir on 5/13/2016.
 */
public class RumahSakit {
    private String id_rs;
    private String nama_rs;
    private String profil;
    private String latitude;
    private String longitude;
    private String alamat;
    private String galeri;
    private String tlpn;

    public void setRsId (String id_rs)
    {
        this.id_rs = id_rs;
    }

    public String getRsId()
    {
        return id_rs;
    }

    public void setRsName (String nama_rs) {this.nama_rs = nama_rs;}

    public String getRsName() {return nama_rs;}

    public void setRsProf (String profil)
    {
        this.profil = profil;
    }

    public String getRsProf()
    {
        return profil;
    }

    public void setRsLati (String latitude)
    {
        this.latitude = latitude;
    }

    public String getRsLati()
    {
        return latitude;
    }
    public void setRsLongi (String longitude)
    {
        this.longitude = longitude;
    }

    public String getRsLongi()
    {
        return longitude;
    }


    public void setRsGaleri (String galeri)
    {
        this.galeri = galeri;
    }

    public String getRsGaleri()
    {
        return galeri;
    }


    public void setRsAlamat (String alamat)
    {
        this.alamat = alamat;
    }

    public String getRsAlamat()
    {
        return alamat;
    }

    public void setRsTlpn (String tlpn)
    {
        this.tlpn = tlpn;
    }

    public String getRsTlpn()
    {
        return tlpn;
    }
}


