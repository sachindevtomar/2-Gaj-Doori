package com.queue20.dogajdoori.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.queue20.dogajdoori.FeaturedClasses.ScannedBarcodeActivity;
import com.queue20.dogajdoori.R;


/* Fragment used as page 1 */
public class Page1Fragment extends Fragment {
    Button btnScanBarcode;
    public static TextView resultQRTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page1, container, false);
        btnScanBarcode = (Button) rootView.findViewById(R.id.btn_scan_qr_code);
        resultQRTextView = (TextView) rootView.findViewById(R.id.text_qr_response);

        btnScanBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ScannedBarcodeActivity.class));
            }
        });
        return rootView;
    }
}
