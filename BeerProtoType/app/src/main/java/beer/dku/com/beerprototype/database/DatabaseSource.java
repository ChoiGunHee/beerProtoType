package beer.dku.com.beerprototype.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import beer.dku.com.beerprototype.dao.BeerInfo;

/**
 * Created by ChoiGunHee on 2016-11-05.
 */

public class DatabaseSource {
    private JejuBeerDatabaseHandler handler;
    private Context context;
    private int dbVersion;

    public DatabaseSource(Context context, int dbVersion) {
        this.context = context;
        this.dbVersion = dbVersion;
        handler = new JejuBeerDatabaseHandler(context, dbVersion);
    }

    public void insertBeerInfos(ArrayList<BeerInfo> datas) {
        SQLiteDatabase database = JejuBeerDatabaseHandler.open(context).getWritableDatabase();

        for (BeerInfo info : datas) {

            ContentValues values = new ContentValues();
            values.put(JejuBeerDatabaseHandler.BID, info.getBID());
            values.put(JejuBeerDatabaseHandler.KRNAME, info.getKrname());
            values.put(JejuBeerDatabaseHandler.ENNAME, info.getEnname());
            values.put(JejuBeerDatabaseHandler.STYLE, info.getStyle());
            values.put(JejuBeerDatabaseHandler.ABV, info.getAbv());
            values.put(JejuBeerDatabaseHandler.PRICE, info.getPrice());
            values.put(JejuBeerDatabaseHandler.DESCRIPTION, info.getDescription());
            values.put(JejuBeerDatabaseHandler.IMG_URL, info.getBeerimg_url());

            database.insert(JejuBeerDatabaseHandler.TABLE_BEERINFO, null, values);
        }

        database.close();
    }

    public ArrayList<BeerInfo> selectBeerInfos() {
        ArrayList<BeerInfo> results = new ArrayList<>();

        String query = "select * from " + JejuBeerDatabaseHandler.TABLE_BEERINFO;

        SQLiteDatabase database = JejuBeerDatabaseHandler.open(context).getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                BeerInfo beerInfo = new BeerInfo(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        getBeerRatingWithBid(cursor.getString(0))
                );
                results.add(beerInfo);
            } while (cursor.moveToNext());
        }

        database.close();
        return results;
    }

    public ArrayList<BeerInfo> selectBeerInfos_style(String style) {
        ArrayList<BeerInfo> results = new ArrayList<>();

        String query = "select * from " + JejuBeerDatabaseHandler.TABLE_BEERINFO + " where style = " + "\"" +style + "\"";

        SQLiteDatabase database = JejuBeerDatabaseHandler.open(context).getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                BeerInfo beerInfo = new BeerInfo(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        getBeerRatingWithBid(cursor.getString(0))
                );
                results.add(beerInfo);
            } while (cursor.moveToNext());
        }

        database.close();
        return results;
    }

    public ArrayList<String> selectBeerInfos_all_name() {
        ArrayList<String> results = new ArrayList<>();

        String query = "select * from " + JejuBeerDatabaseHandler.TABLE_BEERINFO;

        SQLiteDatabase database = JejuBeerDatabaseHandler.open(context).getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {

                String krName = cursor.getString(1);
                String enName = cursor.getString(2);
                results.add(krName);
                results.add(enName);
            } while (cursor.moveToNext());
        }

        database.close();
        return results;
    }

    public void insertWishList(String bid) {
        SQLiteDatabase database = JejuBeerDatabaseHandler.open(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(JejuBeerDatabaseHandler.BID, bid);

        database.insert(JejuBeerDatabaseHandler.TABLE_WISHLIST, null, values);

        database.close();
    }

    public ArrayList<BeerInfo> selectWishListBeerInfos() {
        ArrayList<BeerInfo> results = new ArrayList<>();

        String query = "select * from " + JejuBeerDatabaseHandler.TABLE_BEERINFO +
                " join " + JejuBeerDatabaseHandler.TABLE_WISHLIST +
                " on " + JejuBeerDatabaseHandler.TABLE_BEERINFO + "." + JejuBeerDatabaseHandler.BID + " = " + JejuBeerDatabaseHandler.TABLE_WISHLIST + "." + JejuBeerDatabaseHandler.BID;

        SQLiteDatabase database = JejuBeerDatabaseHandler.open(context).getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                BeerInfo beerInfo = new BeerInfo(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        getBeerRatingWithBid(cursor.getString(0))
                );
                results.add(beerInfo);
            } while (cursor.moveToNext());
        }

        database.close();
        return results;
    }

    public ArrayList<BeerInfo> searchBeerName(String name) {
        ArrayList<BeerInfo> results = new ArrayList<>();

        String query = "select * from " + JejuBeerDatabaseHandler.TABLE_BEERINFO +
                " where krname = " + "\"" + name + "\"" + " or " + "\"" + name + "\"";

        SQLiteDatabase database = JejuBeerDatabaseHandler.open(context).getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                BeerInfo beerInfo = new BeerInfo(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        getBeerRatingWithBid(cursor.getString(0))
                );
                results.add(beerInfo);
            } while (cursor.moveToNext());
        }

        database.close();
        return results;
    }

    public void insertBeerRating(String bid, int rating) {
        SQLiteDatabase database = JejuBeerDatabaseHandler.open(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(JejuBeerDatabaseHandler.BID, bid);
        values.put(JejuBeerDatabaseHandler.BEERRAING, rating);

        database.insert(JejuBeerDatabaseHandler.TABLE_BEERRATING, null, values);

        Log.d("beerRaintg1231", selectWishListBeerInfos().toString());

        database.close();
    }

    public int getBeerRatingWithBid(String bid) {
        int results = 0;

        String query = "select * from " + JejuBeerDatabaseHandler.TABLE_BEERRATING +
                " where " + JejuBeerDatabaseHandler.BID + " = " + bid;

        SQLiteDatabase database = JejuBeerDatabaseHandler.open(context).getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                results = cursor.getInt(2);
            } while (cursor.moveToNext());
        }

        database.close();

        return results;

    }

    public ArrayList<BeerInfo> selectBeerRaintBeerInfos() {
        ArrayList<BeerInfo> results = new ArrayList<>();

        String query = "select * from " + JejuBeerDatabaseHandler.TABLE_BEERINFO +
                " join " + JejuBeerDatabaseHandler.TABLE_BEERRATING +
                " on " + JejuBeerDatabaseHandler.TABLE_BEERINFO + "." + JejuBeerDatabaseHandler.BID + " = " + JejuBeerDatabaseHandler.TABLE_BEERRATING + "." + JejuBeerDatabaseHandler.BID;
        Log.d("beerQu", query);
        SQLiteDatabase database = JejuBeerDatabaseHandler.open(context).getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                BeerInfo beerInfo = new BeerInfo(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        getBeerRatingWithBid(cursor.getString(0))
                );
                Log.d("beerRaintg1231123", selectWishListBeerInfos().toString());
                results.add(beerInfo);
            } while (cursor.moveToNext());
        }

        database.close();
        return results;
    }
}
