package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "appDb";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Meal.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Meal.TABLE_NAME);

        onCreate(db);
    }

    public long insertMeal(Meal meal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Meal.COLUMN_NAME, meal.getName());
        values.put(Meal.COLUMN_CALORIES, meal.getCalories());
        values.put(Meal.COLUMN_CARBOHYDRATES, meal.getCarbohydrates());
        values.put(Meal.COLUMN_FATS, meal.getFats());
        values.put(Meal.COLUMN_PROTEINS, meal.getProteins());

        long id = db.insert(Meal.TABLE_NAME, null, values);

        db.close();

        return id;
    }

    public Meal getMeal(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                Meal.TABLE_NAME,
                new String[] {Meal.COLUMN_ID, Meal.COLUMN_NAME, Meal.COLUMN_CALORIES, Meal.COLUMN_CARBOHYDRATES, Meal.COLUMN_FATS, Meal.COLUMN_PROTEINS},
                Meal.COLUMN_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null
        );

        if (cursor != null)
            cursor.moveToFirst();

        Meal meal = new Meal(
                cursor.getInt(cursor.getColumnIndex(Meal.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Meal.COLUMN_NAME)),
                cursor.getFloat(cursor.getColumnIndex(Meal.COLUMN_CALORIES)),
                cursor.getFloat(cursor.getColumnIndex(Meal.COLUMN_CARBOHYDRATES)),
                cursor.getFloat(cursor.getColumnIndex(Meal.COLUMN_FATS)),
                cursor.getFloat(cursor.getColumnIndex(Meal.COLUMN_PROTEINS))
        );

        cursor.close();

        return meal;
    }

    public List<Meal> getAllMeals() {
        List<Meal> meals = new LinkedList<>();

        String selectQuery = "SELECT * FROM " + Meal.TABLE_NAME + " ORDER BY " +
                Meal.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Meal meal = new Meal();
                meal.setId(cursor.getInt(cursor.getColumnIndex(Meal.COLUMN_ID)));
                meal.setName(cursor.getString(cursor.getColumnIndex(Meal.COLUMN_NAME)));
                meal.setCalories(cursor.getFloat(cursor.getColumnIndex(Meal.COLUMN_CALORIES)));
                meal.setCarbohydrates(cursor.getFloat(cursor.getColumnIndex(Meal.COLUMN_CARBOHYDRATES)));
                meal.setFats(cursor.getFloat(cursor.getColumnIndex(Meal.COLUMN_FATS)));
                meal.setProteins(cursor.getFloat(cursor.getColumnIndex(Meal.COLUMN_PROTEINS)));

                meals.add(meal);
            } while (cursor.moveToNext());
        }

        db.close();

        return meals;
    }

    public int getMealsCount() {
        String countQuery = "SELECT  * FROM " + Meal.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }
}
