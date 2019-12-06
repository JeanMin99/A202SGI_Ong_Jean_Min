package com.example.ongje.rusher;

public class Info_BookRoom {

    public static final int TEXT_TYPE=0;
    public static final int IMAGE_TYPE=1;

    //Activity Room booking info
    public int type;
    public int data;
    public String text;

    public Info_BookRoom(int type, String text, int data)
    {
        this.type=type;
        this.data=data;
        this.text=text;
    }
}
