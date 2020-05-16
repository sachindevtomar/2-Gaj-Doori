package com.queue20.dogajdoori;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.queue20.dogajdoori.Shared.CountryData;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPhoneNumber;
    private Spinner spinner;
    private Button btnSendOtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner_country_code);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        editTextPhoneNumber = findViewById(R.id.editText_phone_number);
        btnSendOtp = findViewById(R.id.button_send_otp);

        btnSendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
                String phoneNumber = editTextPhoneNumber.getText().toString().trim();

                if(phoneNumber.isEmpty() || phoneNumber.length() < 10){
                    editTextPhoneNumber.setError("Valid number is required");
                    editTextPhoneNumber.requestFocus();
                    return;
                }

                String phoneNumberModified = "+" + code + phoneNumber;

                Intent intent = new Intent(MainActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("phoneNumber", phoneNumberModified);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
