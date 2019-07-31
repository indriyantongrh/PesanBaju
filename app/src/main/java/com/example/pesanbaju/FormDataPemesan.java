package com.example.pesanbaju;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pesanbaju.Api.JSONResponse;
import com.example.pesanbaju.Api.ModelProfilUser;
import com.example.pesanbaju.Api.RequestInterface;
import com.example.pesanbaju.Utility.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.pesanbaju.BuildConfig.BASE_URL;

public class FormDataPemesan extends AppCompatActivity implements View.OnClickListener {

    String iduser, nama_lengkap, email, nomor_telepon, alamat, jumlah_baju, total_bayar, gambar_baju, tanggal_pesan, keterangan;


    Intent intent;
    private ArrayList<ModelProfilUser> mArrayListUser;
    private int mYear, mMonth, mDay;

    SharedPreferences sharedpreferences;
    private String url = "http://gudangandroid.universedeveloper.com/pesanbaju/inputbaju.php";  //directory php ning server

    public final static String TAG_ID_PEND = "iduser";
    private static final String TAG = FormDataPemesan.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public static final String BASE_URL = "http://universedeveloper.com/gudangandroid/";

    TextView jumlahharga;
    EditText txtnamalengkap, txttelepon, txtalamat, txtemail, txtjumlahbaju, txttanggalbooking, txtketerangan;
    Button btncekharga, btnimage, btnpesan,btntanggal;
    ImageView image;
    Bitmap bitmap, decoded;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60; // range 1 - 100
    ProgressDialog pDialog;

    ConnectivityManager conMgr;
    String tag_json_obj = "json_obj_req";
    Intent i;
    int success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_data_pemesan);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        iduser = sharedpreferences.getString("iduser", "0");
        Toast.makeText(this, "ini id ke-" + iduser, Toast.LENGTH_SHORT).show();

        txtnamalengkap = findViewById(R.id.txtnamalengkap);
        ////txtnamalengkap.setEnabled(false);
        txtemail = findViewById(R.id.txtemail);
        ///txtnomorhp.setEnabled(false);
        txttelepon = findViewById(R.id.txttelepon);
        txtalamat = findViewById(R.id.txtalamat);
        txtjumlahbaju = findViewById(R.id.txtjumlahbaju);
        jumlahharga = findViewById(R.id.jumlahharga);
        btnpesan = findViewById(R.id.btnpesan);
        image = findViewById(R.id.image);
        txttanggalbooking = findViewById(R.id.txttanggalbooking);
        txtketerangan = findViewById(R.id.txtketerangan);
     ////   btntanggal = findViewById(R.id.btntanggal);

        nama_lengkap = getIntent().getStringExtra("nama_lengkap");
        email = getIntent().getStringExtra("email");
        nomor_telepon = getIntent().getStringExtra("nomor_telepon");
        alamat = getIntent().getStringExtra("alamat");
        jumlah_baju = getIntent().getStringExtra("jumlah_baju");
        total_bayar = getIntent().getStringExtra("total_bayar");
        ///foto_ijazah = getStringImage("scan_ijazah");


        txtnamalengkap.setText(nama_lengkap);
        txtemail.setText(email);
        txttelepon.setText(nomor_telepon);
        txtalamat.setText(alamat);
        txtjumlahbaju.setText(jumlah_baju);
        jumlahharga.setText(total_bayar);
        txtketerangan.setText(keterangan);

        btnimage = findViewById(R.id.btnimage);
        btnimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        btncekharga = findViewById(R.id.btncekharga);
        btncekharga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((txtjumlahbaju.getText().length() > 0)) {

                    Locale localeID = new Locale("in", "ID");
                    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

                    double jumlahbaju = Double.parseDouble(txtjumlahbaju.getText().toString());
                    double result = jumlahbaju * 80000;
                    ///jumlahharga.setText("Rp. " +Double.toString(result));
                    jumlahharga.setText(formatRupiah.format(result));


                } else {
                    Toast toast = Toast.makeText(FormDataPemesan.this, "Mohon masukkan Jumlah Baju dengan benar", Toast.LENGTH_LONG);
                    toast.show();
                }
            }

        });


        btnpesan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String nama_lengkap = txtnamalengkap.getText().toString();
                String email = txtemail.getText().toString();
                String nomor_telepon = txttelepon.getText().toString();
                String alamat = txtalamat.getText().toString();
                String tanggal_pesan = txttanggalbooking.getText().toString();
                String jumlah_baju = txtjumlahbaju.getText().toString();
                String total_bayar = jumlahharga.getText().toString();
                String keterangan = txtketerangan.getText().toString();


                /// if (conMgr.getActiveNetworkInfo() != null
                ///     && conMgr.getActiveNetworkInfo().isAvailable()
                ///   && conMgr.getActiveNetworkInfo().isConnected()) {
                checkData(nama_lengkap, email, nomor_telepon, alamat, tanggal_pesan,jumlah_baju, total_bayar, keterangan,gambar_baju);
                /// } else {
                ///     Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
                //   nn }
            }
        });


        ambilProfilUser();
        txttanggalbooking.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txttanggalbooking:

                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                txttanggalbooking.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
        }
    }




    private void checkData(final String nama_lengkap, final String email, final String nomor_telepon, final String alamat, final String tanggal_pesan, final String jumlah_baju, final String total_bayar, final String keterangan, final String gambar_baju) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Upload Data ...");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {


                        Log.e("Registrasi telah ", jObj.toString());

                        goToPayment();
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                      /*  txtnamalengkap.setText("");
                        txtemail.setText("");*/
                        txttelepon.setText("");
                        txtalamat.setText("");
                        txtjumlahbaju.setText("");
                        jumlahharga.setText("");
                        txtketerangan.setText("");
                        image.setImageResource(0);



                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Upload Gambar data Error " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                ///params.put("id_pendidikan", id_pendidikan);
                params.put("iduser", iduser);
                params.put("nama_lengkap", nama_lengkap);
                params.put("email", email);
                params.put("nomor_telepon", txttelepon.getText().toString());
                params.put("alamat", txtalamat.getText().toString());
                params.put("tanggal_pesan", txttanggalbooking.getText().toString());
                params.put("jumlah_baju", txtjumlahbaju.getText().toString());
                params.put("total_bayar", jumlahharga.getText().toString());
                params.put("keterangan", txtketerangan.getText().toString());
                params.put("gambar_baju", getStringImage(decoded));
                params.put("status", "Belum Lunas");

                return params;
            }

        };

        // Adding request to request queue

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
        ///AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);


    }


    //untuk upload image, compress .JPEG ke bitmap
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    //untuk memilih gambar
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //mengambil fambar dari Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                setToImageView(getResizedBitmap(bitmap, 512));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        image.setImageBitmap(decoded);
    }

    // fungsi resize image
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public void ambilProfilUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getProfilUser(iduser);
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, retrofit2.Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();

                mArrayListUser = new ArrayList<>(Arrays.asList(jsonResponse.getDatauser()));
                String nama_lengkap = mArrayListUser.get(0).getNama_user();
                String email = mArrayListUser.get(0).getEmail_user();


                txtnamalengkap.setText(nama_lengkap);
                txtemail.setText(email);

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

    }

    private void goToPayment(){

        intent = new Intent(FormDataPemesan.this, FormPembayaran.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
