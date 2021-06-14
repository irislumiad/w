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

public class MainActivity extends AppCompatActivity {

    ImageButton image_btn; // 이미지뷰 버튼
    TextView tv_test;
    SharedPreferences sp;
    String roomNumber;


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

        //----------------- SharedPreferences 데이터 호출 / 값받아오기 ----------------------
        sp = getSharedPreferences("temp",MODE_PRIVATE);

        roomNumber = sp.getString("RoomNumber", null); // 해당 값을 불러오는 것, 해당값이 없을 경우 null을 호출한다.
        System.out.println("데이터 저장값"+roomNumber);



        /*------------ putextra 값 받아오는 방법
        Intent testIntent = getIntent();
        String message = testIntent.getStringExtra("101");
        */
        tv_test = findViewById(R.id.tv_test);
        tv_test.setText(roomNumber);

    }
}