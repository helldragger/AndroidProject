package com.example.natw.annonce;

import android.support.v7.app.AppCompatActivity;

import static com.example.natw.annonce.Config.PROPERTY;
import static com.example.natw.annonce.Config.USER;


public class MetaActivity extends AppCompatActivity {


    public long getCurrentProperty() {
        long annonce = -1;
        if (getIntent().getSerializableExtra(PROPERTY) != null) {
            annonce = (long) getIntent().getSerializableExtra(PROPERTY);
        }
        return annonce;
    }

    public void setCurrentProperty(long id) {
        getIntent().putExtra(PROPERTY, id);
    }

    public long getCurrentUser() {
        long user = -1;
        if (getIntent().getSerializableExtra(USER) != null) {
            user = (long) getIntent().getSerializableExtra(USER);
        }
        return user;
    }

    public void setCurrentUser(long id) {
        getIntent().putExtra(USER, id);
    }

}
