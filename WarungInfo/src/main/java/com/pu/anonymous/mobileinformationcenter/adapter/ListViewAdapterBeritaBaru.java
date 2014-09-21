package com.pu.anonymous.mobileinformationcenter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pu.anonymous.mobileinformationcenter.IsiBerita;
import com.pu.anonymous.mobileinformationcenter.R;
import com.pu.anonymous.mobileinformationcenter.model.NewsItem;

import java.util.List;

/**
 * Created by Anonymous on 10/09/2014.
 */
public class ListViewAdapterBeritaBaru extends BaseAdapter {

    Context context;
    List<NewsItem> newsItem;

    public ListViewAdapterBeritaBaru(Context context, List<NewsItem> newsItem) {
        this.context = context;
        this.newsItem = newsItem;
    }

    @Override
    public int getCount() {
        return newsItem.size();
    }

    @Override
    public Object getItem(int position) {

        return newsItem.get(position);
    }

    @Override
    public long getItemId(int position) {

        return newsItem.indexOf(getItem(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
        convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_berita, null);
    }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.image);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        TextView txttanggal = (TextView) convertView.findViewById(R.id.tanggal);



        ImageButton btnBaca = (ImageButton) convertView.findViewById(R.id.btnDetail);
        btnBaca.setTag(position);
        btnBaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "button position : " + position, Toast.LENGTH_LONG).sho);
               if(position == 0) {
                   Intent isiBerita = new Intent(v.getContext(), IsiBerita.class);
                   context.startActivity(isiBerita);
                } else {
                    Toast.makeText(v.getContext(), "Masih dalam tahap penyelesaian", Toast.LENGTH_LONG).show();
                }
            }
        });


        NewsItem row_pos = newsItem.get(position);
        // setting the image resource and title
        imgIcon.setImageResource(row_pos.getIcon());
        txtTitle.setText(row_pos.getJudul());
        txttanggal.setText("Published : " + row_pos.getTanggal());

        return convertView;

        }
}