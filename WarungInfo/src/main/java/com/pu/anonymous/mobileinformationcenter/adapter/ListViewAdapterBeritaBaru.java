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

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.pu.anonymous.mobileinformationcenter.IsiBerita;
import com.pu.anonymous.mobileinformationcenter.R;
import com.pu.anonymous.mobileinformationcenter.model.BeritaModel;
import com.pu.anonymous.mobileinformationcenter.model.NewsItem;

import java.util.List;

/**
 * Created by Anonymous on 10/09/2014.
 */
public class ListViewAdapterBeritaBaru extends BaseAdapter {

    Context context;
    List<BeritaModel> newsItem;
    DisplayImageOptions options;
    public ListViewAdapterBeritaBaru(Context context, List<BeritaModel> newsItem) {
        this.context = context;
        this.newsItem = newsItem;
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


        final BeritaModel row_pos = newsItem.get(position);
        ImageButton btnBaca = (ImageButton) convertView.findViewById(R.id.btnDetail);
        btnBaca.setTag(position);
        btnBaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "button position : " + position, Toast.LENGTH_LONG).sho);

                   Intent isiBerita = new Intent(v.getContext(), IsiBerita.class);
                    isiBerita.putExtra("ID",Integer.toString(row_pos.getId()));
                   context.startActivity(isiBerita);

            }
        });



        // setting the image resource and title
//        imgIcon.setImageResource(row_pos.getGambar());
        txtTitle.setText(row_pos.getJudul());
        txttanggal.setText("Published : " + row_pos.getTanggal());
        ImageLoader.getInstance().displayImage(context.getResources().getString(R.string.urlimage)+row_pos.getGambar(),imgIcon,options);
        System.out.println("image ="+row_pos.getGambar());
        return convertView;

        }
}