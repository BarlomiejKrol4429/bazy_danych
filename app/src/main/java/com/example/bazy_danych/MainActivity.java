package com.example.bazy_danych;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    PrzepisyDatabase przepisyDatabase;
    Wypiek modyfikowanyWypiek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        przepisyDatabase = PrzepisyDatabase.zwrocInstancjeBazyDanych(MainActivity.this);
        //przepisyDatabase.zwrocWypiekiDao().wstawWypiekDoBazy(new Wypiek("Sernik", "Ser, Ziemniaki, Cukier, Jajka", 170, 120));
        //przepisyDatabase.zwrocWypiekiDao().wstawWypiekDoBazy(new Wypiek("Drożdżówka", "Ser, Drożdże, Mąka, Cukier, Jajka", 170, 15));
        //przepisyDatabase.zwrocWypiekiDao().wstawWypiekDoBazy(new Wypiek("Szarlotka", "Jabłka, Mąka, Cukier, Jajka", 170, 60));

        Button dodaj = findViewById(R.id.dodaj);
        Button edytuj = findViewById(R.id.edytuj);
        EditText nazwa = findViewById(R.id.nazwa);
        EditText skladniki = findViewById(R.id.skladniki);
        EditText temperatura = findViewById(R.id.temperatura);
        EditText czas = findViewById(R.id.czas);
        ListView listView = findViewById(R.id.przepisy);
        List<Wypiek> wszystkieWypiekiList = przepisyDatabase.zwrocWypiekiDao().zwrocWszytkieWypiekiZBazy();
        ArrayAdapter<Wypiek> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, wszystkieWypiekiList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        przepisyDatabase.zwrocWypiekiDao().usunZBazy(wszystkieWypiekiList.get(i));
                        wszystkieWypiekiList.remove(i);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
        );
        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        modyfikowanyWypiek = wszystkieWypiekiList.get(i);
                        String nazwai = modyfikowanyWypiek.getNazwa();
                        String skladnikii = modyfikowanyWypiek.getSkladniki();
                        int temperaturai = modyfikowanyWypiek.getTemperaturaPieczenia();
                        int czasi = modyfikowanyWypiek.getCzasPieczenia();

                        nazwa.setText(nazwai);
                        skladniki.setText(skladnikii);
                        temperatura.setText(String.valueOf(temperaturai));
                        czas.setText(String.valueOf(czasi));
                        edytuj.setEnabled(true);
                        return false;
                    }
                }
        );

    }
}