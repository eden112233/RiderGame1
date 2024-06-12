package com.example.ridergame.dataclasses;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class Diamond extends MyPoint
{
    Bitmap bitmapdiamond;
    float m1;


    public Diamond(float x, float y) {
        super(x, y);
    }

    public Diamond(float x, float y, Bitmap bitmapdiamond, float m1) {
        super(x, y);
        this.bitmapdiamond = bitmapdiamond;
        this.m1=m1;
    }

    public void SetM(float m1){
        this.m1=m1;
    }

    public void draw(Canvas canvas)
    {
        float bitmapCenterX = (this.x + (bitmapdiamond.getWidth() / 2));
        float bitmapCenterY = this.y + (bitmapdiamond.getHeight() / 2);
        canvas.save();
        canvas.rotate(m1,bitmapCenterX , bitmapCenterY);
        canvas.drawBitmap(bitmapdiamond, x, y-(bitmapdiamond.getHeight()), null);
        canvas.restore();
    }

}
