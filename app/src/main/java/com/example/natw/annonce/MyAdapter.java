package com.example.natw.annonce;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.natw.annonce.annonces.Annonce;
import com.example.natw.annonce.propriete.Property;
import com.master.glideimageview.GlideImageView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    private List<Property> proprieteArrayList;

    public MyAdapter(List<Property> proprieteArrayList) {
        this.proprieteArrayList = proprieteArrayList;
    }

    @Override

    public int getItemCount() {
        return proprieteArrayList.size();

    }


    @Override
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_liste_annonce, parent, false);
        return new MyViewHolder(view);

    }


    @Override

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Property propriete = proprieteArrayList.get(position);
        holder.display(propriete);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private final GlideImageView image;
        private final TextView description;
        private Property currentPropriete;

        MyViewHolder(final View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.annonce_image);
            description = itemView.findViewById(R.id.description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Création de la futur Intent avec en argument la propriété sélectionner.
                    Intent myIntent = new Intent(view.getContext(), Annonce.class);
                    Log.i("AWB ", String.valueOf(currentPropriete.getClass()));

                    myIntent.putExtra(Config.PROPERTY, currentPropriete.getId());

                    view.getContext().startActivity(myIntent); //affichage de l'annonce

                }

            });

        }


        void display(Property propriete) {
            //préparation de la page avec situé a droite l'image et a gauche la description de l'image choisie
            currentPropriete = propriete;
            String imageURL = currentPropriete.getImages().get(0).getPath();
            image.loadImageUrl(imageURL);
            description.setText(propriete.getDescription());

        }
    }


}