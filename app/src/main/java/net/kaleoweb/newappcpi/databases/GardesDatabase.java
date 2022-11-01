package net.kaleoweb.newappcpi.databases;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import net.kaleoweb.newappcpi.dao.GardesDaoModule;
import net.kaleoweb.newappcpi.utilities.Gardes;


@Database(entities = {Gardes.class}, version = 3, exportSchema = false)
public abstract class GardesDatabase extends RoomDatabase {
    public abstract GardesDaoModule gardesDaoModule();
    
    final static String DB_NAME = "gardes.db";
    private static GardesDatabase INSTANCE;
    
    public static GardesDatabase getInstance(Context context) {
        
        return Room.databaseBuilder(context, GardesDatabase.class, DB_NAME)
                .addMigrations(Migrate_from_1_to_2)
                .allowMainThreadQueries()
                .build();
    }
    
    static Migration Migrate_from_1_to_2 = new Migration(2, 3) {
        
        
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE gardes Add Column datesql TEXT");
        }
    };
    
}
