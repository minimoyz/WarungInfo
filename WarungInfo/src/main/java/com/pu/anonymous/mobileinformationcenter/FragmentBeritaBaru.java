package com.pu.anonymous.mobileinformationcenter;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.pu.anonymous.mobileinformationcenter.adapter.ListViewAdapterBeritaBaru;
import com.pu.anonymous.mobileinformationcenter.model.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anonymous on 09/09/2014.
 */
public class FragmentBeritaBaru extends ListFragment implements AdapterView.OnItemClickListener {

    String[] judul;
    String[] tanggal;
    TypedArray image;
    int btn;

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

        judul = getResources().getStringArray(R.array.judul_berita);
        tanggal = getResources().getStringArray(R.array.tanggal_berita);
        image = getResources().obtainTypedArray(R.array.img_news);

        newsItems = new ArrayList<NewsItem>();

        for (int i = 0; i < judul.length; i++) {
            NewsItem items = new NewsItem(judul[i], tanggal[i], image.getResourceId(i, -1));

            newsItems.add(items);
        }

        adapter = new ListViewAdapterBeritaBaru(getActivity(), newsItems);
        setListAdapter(adapter);

        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
    }
}