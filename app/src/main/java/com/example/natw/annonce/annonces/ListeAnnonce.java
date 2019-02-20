package com.example.natw.annonce.annonces;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.natw.annonce.MyAdapter;
import com.example.natw.annonce.R;
import com.example.natw.annonce.ReponseGet;
import com.example.natw.annonce.propriete.Property;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.annotations.EverythingIsNonNull;

public class ListeAnnonce extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_annonce);

        setTitle("Liste des Annonces");
        getJson();
    }

    public void getJson() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request myGetRequest = new Request.Builder()
                .url("https://ensweb.users.info.unicaen.fr/android-estate/mock-api/liste.json")
                .build();
        Callback call = new Callback() {

            @Override
            @EverythingIsNonNull
            public void onFailure(Call call, IOException e) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String text = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            updateUI(text);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        };
        okHttpClient.newCall(myGetRequest).enqueue(call);

    }

    public void updateUI(String text) throws IOException {

        final RecyclerView rv = findViewById(R.id.annonce_list);

        rv.setLayoutManager(new LinearLayoutManager(this));


        Moshi moshi = new Moshi.Builder().build();

        JsonAdapter<ReponseGet> jsonAdapter = moshi.adapter(ReponseGet.class);
        ReponseGet house = jsonAdapter.fromJson(text);

        ArrayList<Property> proprieteArrayList = new ArrayList<>(house.response);
        rv.setAdapter(new MyAdapter(proprieteArrayList));
    }
}
