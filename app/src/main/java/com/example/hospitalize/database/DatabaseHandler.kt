package com.example.hospitalize.database

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.util.Log
import com.example.hospitalize.model.GoldarModel
import com.example.hospitalize.model.RumahSakitModelClass


//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context: Context): SQLiteOpenHelper(context,
    DATABASE_NAME,null,
    DATABASE_VERSION
) {
    companion object {
        private val DATABASE_VERSION = 3
        private val DATABASE_NAME = "GoldarDatabase"

        private val TABLE_RUMAH_SAKIT = "RumahSakitTable"
        private val KEY_RUMAH_SAKIT_ID = "rumah_sakit_id"
        private val KEY_RUMAH_SAKIT = "rumah_sakit"
        private val KEY_REGION = "region"
        private val KEY_SUMBU_X = "sumbu_x"
        private val KEY_SUMBU_Y = "sumbu_y"
        private val KEY_ALAMAT = "alamat"
        private val KEY_TELEPON = "telepon"

        private val TABLE_GOLDAR = "GoldarTable"
        private val KEY_GOLDAR_ID = "goldar_id"
        private val KEY_SUMBER_ID = "sumber_id"
        private val KEY_GOLDAR = "goldar"
        private val KEY_GOLDAR_STOK = "stok"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        // creating table with fields
        val CREATE_RUMAH_SAKIT_TABLE = ("CREATE TABLE " + TABLE_RUMAH_SAKIT + "("
                + KEY_RUMAH_SAKIT_ID + " INTEGER PRIMARY KEY," + KEY_RUMAH_SAKIT + " TEXT," + KEY_REGION + " TEXT," + KEY_SUMBU_X + " TEXT,"
                + KEY_SUMBU_Y + " TEXT,"+ KEY_TELEPON + " TEXT," + KEY_ALAMAT + " TEXT)")
        db?.execSQL(CREATE_RUMAH_SAKIT_TABLE)
        db?.execSQL("insert into " + TABLE_RUMAH_SAKIT + "(" + KEY_RUMAH_SAKIT_ID + "," + KEY_RUMAH_SAKIT + "," + KEY_REGION + "," + KEY_SUMBU_X + ","
                + KEY_SUMBU_Y + "," + KEY_TELEPON + "," + KEY_ALAMAT + ") " +
                "values(1,'RSUD Bhakti Dharma Husada', 'Surabaya', '-7.254849380414793', '112.6357612344045', '0812-345-256', 'Jl. Kendung No.115 - 117, Sememi, Kec. Benowo, Kota SBY, Jawa Timur 60198')");
        db?.execSQL("insert into " + TABLE_RUMAH_SAKIT + "(" + KEY_RUMAH_SAKIT_ID + "," + KEY_RUMAH_SAKIT + "," + KEY_REGION + "," + KEY_SUMBU_X + ","
                + KEY_SUMBU_Y + "," + KEY_TELEPON + "," + KEY_ALAMAT + ") " +
                "values(2,'Rumah Sakit Bunda', 'Surabaya', '-7.251145620793517', '112.65026662144396', '0815-723-423', 'Jl. Raya Kandangan No.23-24, Kandangan, Kec. Benowo, Kota SBY, Jawa Timur 60199')");
        db?.execSQL("insert into " + TABLE_RUMAH_SAKIT + "(" + KEY_RUMAH_SAKIT_ID + "," + KEY_RUMAH_SAKIT + "," + KEY_REGION + "," + KEY_SUMBU_X + ","
                + KEY_SUMBU_Y + "," + KEY_TELEPON + "," + KEY_ALAMAT + ") " +
                "values(3,'Rumah Sakit Panti Rapih', 'Yogyakarta', '-7.776140207372767', '110.37688875767313', '0287-234-882', 'Jl. Cik Di Tiro No.30, Samirono, Terban, Kec. Gondokusuman, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55223')");


        val CREATE_GOLDAR_TABLE = ("CREATE TABLE " + TABLE_GOLDAR + "("
                + KEY_GOLDAR_ID + " INTEGER PRIMARY KEY," + KEY_SUMBER_ID + " INTEGER," + KEY_GOLDAR + " TEXT,"
                + KEY_GOLDAR_STOK + " INTEGER" + ")")
        db?.execSQL(CREATE_GOLDAR_TABLE)
        db?.execSQL("insert into $TABLE_GOLDAR ( $KEY_GOLDAR_ID ,$KEY_SUMBER_ID ,$KEY_GOLDAR ,$KEY_GOLDAR_STOK ) " +
                "values(1, 1, 'A', 10)");
        db?.execSQL("insert into $TABLE_GOLDAR ( $KEY_GOLDAR_ID ,$KEY_SUMBER_ID ,$KEY_GOLDAR ,$KEY_GOLDAR_STOK ) " +
                "values(2, 1, 'B', 5)");
        db?.execSQL("insert into " + TABLE_GOLDAR + "(" + KEY_GOLDAR_ID + "," + KEY_SUMBER_ID + "," + KEY_GOLDAR + "," + KEY_GOLDAR_STOK + ") " +
                "values(3, 1, 'AB', 9)");
        db?.execSQL("insert into " + TABLE_GOLDAR + "(" + KEY_GOLDAR_ID + "," + KEY_SUMBER_ID + "," + KEY_GOLDAR + "," + KEY_GOLDAR_STOK + ") " +
                "values(4, 1, 'O', 18)");
        db?.execSQL("insert into " + TABLE_GOLDAR + "(" + KEY_GOLDAR_ID + "," + KEY_SUMBER_ID + "," + KEY_GOLDAR + "," + KEY_GOLDAR_STOK + ") " +
                "values(5, 2, 'A', 4)");
        db?.execSQL("insert into " + TABLE_GOLDAR + "(" + KEY_GOLDAR_ID + "," + KEY_SUMBER_ID + "," + KEY_GOLDAR + "," + KEY_GOLDAR_STOK + ") " +
                "values(6, 3, 'AB', 9)");
        db?.execSQL("insert into " + TABLE_GOLDAR + "(" + KEY_GOLDAR_ID + "," + KEY_SUMBER_ID + "," + KEY_GOLDAR + "," + KEY_GOLDAR_STOK + ") " +
                "values(7, 3, 'O', 18)");
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_RUMAH_SAKIT)
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_GOLDAR)
        onCreate(db)
    }

    //method to read data
    fun viewGoldar(id: Int): List<GoldarModel> {
        val empList: ArrayList<GoldarModel> = ArrayList<GoldarModel>()
        val selectQuery = "SELECT * FROM $TABLE_GOLDAR WHERE sumber_id = $id AND $KEY_GOLDAR_STOK > 0"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var goldarId: Int
        var goldar: String
        var goldarStok: Int
        if (cursor.moveToFirst()) {
            do {
                goldarId = cursor.getInt(cursor.getColumnIndexOrThrow("goldar_id"))
                goldar = cursor.getString(cursor.getColumnIndexOrThrow("goldar"))
                goldarStok = cursor.getInt(cursor.getColumnIndexOrThrow("stok"))
                val emp = GoldarModel(id = goldarId.toString(), goldar = goldar, stok = goldarStok.toString())
//                if (sumberId == id) {
                    empList.add(emp)
//                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        return empList
    }

    @SuppressLint("Recycle")
    fun updateGoldar(id: String, stok:String) {
        val db = this.writableDatabase

        val updateQuery = "UPDATE $TABLE_GOLDAR SET $KEY_GOLDAR_STOK = " + (stok.toInt() - 1 ) +" WHERE $KEY_GOLDAR_ID = $id ;"

        val cursor = db.rawQuery( updateQuery,null )
        var sum = 0
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    sum = cursor.getInt(0)
                }
            } finally {
                cursor.close()
            }
        }
//        db.close()
        Log.d("CREATION", "Update Query goldar_id:$id")
    }

    fun addGoldar(rs_id: String, goldar: String) {
        val db = this.writableDatabase

        val checkQuery = "SELECT 1 FROM $TABLE_GOLDAR WHERE $KEY_SUMBER_ID = $rs_id AND $KEY_GOLDAR = '$goldar' ;"
        val cursor: Cursor = db.rawQuery(checkQuery, null)
        var addQuery: String
        addQuery = "INSERT INTO $TABLE_GOLDAR ($KEY_SUMBER_ID, $KEY_GOLDAR, $KEY_GOLDAR_STOK) values($rs_id, '$goldar', 1);"
        if (cursor.count > 0) {
            addQuery =
                "UPDATE $TABLE_GOLDAR SET $KEY_GOLDAR_STOK = $KEY_GOLDAR_STOK + 1 WHERE $KEY_SUMBER_ID = $rs_id AND $KEY_GOLDAR = '$goldar' ;"
        }

        val cursor2: Cursor = db.rawQuery(addQuery, null)
        val count = cursor.count

        var sum = 0
        if (cursor2 != null) {
            try {
                if (cursor2.moveToFirst()) {
                    sum = cursor2.getInt(0)
                }
            } finally {
                cursor2.close()
            }
        }

        Log.d("CREATION", "Add Query rs:$rs_id goldar:$goldar isExist:$count")
        Log.d("CREATION", "Query: $addQuery")
//        cursor2.close()
        cursor.close()
    }

    fun viewRS(region: String): List<RumahSakitModelClass> {
        val empList: ArrayList<RumahSakitModelClass> = ArrayList<RumahSakitModelClass>()
        val selectQuery = "SELECT * FROM $TABLE_RUMAH_SAKIT WHERE region = '$region' "
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var rumahSakitId: Int
        var rumahSakit: String
        var region: String
        var sumbuX: String
        var sumbuY: String
        var alamat: String
        var telepon: String
        if (cursor.moveToFirst()) {
            do {
                rumahSakitId = cursor.getInt(cursor.getColumnIndexOrThrow("rumah_sakit_id"))
                rumahSakit = cursor.getString(cursor.getColumnIndexOrThrow("rumah_sakit"))
                region = cursor.getString(cursor.getColumnIndexOrThrow("region"))
                sumbuX = cursor.getString(cursor.getColumnIndexOrThrow("sumbu_x"))
                sumbuY = cursor.getString(cursor.getColumnIndexOrThrow("sumbu_y"))
                alamat = cursor.getString(cursor.getColumnIndexOrThrow("alamat"))
                telepon = cursor.getString(cursor.getColumnIndexOrThrow("telepon"))
                val emp = RumahSakitModelClass(rumahSakitId= rumahSakitId, rumahSakit= rumahSakit , region= region,
                sumbuX= sumbuX, sumbuY= sumbuY , alamat= alamat, telepon= telepon)
                    empList.add(emp)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return empList
    }

    fun viewRSDetail(id: String): List<RumahSakitModelClass> {

        val empList: ArrayList<RumahSakitModelClass> = ArrayList<RumahSakitModelClass>()
        val selectQuery = "SELECT * FROM $TABLE_RUMAH_SAKIT WHERE rumah_sakit_id = $id LIMIT 1 "
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var rumahSakitId: Int
        var rumahSakit: String
        var region: String
        var sumbuX: String
        var sumbuY: String
        var alamat: String
        var telepon: String
        if (cursor.moveToFirst()) {
            do {
                rumahSakitId = cursor.getInt(cursor.getColumnIndexOrThrow("rumah_sakit_id"))
                rumahSakit = cursor.getString(cursor.getColumnIndexOrThrow("rumah_sakit"))
                region = cursor.getString(cursor.getColumnIndexOrThrow("region"))
                sumbuX = cursor.getString(cursor.getColumnIndexOrThrow("sumbu_x"))
                sumbuY = cursor.getString(cursor.getColumnIndexOrThrow("sumbu_y"))
                alamat = cursor.getString(cursor.getColumnIndexOrThrow("alamat"))
                telepon = cursor.getString(cursor.getColumnIndexOrThrow("telepon"))
                val emp = RumahSakitModelClass(
                    rumahSakitId = rumahSakitId, rumahSakit = rumahSakit, region = region,
                    sumbuX = sumbuX, sumbuY = sumbuY, alamat = alamat, telepon = telepon
                )
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return empList
    }
}