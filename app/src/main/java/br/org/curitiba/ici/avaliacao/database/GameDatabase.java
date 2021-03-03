package br.org.curitiba.ici.avaliacao.database;


import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.org.curitiba.ici.avaliacao.game.pojo.GameData;

@Database(entities = {GameData.class}, version = 1, exportSchema = false)
public abstract class GameDatabase extends RoomDatabase {
    private static final String TAG = GameDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "gameDatabase";
    private static GameDatabase sInstance;


    public static GameDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        GameDatabase.class, GameDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract GameDataDao gameDataDao();
}
