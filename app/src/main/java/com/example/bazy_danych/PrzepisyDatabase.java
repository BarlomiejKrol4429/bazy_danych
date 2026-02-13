package com.example.bazy_danych;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Wypiek.class}, version = 1)
public abstract class PrzepisyDatabase extends RoomDatabase {
    public abstract WypiekiDao zwrocWypiekiDao();

    private static PrzepisyDatabase instancja;

    public static PrzepisyDatabase zwrocInstancjeBazyDanych(Context context){
        if(instancja == null){
            instancja = Room.databaseBuilder(
                    context, PrzepisyDatabase.class, "przepisy_db"
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instancja;
    }
}
