package com.pu.anonymous.mobileinformationcenter.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pu.anonymous.mobileinformationcenter.R;
import com.pu.anonymous.mobileinformationcenter.model.AgendaItem;

import java.util.List;

/**
 * Created by Anonymous on 10/09/2014.
 */
public class ListViewAdapterAgenda extends BaseAdapter {

    Context context;
    List<AgendaItem> agendaItem;

    public ListViewAdapterAgenda(Context context, List<AgendaItem> agendaItem) {
        this.context = context;
        this.agendaItem = agendaItem;
    }

    @Override
    public int getCount() {
        return agendaItem.size();
    }

    @Override
    public Object getItem(int position) {
        return agendaItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return agendaItem.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.listview_item_agenda, null);
    }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.image_agenda);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title_agenda);
        TextView txttanggal = (TextView) convertView.findViewById(R.id.tanggal_agenda);

        AgendaItem row_pos = agendaItem.get(position);
        // setting the image resource and title
        imgIcon.setImageResource(row_pos.getIcon());
        txtTitle.setText(row_pos.getJudulAgenda());
        txttanggal.setText(row_pos.getTanggalAgenda());

        return convertView;

        }
}