package com.example.ongje.rusher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentRequestDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "StudentRequestDB.db";

    //Column that needed to create leave of absence table
    public static final String TABLE_NAME = "leave_table";
    public static final int DB_VERSION = 108;
    public static final String COL_1= "Number";
    public static final String COL_2 = "Student";
    public static final String COL_3 = "Leave_Date_To";
    public static final String COL_4 = "Leave_Date_From";
    public static final String COL_5 = "Leave_Type";
    public static final String COL_6 = "Status_Leave";

    //Column that needed to create activity room booking table
    public static final String TABLE_NAME_BOOK = "booking_table";
    public static final String COL_NUM= "Number";
    public static final String COL_StuID= "StuID";
    public static final String COL_RoomType = "Room_Type";
    public static final String COL_NumOfStu = "Num_Of_Student";
    public static final String COL_duration = "Duration";
    public static final String COL_Booking_Date = "Booking_Date";
    public static final String COL_Booking_Status= "Status_Booking";

    //Column that needed to create food ordering table
    public static final String TABLE_NAME_RESERVATION = "food_reservation_table";
    public static final String COL_NUMBER= "Number";
    public static final String COL_Student= "Student_ID";
    public static final String COL_SELECTION = "Food_Type";
    public static final String COL_QUANTITY = "Quantity";
    public static final String COL_REMARKS = "Remarks";
    public static final String COL_TOTAL = "Total";
    public static final String COL_RESERVE_STATUS = "Status_Reserve";


    //Column that needed to create shuttle service table
    public static final String TABLE_NAME_SHUTTLE = "shuttle_table";
    public static final String COL_NUM2= "Number";
    public static final String COL_StuIDShu= "StudentID";
    public static final String COL_TIME = "Time";
    public static final String COL_LOCATION = "Location";
    public static final String COL_SHUTTLE_STATUS = "Status_Shuttle";

    public StudentRequestDB(Context context)
   {
       super(context,DATABASE_NAME,null,DB_VERSION);
   }

    @Override
    public void onCreate(SQLiteDatabase db) // Create database table
    {
        db.execSQL("create table " + TABLE_NAME +" (Number INTEGER PRIMARY KEY AUTOINCREMENT,Student TEXT,Leave_Date_To INTEGER,Leave_Date_From INTEGER,Leave_Type TEXT, Status_Leave TEXT)");

        db.execSQL("create table " + TABLE_NAME_BOOK +" (Number INTEGER PRIMARY KEY AUTOINCREMENT,StuID TEXT,Room_Type TEXT,Num_Of_Student INTEGER,Duration INTEGER,Booking_Date INTEGER, Status_Booking TEXT)");

        db.execSQL("create table " + TABLE_NAME_RESERVATION +" (Number INTEGER PRIMARY KEY AUTOINCREMENT,Student_ID TEXT,Food_Type TEXT,Quantity INTEGER,Remarks TEXT, Total TEXT, Status_Reserve TEXT)");

        db.execSQL("create table " + TABLE_NAME_SHUTTLE +" (Number INTEGER PRIMARY KEY AUTOINCREMENT,StudentID TEXT,Time TEXT, Location TEXT, Status_Shuttle TEXT)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_BOOK);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_RESERVATION);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_SHUTTLE);
        onCreate(db);
    }

    //To insert leave of absence data
    public boolean insertData(String Student, String Leave_Date_To,String Leave_Date_From,String Leave_Type,String Status_Leave)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentData = new ContentValues();
        contentData.put(COL_2,Student);
        contentData.put(COL_3,Leave_Date_To);
        contentData.put(COL_4,Leave_Date_From);
        contentData.put(COL_5,Leave_Type);
        contentData.put(COL_6,Status_Leave);

        long result = db.insert(TABLE_NAME,null ,contentData);
        if(result == -1)
            return false;
        else
            return true;
    }

    //To insert activity room booking data
    public boolean insertDataBooking(String StuID,String Room_Type, String Num_Of_Student,String Duration,String Booking_Date,String Status_Booking)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentData = new ContentValues();
        contentData.put(COL_StuID,StuID);
        contentData.put(COL_RoomType,Room_Type);
        contentData.put(COL_NumOfStu,Num_Of_Student);
        contentData.put(COL_duration,Duration);
        contentData.put(COL_Booking_Date,Booking_Date);
        contentData.put(COL_Booking_Status,Status_Booking);

        long result = db.insert(TABLE_NAME_BOOK,null ,contentData);
        if(result == -1)
            return false;
        else
            return true;
    }

    //To insert food ordering data
    public boolean insertDataReservation(String Student_ID, String Food_Type,String Quantity,String Remarks, String Total,String Status_Reserve)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentData = new ContentValues();
        contentData.put(COL_Student,Student_ID);
        contentData.put(COL_SELECTION,Food_Type);
        contentData.put(COL_QUANTITY,Quantity);
        contentData.put(COL_REMARKS,Remarks);
        contentData.put(COL_TOTAL,Total);
        contentData.put(COL_RESERVE_STATUS,Status_Reserve);

        long result = db.insert(TABLE_NAME_RESERVATION,null ,contentData);
        if(result == -1)
            return false;
        else
            return true;
    }

    //To insert shuttle service data
    public boolean insertDataShuttle(String StudentID,String Time, String Location,String Status_Shuttle)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentData = new ContentValues();
        contentData.put(COL_StuIDShu,StudentID);
        contentData.put(COL_TIME,Time);
        contentData.put(COL_LOCATION,Location);
        contentData.put(COL_SHUTTLE_STATUS,Status_Shuttle);

        long result = db.insert(TABLE_NAME_SHUTTLE,null ,contentData);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() //To display all pending leave of absence data according to user email
    {
        SQLiteDatabase db = this.getReadableDatabase();

        //To access user's login value
        String data=MainActivity.value;

        String status="Pending";

        //To compare user login data with database
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" WHERE "+
                COL_6 +"='"+status+"'"+ " AND " +COL_2+"='"+data+"'",null);
        return res;

    }

    public Cursor getData() // To display latest user input
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" ORDER BY Number DESC LIMIT 1", null);

        return res;

    }


    public Cursor getAllDataBooking() //To display all pending activity room booking data according to user email
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String data=MainActivity.value;//To access user's login value
        String status="Pending";
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_BOOK+" WHERE "+
                COL_Booking_Status +"='"+status+"'"+ " AND " +COL_StuID+"='"+data+"'",null);
        return res;
    }

    public Cursor getDataBooking() { // To display latest user input

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_BOOK+" ORDER BY Number DESC LIMIT 1", null);

        return res;
    }

    public Cursor getAllDataShuttle() //To display all approved shuttle service data according to user email
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String data=MainActivity.value; //To access user's login value
        Cursor res=db.rawQuery("select * from "+
                TABLE_NAME_SHUTTLE+" WHERE "+
                COL_StuIDShu+"='"+data+"'",null);
        return res;
    }

    public Cursor getDataShuttle()  // To display latest user input
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_SHUTTLE+" ORDER BY Number DESC LIMIT 1", null);
        return res;
    }

    public Cursor getAllDataReservation() //To display all approved canteen food ordering data according to user email
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String data=MainActivity.value; //To access user's login value
        Cursor res=db.rawQuery("select * from "+
                TABLE_NAME_RESERVATION+" WHERE "+
                COL_Student+"='"+data+"'",null);

        return res;
    }

    public Cursor getDataReservation() // To display latest user input
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_RESERVATION+" ORDER BY Number DESC LIMIT 1", null);

        return res;

    }

    //For lecturer
    public Cursor getAdminData() //To get all pending leave of absence request send by user
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String status="Pending";
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" WHERE "+
                COL_6 +"='"+status+"'",null);

        return res;
    }

    //For lecturer
    public Cursor getAdminDataBook() //To get all pending leave of absence request send by user
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String status="Pending";
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_BOOK+" WHERE "+
                COL_Booking_Status +"='"+status+"'",null);

        return res;
    }

    public Cursor getApprovedLeaveRequest() //To display approved leave of absence request according to each student email
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String status="Approved";
        String data=MainActivity.value;

        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" WHERE "+
                COL_6 +"='"+status+"'"+ " AND " +COL_2+"='"+data+"'",null);
        return res;
    }

    public Cursor getApprovedBookRequest() //To display approved activity room booking request according to each student email
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String status="Approved";
        String data=MainActivity.value;

        Cursor res = db.rawQuery("select * from "+TABLE_NAME_BOOK+" WHERE "+
                COL_Booking_Status +"='"+status+"'"+ " AND " +COL_StuID+"='"+data+"'",null);
        return res;
    }


    //For lecturer
    public boolean updateDataBooking(String status) //To update activity room booking request status
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_Booking_Status,"Approved");
        db.update(TABLE_NAME_BOOK, contentValues, "Status_Booking=" + status, null);

        return true;
    }

    //For lecturer
    public boolean updateDataLeave(String status)//To update leave of absence request status
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_6,"Approved");
        db.update(TABLE_NAME, contentValues, "Status_Leave=" + status, null);

        return true;
    }

}
