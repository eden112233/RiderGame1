package com.example.ridergame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class Car {
    private float x;
    private float y;
    private  float m1;
    Bitmap bitmapCar;
    boolean carJump;
    Point p;


    public Car(float x, float y, Bitmap bitmap) {
        this.x = x;
        this.y = y;
        this.bitmapCar = bitmap;
    }


    public void setM1(float m1)
    {
        this.m1 = m1;
    }

    public void setPosition(Point pPosition)
    {
         this.x = pPosition.getX();
        this.y=pPosition.getY()- bitmapCar.getHeight();
        if(carJump) //לעשןת counter שאחרי 10 פעמים שזה מוריד אותו ב20 זה עוצר כדי שזה יגיע ל200 שזה כמה שהעלנו
        {
            y = y + 20;
        }

    }

    public void draw(Canvas canvas)
    {
        float bitmapCenterX = (this.x + (bitmapCar.getWidth() / 2));
        float bitmapCenterY = this.y + (bitmapCar.getHeight() / 2);
        canvas.save();
        canvas.rotate(m1, bitmapCenterX, bitmapCenterY);
        canvas.drawBitmap(bitmapCar, this.x, this.y, null);
        canvas.restore();
    }

    public int getCarHeight ()
    {
        int h= bitmapCar.getHeight();
        return h;
    }

    public void jump() {
        carJump = true;
        y = y -200;
    }
}