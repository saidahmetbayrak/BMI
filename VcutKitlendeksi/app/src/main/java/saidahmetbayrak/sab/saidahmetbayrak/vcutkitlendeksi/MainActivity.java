package saidahmetbayrak.sab.saidahmetbayrak.vcutkitlendeksi;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    private InterstitialAd InterstitialAds;

    EditText kilo, boy;
    TextView txt_Endeks, txt_Kilonuz, txt_Kalori, txtİdeal, txt_Boy, txt_Kilo, txtMetre, txtKilo;
    Button btnHesap,btnYenile;
    float kilonuz;
    float boyunuz;
    float k;
    float kalori, ikilo, x;
    RadioButton rb_bay;
    RadioButton rb_bayan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this,
                "ca-app-pub-9399318819724364~3473837934");


        mAdView =  findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        InterstitialAds = new InterstitialAd(this);
        InterstitialAds.setAdUnitId("ca-app-pub-9399318819724364/5696800309");
        reklamiYukle();







        kilo = findViewById(R.id.editBoxKilo);

        boy = findViewById(R.id.editBoxBoy);

        txt_Endeks = findViewById(R.id.txtEndeks);

        txt_Kilonuz = findViewById(R.id.txtKilonuz);

        txt_Kalori = findViewById(R.id.txtKaloriMiktari);

        txtİdeal = findViewById(R.id.txtIdealKilo);

        rb_bay = findViewById(R.id.rdBtnErkek);

        rb_bayan = findViewById(R.id.rdBtnKadın);

        txt_Boy = findViewById(R.id.txtBoy);

        txt_Kilo = findViewById(R.id.txtKilo);

        txtMetre = findViewById(R.id.textView3);

        txtKilo = findViewById(R.id.textView3);

        btnHesap =  findViewById(R.id.btnHesapla);
        btnHesap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //edittext boş mu dolu mu  olduğunu kontrol ettirmek için yazdım.

                if (( boy.getText().toString().trim().equals(""))||(kilo.getText().toString().trim().equals("")))

                {
                    Toast.makeText(getApplicationContext(),"Lütfen değerleri giriniz!",Toast.LENGTH_LONG).show();
                }
                else {

                    //Boy kriteri için aralık  verdim

                    double giris = Double.parseDouble(boy.getText().toString());
                    double kilogiris = Double.parseDouble(kilo.getText().toString());

                    if ((giris >= 1.40 && giris <= 2.00) && (kilogiris >= 35.0 && kilogiris <= 150.0)) {

                        //vücut kitle endeksinin formülü
                        kilonuz = (Float.parseFloat(kilo.getText().toString()));
                        boyunuz = (Float.parseFloat(boy.getText().toString()));

                        k = kilonuz / (boyunuz * boyunuz);
                        txt_Endeks.setText("Vücut Kitle Endeksiniz: " + String.valueOf(k));

                        if (k <= 18) {

                            txt_Kilonuz.setText("Kilonu: Zayıf");
                        } else if (18 < k && k <= 25) {

                            txt_Kilonuz.setText("Kilonuz: Normal");
                        } else if (25 < k && k <= 30) {

                            txt_Kilonuz.setText("Kilonuz: Fazla Kilolu");
                        } else if (30 < k && k <= 35) {

                            txt_Kilonuz.setText("Kilonuz: 1.Derecede Obez");
                        } else if (35 < k && k <= 40) {

                            txt_Kilonuz.setText("Kilonuz: 2. Derecede Obez");
                        } else {
                            txt_Kilonuz.setText("Kilonuz: 3. Derecede Obez");

                        }
                        //kişinin günlük alması gereken kalori miktarını verir.
                        kalori = kilonuz * 2 * 10;
                        txt_Kalori.setText("Günlük Kalori Miktarı: " + String.valueOf(kalori));

                        //cinsiyete göre ideal kilo formülü
                        if (rb_bay.isChecked()) {

                            x = boyunuz * 100;
                            ikilo = x - 100 - ((x - 150) / 4);
                            txtİdeal.setText("İdeal Kilonuz:" + String.valueOf(ikilo));

                        } else if (rb_bayan.isChecked()) {

                            x = boyunuz * 100;
                            ikilo = x - 100 - ((x - 150) / 2);
                            txtİdeal.setText("İdeal Kilonuz:" + String.valueOf(ikilo));
                        }

                    } else {

                        Context context = getApplicationContext();
                        {
                            CharSequence text = "Boy Aralığı:140 ve 200 Kilo aralığı: 35 ve 150 arasında olmalıdır.";
                            int duration = Toast.LENGTH_LONG;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }

                }
            }
        });

        //yenile butonu
        btnYenile = findViewById(R.id.btnYenile);
        btnYenile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kilo.setText("");
                boy.setText("");
                txtİdeal.setText("İdeal Kilonuz:");
                txt_Endeks.setText("Vücut Kitle Endeksiniz:");
                txt_Kalori.setText("Günlük Kalori Miktarı:");
                txt_Kilonuz.setText("Kilonuz:");
                rb_bayan.setChecked(false);
                rb_bay.setChecked(false);

            }
        });


        InterstitialAds.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                InterstitialAds.show();
            }
        });
    }
    private void reklamiYukle() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        InterstitialAds.loadAd(adRequest);
    }


}

