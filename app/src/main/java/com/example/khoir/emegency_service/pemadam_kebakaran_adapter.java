package com.example.khoir.emegency_service;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by khoir on 5/12/2016.
 */

public class pemadam_kebakaran_adapter extends BaseAdapter {
    private Activity activity;

    private ArrayList<pemadam_kebakaran> data_pemadam=new ArrayList<pemadam_kebakaran>();//membuat list dari database

    private static LayoutInflater inflater = null;

    public pemadam_kebakaran_adapter(Activity a, ArrayList<pemadam_kebakaran> d) {
        activity = a; data_pemadam = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return data_pemadam.size();
    }//dari posisi gps
    public Object getItem(int position) {
        return data_pemadam.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.list_item_bpbd, null);//mengambil (mencari data dari class pemadam)
        TextView id_pemadam = (TextView) vi.findViewById(R.id.id_pemadam);
        TextView nama_pemadam = (TextView) vi.findViewById(R.id.nama_pemadam);
        TextView profil = (TextView) vi.findViewById(R.id.profil);
        TextView latitude = (TextView) vi.findViewById(R.id.latitude);
        TextView longitude = (TextView) vi.findViewById(R.id.longitude);
        TextView galeri = (TextView) vi.findViewById(R.id.galeri);
        ImageView img_galeri = (ImageView) vi.findViewById(R.id.img_galeri);
        TextView alamat = (TextView) vi.findViewById(R.id.alamat);
        TextView tlpn = (TextView) vi.findViewById(R.id.tlpn);


        pemadam_kebakaran daftar_pemadam = data_pemadam.get(position); //menampilkan data ke layout list item
        id_pemadam.setText(daftar_pemadam.getPemId());
        nama_pemadam.setText(daftar_pemadam.getPemName());
        profil.setText(daftar_pemadam.getPemProf());
        latitude.setText(daftar_pemadam.getPemLati());
        longitude.setText(daftar_pemadam.getPemLongi());
        galeri.setText(daftar_pemadam.getPemGaleri());
        alamat.setText(daftar_pemadam.getPemAlamat());
        tlpn.setText(daftar_pemadam.getPemTlpn());
        Picasso.with(vi.getContext())
                .load(daftar_pemadam.getPemGaleri())
                .resize(620,380)
                .into(img_galeri);


        return vi;
    }
}