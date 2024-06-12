package com.example.ridergame.dataclasses;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Buttons
{
    Bitmap bitmap;
    private int x;
    private int y;



    public Buttons(Bitmap bitmap, int x, int y){
        this.bitmap =bitmap;
        this.x=x;
        this.y=y;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, this.x, this.y, null);
    }

}
