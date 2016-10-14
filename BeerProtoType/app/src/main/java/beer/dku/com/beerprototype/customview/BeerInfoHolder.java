package beer.dku.com.beerprototype.customview;

import android.widget.TextView;

/**
 * Created by ChoiGunHee on 2016-10-14.
 */

public class BeerInfoHolder {
    private TextView beerNameView;
    private TextView countryView;
    private TextView degreeView;
    private TextView img_url_View;
    private TextView categoryView;
    private TextView evaluationView;
    private TextView descriptionView;

    public TextView getBeerNameView() {
        return beerNameView;
    }

    public void setBeerNameView(TextView beerNameView) {
        this.beerNameView = beerNameView;
    }

    public TextView getCountryView() {
        return countryView;
    }

    public void setCountryView(TextView countryView) {
        this.countryView = countryView;
    }

    public TextView getDegreeView() {
        return degreeView;
    }

    public void setDegreeView(TextView degreeView) {
        this.degreeView = degreeView;
    }

    public TextView getImg_url_View() {
        return img_url_View;
    }

    public void setImg_url_View(TextView img_url_View) {
        this.img_url_View = img_url_View;
    }

    public TextView getCategoryView() {
        return categoryView;
    }

    public void setCategoryView(TextView categoryView) {
        this.categoryView = categoryView;
    }

    public TextView getEvaluationView() {
        return evaluationView;
    }

    public void setEvaluationView(TextView evaluationView) {
        this.evaluationView = evaluationView;
    }

    public TextView getDescriptionView() {
        return descriptionView;
    }

    public void setDescriptionView(TextView descriptionView) {
        this.descriptionView = descriptionView;
    }
}
