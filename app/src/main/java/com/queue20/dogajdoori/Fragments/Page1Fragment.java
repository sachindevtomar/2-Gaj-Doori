package com.queue20.dogajdoori.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.queue20.dogajdoori.R;


/* Fragment used as page 1 */
public class Page1Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page1, container, false);
        return rootView;
    }
}
