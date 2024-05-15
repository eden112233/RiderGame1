package com.example.ridergame;

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
}
