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
public class Main_Pemadam_Kebakaran extends ActionBarActivity {
    ListView list;

    JSONparser jParser = new JSONparser();
    ArrayList<pemadam_kebakaran> daftar_pemadam = new ArrayList<pemadam_kebakaran>();
    JSONArray daftarPem = null;
    String url_read_Pem = "http://emergency-diy.hol.es/emergency_service/read_pemadam.php";
    // JSON Node names, ini harus sesuai yang di API
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_PEM= "pemadam";
    public static final String TAG_ID_PEMADAM = "id_pemadam";
    public static final String TAG_NAMA_PEMADAM = "nama_pemadam";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_TLPN = "tlpn";
    public static final String TAG_PROFIL = "profil";
    public static final String TAG_LATITUDE = "latitude";
    public static final String TAG_LONGITUDE = "longitude";
    public static final String TAG_GALERI = "galeri";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bpbd);
        list = (ListView) findViewById(R.id.listview_pemadam);
        ReadHotTask m = (ReadHotTask) new ReadHotTask().execute();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int urutan, long id) {
                //dapatkan data id, nama, nim mahasiswa dan simpan dalam variable string
                String id_pemadam = ((TextView) view.findViewById(R.id.id_pemadam)).getText().toString();
                String nama_pemadam = ((TextView) view.findViewById(R.id.nama_pemadam)).getText().toString();
                String latitude = ((TextView) view.findViewById(R.id.latitude)).getText().toString();
                String longitude = ((TextView) view.findViewById(R.id.longitude)).getText().toString();
                String profil = ((TextView) view.findViewById(R.id.profil)).getText().toString();
                String galeri = ((TextView) view.findViewById(R.id.galeri)).getText().toString();
                String alamat = ((TextView) view.findViewById(R.id.alamat)).getText().toString();
                String tlpn = ((TextView) view.findViewById(R.id.tlpn)).getText().toString();

                //varible string tadi kita simpan dalam suatu Bundle, dan kita parse bundle tersebut bersama intent menuju kelas UpdateDeleteActivity
                Intent i = null;
                i = new Intent(Main_Pemadam_Kebakaran.this, pemadam_kebakaran_profil.class);
                Bundle b = new Bundle();
                b.putString("id_pemadam", id_pemadam);
                b.putString("nama_pemadam", nama_pemadam);
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
            pDialog = new ProgressDialog(Main_Pemadam_Kebakaran.this);
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
                Toast.makeText(Main_Pemadam_Kebakaran.this, "Gagal koneksi ke Server, periksa koneksi internet Anda!", Toast.LENGTH_LONG).show();
            }

            if (result.equalsIgnoreCase("no results")) {
                Toast.makeText(Main_Pemadam_Kebakaran.this, "Data belum ada", Toast.LENGTH_LONG).show();
            } else {
                list.setAdapter(new pemadam_kebakaran_adapter(Main_Pemadam_Kebakaran.this, daftar_pemadam)); //Adapter menampilkan data mahasiswa ke dalam listView
            }
        }

        public String getPemList() {
            pemadam_kebakaran tempPem = new pemadam_kebakaran();
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            try {
                JSONObject json = jParser.AmbilJson(url_read_Pem, "POST", parameter);

                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) { //Ada record Data (SUCCESS = 1)
                    //Getting Array of daftar_mhs
                    daftarPem = json.getJSONArray(TAG_PEM);
                    // looping through All daftar_mhs
                    for (int i = 0; i < daftarPem.length(); i++) {
                        JSONObject c = daftarPem.getJSONObject(i);
                        tempPem = new pemadam_kebakaran();
                        tempPem.setPemId(c.getString(TAG_ID_PEMADAM));
                        tempPem.setPemName(c.getString(TAG_NAMA_PEMADAM));
                        tempPem.setPemProf(c.getString(TAG_PROFIL));
                        tempPem.setPemLati(c.getString(TAG_LATITUDE));
                        tempPem.setPemLongi(c.getString(TAG_LONGITUDE));
                        tempPem.setPemGaleri(c.getString(TAG_GALERI));
                        tempPem.setPemAlamat(c.getString(TAG_ALAMAT));
                        tempPem.setPemTlpn(c.getString(TAG_TLPN));
                        daftar_pemadam.add(tempPem);
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
