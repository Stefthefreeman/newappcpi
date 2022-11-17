package net.kaleoweb.newappcpi.utilities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pharmacie")
public class Pharma {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;
    
    @ColumnInfo(name="cat_id")
    private int cat_id;
    
    @ColumnInfo(name="description")
    private String description;
    
    @ColumnInfo(name="dotation")
    private int dotation;
    
    @ColumnInfo(name="restant")
    private int restant;
    
    @ColumnInfo(name="peremption")
    private String peremption;
    
    @ColumnInfo(name="bg")
    private int bg;
    
    @ColumnInfo(name="web_id")
    private int webid;
    
    public Pharma(int cat_id,String description,int dotation,int restant,String peremption,int bg,int webid) {
     setId(id);
     setCat_id(cat_id);
     setDescription(description);
     setDotation(dotation);
     setRestant(restant);
     setPeremption(peremption);
     setBg(bg);
     setWebid(webid);
    }
    
    public int getWebid() {
        return webid;
    }
    
    public void setWebid(int webid) {
        this.webid = webid;
    }
    
    public int getBg() {
        return bg;
    }
    
    public void setBg(int bg) {
        this.bg = bg;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getCat_id() {
        return cat_id;
    }
    
    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getDotation() {
        return dotation;
    }
    
    public void setDotation(int dotation) {
        this.dotation = dotation;
    }
    
    public int getRestant() {
        return restant;
    }
    
    public void setRestant(int restant) {
        this.restant = restant;
    }
    
    public String getPeremption() {
        return peremption;
    }
    
    public void setPeremption(String peremption) {
        this.peremption = peremption;
    }
    
    @Override
    public String toString() {
        return "Pharma{" +
                "id=" + id +
                ", cat_id=" + cat_id +
                ", description='" + description + '\'' +
                ", dotation=" + dotation +
                ", restant=" + restant +
                ", peremption='" + peremption + '\'' +
                ", bg=" + bg +
                '}';
    }
}
