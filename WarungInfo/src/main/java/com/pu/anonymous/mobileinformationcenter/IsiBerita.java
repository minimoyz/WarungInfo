package com.pu.anonymous.mobileinformationcenter;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.pu.anonymous.mobileinformationcenter.adapter.ListViewAdapterBeritaBaru;
import com.pu.anonymous.mobileinformationcenter.model.BeritaModel;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Anonymous on 18/09/2014.
 */

public class IsiBerita extends Activity {

    TextView txtTittle, txtTanggal, txtIsi;
    ImageView imgBerita;
    ImageButton btnShare;
    Intent i;
    String id_berita;
    ProgressDialog pDialog;
    JSONArray jsonBerita = null;
    DisplayImageOptions options;

    ArrayList<BeritaModel> listBerita = new ArrayList<BeritaModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.isi_berita);
        i = getIntent();
        id_berita = i.getStringExtra("ID");
        txtTittle = (TextView) findViewById(R.id.berita_title);
        txtTanggal = (TextView) findViewById(R.id.berita_tanggal);
        txtIsi = (TextView) findViewById(R.id.berita_isi);
        imgBerita = (ImageView) findViewById(R.id.berita_image);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.logo_app)
                .showImageForEmptyUri(R.drawable.logo_app)
                .showImageOnFail(R.drawable.logo_app)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(10))
                .build();
        btnShare = (ImageButton) findViewById(R.id.berita_btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Masih dalam tahap penyelesaian", Toast.LENGTH_LONG).show();
            }
        });


        // get action bar
        ActionBar actionBar = getActionBar();

        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
        new GetDetailBerita().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
              default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class GetDetailBerita extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(IsiBerita.this);
            pDialog.setMessage("Mohon Tunggu...");
            pDialog.setCancelable(true);
            pDialog.show();

        }
        @Override
        protected String doInBackground(String... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("method","detailnews"));
            params.add(new BasicNameValuePair("id",id_berita));
            params.add(new BasicNameValuePair("uid","0"));
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(getResources().getString(R.string.url), ServiceHandler.GET,params);

            Log.d("Response: ", "> " + jsonStr);
            int result = 0;
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    result = jsonObj.getInt("result");
                    if(result==1){
                        jsonBerita = jsonObj.getJSONArray("data");

                        // looping through All Contacts
                        for (int i = 0; i < jsonBerita.length(); i++) {
                            JSONObject c = jsonBerita.getJSONObject(i);
                            BeritaModel data = new BeritaModel(c.getInt("id"),
                                    c.getString("tittle"),
                                    c.getString("tanggal"),
                                    c.getString("gambar"),
                                    c.getString("like"),
                                    c.getString("isi"));


                            // adding contact to contact list
                            listBerita.add(data);
                        }}

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return Integer.toString(result);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result.equals("1")) {
                BeritaModel berita = listBerita.get(0);
                txtTittle.setText(berita.getJudul());
                txtIsi.setText(Html.fromHtml(berita.getIsi()));
                txtTanggal.setText(berita.getTanggal());
                ImageLoader.getInstance().displayImage(getResources().getString(R.string.urlimage) + berita.getGambar(), imgBerita, options);
            }
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

        }



    }
}