package com.ch96.ex14toastanddialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn01;
    Button btn02;

    //다이얼로그가 보여줄 목록의 항목들
    String[] items = new String[]{"Apple", "Banana", "Orange"};
    boolean[] checkedItems = new boolean[]{true, false, true};
    //boolean의 갯수는 item의 갯수와 동일해야함
    int selectedItemPosition = 0;

    TextView dialogTv;
    ImageView dialogIv;
    Button dialogBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn01 = findViewById(R.id.btn01);
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast 객체 생성
                //static method : 객체를 생성하지 않고 사용가능
                //익명 class (inner class) this라고 쓸 경우 익명클래스를 지칭
                //MainActivity.this라고 써주어야 함
                Toast t = Toast.makeText(MainActivity.this, "TOAST IS DONE!", Toast.LENGTH_SHORT);
                t.show();
            }
        });

        btn02 = findViewById(R.id.btn02);
        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AlertDialog를 만들어주는 건축가 객체(Builder) 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //건축가에게 원하는 모양을 의뢰(설정)
                builder.setTitle("DIALOG");
                builder.setIcon(R.drawable.baseline_crisis_alert_24);

                //1. 단순 메세지 1개 보여줄때
                //builder.setMessage("Do you wanna QUIT?");

                //2. 목록으로 항목들 보여줄때 (List로 만들때는 Positive / Negative X)
//                builder.setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //두 번째 파라미터 i = 클릭된 항목의 위치 (인덱스 번호 : 시작 숫자 '0')
//                        //makeText(String) --> int i(정수) : 문자열로 변환 필요(i+"")
//                        Toast.makeText(MainActivity.this, items[i] , Toast.LENGTH_SHORT).show();
//                    }
//                });

                ///3. Single Choice
//                builder.setSingleChoiceItems(items, selectedItemPosition, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //i는 지역변수기때문에 PositiveButton에서 클릭된 값을 받을 수 없음
//                        //따라서 멤버변수를 만들고 지역변수값을 멤버변수에 담아두기
//                        //PositiveButtton에서는 멤버변수 selectedItemPosition 불러서 사용하면됨!
//                        selectedItemPosition = i;
//                    }
//                });

                //4. Multiple Choice
//                builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
//                        //두 번째 파라미터 i : 클릭된 항목의 위치 인덱스 번호
//                        //세 번째 파라미너 b : 클릭된 항목의 true/false 여부
//                        //새로 바뀐 b의 값을 boolean[]에 대입
//                        checkedItems[i] = b;
//                    }
//                });

                //5. Custom View 보여주기
                //java로 직접 View들을 만들어서 설정하면 코드가 어지러움
                //xml언어로 View를 설계하면 편하게 적용 가능
                //res 폴더 안 layout 폴더 안 dialog.xml파일에 dialog의 커스텀뷰 모양 설계
                builder.setView(R.layout.dialog);

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() { //view용이 아닌 Dialog용 OnClickListener
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Single choice를 통해 선택한 아이템 문자열 출력해보기
                        //Toast.makeText(MainActivity.this, items[selectedItemPosition], Toast.LENGTH_SHORT).show();

                        //Multiple choice를 통해 선택된 아이템들의 문자열 출력해보기
                        String s = "";
                        for(int k = 0; k < checkedItems.length; k++){
                            if (checkedItems[k]) s += items[k];
                        }
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();
                    }
                });

                //건축가에게 의뢰(설정)한대로 다이얼로그를 만들어달라고 요청
                AlertDialog dialog = builder.create();

                //다이얼로그의 바깥쪽을 터치했을때 없어지지(cancel) 않도록
                dialog.setCanceledOnTouchOutside(false);
                //다이얼로그 버튼이 아니면 어떤 방법으로도 취소하지 못하도록(TouchOutside 포함되어 있음)
                dialog.setCancelable(false);
                
                //다이얼로그를 화면에 보여주기
                dialog.show();

                //다이얼로그 안에 있는 Custom View의 뷰들을 찾아서 제어
                //btn02 = findViewById(R.id.btn02); this.(MainActivity) 생략
                dialogTv = dialog.findViewById(R.id.dialog_tv);
                dialogBtn = dialog.findViewById(R.id.dialog_btn);
                dialogBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogTv.setText("Good:)");
                    }
                });

            }
        });

    }
}