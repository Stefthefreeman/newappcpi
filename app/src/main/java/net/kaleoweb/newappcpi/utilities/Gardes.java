package net.kaleoweb.newappcpi.utilities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "gardes")
public class Gardes {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "Chef")
    private String chef;
    @ColumnInfo(name = "conducteur")
    private String conducteur;
    @ColumnInfo(name="equipier1")
    private String equipier1;
    @ColumnInfo(name="equipier2")
    private String equipier2;
    @ColumnInfo(name="consignes")
    private String consignes;
    @ColumnInfo(name="presence")
    private int presence;
    @ColumnInfo(name="datesql")
    private String dtsql;
    
    public Gardes(String date, String chef, String conducteur, String equipier1, String equipier2, String consignes,int presence, String dtsql) {
        setDate(date);
        setChef(chef);
        setConducteur(conducteur);
        setEquipier1(equipier1);
        setEquipier2(equipier2);
        setConsignes(consignes);
        setPresence(presence);
        setDtsql(dtsql);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }

    public String getConducteur() {
        return conducteur;
    }

    public void setConducteur(String conducteur) {
        this.conducteur = conducteur;
    }

    public String getEquipier1() {
        return equipier1;
    }

    public void setEquipier1(String equipier1) {
        this.equipier1 = equipier1;
    }

    public String getEquipier2() {
        return equipier2;
    }

    public void setEquipier2(String equipier2) {
        this.equipier2 = equipier2;
    }

    public String getConsignes() {
        return consignes;
    }

    public void setConsignes(String consignes) {
        this.consignes = consignes;
    }
    
    public int getPresence() {
        return presence;
    }
    
    public void setPresence(int presence) {
        this.presence = presence;
    }
    
    public String getDtsql() {
        return dtsql;
    }
    
    public void setDtsql(String dtsql) {
        this.dtsql = dtsql;
    }
    
    @NonNull
    @Override
    public String toString() {
        return "Gardes{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", chef='" + chef + '\'' +
                ", conducteur='" + conducteur + '\'' +
                ", equipier1='" + equipier1 + '\'' +
                ", equipier2='" + equipier2 + '\'' +
                ", consignes='" + consignes + '\'' +
                ", presence=" + presence +
                '}';
    }
}
