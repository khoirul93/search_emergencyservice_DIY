package com.example.khoir.emegency_service;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DialogInterface.OnClickListener listener;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button go = (Button) findViewById(R.id.button1);
        go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Main_Pemadam_Kebakaran.class);
                startActivity(i);
            }
        });

        Button kul = (Button) findViewById(R.id.button2);
        kul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainRumahSakit.class);
                startActivity(i);
            }
        });



        final Button maps = (Button) findViewById(R.id.button4);
        maps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                i.putExtra("lt","-7.8013823");
                i.putExtra("lg","110.3647725");





                startActivity(i);
            }
        });
        Button peng = (Button) findViewById(R.id.button3);
        peng.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainPolisi.class);
                startActivity(i);
            }
        });
        Button tent = (Button) findViewById(R.id.button5);
        tent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Tentang.class);
                startActivity(i);
            }
        });

        Button twit= (Button) findViewById(R.id.btntwitter);
        twit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, twitter.class);
                startActivity(i);
            }
        });

        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Aplikasi Memerlukan Akses GPS, GPS Anda Belum Aktif, Buka Setting Dan Aktifkan GPS?")
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            dialog.cancel();
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();
        } else {
            Toast.makeText(getApplicationContext(), "GPS Sudah Aktif", Toast.LENGTH_LONG).show();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //jika tombol BACK ditekan
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Keluar();
        }
        return super.onKeyDown(keyCode, event);
    }

    //method untuk keluar aplikasi menggunakan dialog terlebih dahulu
    private void Keluar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda Yakin Ingin Keluar?");
        builder.setCancelable(false);//tombol BACK tidak bisa tekan

        //Membuat listener untuk tombol DIALOG
        listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    finish(); //keluar aplikasi
                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    dialog.cancel(); //batal keluar
                }
            }
        };

        //menerapkan listener pada tombol ya dan tidak
        builder.setPositiveButton("Ya", listener);
        builder.setNegativeButton("Tidak", listener);
        builder.show(); //menampilkan dialog


    }
}
