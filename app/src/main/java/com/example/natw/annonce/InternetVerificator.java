package com.example.natw.annonce;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class InternetVerificator {

    private boolean isConnect;
    private String typeOfConnection;

    ///////////////////////////////////////Constructeur/////////////////////////////////////////////
    public InternetVerificator(ConnectivityManager manager) {
        connection(manager);
    }


    ///////////////////////////////////////// Methode //////////////////////////////////////////////
    private void connection(ConnectivityManager manager) {
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {
            //vous êtes connecté(e) au service internet
            this.isConnect = true;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                this.typeOfConnection = "WIFI";
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                this.typeOfConnection = "DATA";
            }
        } else {
            this.isConnect = false;
        }
    }

    /////////////////////////////////////////GETTER/////////////////////////////////////////////////

    public boolean isConnect() {
        return isConnect;
    }

    public String getTypeOfConnection() {
        return typeOfConnection;
    }
}
