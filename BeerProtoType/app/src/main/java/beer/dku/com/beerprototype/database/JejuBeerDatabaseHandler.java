package beer.dku.com.beerprototype.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by ChoiGunHee on 2016-11-05.
 */

class JejuBeerDatabaseHandler extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;

    //database name
    public static final String DATABASE_NAME = "jejuBeerDatabase";

    //database table
    public static final String TABLE_BEERINFO = "beerInfoTable";
    public static final String TABLE_WISHLIST = "wishListTable";
    public static final String TABLE_BEERRATING = "beerRatingTable";

    //database beerInfo Table Columns
    public static final String BID = "bid";
    public static final String KRNAME = "krname";
    public static final String ENNAME = "enname";
    public static final String STYLE  = "style";
    public static final String ABV    = "abv";
    public static final String PRICE  = "price";
    public static final String DESCRIPTION = "description";
    public static final String IMG_URL = "img_url";

    public static final String BEERRAING = "beerrating";

    //SQL beerInfo Table
    private String CREATE_BEERINFO_TABLE =
            "create table "
            + TABLE_BEERINFO
            + "("
            + BID + " text primary key, "
            + KRNAME + " text, "
            + ENNAME + " text, "
            + STYLE + " text, "
            + ABV + " text, "
            + PRICE + " text, "
            + DESCRIPTION + " text, "
            + IMG_URL + " text"
            + ")";

    private String CREATE_WISHLIST_TABLE =
            "create table "
            + TABLE_WISHLIST
            + "("
            + "id INTEGER primary key, "
            + BID + " text "
            + ")";

    private String CREATE_BEERRATING_TABLE =
            "create table "
                    + TABLE_BEERRATING
                    + "("
                    + "id INTEGER primary key, "
                    + BID + " text, "
                    + BEERRAING + " integer "
                    + ")";


    public JejuBeerDatabaseHandler(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    public static JejuBeerDatabaseHandler open(Context context) {
        return new JejuBeerDatabaseHandler(context, 1);
    };

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BEERINFO_TABLE);
        sqLiteDatabase.execSQL(CREATE_WISHLIST_TABLE);
        sqLiteDatabase.execSQL(CREATE_BEERRATING_TABLE);
        Log.d("beerCreate", CREATE_BEERINFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BEERINFO);
    }

}
