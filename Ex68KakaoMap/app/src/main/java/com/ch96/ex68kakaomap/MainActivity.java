package com.ch96.ex68kakaomap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.kakao.util.maps.helper.Utility;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class MainActivity extends AppCompatActivity {

    // 카카오지도 API 는 AVD에서 동작 안됨. 실디바이스나 mac os의 M1,M2 chip AVD만 동작함.//

    // 카카오 개발자 사이트 의 가이드 문서를 따라하면 라이브러리를 추가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //키 해시 값 얻어오기
        //컴퓨터마다 다름
        String keyHash = Utility.getKeyHash(this);
        Log.i("keyHash", keyHash);

        //Mapview 객체 생성 및 뷰그룹에 붙이기
        MapView mapView = new MapView(this);
        ViewGroup containerMapView = findViewById(R.id.container_mapview);
        containerMapView.addView(mapView);
        //여기까지만 작성해도 지도는 보여야 함, 인텔칩에서는 애뮬레이터 동작 불가

        //가이드 문서에 지도를 다루는 코드들이 소개되어 있음

        // 중심점 변경
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), true);
        // 줌 레벨 변경
        mapView.setZoomLevel(7, true);
        // 중심점 변경 + 줌 레벨 변경
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(33.41, 126.52), 9, true);
        // 줌 인
        mapView.zoomIn(true);
        // 줌 아웃
        mapView.zoomOut(true);

        //마커 표시하기[POI]
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("나의 마커");
        marker.setTag(0);;
        marker.setMapPoint(MapPoint.mapPointWithCONGCoord(37.5618, 127.0342));
        //회사마다 좌표를 일컫는 용어가 다름, android : Location, kakao : MapPoint, google : LatLong
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(marker);


    }
}