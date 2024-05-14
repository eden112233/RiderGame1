package com.example.ridergame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class Obstacle extends Point
{
    Bitmap bitmapCone;
    float m1;

    public Obstacle(float x, float y) {
        super(x, y);
    }

    public Obstacle(float x, float y, Bitmap bitmapCone, float m1) {
        super(x, y);
        this.bitmapCone = bitmapCone;
        this.m1=m1;
    }

    public void SetM(float m1){
        this.m1=m1;
    }

    public void draw(Canvas canvas)
    {

        float bitmapCenterX = (this.x + (bitmapCone.getWidth() / 2));
        float bitmapCenterY = this.y + (bitmapCone.getHeight() / 2);
        canvas.save();
        canvas.rotate(m1,bitmapCenterX , bitmapCenterY);
        canvas.drawBitmap(bitmapCone, x, y-(bitmapCone.getHeight()), null);
        canvas.restore();
    }

}

