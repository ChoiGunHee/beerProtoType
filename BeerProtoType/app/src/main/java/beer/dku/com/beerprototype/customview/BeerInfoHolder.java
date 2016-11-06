package beer.dku.com.beerprototype.customview;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by ChoiGunHee on 2016-10-14.
 */

public class BeerInfoHolder {
    private ImageView beerImgView;
    private ImageView favoriteImgView;
    private TextView korNameView;
    private TextView engNameView;
    private TextView styleNameView;
    private TextView priceNameView;
    private TextView abvNameView;
    private TextView ratingView;

    public BeerInfoHolder(ImageView beerImgView,
                          ImageView favoriteImgView,
                          TextView korNameView,
                          TextView engNameView,
                          TextView styleNameView,
                          TextView priceNameView,
                          TextView abvNameView,
                          TextView ratingView) {
        this.beerImgView = beerImgView;
        this.favoriteImgView = favoriteImgView;
        this.engNameView = engNameView;
        this.korNameView = korNameView;
        this.styleNameView = styleNameView;
        this.priceNameView = priceNameView;
        this.abvNameView = abvNameView;
        this.ratingView = ratingView;
    }

    public TextView getRatingView() {
        return ratingView;
    }

    public TextView getAbvNameView() {
        return abvNameView;
    }

    public ImageView getBeerImgView() {
        return beerImgView;
    }

    public ImageView getFavoriteImgView() {
        return favoriteImgView;
    }

    public TextView getEngNameView() {
        return engNameView;
    }

    public TextView getKorNameView() {
        return korNameView;
    }

    public TextView getStyleNameView() {
        return styleNameView;
    }

    public TextView getPriceNameView() {
        return priceNameView;
    }

}
