package com.example.natw.annonce;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.natw.annonce.annonces.Annonce;
import com.example.natw.annonce.annonces.ListeAnnonce;
import com.example.natw.annonce.profile.Login;
import com.example.natw.annonce.profile.Profil;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyConnection();
    }


    public void randomAnnonce(View view) {
        Intent myIntent = new Intent(this, Annonce.class);
        startActivity(myIntent);
    }

    public void profil(View view) {
        if (Config.getCurrentUser(getIntent()) == -1) {
            login(view);
        } else {
            //Si il possède un compte alors on affiche le profil de l'utilisateur
            Intent myIntent = new Intent(this, Profil.class);
            startActivity(myIntent);
        }
    }

    public void deposit(View view) {
        Snackbar.make(view, "Operation non implémentée pour le moment", Snackbar.LENGTH_LONG).show();
    }

    //methode de test a renommer
    public void annonce_list(View view) {
        Intent myIntent = new Intent(this, ListeAnnonce.class);
        startActivity(myIntent);
    }

    public void login(View view) {
        Intent myIntent = new Intent(this, Login.class);
        startActivity(myIntent);
    }

    //Vérifie si on est connecter a internet par data ou par Wifi. A rajouter un sharedPreference afin de bloquer l'acces au onglet avec internet si indisponible
    public void verifyConnection() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        InternetVerificator connection = new InternetVerificator(manager);
        View view = getCurrentFocus();
        if (view == null)
            return;
        if (connection.isConnect()) {

            Snackbar.make(view, " Vous êtes connecté au réseau via  " + connection.getTypeOfConnection(), Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(view, " Vous n'êtes pas connecté au réseau  ", Snackbar.LENGTH_LONG).show();
        }
    }
}