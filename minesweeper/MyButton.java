package com.example.soumya.minesweeper;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Soumya on 16-06-2017.
 */

public class MyButton extends Button{

    public MyButton(Context context)
    {
        super(context);}

     public int Count_Bomb=0;
     public boolean Mine_Status;
   //  String symbol="";
    boolean isClicked=false;
    public  boolean isFlag;
    public int x;
    public int y;


}
