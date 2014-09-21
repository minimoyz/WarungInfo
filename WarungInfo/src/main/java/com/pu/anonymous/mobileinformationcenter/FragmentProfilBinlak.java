package com.pu.anonymous.mobileinformationcenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Anonymous on 09/09/2014.
 */
public class FragmentProfilBinlak extends Fragment {

    FragmentTabHost mTabHost;
//    TabHost tabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_fragment_profil_binlak_2,container, false);

        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.tabhost);

        mTabHost.addTab(mTabHost.newTabSpec("struktur").setIndicator("Struktur Organisasi"), TabActivityStruktur.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tugas").setIndicator("Tugas & Fungsi"), TabActivityTugasDanFungsi.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("memori").setIndicator("Memori Tugas Akhir"), TabActivityMemoriTugasAkhir.class, null);

        return mTabHost;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }

}