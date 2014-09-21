package com.pu.anonymous.mobileinformationcenter;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.pu.anonymous.mobileinformationcenter.adapter.ListViewAdapterAgenda;
import com.pu.anonymous.mobileinformationcenter.model.AgendaItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anonymous on 09/09/2014.
 */
public class FragmentAgenda extends ListFragment implements AdapterView.OnItemClickListener {

    String[] judul_agenda;
    String[] tanggal_agenda;
    TypedArray image_agenda;

    ListViewAdapterAgenda adapterAgenda;
    private List<AgendaItem> agendaItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.list_view_main_agenda, null, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        judul_agenda = getResources().getStringArray(R.array.judul_agenda);
        tanggal_agenda = getResources().getStringArray(R.array.tanggal_agenda);
        image_agenda = getResources().obtainTypedArray(R.array.img_agenda);

        agendaItem = new ArrayList<AgendaItem>();

        for (int i = 0; i < judul_agenda.length; i++) {
            AgendaItem items = new AgendaItem(judul_agenda[i], tanggal_agenda[i], image_agenda.getResourceId(i, -1));

            agendaItem.add(items);
        }

        adapterAgenda = new ListViewAdapterAgenda(getActivity(), agendaItem);
        setListAdapter(adapterAgenda);
        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        if(position == 0) {

            Intent isiAgenda = new Intent(getActivity(), IsiAgenda.class);
            startActivity(isiAgenda);

//            Toast.makeText(getActivity(), "button position : " + position, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "Masih dalam tahap penyelesaian", Toast.LENGTH_LONG).show();
        }

    }
}