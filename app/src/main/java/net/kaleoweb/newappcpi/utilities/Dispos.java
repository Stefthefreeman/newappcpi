package net.kaleoweb.newappcpi.utilities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dispos")
public class Dispos

{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;
    
    @ColumnInfo(name="dt")
    private String dt;
    
    public Dispos(String dt) {
        this.dt = dt;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getDt() {
        return dt;
    }
    
    public void setDt(String dt) {
        this.dt = dt;
    }
}
