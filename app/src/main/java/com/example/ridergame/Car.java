package com.example.ridergame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class Car {
    private float x;
    private float y;
    private float m1;
    private float jumpHeight;
    private boolean isJumping = false;
    Bitmap bitmapCar;
    boolean carJump;
    Point p;

    public Car(float x, float y, Bitmap bitmap) {
        this.x = x;
        this.y = y;
        this.bitmapCar = bitmap;
        this.jumpHeight = y; // Initialize jumpHeight to the original y position
    }

    public void setM1(float m1) {
        this.m1 = m1;
    }

    public void setPosition(Point pPosition) {
        this.x = pPosition.getX();
        this.y = pPosition.getY() - bitmapCar.getHeight();
        if (carJump) {
            y = y + 20;
        }
    }

    public void draw(Canvas canvas) {
        float bitmapCenterX = (this.x + (bitmapCar.getWidth() / 2));
        float bitmapCenterY = this.y + (bitmapCar.getHeight() / 2);
        canvas.save();
        canvas.rotate(m1, bitmapCenterX, bitmapCenterY);
        canvas.drawBitmap(bitmapCar, this.x, this.y, null);
        canvas.restore();
    }

    public int getCarHeight() {
        return bitmapCar.getHeight();
    }

    public void jump() {
        if (!isJumping) {
            carJump = true;
            isJumping = true;
            jumpHeight = y;
            y = y - 200;
        }
    }

    public void update() {
        if (isJumping) {
            y += 50; // Adjust this value to control the speed of descent
            if (y >= jumpHeight) {
                y = jumpHeight;
                carJump = false;
                isJumping = false;
            }
        }
    }
}
