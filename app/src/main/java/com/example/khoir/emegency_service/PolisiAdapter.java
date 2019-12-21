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
public class PolisiAdapter extends BaseAdapter {
    private Activity activity;

    private ArrayList<Polisi> data_polisi=new ArrayList<Polisi>();

    private static LayoutInflater inflater = null;

    public PolisiAdapter(Activity a, ArrayList<Polisi> d) {
        activity = a; data_polisi = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return data_polisi.size();
    }
    public Object getItem(int position) {
        return data_polisi.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.list_item_polisi, null);
        TextView id_polisi = (TextView) vi.findViewById(R.id.id_polisi);
        TextView nama_polisi = (TextView) vi.findViewById(R.id.nama_polisi);
        TextView profil = (TextView) vi.findViewById(R.id.profil);
        TextView latitude = (TextView) vi.findViewById(R.id.latitude);
        TextView longitude = (TextView) vi.findViewById(R.id.longitude);
        TextView galeri = (TextView) vi.findViewById(R.id.galeri);
        ImageView img_galeri = (ImageView) vi.findViewById(R.id.img_galeri);
        TextView alamat = (TextView) vi.findViewById(R.id.alamat);
        TextView tlpn = (TextView) vi.findViewById(R.id.tlpn);


        Polisi daftar_polisi = data_polisi.get(position);
        id_polisi.setText(daftar_polisi.getPolId());
        nama_polisi.setText(daftar_polisi.getPolName());
        profil.setText(daftar_polisi.getPolProf());
        latitude.setText(daftar_polisi.getPolLati());
        longitude.setText(daftar_polisi.getPolLongi());
        galeri.setText(daftar_polisi.getPolGaleri());
        alamat.setText(daftar_polisi.getPolAlamat());
        tlpn.setText(daftar_polisi.getPolTlpn());
        Picasso.with(vi.getContext())
                .load(daftar_polisi.getPolGaleri())
                .resize(620,380)
                .into(img_galeri);


        return vi;
    }
}
