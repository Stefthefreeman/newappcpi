package net.kaleoweb.newappcpi.databases;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import net.kaleoweb.newappcpi.dao.GardesDaoModule;
import net.kaleoweb.newappcpi.dao.MaterielDaoModule;
import net.kaleoweb.newappcpi.utilities.Materiel;


@Database(entities = {Materiel.class}, version = 2, exportSchema = false)
public abstract class MaterielDatabase extends RoomDatabase {
    
    public abstract MaterielDaoModule materielDaoModule();
    
    final static String DB_NAME = "materiel.db";
    
    public static MaterielDatabase get(Context context) {
        return Room.databaseBuilder(context, MaterielDatabase.class, DB_NAME)
                .addMigrations(Migrate_from_1_to_2)
                .allowMainThreadQueries()
                .build();
        
    }
    
    static Migration Migrate_from_1_to_2 = new Migration(1, 2) {
        
        
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE User Add Column last_update INTEGER");
        }
    };
}
