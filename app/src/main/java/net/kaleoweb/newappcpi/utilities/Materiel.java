package net.kaleoweb.newappcpi.utilities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "materiel")
public class Materiel {
    
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;
    @ColumnInfo(name = "qte")
    private int qte;
    @ColumnInfo(name="nom")
    private String nom;
    @ColumnInfo(name="localisation")
    private int localisation;
    
    public Materiel(int qte, String nom, int localisation) {
        this.qte = qte;
        this.nom = nom;
        this.localisation = localisation;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getQte() {
        return qte;
    }
    
    public void setQte(int qte) {
        this.qte = qte;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public int getLocalisation() {
        return localisation;
    }
    
    public void setLocalisation(int localisation) {
        this.localisation = localisation;
    }
    
    @Override
    public String toString() {
        return "Materiel{" +
                "id=" + id +
                ", qte=" + qte +
                ", nom='" + nom + '\'' +
                ", localisation=" + localisation +
                '}';
    }
}
