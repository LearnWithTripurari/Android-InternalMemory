package com.example.internalmemoryactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText textContents;
    Button saveBtn,getContentBtn;
    final String FILE_NAME = "test.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textContents = findViewById(R.id.textContent);
        saveBtn = findViewById(R.id.saveBtn);
        getContentBtn = findViewById(R.id.getContentBtn);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String contents = textContents.getText().toString();

                FileOutputStream fileOutputStream = null;

                try {
                    fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);

                    fileOutputStream.write(contents.getBytes());

                    textContents.getText().clear();

                    Toast.makeText(MainActivity.this, "File saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        getContentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileInputStream fileInputStream = null;

                try {
                    fileInputStream = openFileInput(FILE_NAME);

                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder stringBuilder = new StringBuilder();

                    String text;

                    while ((text = bufferedReader.readLine()) != null) {

                        stringBuilder.append(text).append("\n");
                    }

                    textContents.setText(stringBuilder.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {

                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}