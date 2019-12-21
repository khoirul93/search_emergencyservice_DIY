package com.example.khoir.emegency_service;

/**
 * Created by khoir on 5/12/2016.
 */
public class pemadam_kebakaran {
    private String id_pemadam;
    private String nama_pemadam;
    private String profil;
    private String latitude;
    private String longitude;
    private String alamat;
    private String galeri;
    private String tlpn;



    public void setPemId (String id_pemadam)
    {
        this.id_pemadam = id_pemadam;
    }//Untuk memanggil data dari database

    public String getPemId()
    {
        return id_pemadam;
    }//memanggil dari dat base

    public void setPemName (String nama_pemadam)
    {
        this.nama_pemadam = nama_pemadam;
    }

    public String getPemName() {return nama_pemadam;}

    public void setPemProf (String profil)
    {
        this.profil = profil;
    }

    public String getPemProf()
    {
        return profil;
    }

    public void setPemLati (String latitude)
    {
        this.latitude = latitude;
    }

    public String getPemLati()
    {
        return latitude;
    }
    public void setPemLongi (String longitude)
    {
        this.longitude = longitude;
    }

    public String getPemLongi()
    {
        return longitude;
    }


    public void setPemGaleri (String galeri)
    {
        this.galeri = galeri;
    }

    public String getPemGaleri()
    {
        return galeri;
    }


    public void setPemAlamat (String alamat)
    {
        this.alamat = alamat;
    }

    public String getPemAlamat()
    {
        return alamat;
    }

    public void setPemTlpn (String tlpn)
    {
        this.tlpn = tlpn;
    }

    public String getPemTlpn()
    {
        return tlpn;
    }
}


