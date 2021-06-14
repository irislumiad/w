package com.example.w;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QrActivity extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 스캔구역
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "스캔이 안되었다.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "스캔: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

        String str = result.getContents();
        //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 값 넘어가는 곳


        //String address = str.substring(0,6);
        /*
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("101", address);
        startActivity(intent);
        */
        //----------------- SharePreferences 사용 (내부 저장소에 값 저장)----------------
        sp = getSharedPreferences("temp", MODE_PRIVATE); // 선언

        editor = sp.edit(); // editor에 put 하기
        editor.putString("address", str.substring(0, 7)); // RoomNumber라는 key값에 데이터를 저장한다.
        editor.putString("roomNumber", str.substring(9, 12));
        editor.putString("deposit", str.substring(14, 18));
        editor.putString("rent", str.substring(20, 22));
        editor.putString("cost", str.substring(24, 26));



        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //------------------------------------------------------------------------------

        /*----------------- SharePreferences 데이터 삭제 --------------------------------
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("first");  //값 삭제
        editor.remove("Second");
        editor.clear();   //모든 값 삭제
        editor.commit();
        출처: https://kylblog.tistory.com/7 [ylblog]
        --------------------------------------------------------------------------------*/
        /* ------------------- 인턴트 값 넘기기 ------------------------------------------
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("101", sharedPreferences.getString("RoomNumber","none"));
        startActivity(intent);
        -------------------------------------------------------------------------------*/

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(false);
        integrator.setPrompt("등록을 위해 상자 안에 위치시켜주세요");
        integrator.initiateScan();


    }
}