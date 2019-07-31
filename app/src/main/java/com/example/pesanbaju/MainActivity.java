package com.example.pesanbaju;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    CardView btnkonfirm ,btnpesanbaju;
    Button btn_logout;

    String iduser;
    SharedPreferences sharedpreferences;


    public final static String TAG_ID_USER = "iduser";
    public final static String TAG_ID_EMAIL = "email";
    public final static String TAG_TOKEN = "token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getApplicationContext().getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        iduser = sharedpreferences.getString("iduser", "0");

        btnpesanbaju = findViewById(R.id.btnpesanbaju);
        btnpesanbaju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FormDataPemesan.class);
                getApplicationContext().startActivity(intent);
            }
        });

        btnkonfirm = findViewById(R.id.btnkonfirm);
        btnkonfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentWhatsapp = new Intent("android.intent.action.MAIN");
                intentWhatsapp.setAction(Intent.ACTION_VIEW);
                String url = "https://api.whatsapp.com/send?phone=" + "6285803000346" + "&text=Halo admin, Mau tanya nihh....";

                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                getApplicationContext().startActivity(intentWhatsapp);
            }

        });


        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

                sharedpreferences = getApplicationContext().getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(Login.session_status, false);
                editor.putString(TAG_ID_USER, null);
                editor.putString(TAG_ID_EMAIL, null);
                editor.commit();
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);


            }


        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        /// builder.setTitle("Keluar ");
        builder.setMessage("Apakah kamu yakin ingin keluar dari aplikasi ?");

        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {



                moveTaskToBack(true);
                finish();
                //// new DetailAplikasiSaya.HapusData().execute();
                dialog.dismiss();
            }

        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        //// Toast.makeText(this,"Keluar aplikasi!", Toast.LENGTH_LONG).show();

    }
}
