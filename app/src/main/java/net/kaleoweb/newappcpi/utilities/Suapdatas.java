package net.kaleoweb.newappcpi.utilities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "suap")
public class Suapdatas {
    
  
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    public int id;
    
    @ColumnInfo(name = "nom")
    public String nom;
    
    @ColumnInfo(name = "prenom")
    public String prenom;
    
    @ColumnInfo(name = "numinter")
    public String numinter;
    
    @ColumnInfo(name = "diastole")
    public String diastole;
    
    @ColumnInfo(name = "systole")
    public String systole;
    
    @ColumnInfo(name = "pouls")
    public String pouls;
    
    @ColumnInfo(name = "freqrespi")
    public String freqrespi;
    
    @ColumnInfo(name = "glycemie")
    public String glycemie;
    
    @ColumnInfo(name = "temperature")
    public String temperature;
    
  public Suapdatas() {}
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getNuminter() {
        return numinter;
    }
    
    public void setNuminter(String numinter) {
        this.numinter = numinter;
    }
    
    public String getDiastole() {
        return diastole;
    }
    
    public void setDiastole(String diastole) {
        this.diastole = diastole;
    }
    
    public String getSystole() {
        return systole;
    }
    
    public void setSystole(String systole) {
        this.systole = systole;
    }
    
    public String getPouls() {
        return pouls;
    }
    
    public void setPouls(String pouls) {
        this.pouls = pouls;
    }
    
    public String getFreqrespi() {
        return freqrespi;
    }
    
    public void setFreqrespi(String freqrespi) {
        this.freqrespi = freqrespi;
    }
    
    public String getGlycemie() {
        return glycemie;
    }
    
    public void setGlycemie(String glycemie) {
        this.glycemie = glycemie;
    }
    
    public String getTemperature() {
        return temperature;
    }
    
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
