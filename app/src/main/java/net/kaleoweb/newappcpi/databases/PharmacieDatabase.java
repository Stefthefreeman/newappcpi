package net.kaleoweb.newappcpi.databases;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import net.kaleoweb.newappcpi.dao.PharmacieDaoModule;
import net.kaleoweb.newappcpi.utilities.Pharma;

@Database(entities = {Pharma.class}, version = 2, exportSchema = false)
public abstract class PharmacieDatabase extends RoomDatabase {
    
    public abstract PharmacieDaoModule pharmacieDaoModule();
    
    final static String DB_NAME = "pharmacie.db";
    
    public static PharmacieDatabase get(Context context) {
        return Room.databaseBuilder(context, PharmacieDatabase.class, DB_NAME)
                .addMigrations(Migrate_from_1_to_2)
                .allowMainThreadQueries()
                .build();
        
    }
    
    static Migration Migrate_from_1_to_2 = new Migration(1, 2) {
        
        
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE pharmacie Add Column last_update INTEGER");
        }
    };
    
}
