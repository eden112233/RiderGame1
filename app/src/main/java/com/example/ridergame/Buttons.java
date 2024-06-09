package com.example.ridergame;

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

    public boolean didUserTouchMe(MyPoint point) {
        float xU = point.getX();
        float yU = point.getY();
        if(xU > x && xU < x + bitmap.getWidth() && yU>y && yU< y+bitmap.getHeight())
            return true;
        return false;
    }
}
