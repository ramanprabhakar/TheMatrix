package com.ramanprabhakar.thematrix;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etNumberInput;
    TextView bStart;
    int inputNumber;
    String check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etNumberInput = (EditText) findViewById(R.id.et_input);
        bStart = (TextView) findViewById(R.id.button_start);

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    inputNumber = Integer.parseInt(etNumberInput.getText().toString());

                    if (inputNumber >= 1 && inputNumber <= 5) {
                        Intent intent = new Intent(MainActivity.this, MatrixActivity.class);
                        intent.putExtra("INPUT", inputNumber);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Please enter a number between 1 and 5", Toast.LENGTH_SHORT).show();
                        etNumberInput.setText("");
                    }
                }catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Please enter a number between 1 and 5", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });


    }
}
