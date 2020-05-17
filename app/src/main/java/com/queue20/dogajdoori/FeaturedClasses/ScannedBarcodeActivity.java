package com.queue20.dogajdoori.FeaturedClasses;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;
import com.queue20.dogajdoori.HomeActivity;
import com.queue20.dogajdoori.Model.MyTurnQueue;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannedBarcodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scannerView;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView =  new ZXingScannerView(this);
        setContentView(scannerView);
    }

    @Override
    public void handleResult(Result result) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        writeNewUser(FirebaseAuth.getInstance().getCurrentUser().getUid(), result.getText(), 23);
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("qrResult", result.getText());
        startActivity(intent);
//        Page1Fragment.resultQRTextView.setText(result.getText());
//        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    private void writeNewUser(String UserId, String StoreName, int MyTurnNumber) {
        MyTurnQueue myTurnQueue = new MyTurnQueue(UserId, StoreName, MyTurnNumber);

        mDatabase.child("Queue").child(StoreName).setValue(myTurnQueue);
    }
}
