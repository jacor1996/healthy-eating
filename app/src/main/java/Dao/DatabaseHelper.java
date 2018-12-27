package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jacek.healthy_eating.DateConverter;
import com.example.jacek.healthy_eating.UserData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        db.execSQL(MealData.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Meal.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MealData.TABLE_NAME);

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

        return db.update(User.TABLE_NAME, values, User.COLUMN_ID + " = ?",
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
                user.setActivity(cursor.getDouble(cursor.getColumnIndex(User.COLUMN_ACTIVITY)));

                users.add(user);
            } while (cursor.moveToNext());
        }

        db.close();

        return users;
    }

    public List<MealData> getAllMealData() {
        List<MealData> mealsData = new LinkedList<>();

        String selectQuery = "SELECT * FROM " + MealData.TABLE_NAME + " ORDER BY " +
                MealData.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                MealData mealData = new MealData();

                mealData.setId(cursor.getInt(cursor.getColumnIndex(MealData.COLUMN_ID)));
                mealData.setMealId(cursor.getInt(cursor.getColumnIndex(MealData.COLUMN_MEAL_ID)));
                mealData.setAmount(cursor.getDouble(cursor.getColumnIndex(MealData.COLUMN_AMOUNT)));
                mealData.setDate(cursor.getLong(cursor.getColumnIndex(MealData.COLUMN_DATE)));
                mealData.setMealType(cursor.getInt(cursor.getColumnIndex(MealData.COLUMN_MEAL_TYPE)));

                mealsData.add(mealData);
            } while (cursor.moveToNext());
        }

        db.close();

        return mealsData;
    }

    public List<MealData> getMealDataByMealType(int mealType) {
        List<MealData> mealsData = getAllMealData();
        List<MealData> mealsDataToReturn = new LinkedList<>();

        for (MealData mData: mealsData) {
            if (mData.getMealType() == mealType) {
                mealsDataToReturn.add(mData);
            }
        }

        return mealsDataToReturn;
    }

    public List<MealData> getMealDataByMealType(int mealType, long dateInMilliseconds) {
        List<MealData> mealsData = getAllMealData();
        List<MealData> mealsDataToReturn = new LinkedList<>();

        String date1 = DateConverter.getDateFromMilliseconds(dateInMilliseconds);

        for (MealData mData: mealsData) {
            String date2 = DateConverter.getDateFromMilliseconds(mData.getDate());

            if (mData.getMealType() == mealType && date1.equals(date2)) {
                mealsDataToReturn.add(mData);
            }
        }

        return mealsDataToReturn;
    }

    public List<MealData> getMealDataByDate(long dateInMilliseconds) {
        List<MealData> mealsData = getAllMealData();
        List<MealData> mealsDataToReturn = new LinkedList<>();

        String date1 = DateConverter.getDateFromMilliseconds(dateInMilliseconds);

        for (MealData mData: mealsData) {
            String date2 = DateConverter.getDateFromMilliseconds(mData.getDate());

            if (date1.equals(date2)) {
                mData.setMeal(getMeal(mData.getMealId()));
                mealsDataToReturn.add(mData);
            }
        }

        return mealsDataToReturn;
    }

    public long insertMealData(MealData mealData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MealData.COLUMN_MEAL_ID, mealData.getMealId());
        values.put(MealData.COLUMN_AMOUNT, mealData.getAmount());
        values.put(MealData.COLUMN_DATE, mealData.getDate());
        values.put(MealData.COLUMN_MEAL_TYPE, mealData.getMealType());

        long id = db.insert(MealData.TABLE_NAME, null, values);

        db.close();

        return id;
    }

    public MealData getMealData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                MealData.TABLE_NAME,
                new String[] {MealData.COLUMN_ID, MealData.COLUMN_MEAL_ID, MealData.COLUMN_MEAL_TYPE, MealData.COLUMN_AMOUNT, MealData.COLUMN_DATE },
                MealData.COLUMN_ID + " = ?",
                new String[] {String.valueOf(id)}, null, null, null, null
        );

        if (cursor != null)
            cursor.moveToFirst();


        MealData mealData = new MealData();

        mealData.setMealId(cursor.getInt(cursor.getColumnIndex(MealData.COLUMN_MEAL_ID)));
        mealData.setAmount(cursor.getDouble(cursor.getColumnIndex(MealData.COLUMN_AMOUNT)));
        mealData.setDate(cursor.getLong(cursor.getColumnIndex(MealData.COLUMN_DATE)));
        mealData.setMealType(cursor.getInt(cursor.getColumnIndex(MealData.COLUMN_MEAL_TYPE)));
        mealData.setMeal(getMeal(mealData.getMealId()));

        cursor.close();

        return mealData;
    }

    public void deleteMealData(MealData mealData) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(MealData.TABLE_NAME, MealData.COLUMN_ID + " = ?",
                new String[] {String.valueOf(mealData.getId())});

        db.close();
    }
}
