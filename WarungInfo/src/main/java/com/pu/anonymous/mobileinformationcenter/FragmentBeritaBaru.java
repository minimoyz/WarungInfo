package com.pu.anonymous.mobileinformationcenter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.pu.anonymous.mobileinformationcenter.adapter.ListViewAdapterBeritaBaru;
import com.pu.anonymous.mobileinformationcenter.model.BeritaModel;
import com.pu.anonymous.mobileinformationcenter.model.NewsItem;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anonymous on 09/09/2014.
 */
public class FragmentBeritaBaru extends ListFragment implements AdapterView.OnItemClickListener {
    ProgressDialog pDialog;
    JSONArray jsonBerita = null;
    String[] judul;
    String[] tanggal;
    TypedArray image;
    int btn;
    ArrayList<BeritaModel> listBerita = new ArrayList<BeritaModel>();
    ListViewAdapterBeritaBaru adapter;
    private List<NewsItem> newsItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.list_view_main_berita_baru, null, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

//        judul = getResources().getStringArray(R.array.judul_berita);
//        tanggal = getResources().getStringArray(R.array.tanggal_berita);
//        image = getResources().obtainTypedArray(R.array.img_news);
//
//        newsItems = new ArrayList<NewsItem>();
//
//        for (int i = 0; i < judul.length; i++) {
//            NewsItem items = new NewsItem(judul[i], tanggal[i], image.getResourceId(i, -1));
//
//            newsItems.add(items);
//        }

            new GetBerita().execute();

        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
    }

    private class GetBerita extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
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

            params.add(new BasicNameValuePair("method","getnews"));
            params.add(new BasicNameValuePair("start","0"));
            params.add(new BasicNameValuePair("limit","100"));
            params.add(new BasicNameValuePair("uid","1"));
             // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(getResources().getString(R.string.url), ServiceHandler.GET,params);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    int result = jsonObj.getInt("result");
                    if(result==1){
                        jsonBerita = jsonObj.getJSONArray("data");

                        // looping through All Contacts
                        for (int i = 0; i < jsonBerita.length(); i++) {
                            JSONObject c = jsonBerita.getJSONObject(i);
                            BeritaModel data = new BeritaModel(c.getInt("id"),
                                    c.getString("tittle"),
                                    c.getString("tanggal"),
                                    c.getString("gambar"),
                                    c.getString("like"),"");


                            // adding contact to contact list
                            listBerita.add(data);
                        }}

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            adapter = new ListViewAdapterBeritaBaru(getActivity(), listBerita);
            setListAdapter(adapter);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

        }



    }
}