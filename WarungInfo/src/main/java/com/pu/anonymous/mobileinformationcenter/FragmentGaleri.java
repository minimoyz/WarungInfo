package com.pu.anonymous.mobileinformationcenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.pu.anonymous.mobileinformationcenter.adapter.ListViewAdapterBeritaBaru;
import com.pu.anonymous.mobileinformationcenter.model.BeritaModel;
import com.pu.anonymous.mobileinformationcenter.model.GaleriModel;
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
public class FragmentGaleri extends Fragment {

    private DisplayImageOptions options;
    private ActionBarActivity activity;
    ProgressDialog pDialog;
    JSONArray jsonGallery = null;
    ViewPager viewPager;
    ArrayList<GaleriModel> listGallery= new ArrayList<GaleriModel>();
    public FragmentGaleri() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (ActionBarActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initImageLoader();
        initImageOption();

        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.galleryView);
        new GetGallery().execute();
//        ArrayList<NewsItem> listBitmap = new ArrayList<NewsItem>();
//        String[] titles = getResources().getStringArray(R.array.title_galeri);
//        String[] detail = getResources().getStringArray(R.array.detail);
//        Resources res = getResources();
//
//        listBitmap.add(initDummy(R.drawable.pict1, titles[0], detail[0]));
//        listBitmap.add(initDummy(R.drawable.pict2, titles[1], detail[1]));
//        listBitmap.add(initDummy(R.drawable.pict3, titles[2], detail[2]));
//        listBitmap.add(initDummy(R.drawable.pict4, titles[3], detail[3]));
//        listBitmap.add(initDummy(R.drawable.pict5, titles[4], detail[4]));
//


        return rootView;
    }

    private class GetGallery extends AsyncTask<String, String, String> {

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

            params.add(new BasicNameValuePair("method","getgallery"));
            params.add(new BasicNameValuePair("start","0"));
            params.add(new BasicNameValuePair("limit","100"));
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(getResources().getString(R.string.url), ServiceHandler.GET,params);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    int result = jsonObj.getInt("result");
                    if(result==1){
                        jsonGallery = jsonObj.getJSONArray("data");

                        // looping through All Contacts
                        for (int i = 0; i < jsonGallery.length(); i++) {
                            JSONObject c = jsonGallery.getJSONObject(i);
                            GaleriModel data = new GaleriModel(c.getInt("id"),
                                    c.getString("judul"),
                                    c.getString("deskripsi"),
                                    c.getString("gambar"),
                                    c.getString("tanggal"));


                            // adding contact to contact list
                            listGallery.add(data);
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
            viewPager.setAdapter(new GaleriAdapter(listGallery));
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

        }



    }

    private NewsItem initDummy(int imageResource, String title, String detail) {
        NewsItem item = new NewsItem();
        item.setIcon(imageResource);
        item.setJudul(title);
        item.setIsiberita(detail);
        return item;
    }

    private void initImageLoader() {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(activity)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    private void initImageOption() {
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(android.R.color.transparent)
                .showImageOnFail(android.R.color.transparent)
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
    }


    public class GaleriAdapter extends PagerAdapter {

        private ArrayList<GaleriModel> listGallery;

        public GaleriAdapter(ArrayList<GaleriModel> listGallery) {
            this.listGallery = listGallery;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.gallery_inflater, container, false);

            ImageView imgGaleri = (ImageView) v.findViewById(R.id.image_galeri);
            TextView title = (TextView) v.findViewById(R.id.title_galeri);
            TextView detail = (TextView) v.findViewById(R.id.detail_galeri);

            GaleriModel item = listGallery.get(position);
            title.setText(item.getJudul());
            detail.setText(item.getDeskripsi());

            ImageLoader.getInstance().displayImage(getResources().getString(R.string.urlimage)+item.getGambar(), imgGaleri, options, new SimpleImageLoadingListener() {

                @Override
                public void onLoadingStarted(String imageUri, View view) {
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    String message = null;
                    switch (failReason.getType()) {
                        case IO_ERROR:
                            message = "Input/Output error";
                            break;
                        case DECODING_ERROR:
                            message = "Image can't be decoded";
                            break;
                        case NETWORK_DENIED:
                            message = "Downloads are denied";
                            break;
                        case OUT_OF_MEMORY:
                            message = "Out Of Memory error";
                            break;
                        case UNKNOWN:
                            message = "Unknown error";
                            break;
                    }
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                }
            });
            container.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return listGallery.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view.equals(o);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }
}

