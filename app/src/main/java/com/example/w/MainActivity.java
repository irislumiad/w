package com.example.w;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    ImageButton image_btn; // 이미지뷰 버튼
    TextView tv_test;
    SharedPreferences sp;
    String roomNumber;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image_btn = findViewById(R.id.image_btn); // 이미지 버튼 연결
        image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QrActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Qr모드 입니다.", Toast.LENGTH_SHORT).show(); // 토스트 메시지를 띄운다.
            }
        });

        //------------ putextra 값 받아오는 방법
        Intent testIntent = getIntent();
        String message = testIntent.getStringExtra("name");



        //----------------- SharedPreferences 데이터 호출 / 값받아오기 ----------------------


        sp = getSharedPreferences("temp51", MODE_PRIVATE);
        roomNumber = sp.getString("roomNumber", null); // 해당 값을 불러오는 것, 해당값이 없을 경우 null을 호출한다.

        Toast.makeText(MainActivity.this, roomNumber, Toast.LENGTH_SHORT).show(); // 토스트 메시지를 띄운다.


        tv_test = findViewById(R.id.tv_test);
        String ex = "temp51";

        if (roomNumber.equals("1051")) {
            tv_test.setText(roomNumber);

            // 다이알로그 팝업 연결하기
            // ++ 각 호수별 class 만들어서 메소드 호출하기 

        } else {
            SharedPreferences sp2 = getSharedPreferences("temp51",MODE_PRIVATE);
            tv_test.setText("값이없음.");

            tv_test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent2 = new Intent(MainActivity.this, QrActivity.class);
                    startActivity(intent2);

                }
            });

        }

    }
}