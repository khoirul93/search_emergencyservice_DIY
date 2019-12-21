package com.example.khoir.emegency_service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * Created by khoir on 5/13/2016.
 */
public class pemadam_kebakaran_profil extends ActionBarActivity {
    TextView TxtViewId, TxtViewNama, TxtViewProfil, TxtViewLati,TxtViewLongi, TxtViewGaleri, TxtViewAlamat, TxtViewTlpn;
    ImageView Img;
    Button LokasiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_bpbd);//menpilkan data profil ke layout profil

        TxtViewId = (TextView) findViewById(R.id.id_pemadam);
        TxtViewNama = (TextView) findViewById(R.id.nama_pemadam);
        TxtViewProfil = (TextView) findViewById(R.id.profil);
        TxtViewLati = (TextView) findViewById(R.id.latitude);
        TxtViewLongi = (TextView) findViewById(R.id.longitude);
        TxtViewGaleri = (TextView) findViewById(R.id.galeri);
        TxtViewAlamat = (TextView) findViewById(R.id.alamat);
        TxtViewTlpn = (TextView) findViewById(R.id.tlpn);
        Img =(ImageView) findViewById(R.id.img_galeri);

        LokasiBtn = (Button) findViewById(R.id.btMaps);

        //menangkap bundle yang telah di-parsed dari MainActivity
        Bundle b = getIntent().getExtras();
        String isi_id_pemadam = b.getString("id_pemadam");
        String isi_nama_pemadam = b.getString("nama_pemadam");
        String isi_profil= b.getString("profil");
        final String isi_latitude = b.getString("latitude");
        final String isi_longitude= b.getString("longitude");
        String isi_galeri = b.getString("galeri");
        String isi_alamat = b.getString("alamat");
        String isi_tlpn = b.getString("tlpn");

        //meng-set bundle tersebut
        TxtViewId.setText(isi_id_pemadam);
        TxtViewNama.setText(isi_nama_pemadam);
        TxtViewProfil.setText("\n"+isi_profil);
        TxtViewLati.setText("Latitude : "+isi_latitude);
        TxtViewLongi.setText("Longitude: "+isi_longitude);
        TxtViewGaleri.setText(isi_galeri);
        TxtViewAlamat.setText(isi_alamat);
        TxtViewTlpn.setText(isi_tlpn);
        Picasso.with(this)
                .load(isi_galeri)
                .resize(540,380)
                .into(Img);

        Button maps = (Button) findViewById(R.id.btMaps);

        maps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(pemadam_kebakaran_profil.this, MapsActivity.class);
                i.putExtra("lt",isi_latitude);
                i.putExtra("lg",isi_longitude);





                startActivity(i);


            }
        });
    }}