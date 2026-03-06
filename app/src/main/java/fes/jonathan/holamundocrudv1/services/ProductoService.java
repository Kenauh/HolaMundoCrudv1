package fes.jonathan.holamundocrudv1.services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fes.jonathan.holamundocrudv1.models.ProductoModel;

public class ProductoService {

    DatabaseHelper databaseHelper;

    public ProductoService(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public List<ProductoModel> obtenerTodos(){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM producto", null);
        List<ProductoModel> productos = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                ProductoModel productoModel = new ProductoModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
                productos.add(productoModel);
            }while (cursor.moveToNext());
        }
        cursor.close();

        return  productos;
    }

    public ProductoModel obtenerPorId(int productoId){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM producto WHERE id = "+ productoId, null);
        ProductoModel productoModel = null;
        if(cursor.moveToFirst()){
            productoModel = new ProductoModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
        }
        cursor.close();

        return  productoModel;
    }

    public void borrar(int productoId){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        String sql = "DELETE FROM producto WHERE id="+ productoId;
        sqLiteDatabase.execSQL(sql);
    }

    public  int agregar(ProductoModel productoModel){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        String sql = "INSERT INTO producto(nombre, descripcion) VALUES('"+productoModel.getNombre()+"','"+productoModel.getDescripcion()+"')";
        sqLiteDatabase.execSQL(sql);
        return  1;
    }

    public void editar(ProductoModel productoModel){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        String sql = "UPDATE producto SET nombre='"+productoModel.getNombre()+"', descripcion='"+productoModel.getDescripcion()+"' WHERE id="+productoModel.getId();
        sqLiteDatabase.execSQL(sql);
    }
}