package com.ch96.ex53xmlpullparsermovie;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //영화정보를 가진 MovieItem을 여러 개 관리하는 리스트객체
    ArrayList<MovieItem> movieItems = new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapter adapter;

    String apiKey = "f5eef3421c602c6cb7ea224104795888";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //원래는 서버의 xml 문서를 읽어와야 하지만, RecyclerView의 동작 테스트를 위해 가상의 값 추가
        //movieItems.add(new MovieItem("0", "sample", "0000-00-00", "00000"));
        //movieItems.add(new MovieItem("0", "sample", "0000-00-00", "00000"));

        recyclerView = findViewById(R.id.recycler);
        adapter = new MyAdapter(this, movieItems);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());

    }

    void clickBtn() {
        //영화진흥위원회 open API 정보(일일 박스오피스)를 가져와서 리사이클러뷰에 보여주기
        //xml 파일포맷의 데이터를 읽어와서 분석

        //네트워크 작업은 권한이 반드시 요구됨 [AndroidManifest.xml에서 권한 부여]
        //네트워크 작업은 오래 걸리는 작업으로 인식됨 --> 반드시 별도의 Thread가 작업해야만 함
        new Thread(){
            @Override
            public void run() {
                //네트워크 작업 비동기로 시작...
                //xml문서의 REST 방식의 url 주소

                //검색 날짜 [오늘 날짜의 전날]
                Date date = new Date(); //객체가 생성되는 순간의 날짜와 시간을 가지고 있음
                date.setTime(date.getTime() - (1000*60*60*24)); //0 == 1970년 1월 1일 0시 0분 0초 , 현재의 하루 전 시간
                //특정포맷으로 날짜를 문자열로 만들어주는 객체
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //소문자 m 은 minute
                String yesterday = sdf.format(date);

                //SAMPLE ADDRESS
                String address = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml" //기본 요청 url
                                +"?key="+apiKey //첫번째 파라미터
                                +"&targetDt="+yesterday //두번째 파라미터, 파라미터 몇 개든 추가 가능
                                +"&itemPerPage=5"; 

                //위 서버 url 위치까지 무지개로드를 열어주는 해임달 객체 생성
                try {
                    URL url = new URL(address);

                    //무지개로드 열기
                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is); //바이트스트림 --> 문자스트림

                    //XML 문서를 조금 더 쉽게 분석(parse)해주는 객체 생성
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(isr);

                    //Xml Parser에게 분석요청
                    //xpp.next(); //pullparser는 next 안해도 이미 START_DOCUMENT
                    int eventType = xpp.getEventType(); //시작위치가 START_DOCUMENT

                    MovieItem movieItem = null; //영화 1개 정보 참조변수

                    while(eventType != XmlPullParser.END_DOCUMENT){

                        switch (eventType){
                            case XmlPullParser.START_DOCUMENT:
                                //Toast.makeText(MainActivity.this, "Start Parsing", Toast.LENGTH_SHORT).show();
                                //별도의 Thread는 UI작업 불가! UI thread에서 작업하도록
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Start Parsing", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                break;
                            case XmlPullParser.START_TAG:
                                String tagName = xpp.getName();
                                if (tagName.equals("dailyBoxOffice")){
                                    movieItem = new MovieItem(); //정보시작 빈 movieItem 만들기
                                }else if (tagName.equals("rank")){
                                    xpp.next(); //시작태그 옆은 TEXT
                                    movieItem.rank = xpp.getText();
                                } else if (tagName.equals("movieNm")){
                                    xpp.next();
                                    movieItem.movieNm = xpp.getText();
                                } else if (tagName.equals("openDt")) {
                                    xpp.next();
                                    movieItem.openDt = xpp.getText();
                                } else if (tagName.equals("audiAcc")){
                                    xpp.next();
                                    movieItem.audiAcc = xpp.getText();
                                }
                                break;
                            case XmlPullParser.END_TAG:
                                String tagName2 = xpp.getName();
                                if (tagName2.equals("dailyBoxOffice")){
                                    //리사이클러가 보여주는 대량의 데이터들 리스트에 추가
                                    movieItems.add(movieItem);
                                }
                                break;
                            //case XmlPullParser.TEXT: //위에서 xpp.next() 로 작성했기때문에 필요없음
                                //break;
                        }
                        eventType = xpp.next();
                    }

                    //UI작업은 UiThread위에서 작업
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Finished Parsing", Toast.LENGTH_SHORT).show();
                            //대량의 데이터가 추가되었다는것을 Adapter에게 공지해야만 RecyclerView가 화면 갱신
                            adapter.notifyDataSetChanged();
                        }
                    });


                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (XmlPullParserException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }
}