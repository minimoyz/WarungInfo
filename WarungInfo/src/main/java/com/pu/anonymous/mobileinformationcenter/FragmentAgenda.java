package com.pu.anonymous.mobileinformationcenter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.pu.anonymous.mobileinformationcenter.adapter.ListViewAdapterAgenda;
import com.pu.anonymous.mobileinformationcenter.adapter.ListViewAdapterBeritaBaru;
import com.pu.anonymous.mobileinformationcenter.model.AgendaItem;
import com.pu.anonymous.mobileinformationcenter.model.AgendaModel;
import com.pu.anonymous.mobileinformationcenter.model.BeritaModel;
import com.pu.anonymous.mobileinformationcenter.model.NewsItem;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Anonymous on 09/09/2014.
 */
public class FragmentAgenda extends ListFragment implements AdapterView.OnItemClickListener {
    ProgressDialog pDialog;
    JSONArray jsonAgenda = null;
    String[] judul_agenda;
    String[] tanggal_agenda;
    TypedArray image_agenda;
    ArrayList<AgendaModel> listAgenda = new ArrayList<AgendaModel>();
    ListViewAdapterAgenda adapter;
    private List<NewsItem> newsItems;
    ListViewAdapterAgenda adapterAgenda;
    private List<AgendaItem> agendaItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_view_main_agenda, null, false);
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
//        judul_agenda = getResources().getStringArray(R.array.judul_agenda);
//        tanggal_agenda = getResources().getStringArray(R.array.tanggal_agenda);
//        image_agenda = getResources().obtainTypedArray(R.array.img_agenda);

//        agendaItem = new ArrayList<AgendaItem>();

//        for (int i = 0; i < judul_agenda.length; i++) {
//            AgendaItem items = new AgendaItem(judul_agenda[i], tanggal_agenda[i], image_agenda.getResourceId(i, -1));
//
//            agendaItem.add(items);
//        }

//        adapterAgenda = new ListViewAdapterAgenda(getActivity(), agendaItem);
//        setListAdapter(adapterAgenda);
        new GetAgenda().execute();
        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {

            Intent isiAgenda = new Intent(getActivity(), IsiAgenda.class);
            AgendaModel agenda = listAgenda.get(position);
            isiAgenda.putExtra("ID",Integer.toString(agenda.getId()));
            startActivity(isiAgenda);



    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            Toast.makeText(getActivity(), "Example action.", Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }

    private class GetAgenda extends AsyncTask<String, String, String> {

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

            params.add(new BasicNameValuePair("method","getagenda"));
            params.add(new BasicNameValuePair("start","0"));
            params.add(new BasicNameValuePair("limit","100"));
            params.add(new BasicNameValuePair("tanggal","0"));
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(getResources().getString(R.string.url), ServiceHandler.GET,params);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    int result = jsonObj.getInt("result");
                    if(result==1){
                        jsonAgenda = jsonObj.getJSONArray("data");

                        // looping through All Contacts
                        for (int i = 0; i < jsonAgenda.length(); i++) {
                            JSONObject c = jsonAgenda.getJSONObject(i);
                            AgendaModel data = new AgendaModel(c.getInt("id"),
                                    c.getString("judul"),
                                    c.getString("deskripsi"),
                                    c.getString("gambar"),
                                    c.getString("tanggal"));


                            // adding contact to contact list
                            listAgenda.add(data);
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
            adapter = new ListViewAdapterAgenda(getActivity(), listAgenda);
            setListAdapter(adapter);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

        }



    }
}