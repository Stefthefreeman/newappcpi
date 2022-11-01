package net.kaleoweb.newappcpi.utilities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String nom;

    public String prenom;

    public String centre;

    public String grade;



    public User() { }



}
