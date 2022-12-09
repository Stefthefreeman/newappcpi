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
    
    @ColumnInfo(name="dtsql")
    private String dtsql;
    
    public Dispos(String dt, String dtsql) {
        this.dt = dt;
        this.dtsql = dtsql;
    }
    
    public String getDtsql() {
        return dtsql;
    }
    
    public void setDtsql(String dtsql) {
        this.dtsql = dtsql;
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
