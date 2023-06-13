package com.ch96.ex95webapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {

    val wv: WebView by lazy { findViewById(R.id.wv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //webview 기본 설정
        wv.settings.javaScriptEnabled = true //웹뷰 설정객체를 통해 JS 사용을 허용하도록 변경설정
        wv.webViewClient = WebViewClient() //새로운 웹문서가 열릴때 기본 웹뷰는 새탭으로 열기에 웹브라우저가 실행되면서 열림
        wv.webChromeClient = WebChromeClient() //alert(), confirm() 같은 팝업기능 허용
        
        //웹뷰가 보여줄 웹문서 (.html) 로드하기
        //웹문서의 위치는 프로젝트 안에 있거나(인터넷 연결없이도 사용가능하지만, 업데이트를 매번 앱번들만들어서 해야함) 웹서버에 위치
        //1. 프로젝트 안 assets 폴더 안에 html 문서
        //wv.loadUrl("file:///android_asset/index.html")

        //2. 닷홈 or AWS 같은 웹서버에 html 문서가 존재
        wv.loadUrl("http://testhue96.dothome.co.kr/ajax/05_ajax.html")
    }

    override fun onBackPressed() {
        if(wv.canGoBack()) wv.goBack()
        else finish()
    }
}