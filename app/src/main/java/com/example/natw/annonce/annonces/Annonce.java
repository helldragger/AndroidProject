package com.example.natw.annonce.annonces;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.natw.annonce.Config;
import com.example.natw.annonce.R;
import com.example.natw.annonce.ReponseGet;
import com.example.natw.annonce.profile.Seller;
import com.example.natw.annonce.propriete.Property;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Annonce extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce);

        Property annonce = Config.getCurrentProperty(getIntent());
        setter(annonce);

    }


    private void affichage() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request myGetRequest = new Request.Builder()
                .url("https://ensweb.users.info.unicaen.fr/android-estate/mock-api/liste.json")
                .build();

        okHttpClient.newCall(myGetRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Snackbar.make(findViewById(R.id.scroll), " Ca marche.  ", Snackbar.LENGTH_LONG).show();
                final String text = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Snackbar.make(findViewById(R.id.scroll), " Ca Run  ", Snackbar.LENGTH_LONG).show();
                        TextView des = findViewById(R.id.description);
                        des.setText(text);
                        try {
                            updateUI(text);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void updateUI(String text) throws IOException {


        Moshi moshi = new Moshi.Builder().build();

        JsonAdapter<ReponseGet> jsonAdapter = moshi.adapter(ReponseGet.class);
        ReponseGet house = jsonAdapter.fromJson(text);

        List<Property> listAnnonce = new ArrayList<>(house.response);

        setter(listAnnonce.get(0));
    }


    public void setter(Property annonce) {

        Seller seller = annonce.getVendeur();
        ((TextView) findViewById(R.id.title)).setText(annonce.getTitre());
        ((TextView) findViewById(R.id.piece)).setText(annonce.getNbPieces());
        ((TextView) findViewById(R.id.price)).setText(annonce.getPrix());
        ((TextView) findViewById(R.id.description)).setText(annonce.getDescription());
        ((TextView) findViewById(R.id.date)).setText(annonce.getDate());
        ((TextView) findViewById(R.id.seller)).setText(seller.getName());
        ((TextView) findViewById(R.id.phone)).setText(seller.getPhone());
        ((TextView) findViewById(R.id.email)).setText(seller.getEmail());

        String imageURL = annonce.getImages().get(0).getPath();
        Glide.with(this).load(imageURL).into((ImageView) findViewById(R.id.annonce_image));

    }

}
