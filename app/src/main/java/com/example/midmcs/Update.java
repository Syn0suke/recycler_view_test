package com.example.midmcs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    EditText name_update, desc_update, quantity_update;
    Button updateButton;
    String id,name,desc,quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_update = findViewById(R.id.name_update);
        desc_update = findViewById(R.id.desc_update);
        quantity_update = findViewById(R.id.quantity_update);
        updateButton = findViewById(R.id.update_button);
        getSetIntentData();
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(Update.this);
                name = name_update.getText().toString().trim();
                desc = desc_update.getText().toString().trim();
                quantity = quantity_update.getText().toString().trim();
                db.updateData(id,name,desc,quantity);
            }
        });
    }

    void getSetIntentData()
    {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("desc") && getIntent().hasExtra("quantity"))
        {
            //get
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            desc = getIntent().getStringExtra("desc");
            quantity = getIntent().getStringExtra("quantity");
            //set
            name_update.setText(name);
            desc_update.setText(desc);
            quantity_update.setText(quantity);

        }else
        {
            Toast.makeText(this,"No Data Found",Toast.LENGTH_SHORT).show();
        }
    }
}
