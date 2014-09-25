package com.pu.anonymous.mobileinformationcenter.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.pu.anonymous.mobileinformationcenter.R;
import com.pu.anonymous.mobileinformationcenter.model.AgendaItem;
import com.pu.anonymous.mobileinformationcenter.model.AgendaModel;

import java.util.List;

/**
 * Created by Anonymous on 10/09/2014.
 */
public class ListViewAdapterAgenda extends BaseAdapter {

    Context context;
    List<AgendaModel> agendaItem;
    DisplayImageOptions options;
    public ListViewAdapterAgenda(Context context, List<AgendaModel> agendaItem) {
        this.context = context;
        this.agendaItem = agendaItem;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.logo_app)
                .showImageForEmptyUri(R.drawable.logo_app)
                .showImageOnFail(R.drawable.logo_app)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(10))
                .build();
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

        AgendaModel row_pos = agendaItem.get(position);
        // setting the image resource and title
        ImageLoader.getInstance().displayImage(context.getResources().getString(R.string.urlimage)+row_pos.getGambar(),imgIcon,options);
        txtTitle.setText(row_pos.getJudul());
        txttanggal.setText(row_pos.getTanggal());

        return convertView;

        }
}