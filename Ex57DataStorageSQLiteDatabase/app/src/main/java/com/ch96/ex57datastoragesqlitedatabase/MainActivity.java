package com.ch96.ex57datastoragesqlitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge, etEmail;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        etEmail = findViewById(R.id.et_email);

        findViewById(R.id.btn_insert).setOnClickListener(v -> clickInsert());
        findViewById(R.id.btn_update).setOnClickListener(v -> clickUpdate());
        findViewById(R.id.btn_delete).setOnClickListener(v -> clickDelete());
        findViewById(R.id.btn_select_all).setOnClickListener(v -> clickSelectAll());
        findViewById(R.id.btn_select_by_name).setOnClickListener(v -> clickSelectByName());

        //onCreate하면서 test.db라는 이름으로 데이터베이스 파일 열거나 또는 생성 (없으면 만들고 있으면 열기)
        //액티비티클래스에 이미 이 기능이 존재
        //이 메소드를 실행하면 text.db를 제어할 수 있는 능력을 가진 객체(SQLiteDatabase)가 리턴됨
        //여기서 참조변수를 만들면 onCreate안에서만 쓸 수 있기때문에 멤버변수 위치에 만들기
        db = openOrCreateDatabase("test.db", MODE_PRIVATE, null); //MODE_PRIVATE만 가능

        //만들어진 또는 열려진 DB파일 안에 테이블(표)을 생성하는 작업 수행
        //SQL언어를 이용해서 원하는 명령어를 Database에 수행
        db.execSQL("CREATE TABLE IF NOT EXISTS member(num INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20) NOT NULL, age INTEGER, email TEXT)");
        //IF NOT EXISTS 존재하지않으면 만들어라 (있을때는 만들지마라)
        //variablechar VARCHAR 최대값 정해져있고 쓴글자만큼만 메모리 먹음
        //num은 중복되지않는 주요 식별자 --> SQL PRIMARY KEY 꼭 하나 있어야 함
        //AUTOINCREMENT 자동으로 증가 (INTEGER만 가능) --> 자동으로 증가하는 애들이 프라이머리키가 됨
        //NOT NULL 이 곳에 값을 주지 않으면 저장이 되지 않음

    }

    void clickInsert() {
        String name = etName.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        String email = etEmail.getText().toString();

        //member라는 이름의 테이블(표)에 값을 삽입하는 쿼리문(SQL)
        //record (한 줄) 단위로 값 넣어야함
        db.execSQL("INSERT INTO member(name, age, email) VALUES('"+name+"','"+age+"','"+email+"')");
        //num은 자동증가를 했기때문에 두번째 칸부터 값 넣어야함 --> 넣을 칸 (name, age, email) 명시
        //이미 큰따옴표가 있기때문에 큰따옴표 안에서 작은따옴표 사용 (문자열은 반드시, 정수형,boolean 해도되고 안해도 됨)
    }

    void clickUpdate() {
        //업데이트할 데이터의 이름값
        String name = etName.getText().toString();
        db.execSQL("UPDATE member SET age=30 WHERE name=?", new String[]{name});
        //결합연산자 대신에 배열(값이 둘 이상일 수 있기 때문)을 하나 더 줌 --> 물음표에 name 들어감
    }

    void clickDelete() {
        String name = etName.getText().toString();
        db.execSQL("DELETE FROM member WHERE name=?", new String[]{name});
    }

    void clickSelectAll() {
        //member 테이블의 모든 데이터들을 검색하여 가져오기
        //execSQL은 리턴값 void, 값을 받아야하기때문에 rawQuery 사용
        Cursor cursor = db.rawQuery("SELECT * FROM member", null);
        //WHERE구문이 없으면 모든 줄을 말함 (칸이 아님, 모든 칸은 SELECT 이후 항목 --> 모든 '칸(항목)'을 *로 줄여서 사용 가능)
        //select의 명령 조건을 통해 Cursor가 새로 생김 그리고 그걸 읽는것!!!
        if (cursor == null) return; //null이라는 것은 조건에 맞는 값이 없는 것이 아니라 명령어가 잘못된것, 조건에 맞는 값이 없어도 Cursor는 만들어짐
        int cnt = cursor.getCount(); //총 줄(row:레코드) 수
        cursor.moveToFirst(); //첫 레코드(줄)로 이동

        StringBuffer buffer = new StringBuffer(); //원래는 ArrayList
        for(int i = 0; i < cnt; i++){
            int num = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String email = cursor.getString(3);

            buffer.append(num+" "+name+" "+age+" "+email+"\n");
            cursor.moveToNext(); //다음 레코드로 이동
        }

        //화면에 결과 보여주기
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(buffer.toString());
        builder.create().show();
    }

    void clickSelectByName() {

        String name = etName.getText().toString();
        Cursor cursor = db.rawQuery("SELECT name, age FROM member WHERE name=?", new String[]{name});
        if (cursor == null) return;

        int cnt = cursor.getCount();
        cursor.moveToFirst();

        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < cnt; i++){
            String nameB = cursor.getString(0);
            int age = cursor.getInt(1);

            buffer.append(nameB+" : "+age+"\n");
            cursor.moveToNext();
        }

        new AlertDialog.Builder(this).setMessage(buffer.toString()).create().show();

    }

}