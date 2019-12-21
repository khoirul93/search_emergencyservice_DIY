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
 * Created by khoir on 5/13/2016.
 */
public class RumahSakitAdapter extends BaseAdapter {
    private Activity activity;

    private ArrayList<RumahSakit> data_rs=new ArrayList<RumahSakit>();

    private static LayoutInflater inflater = null;

    public RumahSakitAdapter(Activity a, ArrayList<RumahSakit> d) {
        activity = a; data_rs = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return data_rs.size();
    }
    public Object getItem(int position) {
        return data_rs.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.list_item_rs, null);
        TextView id_rs = (TextView) vi.findViewById(R.id.id_rs);
        TextView nama_rs = (TextView) vi.findViewById(R.id.nama_rs);
        TextView profil = (TextView) vi.findViewById(R.id.profil);
        TextView latitude = (TextView) vi.findViewById(R.id.latitude);
        TextView longitude = (TextView) vi.findViewById(R.id.longitude);
        TextView galeri = (TextView) vi.findViewById(R.id.galeri);
        ImageView img_galeri = (ImageView) vi.findViewById(R.id.img_galeri);
        TextView alamat = (TextView) vi.findViewById(R.id.alamat);
        TextView tlpn = (TextView) vi.findViewById(R.id.tlpn);


        RumahSakit daftar_rs = data_rs.get(position);
        id_rs.setText(daftar_rs.getRsId());
        nama_rs.setText(daftar_rs.getRsName());
        profil.setText(daftar_rs.getRsProf());
        latitude.setText(daftar_rs.getRsLati());
        longitude.setText(daftar_rs.getRsLongi());
        galeri.setText(daftar_rs.getRsGaleri());
        alamat.setText(daftar_rs.getRsAlamat());
        tlpn.setText(daftar_rs.getRsTlpn());
        Picasso.with(vi.getContext())
                .load(daftar_rs.getRsGaleri())
                .resize(620,380)
                .into(img_galeri);


        return vi;
    }
}
