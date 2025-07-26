package com.sauloab.androidborrador.imccalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Clase para gestionar la base de datos SQLite de resultados IMC.
 */
public class BD_imc extends SQLiteOpenHelper {

    // Nombre y versión de la base de datos
    private static final String NOMBRE_BD = "IMC.db";
    private static final int VERSION_BD = 2;

    // Constantes para tabla y columnas
    public static final String TABLA_IMC = "resultados_imc";
    public static final String COL_ID = "id";
    public static final String COL_PESO = "peso";
    public static final String COL_ALTURA = "altura";
    public static final String COL_RESULTADO = "resultado";
    public static final String COL_FECHA = "fecha";

    public BD_imc(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    // Crea la tabla con las columnas necesarias
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLA_IMC + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_PESO + " REAL, " +
                COL_ALTURA + " REAL, " +
                COL_RESULTADO + " REAL, " +
                COL_FECHA + " TEXT)";
        db.execSQL(query);
    }

    // Actualiza la base de datos si cambia la versión
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_IMC);
        onCreate(db);
    }

    /**
     * Inserta un nuevo resultado con la fecha actual.
     */
    public void insertarResultado(double peso, double altura, double resultado) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(COL_PESO, peso);
        valores.put(COL_ALTURA, altura);
        valores.put(COL_RESULTADO, resultado);

        // Fecha en formato yyyy-MM-dd HH:mm
        String fechaActual = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
        valores.put(COL_FECHA, fechaActual);

        db.insert(TABLA_IMC, null, valores);
        db.close();
    }

    /**
     * Devuelve todos los resultados ordenados del más reciente al más antiguo.
     */
    public List<IMCResult> obtenerResultados() {
        List<IMCResult> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_IMC + " ORDER BY " + COL_ID + " DESC", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID));
                double peso = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_PESO));
                double altura = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_ALTURA));
                double resultado = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_RESULTADO));
                String fecha = cursor.getString(cursor.getColumnIndexOrThrow(COL_FECHA));

                lista.add(new IMCResult(id, peso, altura, resultado, fecha));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }

    public IMCResult obtenerResultadoPorId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLA_IMC, null, COL_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        IMCResult resultado = null;
        if (cursor != null && cursor.moveToFirst()) {
            double peso = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_PESO));
            double altura = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_ALTURA));
            double resultadoIMC = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_RESULTADO));
            String fecha = cursor.getString(cursor.getColumnIndexOrThrow(COL_FECHA));
            resultado = new IMCResult(id, peso, altura, resultadoIMC, fecha);
        }
        if (cursor != null) cursor.close();
        db.close();
        return resultado;
    }
    public int actualizarResultado(int id, double peso, double altura, double resultado) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(COL_PESO, peso);
        valores.put(COL_ALTURA, altura);
        valores.put(COL_RESULTADO, resultado);

        // Actualiza también la fecha a la actual
        String fechaActual = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
        valores.put(COL_FECHA, fechaActual);

        int filasAfectadas = db.update(TABLA_IMC, valores, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return filasAfectadas; // número de filas modificadas, normalmente 1
    }
    public int eliminarResultado(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int filasEliminadas = db.delete(TABLA_IMC, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return filasEliminadas; // 1 si se eliminó correctamente
    }


    /**
     * Elimina todos los registros de la tabla.
     */
    public void clearResultados() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLA_IMC, null, null);
        db.close();
    }
}
