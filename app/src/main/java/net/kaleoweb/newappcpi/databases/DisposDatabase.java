package net.kaleoweb.newappcpi.databases;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import net.kaleoweb.newappcpi.dao.DisposDao;
import net.kaleoweb.newappcpi.utilities.Dispos;


@Database(entities = {Dispos.class}, version = 2, exportSchema = false)
public abstract class DisposDatabase extends RoomDatabase {
    
    public abstract DisposDao disposDao();
    
    final static String DB_NAME = "dispos.db";
    
    public static DisposDatabase get(Context context) {
        
        return Room.databaseBuilder(context, DisposDatabase.class, DB_NAME)
                .addMigrations(Migrate_from_1_to_2)
                .allowMainThreadQueries()
                .build();
    }
    
    
    static Migration Migrate_from_1_to_2 = new Migration(1, 2) {
        
        
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE dispos Add Column last_update INTEGER");
        }
    };
}