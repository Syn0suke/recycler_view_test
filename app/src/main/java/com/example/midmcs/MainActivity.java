package com.example.midmcs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper db;
    ArrayList<String> item_id, item_name, item_desc, item_quantity;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyler_v);
        db = new DatabaseHelper(MainActivity.this);
        item_id = new ArrayList<>();
        item_name = new ArrayList<>();
        item_desc = new ArrayList<>();
        item_quantity = new ArrayList<>();

        display();
        customAdapter =  new CustomAdapter(MainActivity.this, this, item_id,  item_name, item_quantity, item_desc);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            recreate();
        }
    }

    void display() {
        Cursor cursor = db.readAllData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this,"No Data Found",Toast.LENGTH_SHORT).show();
        }else
            {
            while (cursor.moveToNext())
            {
                item_id.add(cursor.getString(0));
                item_name.add(cursor.getString(1));
                item_quantity.add(cursor.getString(2));
                item_desc.add(cursor.getString(3));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        if(item.getItemId()==R.id.add_item)
        {
            intent = new Intent(MainActivity.this, Add.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
