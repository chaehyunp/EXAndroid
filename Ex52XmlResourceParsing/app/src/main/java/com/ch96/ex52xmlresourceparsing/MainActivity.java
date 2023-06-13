package com.ch96.ex52xmlresourceparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());
    }

    void clickBtn() {
        //Res 폴더에 있는 xml문서를 읽어와서 분석(parse)하는 작업 수행

        //res폴더 창고관리자 소환
        Resources res = getResources();
        //창고관리자로부터 parser객체 얻어오기
        XmlResourceParser xrp = res.getXml(R.xml.movies);
        //xml parser에게 분석작업요청
        try {
            xrp.next(); //xml의 0번째줄 부터 시작 일단 next로 첫번째줄 진입
            int eventType = xrp.getEventType(); //리턴값 int형 0,1,2,3,4

            StringBuffer buffer = new StringBuffer();
            //String은 이뮤터블 성질이 있어 변하지 않고 계속 새로운 데이터를 만듦 --> 메모리낭비
            //문자열을 만들지 않고 쌓아놓고 있는 Buffer이용

            while (eventType != XmlResourceParser.END_DOCUMENT) { //END_DOCUMENT까지 계속 반복해서 .next()

                switch(eventType){
                    case XmlResourceParser.START_DOCUMENT:
                        buffer.append("----- 파싱시작 -----\n\n");
                        break;

                    case XmlResourceParser.START_TAG:
                        String tagName = xrp.getName(); //START_TAG가 많으므로 무슨 태그인지 알아야함
                        if (tagName.equals("item")) {

                        }else if(tagName.equals("no")){
                            buffer.append("순위 : ");
                        }else if(tagName.equals("title")){
                            buffer.append("제목 : ");
                        }else if(tagName.equals("genre")){
                            buffer.append("장르 : ");
                        }
                        break;

                    case XmlResourceParser.END_TAG:
                        String tagName2 = xrp.getName();
                        if (tagName2.equals("item")) {
                            buffer.append("--------------------\n");
                        }else if(tagName2.equals("no")||tagName2.equals("title")||tagName2.equals("genre")){
                            buffer.append("\n");
                        }
                        break;

                    case XmlResourceParser.TEXT:
                        String text = xrp.getText();
                        buffer.append(text);
                        break;

                }

                eventType = xrp.next(); //next를 하면 자동으로 값 리턴
                //eventType = xrp.getEventType();
                //if (eventType == XmlResourceParser.END_DOCUMENT) break; //while의 조건 true 대신 --> 이 조건이 아닐때까지 반복해!
            }

            buffer.append("\n\n----- 파싱완료 -----\n\n");
            tv.setText(buffer.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        }
    }
}