package com.example.natw.annonce.profile;

import java.io.Serializable;

/**
 * <h1>La classe Seller </h1>
 * <p>La classe Seller est la classe qui correspond a un vendeur sur l'application.</p>
 * <p>Les attribut de Seller sont : </p>
 *
 * <ul>
 * <li> Son identifiant, de type String (id)</li>
 * <li> Son nom, de type String (name) </li>
 * <li> Son prenom, de type String (firstName) </li>
 * <li> Son email, de type String (email) </li>
 * <li> Son numéro de téléphone, de type String (phone) </li>
 * </ul>
 *
 * @author BAUDIN Arthur, MEZZA Nathan
 */
public class Seller implements Serializable {

    private long id = -1;
    private String nom = "UNKNOWN NAME";
    private String prenom = "UNKNOWN SURNAME";
    private String email = "UNKNOWN EMAIL";
    private String telephone = "UNKNOWN PHONE";


    /////////////////////////////////////////GETTER/////////////////////////////////////////////////
    public long getId() {
        return id;
    }

    /////////////////////////////////////////SETTER/////////////////////////////////////////////////
    public Seller setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return nom;
    }

    public Seller setName(String name) {
        this.nom = name;
        return this;
    }

    public String getFirstName() {
        return prenom;
    }

    public Seller setFirstName(String firstName) {
        this.prenom = firstName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Seller setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return telephone;
    }

    public Seller setPhone(String phone) {
        this.telephone = phone;
        return this;
    }
}

