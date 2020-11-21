package id.alin_gotama.notescine.Room.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import id.alin_gotama.notescine.Room.Dao.MyDao;
import id.alin_gotama.notescine.Room.Entity.Note;
import id.alin_gotama.notescine.Room.DatabaseView.dbview;

@Database(entities = {Note.class},views = {dbview.class},version = 2,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract MyDao noteDao();

    private static MyDatabase INSTANCE;

    public static MyDatabase createDatabase(Context context){
        synchronized(MyDatabase.class){
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context,MyDatabase.class,"db_class")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }
}

