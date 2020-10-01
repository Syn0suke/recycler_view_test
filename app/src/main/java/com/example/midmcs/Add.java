package com.example.midmcs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add extends AppCompatActivity {

    EditText nameInput,quantityInput,descInput;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameInput = findViewById(R.id.name_input);
        quantityInput = findViewById(R.id.quantity_input);
        descInput = findViewById(R.id.desc_input);
        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbh = new DatabaseHelper(Add.this);
                dbh.addItem(nameInput.getText().toString().trim(),
                        Integer.parseInt(quantityInput.getText().toString().trim()),
                        descInput.getText().toString().trim());
            }
        });
    }
}
