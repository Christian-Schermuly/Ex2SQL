package awesomecompanyofawesomeness.ex2sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Chris on 18.06.2016.
 */


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "excercise.db";
    public static final String TABLE_NAME = "person_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "SURNAME";
    public static final String COL4 = "CITY";
    public static final String COL5 = "STREET";
    public static final String COL6 = "PHONE";
    public static final String COL7 = "MAIL";

    /**
     *
     * @param context
     */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, "
                + COL5 + " TEXT, " + COL6 + " INT, " + COL7 + " TEXT)");

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    /**
     *Methode zum Befüllen der Db
     * @param name
     * @param surname
     * @param city
     * @param street
     * @return boolean zum prüfen, ob´s überhaupt funktioniert hat ;)
     */
    public boolean insertData(String name, String surname, String city, String street, int phone, String mail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, surname);
        contentValues.put(COL4, city);
        contentValues.put(COL5, street);
        contentValues.put(COL6, phone);
        contentValues.put(COL7, mail);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }


    }

    /**
     *
     * @return Alle Tupel in der DB
     */
    public Cursor getAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    /**
     *
     * @param id ds zu betrachtenden Tupels
     * @return Der Tupel
     */
    public Cursor getDetail(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where ID = " + id, null);
        return res;
    }

    /**
     * Überschreibt den Tupel mit der übergebenen ID mit den Übergebenen Werten
     * @param id
     * @param name
     * @param surname
     * @param city
     * @param street
     * @param phone
     * @param mail
     * @return
     */
    public boolean updateData(String id, String name, String surname, String city, String street, int phone, String mail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, id);
        contentValues.put(COL2, name);
        contentValues.put(COL3, surname);
        contentValues.put(COL4, city);
        contentValues.put(COL5, street);
        contentValues.put(COL6, phone);
        contentValues.put(COL7, mail);
        db.update(TABLE_NAME, contentValues, "id = ?",new String[] { id });
        return true;
     }

    /**
     * Löscht den Tupel mit Übergebener ID
     * @param id
     * @return errorcode
     */
    public int deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }

    /**
     * löscht alles...
     */
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
    }

    /**
     *
     * @param name
     * @return Tupel mit dem Namen
     */
    public Cursor searchForName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where NAME ='" + name +"'", null);
        return res;
    }

    /**
     *
     * @param surname
     * @return Tupel mit dem Nachnamen
     */
    public Cursor searchForSurname(String surname){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where SURNAME ='" + surname +"'", null);
        return res;
    }

    /**
     *
     * @param city
     * @return Tulpen aus Amsterdam
     */
    public Cursor searchForCity(String city){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where CITY ='" + city +"'", null);
        return res;
    }

}
