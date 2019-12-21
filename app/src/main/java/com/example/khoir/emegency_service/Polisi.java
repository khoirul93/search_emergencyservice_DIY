package com.example.khoir.emegency_service;

/**
 * Created by khoir on 5/13/2016.
 */
public class Polisi {
    private String id_polisi;
    private String nama_polisi;
    private String profil;
    private String latitude;
    private String longitude;
    private String alamat;
    private String galeri;
    private String tlpn;

    public void setPolId (String id_polisi)
    {
        this.id_polisi = id_polisi;
    }

    public String getPolId()
    {
        return id_polisi;
    }

    public void setPolName (String nama_polisi) {this.nama_polisi = nama_polisi;}

    public String getPolName() {return nama_polisi;}

    public void setPolProf (String profil)
    {
        this.profil = profil;
    }

    public String getPolProf()
    {
        return profil;
    }

    public void setPolLati (String latitude)
    {
        this.latitude = latitude;
    }

    public String getPolLati()
    {
        return latitude;
    }
    public void setPolLongi (String longitude)
    {
        this.longitude = longitude;
    }

    public String getPolLongi()
    {
        return longitude;
    }


    public void setPolGaleri (String galeri)
    {
        this.galeri = galeri;
    }

    public String getPolGaleri()
    {
        return galeri;
    }


    public void setPolAlamat (String alamat)
    {
        this.alamat = alamat;
    }

    public String getPolAlamat()
    {
        return alamat;
    }

    public void setPolTlpn (String tlpn)
    {
        this.tlpn = tlpn;
    }

    public String getPolTlpn()
    {
        return tlpn;
    }
}
