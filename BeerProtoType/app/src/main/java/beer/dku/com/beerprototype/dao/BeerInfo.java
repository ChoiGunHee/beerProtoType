package beer.dku.com.beerprototype.dao;

import org.json.JSONObject;

/**
 * Created by ChoiGunHee on 2016-10-14.
 */

public class BeerInfo {
    public static final String BEERNAME = "beername";
    public static final String COUNTRY = "country";
    public static final String DEGREE = "degree";
    public static final String IMG_URL = "img_url";
    public static final String CATEGORY = "category";
    public static final String EVALUATION = "evaluation";
    public static final String DESCRIPTIONAL = "description";

    private String beerName;
    private String country;
    private double degree;
    private String img_url;
    private String category;
    private String evaluation;
    private String descriptional;

    public BeerInfo(String beerName, String country, double degree, String img_url, String category, String evaluation, String descriptional) {
        this.beerName = beerName;
        this.country = country;
        this.degree = degree;
        this.img_url = img_url;
        this.category = category;
        this.evaluation = evaluation;
        this.descriptional = descriptional;
    }

    @Override
    public String toString() {
        return "BeerInfo{" +
                "beerName='" + beerName + '\'' +
                ", country='" + country + '\'' +
                ", degree=" + degree +
                ", img_url='" + img_url + '\'' +
                ", category='" + category + '\'' +
                ", evaluation='" + evaluation + '\'' +
                ", descriptional='" + descriptional + '\'' +
                '}';
    }

    public String getBeerName() {
        return beerName;
    }

    public String getCountry() {
        return country;
    }

    public double getDegree() {
        return degree;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getCategory() {
        return category;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public String getDescriptional() {
        return descriptional;
    }
}
