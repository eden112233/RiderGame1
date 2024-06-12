package com.example.ridergame.dataclasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Random;

public class Road {
    private Context context;
    private ArrayList<MyPoint> arrayList;
    private Paint p;
    private Bitmap dim;
    private Bitmap candy;
    Random rnd;
    int counter = 0;


    public Road(Bitmap b1, Bitmap c1, Context context) //פעולה  בונה
    {
        rnd = new Random();
        this.dim = b1;
        this.candy = c1;
        this.context = context;
        p = new Paint();//draw on the canvas
        p.setStrokeWidth(20);

        arrayList = new ArrayList<>();
        MyPoint p0 = new MyPoint(0, 1100);
        arrayList.add(p0);
        MyPoint p1 = new MyPoint(100, 1200);
        arrayList.add(p1);
        MyPoint p2 = new MyPoint(200, 1200);
        arrayList.add(p2);
        MyPoint p4 = new MyPoint(400, 1200);
        arrayList.add(p4);
        MyPoint p6 = new MyPoint(500, 1400);
        arrayList.add(p6);
        MyPoint p7 = new MyPoint(600, 1500);
        arrayList.add(p7);
        MyPoint p8 = new MyPoint(700, 1600);
        arrayList.add(p8);
        MyPoint p9 = new MyPoint(800, 1700);
        arrayList.add(p9);
    }


    public void draw(Canvas canvas) {
        for (int i = 0; i < arrayList.size() - 1; i++) {
            canvas.drawLine(arrayList.get(i).getX(), arrayList.get(i).getY(), arrayList.get(i + 1).getX(), arrayList.get(i + 1).getY(), p);
            if (arrayList.get(i) instanceof Diamond)
                ((Diamond) arrayList.get(i)).draw(canvas);
            if (arrayList.get(i) instanceof Obstacle)
                ((Obstacle) arrayList.get(i)).draw(canvas);
        }
    }

    public float getM()//שיפוע בין 2 נקןדות על הכביש
    {
        if (arrayList.get(1) instanceof Diamond)
            return 0;
        if (arrayList.get(1) instanceof Obstacle)
            return 0;
        float y = arrayList.get(0).getY() - arrayList.get(1).getY();
        float x = ((arrayList.get(0).getX() - arrayList.get(1).getX()));
        float m1 = (y / x);
        if (m1 == 1)
            return 45;
        if (m1 == 0)
            return 0;
        if (m1 == -1)
            return -45;
        return m1;

    }

    public MyPoint getPosition()//מיקום של נקודה על הכביש
    {
        float xPosition = arrayList.get(0).getX();
        float yPosition = arrayList.get(0).getY();
        MyPoint pPosition = new MyPoint(xPosition, yPosition);
        return pPosition;
    }


    public void move() {
        //DeleteFirstCell();
        arrayList.remove(0);
        if (arrayList.get(0) instanceof Diamond) {
            arrayList.remove(0);
        }
        if (arrayList.get(0) instanceof Obstacle) {
            arrayList.remove(0);
        }
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).moveX();
        }
        int rndY = rnd.nextInt(3) - 1;

        int screenHeight = getScreenHeight(context);
        if (arrayList.get(arrayList.size() - 1).getY() < screenHeight / 4) {
            rndY = 1;
        } else if (arrayList.get(arrayList.size() - 1).getY() > screenHeight * 3 / 4) {
            rndY = -1;
        }

        float tempX = arrayList.get(arrayList.size() - 1).getX() + 100;
        float tempY = (arrayList.get(arrayList.size() - 1).getY() + rndY * 100);
        MyPoint ppp = new MyPoint(tempX, tempY);
        arrayList.add(ppp);

        int randomNumber = 4 + rnd.nextInt(5); // 4 to 8

        if (counter % randomNumber == 0)//יהלום
        {
            Diamond d = new Diamond(tempX, tempY, dim, 0);
            arrayList.add(d);
        }


        if (counter % (randomNumber + 2) == 0 && counter % 5 != 0)//קונוס
        {
            Obstacle b = new Obstacle(tempX, tempY, candy, 0);
            arrayList.add(b);
        }
        counter++;

    }

    public int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public boolean checkCollisionD(Car car) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) instanceof Diamond) {
                Diamond diamond = (Diamond) arrayList.get(i);
                if (isCollidingCarDiamond(car, diamond)) {
                    // Handle the collision (e.g., remove diamond, update score)
                    arrayList.remove(diamond);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkCollisionC(Car car) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) instanceof Obstacle) {
                Obstacle candy = (Obstacle) arrayList.get(i);
                if (isCollidingCarCandy(car, candy)) {

                    // Handle the collision (e.g., remove diamond, update score)
                    //arrayList.remove(candy);
                    return true;
                }
            }
        }
        return false;
    }


    private boolean isCollidingCarDiamond(Car car, Diamond diamond) {
        float carLeft = car.getX();
        float carRight = car.getX() + car.bitmapCar.getWidth();
        float carTop = car.getY();
        float carBottom = car.getY() + car.bitmapCar.getHeight();

        float diamondLeft = diamond.getX();
        float diamondRight = diamond.getX() + diamond.bitmapdiamond.getWidth();
        float diamondTop = diamond.getY() - diamond.bitmapdiamond.getHeight();
        float diamondBottom = diamond.getY();

        return carRight > diamondLeft && carLeft < diamondRight &&
                carBottom > diamondTop && carTop < diamondBottom;
    }

    private boolean isCollidingCarCandy(Car car, Obstacle candy) {
        float carLeft = car.getX();
        float carRight = car.getX() + car.bitmapCar.getWidth();
        float carTop = car.getY();
        float carBottom = car.getY() + car.bitmapCar.getHeight();

        float candyLeft = candy.getX();
        float candyRight = candy.getX() + candy.bitmapC.getWidth();
        float candyTop = candy.getY() - candy.bitmapC.getHeight();
        float candyBottom = candy.getY();
        return carRight > candyLeft && carLeft < candyRight &&
                    carBottom > candyTop && carTop < candyBottom;
        }


    public boolean didUserTouchCandy(float x, float y) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) instanceof Obstacle) {
                if(((Obstacle)arrayList.get(i)).didUserTouchMe(x,y))
                {
                    arrayList.remove(i);
                    return true;
                }

            }

        }
        return false;


    }
}
