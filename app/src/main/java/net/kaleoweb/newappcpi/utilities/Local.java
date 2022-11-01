package net.kaleoweb.newappcpi.utilities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "localisation")
public class Local {
    
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;
    
    @ColumnInfo(name="locid")
    private int locid;
    
    @ColumnInfo(name="name")
    private String thename;
    
    public Local(int locid, String thename) {
        this.locid = locid;
        this.thename = thename;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getLocid() {
        return locid;
    }
    
    public void setLocid(int locid) {
        this.locid = locid;
    }
    
    public String getThename() {
        return thename;
    }
    
    public void setThename(String thename) {
        this.thename = thename;
    }
    
    @Override
    public String toString() {
        return "Local{" +
                "id=" + id +
                ", locid=" + locid +
                ", thename='" + thename + '\'' +
                '}';
    }
}
