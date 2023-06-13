package com.ch96.ex53xmlpullparsermovie;

public class MovieItem {
    String rank;
    String movieNm;
    String openDt;
    String audiAcc;

    //생성자
    //파라미터 받지 않는 것 하나, 받는 것 하나씩 만들기
    public MovieItem() {
    }

    public MovieItem(String rank, String movieNm, String openDt, String audiAcc) {
        this.rank = rank;
        this.movieNm = movieNm;
        this.openDt = openDt;
        this.audiAcc = audiAcc;
    }
}
