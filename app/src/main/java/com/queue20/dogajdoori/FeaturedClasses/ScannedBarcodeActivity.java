package com.queue20.dogajdoori.FeaturedClasses;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;
import com.queue20.dogajdoori.HomeActivity;
import com.queue20.dogajdoori.Model.MyTurnQueue;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannedBarcodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scannerView;
    private DatabaseReference mDatabase;
    long QueueCountValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView =  new ZXingScannerView(this);
        setContentView(scannerView);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void handleResult(Result result) {

        final Result codeResult = result;

        final Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("qrResult", result.getText());

        mDatabase.child("Queue").child(result.getText()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    QueueCountValue = dataSnapshot.getChildrenCount();
//                Toast.makeText(getApplicationContext(), "Data Count : "+ QueueCountValue, Toast.LENGTH_LONG);
                writeNewQueue(FirebaseAuth.getInstance().getCurrentUser().getUid(), codeResult.getText(), QueueCountValue);
                mDatabase.removeEventListener(this);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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

    private void writeNewQueue(String UserId, String StoreName, long MyTurnNumber) {
        MyTurnQueue myTurnQueue = new MyTurnQueue(UserId, StoreName, MyTurnNumber);

        mDatabase.child("Queue").child(StoreName).child(UserId).setValue(myTurnQueue);
    }
}
