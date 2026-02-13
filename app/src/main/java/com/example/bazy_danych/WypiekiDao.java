package com.example.bazy_danych;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WypiekiDao {
    @Insert
    public void wstawWypiekDoBazy(Wypiek wypiek);
    @Insert
    public void wstawKilkaWypiekow(Wypiek ...wypieks);
    @Delete
    public void usunZBazy(Wypiek wypiek);
    @Update
    public void zaktualizuj(Wypiek wypiek);
    @Query("SELECT * FROM wypieki_tabela")
    List<Wypiek> zwrocWszytkieWypiekiZBazy();
    @Query("SELECT nazwa FROM wypieki_tabela WHERE czas_pieczenia < :czas")
    List<String> zwrocNazwyWypiekow(int czas);
}
