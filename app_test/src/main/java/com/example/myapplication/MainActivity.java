package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isLocal = true;
    private String mString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(v -> startActivity(new Intent(this, Main1Activity.class)));
        findViewById(R.id.btn2).setOnClickListener(v -> startActivity(new Intent(this, Main2Activity.class)));
        EditText et_input = findViewById(R.id.et_input);


        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mString = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() == 0) return;
                
                float num = Float.parseFloat(s.toString());
                if (num >= 10) {
                    System.out.println("超出5");
                    return;
                } else if (num <= 2) {
                    System.out.println("提现2");
                } else {
                    System.out.println("满足");
                }
            }
        });

    }

}