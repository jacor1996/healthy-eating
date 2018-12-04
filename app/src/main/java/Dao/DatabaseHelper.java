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

    private static DatabaseHelper instance = null;
    private Context context;

    public static DatabaseHelper getInstance(Context ctx) {
        if (instance == null) {
            instance = new DatabaseHelper(ctx.getApplicationContext());
        }
        return instance;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Meal.CREATE_TABLE);
        db.execSQL(Meal.INSERT_DEFAULT_DATA);
        db.execSQL(User.CREATE_TABLE);
        db.execSQL(User.INSERT_DEFAULT_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Meal.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);

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

    public int updateMeal(Meal meal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Meal.COLUMN_NAME, meal.getName());
        values.put(Meal.COLUMN_CALORIES, meal.getCalories());
        values.put(Meal.COLUMN_CARBOHYDRATES, meal.getCarbohydrates());
        values.put(Meal.COLUMN_FATS, meal.getFats());
        values.put(Meal.COLUMN_PROTEINS, meal.getProteins());

        return db.update(Meal.TABLE_NAME, values, Meal.COLUMN_ID + " = ?",
                new String[] {String.valueOf(meal.getId())});

    }

    public void deleteMeal(Meal meal) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Meal.TABLE_NAME, Meal.COLUMN_ID + " = ?",
                new String[] {String.valueOf(meal.getId())});

        db.close();
    }

    public int getMealsCount() {
        String countQuery = "SELECT  * FROM " + Meal.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }


    public User getUser(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                User.TABLE_NAME,
                new String[] {User.COLUMN_ID, User.COLUMN_AGE, User.COLUMN_HEIGHT, User.COLUMN_WEIGHT, User.COLUMN_ACTIVITY },
                User.COLUMN_ID + " = ?",
                new String[] {String.valueOf(id)}, null, null, null, null
        );

        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(
                cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
                cursor.getInt(cursor.getColumnIndex(User.COLUMN_AGE)),
                cursor.getDouble(cursor.getColumnIndex(User.COLUMN_HEIGHT)),
                cursor.getDouble(cursor.getColumnIndex(User.COLUMN_WEIGHT)),
                cursor.getDouble(cursor.getColumnIndex(User.COLUMN_ACTIVITY))
        );

        cursor.close();

        return user;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(User.COLUMN_AGE, user.getAge());
        values.put(User.COLUMN_HEIGHT, user.getHeight());
        values.put(User.COLUMN_WEIGHT, user.getWeight());
        values.put(User.COLUMN_ACTIVITY, user.getActivity());

        return db.update(Meal.TABLE_NAME, values, User.COLUMN_ID + " = ?",
                new String[] {String.valueOf(user.getId())});

    }

    public List<User> getAllUsers() {
        List<User> users = new LinkedList<>();

        String selectQuery = "SELECT * FROM " + User.TABLE_NAME + " ORDER BY " +
                User.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();

                user.setId(cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)));
                user.setAge(cursor.getInt(cursor.getColumnIndex(User.COLUMN_AGE)));
                user.setHeight(cursor.getDouble(cursor.getColumnIndex(User.COLUMN_HEIGHT)));
                user.setWeight(cursor.getDouble(cursor.getColumnIndex(User.COLUMN_WEIGHT)));

                users.add(user);
            } while (cursor.moveToNext());
        }

        db.close();

        return users;
    }
}
