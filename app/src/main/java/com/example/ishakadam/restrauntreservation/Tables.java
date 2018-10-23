package com.example.ishakadam.restrauntreservation;

public class Tables {

    String tablename;
    String isReserved;
    String reserveDate;
    String reserveTime;

    public Tables() {

    }

    public Tables( String tablename, String isReserved,
                  String reserveDate, String reserveTime) {

        this.tablename = tablename;
        this.isReserved = isReserved;
        this.reserveDate = reserveDate;
        this.reserveTime = reserveTime;
    }
}
