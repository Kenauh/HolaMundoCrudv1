package fes.jonathan.holamundocrudv1.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "pizzas.db";
    private static final  int DB_VERION = 1;

    public DatabaseHelper(Context context) {
        super(context,DB_NAME,null, DB_VERION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE producto(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, descripcion text)";
        sqLiteDatabase.execSQL(sql);
        //seeds
        sql = "INSERT INTO producto(nombre, descripcion) VALUES('Hawaiana','Trae Piña y Jamón')";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS producto");
        onCreate(sqLiteDatabase);
    }
}