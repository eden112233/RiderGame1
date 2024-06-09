package com.example.ridergame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class Obstacle extends MyPoint
{
    Bitmap bitmapC;
    float m1;

    public Obstacle(float x, float y) {
        super(x, y);
    }

    public Obstacle(float x, float y, Bitmap bitmapC, float m1) {
        super(x, y);
        this.bitmapC = bitmapC;
        this.m1=m1;
    }

    public void SetM(float m1){
        this.m1=m1;
    }

    public void draw(Canvas canvas)
    {

        float bitmapCenterX = (this.x + (bitmapC.getWidth() / 2));
        float bitmapCenterY = this.y + (bitmapC.getHeight() / 2);
        canvas.save();
        canvas.rotate(m1,bitmapCenterX , bitmapCenterY);
        canvas.drawBitmap(bitmapC, x, y-(bitmapC.getHeight()), null);
        canvas.restore();
    }


}

