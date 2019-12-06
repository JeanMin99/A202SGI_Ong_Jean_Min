package com.example.ongje.rusher;

import java.io.Serializable;

public class Info_RequestData
{
    // To save leave of absence data
    public Long Number;
    public String Student;
    public String Leave_Date_To;
    public String Leave_Date_From;
    public String Leave_Reason;
    public String Status_Leave;

    // To save activity rooms booking data
    public String stuID;
    public String roomType;
    public String numOfStudent;
    public String durationBooking;
    public String bookDate;
    public String status_Booking;

    // To save canteen food ordering data
    public String studentID;
    public String foodType;
    public String quantity;
    public String remarks;
    public String total;
    public String status_Reserve;

    // To save shuttle services data
    public String studentIDShu;
    public String time;
    public String locations;
    public String status_Shuttle;

}
