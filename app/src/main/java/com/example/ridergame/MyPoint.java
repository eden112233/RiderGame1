package com.example.ridergame;

import android.graphics.Bitmap;

public class MyPoint
{
   protected float x;
   protected float y;

   public MyPoint(float x, float y) {
      this.x = x;
      this.y = y;
   }

   public float getX() {
      return x;
   }

   public float getY() {
      return y;
   }

   public void setX(float x) {
      this.x = x;
   }

   public void setY(float y) {
      this.y = y;
   }

   public void moveX() {
      x = x - 100;
   }

//TODO:
   public boolean didUserTouchMe(float x, float y) {

   }

   //public boolean didUserTouchMe(float xU, float yU, Bitmap candy) {
     // if(xU > x && xU < x + bitmapC.getWidth() && yU>y && yU< y+bitmapC.getHeight())
     //    return true;
    //  return false;
 //  }
}
