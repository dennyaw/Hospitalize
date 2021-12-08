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
        private val DATABASE_VERSION = 4
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

        private val TABLE_ACCOUNT = "AccountTable"
        private val KEY_ACCOUNT_ID = "account_id"
        private val KEY_UNAME = "username"
        private val KEY_PASSWORD = "password"
        private val KEY_NAME = "nama"
        private val KEY_LOGGED_IN = "logged_in"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        // creating table with fields
        val CREATE_RUMAH_SAKIT_TABLE = ("CREATE TABLE $TABLE_RUMAH_SAKIT($KEY_RUMAH_SAKIT_ID INTEGER PRIMARY KEY, $KEY_RUMAH_SAKIT TEXT, $KEY_REGION TEXT, $KEY_SUMBU_X TEXT, $KEY_SUMBU_Y TEXT, $KEY_TELEPON TEXT,$KEY_ALAMAT TEXT)")
        val insertRs = "insert into $TABLE_RUMAH_SAKIT($KEY_RUMAH_SAKIT_ID,$KEY_RUMAH_SAKIT,$KEY_REGION,$KEY_SUMBU_X,$KEY_SUMBU_Y,$KEY_TELEPON,$KEY_ALAMAT)"
        db?.execSQL(CREATE_RUMAH_SAKIT_TABLE)
        db?.execSQL(insertRs + "values(1,'RSUD Bhakti Dharma Husada', 'Surabaya', '-7.254849380414793', '112.6357612344045', '0812-345-256', 'Jl. Kendung No.115 - 117, Sememi, Kec. Benowo, Kota SBY, Jawa Timur 60198')");
        db?.execSQL(insertRs + "values(2,'Rumah Sakit Bunda', 'Surabaya', '-7.251145620793517', '112.65026662144396', '0815-723-423', 'Jl. Raya Kandangan No.23-24, Kandangan, Kec. Benowo, Kota SBY, Jawa Timur 60199')");
        db?.execSQL(insertRs + "values(3,'Rumah Sakit Panti Rapih', 'Yogyakarta', '-7.776140207372767', '110.37688875767313', '0287-234-882', 'Jl. Cik Di Tiro No.30, Samirono, Terban, Kec. Gondokusuman, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55223')");

        val CREATE_GOLDAR_TABLE = ("CREATE TABLE $TABLE_GOLDAR($KEY_GOLDAR_ID INTEGER PRIMARY KEY,$KEY_SUMBER_ID INTEGER,$KEY_GOLDAR TEXT,$KEY_GOLDAR_STOK INTEGER)")
        val insertGoldar = "insert into $TABLE_GOLDAR ( $KEY_GOLDAR_ID ,$KEY_SUMBER_ID ,$KEY_GOLDAR ,$KEY_GOLDAR_STOK ) "
        db?.execSQL(CREATE_GOLDAR_TABLE)
        db?.execSQL(insertGoldar + "values(1, 1, 'A', 10)");
        db?.execSQL(insertGoldar + "values(2, 1, 'B', 5)");
        db?.execSQL(insertGoldar + "values(3, 1, 'AB', 9)");
        db?.execSQL(insertGoldar + "values(4, 1, 'O', 18)");
        db?.execSQL(insertGoldar + "values(5, 2, 'A', 4)");
        db?.execSQL(insertGoldar + "values(6, 3, 'AB', 9)");
        db?.execSQL(insertGoldar + "values(7, 3, 'O', 18)");

        val CREATE_ACCOUNT_TABLE = ("CREATE TABLE $TABLE_ACCOUNT($KEY_ACCOUNT_ID INTEGER PRIMARY KEY, $KEY_UNAME TEXT, $KEY_PASSWORD TEXT, $KEY_NAME TEXT, $KEY_LOGGED_IN INTEGER)")
        db?.execSQL(CREATE_ACCOUNT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_RUMAH_SAKIT)
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_GOLDAR)
        onCreate(db)
    }

    // Create akun
    fun createAccount(uname: String, pass: String, name: String): Boolean {
        val db = this.writableDatabase
        val getQuery = "SELECT * FROM $TABLE_ACCOUNT WHERE $KEY_UNAME = '$uname';"
        val cursorCheck: Cursor = db.rawQuery(getQuery, null)
        val res: Int
        if (cursorCheck.count > 0) {
            Log.d("CREATION", "Failed create account, uname:$uname used")
            cursorCheck.close()
            db.close()
            return false
        } else {
            val addQuery = "INSERT INTO $TABLE_ACCOUNT($KEY_UNAME, $KEY_PASSWORD, $KEY_NAME, $KEY_LOGGED_IN) values('$uname', '$pass', '$name', -1);"
            val cursor: Cursor = db.rawQuery(addQuery, null)

            Log.d("CREATION", "Create account, name:$name, uname:$uname, pass:$pass")
            Log.d("CREATION", addQuery)
            Log.d("CREATION", getQuery)
            cursorCheck.close()
            try {
                if (cursor.moveToFirst()) {
                }
            } finally {
                cursor.close()
            }
            db.close()
            return true
        }
    }

    // Login akun
    fun loginAccount(uname: String, pass: String): Boolean {
        val db = this.writableDatabase

        val getQuery = "SELECT * FROM $TABLE_ACCOUNT WHERE $KEY_UNAME = '$uname';"
        val cursor: Cursor = db.rawQuery(getQuery, null)
        var res = true
        if (cursor.count > 0) {
            val updateQuery = "UPDATE $TABLE_ACCOUNT SET $KEY_LOGGED_IN = 1 WHERE $KEY_UNAME = '$uname' ;"
            val cursor2 = db.rawQuery( updateQuery,null )
            try {
                if (cursor2.moveToFirst()) {
                }
            } finally {
                cursor2.close()
            }
        } else {
            res = false
        }

        Log.d("CREATION", "Login account, uname:$uname, pass:$pass")
        cursor.close()
        db.close()
        return res
    }

    // Logout akun
    fun logoutAccount(id: Int): Boolean {
        val db = this.writableDatabase

        val getQuery = "SELECT * FROM $TABLE_ACCOUNT WHERE $KEY_ACCOUNT_ID = $id;"
        val cursor: Cursor = db.rawQuery(getQuery, null)
        var res = true
        if (cursor.count > 0) {
            val updateQuery = "UPDATE $TABLE_ACCOUNT SET $KEY_LOGGED_IN = -1 WHERE $KEY_ACCOUNT_ID = $id ;"
            val cursor2 = db.rawQuery( updateQuery,null )
            try {
                if (cursor2.moveToFirst()) {
                }
            } finally {
                cursor2.close()
            }
        } else {
            res = false
        }

        Log.d("CREATION", "Logout account, id:$id")
        try {
            if (cursor.moveToFirst()) {
            }
        } finally {
            cursor.close()
        }
        db.close()
        return res
    }

    // Check is logged in
    fun checkAccount(): Int {
        val db = this.writableDatabase

        val checkQuery = "SELECT * FROM $TABLE_ACCOUNT WHERE $KEY_LOGGED_IN = 1 LIMIT 1;"
        val cursor: Cursor = db.rawQuery(checkQuery, null)
        var loggedAccount = -1
        if (cursor.count > 0) {
            if (cursor.moveToFirst()) {
                do {
                    loggedAccount = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ACCOUNT_ID))
//                }
                } while (cursor.moveToNext())
            }
        }
        Log.d("CREATION", "Account logged id:$loggedAccount")
        cursor.close()
        db.close()
        return loggedAccount
    }

    // GET list kantong darah tersedia
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
        db.close()
        cursor.close()
        return empList
    }

    @SuppressLint("Recycle")
    // Mengambil kantong darah
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
        db.close()
        Log.d("CREATION", "Update Query goldar_id:$id")
    }

    // Mendonor kantong darah
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
        db.close()
    }

    // GET list rumah sakit
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
        db.close()
        return empList
    }

    // GET info salah satu rumah sakit
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
        db.close()
        return empList
    }
}