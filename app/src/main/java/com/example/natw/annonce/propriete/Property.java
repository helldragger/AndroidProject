package com.example.natw.annonce.propriete;

import com.example.natw.annonce.profile.Seller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>La classe Property </h1>
 * <p>La classe Property est la classe qui correspond a une demeure en vente sur l'application.</p>
 * <p>Les attribut de Property sont : </p>
 *
 * <ul>
 * <li> Son identifiant, de type String (id)</li>
 * <li> Son titre, de type String (title) </li>
 * <li> Sa description, de type String (description) </li>
 * <li> Le nombre de pièce(s), de type int (nbPieces) </li>
 * <li> Ses caractéristiques, de type ArrayList (carateristiques) </li>
 * <li> Son prix, de type int (cost) </li>
 * <li> La ville où elle est située, de type String (city) </li>
 * <li> Son code postal, de type String (postal) </li>
 * <li> Le vendeur du bien, de type Seller (seller) </li>
 * <li> Les images de l'objet vendu, de type ArrayList (images) </li>
 * <li> La date de mise en ligne, de type Date (date) </li>
 * </ul>
 *
 * @author BAUDIN Arthur, MEZZA Nathan
 */
public class Property implements Serializable {

    private long id = -1;
    private String titre = "NO TITLE";
    private String description = "NO DESCRIPTION";
    private int nbPieces = -1;
    private List<Characteristic> characteristics = new ArrayList<>();
    private int prix = -1;
    private String ville = "UNKNOWN CITY";
    private String codePostal = "UNKNOWN CITY CODE";
    private Seller vendeur = new Seller();
    private List<PropertyImage> images = new ArrayList<>();
    private String date = "UNKNOWN DATE";
    private List<Comment> comments = new ArrayList<>();

    public long getId() {
        return id;
    }

    public Property setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitre() {
        return titre;
    }

    public Property setTitre(String titre) {
        this.titre = titre;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Property setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getNbPieces() {
        return nbPieces;
    }

    public Property setNbPieces(int nbPieces) {
        this.nbPieces = nbPieces;
        return this;
    }

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public Property setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
        return this;
    }

    public int getPrix() {
        return prix;
    }


    /////////////////////////////////////////SETTER/////////////////////////////////////////////////

    public Property setPrix(int prix) {
        this.prix = prix;
        return this;
    }

    public String getVille() {
        return ville;
    }

    public Property setVille(String ville) {
        this.ville = ville;
        return this;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public Property setCodePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public Seller getVendeur() {
        return vendeur;
    }

    public Property setVendeur(Seller vendeur) {
        this.vendeur = vendeur;
        return this;
    }

    public List<PropertyImage> getImages() {
        return images;
    }

    public Property setImages(List<PropertyImage> images) {
        this.images = images;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Property setDate(String date) {
        this.date = date;
        return this;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Property setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }
}

