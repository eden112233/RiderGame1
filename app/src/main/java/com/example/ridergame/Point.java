package com.example.ridergame;

import java.util.ArrayList;

class Point
{
   protected float x;
   protected float y;

   public Point(float x, float y) {
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
