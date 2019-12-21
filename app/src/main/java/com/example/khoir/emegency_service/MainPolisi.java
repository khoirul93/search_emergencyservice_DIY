package com.example.khoir.emegency_service;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khoir on 5/13/2016.
 */
public class MainPolisi extends ActionBarActivity {
    ListView list;

    JSONparser jParser = new JSONparser();
    ArrayList<Polisi> daftar_polisi = new ArrayList<Polisi>();
    JSONArray daftarPol = null;
    String url_read_Pol = "http://emergency-diy.hol.es/emergency_service/read_polisi.php";
    // JSON Node names, ini harus sesuai yang di API
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_POL= "polisi";
    public static final String TAG_ID_POLISI = "id_polisi";
    public static final String TAG_NAMA_POLISI = "nama_polisi";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_TLPN = "tlpn";
    public static final String TAG_PROFIL = "profil";
    public static final String TAG_LATITUDE = "latitude";
    public static final String TAG_LONGITUDE = "longitude";
    public static final String TAG_GALERI = "galeri";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_polisi);
        list = (ListView) findViewById(R.id.listview_polisi);
        ReadHotTask m = (ReadHotTask) new ReadHotTask().execute();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int urutan, long id) {
                //dapatkan data id, nama, nim mahasiswa dan simpan dalam variable string
                String id_polisi = ((TextView) view.findViewById(R.id.id_polisi)).getText().toString();
                String nama_polsi = ((TextView) view.findViewById(R.id.nama_polisi)).getText().toString();
                String latitude = ((TextView) view.findViewById(R.id.latitude)).getText().toString();
                String longitude = ((TextView) view.findViewById(R.id.longitude)).getText().toString();
                String profil = ((TextView) view.findViewById(R.id.profil)).getText().toString();
                String galeri = ((TextView) view.findViewById(R.id.galeri)).getText().toString();
                String alamat = ((TextView) view.findViewById(R.id.alamat)).getText().toString();
                String tlpn = ((TextView) view.findViewById(R.id.tlpn)).getText().toString();

                //varible string tadi kita simpan dalam suatu Bundle, dan kita parse bundle tersebut bersama intent menuju kelas UpdateDeleteActivity
                Intent i = null;
                i = new Intent(MainPolisi.this,PolisiProfil.class);
                Bundle b = new Bundle();
                b.putString("id_polisi", id_polisi);
                b.putString("nama_polisi", nama_polsi);
                b.putString("profil", profil);
                b.putString("latitude", latitude);
                b.putString("longitude", longitude);
                b.putString("galeri", galeri);
                b.putString("alamat", alamat);
                b.putString("tlpn", tlpn);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
    class ReadHotTask extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainPolisi.this);
            pDialog.setMessage("Mohon Tunggu..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText) {
            String returnResult = getPemList(); //memanggil method getMhsList()
            return returnResult;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            if (result.equalsIgnoreCase("Exception Caught")) {
                Toast.makeText(MainPolisi.this, "Gagal koneksi ke Server, periksa koneksi internet Anda!", Toast.LENGTH_LONG).show();
            }

            if (result.equalsIgnoreCase("no results")) {
                Toast.makeText(MainPolisi.this, "Data belum ada", Toast.LENGTH_LONG).show();
            } else {
                list.setAdapter(new PolisiAdapter(MainPolisi.this, daftar_polisi)); //Adapter menampilkan data mahasiswa ke dalam listView
            }
        }

        public String getPemList() {
            Polisi tempPem = new Polisi();
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            try {
                JSONObject json = jParser.AmbilJson(url_read_Pol, "POST", parameter);

                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) { //Ada record Data (SUCCESS = 1)
                    //Getting Array of daftar_mhs
                    daftarPol = json.getJSONArray(TAG_POL);
                    // looping through All daftar_mhs
                    for (int i = 0; i < daftarPol.length(); i++) {
                        JSONObject c = daftarPol.getJSONObject(i);
                        tempPem = new Polisi();
                        tempPem.setPolId(c.getString(TAG_ID_POLISI));
                        tempPem.setPolName(c.getString(TAG_NAMA_POLISI));
                        tempPem.setPolProf(c.getString(TAG_PROFIL));
                        tempPem.setPolLati(c.getString(TAG_LATITUDE));
                        tempPem.setPolLongi(c.getString(TAG_LONGITUDE));
                        tempPem.setPolGaleri(c.getString(TAG_GALERI));
                        tempPem.setPolAlamat(c.getString(TAG_ALAMAT));
                        tempPem.setPolTlpn(c.getString(TAG_TLPN));
                        daftar_polisi.add(tempPem);
                    }
                    return "OK";
                } else {
                    //Tidak Ada Record Data (SUCCESS = 0)
                    return "no results";
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception Caught";
            }
        }

    }
}
