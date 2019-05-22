package com.zizi.zeinabmahmoud12997.film_show;

public class ExamplItem {

    private String mImageURL;
    private String filmName;
    private String cat;
    private String det;
    private String film;

    public ExamplItem(String mImageURL, String filmName, String cat, String det, String film) {
        this.mImageURL = mImageURL;
        this.filmName = filmName;
        this.cat = cat;
        this.det = det;
        this.film = film;
    }

    public String getmImageURL() {
        return mImageURL;
    }

    public String getFilmName() {
        return filmName;
    }

    public String getCat() {
        return cat;
    }

    public String getDet() {
        return det;
    }

    public  String getFilm() {
        return film;
    }
}
