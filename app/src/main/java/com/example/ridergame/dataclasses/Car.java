package com.example.ridergame.dataclasses;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Car {
    private float x;
    private float y;
    private float m1;
    private float jumpHeight;
    private boolean isJumping = false;
    Bitmap bitmapCar;
    boolean carJump;
    MyPoint p;
    private static  int JUMP_HEIGHT = 1000;


    public Car(float x, float y, Bitmap bitmap) {
        this.x = x;
        this.y = y;
        this.bitmapCar = bitmap;
        this.jumpHeight = y; // Initialize jumpHeight to the original y position
    }

    public void setM1(float m1) {
        this.m1 = m1;
    }

    public void setPosition(MyPoint pPosition) {
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

    public void jump() { // called via button click
        if (!isJumping) {
            carJump = true;
            isJumping = true;
            jumpHeight = y;
            y = y - JUMP_HEIGHT;
        }
    }

    public void update() { // happens automatically by the game loop
        if (isJumping) {
            y += JUMP_HEIGHT; // Adjust this value to control the speed of descent
            if (y >= jumpHeight) {
                y = jumpHeight;
                carJump = false;
                isJumping = false;
            }
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isJumping() {
        return isJumping;
    }
}
