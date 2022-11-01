package net.kaleoweb.newappcpi.databases;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import net.kaleoweb.newappcpi.dao.SuapDao;
import net.kaleoweb.newappcpi.utilities.Suapdatas;

@Database(entities = {Suapdatas.class}, version = 2, exportSchema = false)
public abstract class SuapDatabase extends RoomDatabase {
    
    public abstract SuapDao suapDao();
    
    final static String DB_NAME = "suap.db";
    
    public static SuapDatabase get(Context context) {
        
        return Room.databaseBuilder(context, SuapDatabase.class, DB_NAME)
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
