package com.queue20.dogajdoori.Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class MyTurnQueue {

    public String UserId;
    public String StoreName;
    public long MyTurnNumber;
    public String CreatedDate;
    public String Status;


    public MyTurnQueue() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public MyTurnQueue(String UserId, String StoreName, long MyTurnNumber) {
        this.UserId = UserId;
        this.StoreName = StoreName;
        this.MyTurnNumber = MyTurnNumber;
    }

}
