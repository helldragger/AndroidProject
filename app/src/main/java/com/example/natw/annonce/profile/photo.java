package com.example.natw.annonce.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.natw.annonce.R;

import java.util.ArrayList;
import java.util.List;

import in.goodiebag.carouselpicker.CarouselPicker;

public class photo extends AppCompatActivity {

    CarouselPicker carouselPicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo2);

        carouselPicker = findViewById(R.id.carousel);

        List<CarouselPicker.PickerItem> itemsImages = new ArrayList<>();
        //itemsImages.add(new CarouselPicker.DrawableItem("drawable/maison1"));
        //itemsImages.add(new CarouselPicker.DrawableItem(R.mipmap.ic_launcher_round));
        //itemsImages.add(new CarouselPicker.DrawableItem(R.mipmap.ic_launcher));
        CarouselPicker.CarouselViewAdapter imageAdapter = new CarouselPicker.CarouselViewAdapter(this, itemsImages, 0);
        carouselPicker.setAdapter(imageAdapter);
    }


}
