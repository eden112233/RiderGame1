package com.example.ridergame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

class Road {
    private ArrayList<Point> arrayList;
    private Paint p;
    private Bitmap dim;
    private Bitmap conus;
    Random rnd;
    int counter = 0;
    
    

    public Road(Bitmap b1,Bitmap c1) //פעולה  בונה
    {
        rnd = new Random();
        this.dim = b1;
        this.conus=c1;
        p = new Paint();//draw on the canvas
        p.setStrokeWidth(20);

        arrayList = new ArrayList<>();
        Point p0= new Point(0,1100);
        arrayList.add(p0);
        Point p1=new Point(100,1200);
        arrayList.add(p1);
        Point p2=new Point(200,1200);
        arrayList.add(p2);
        Diamond d1=new Diamond(300, 1200, b1,0);
        arrayList.add(d1);
        Point p4=new Point(400,1200);
        arrayList.add(p4);
        //Obstacle cone=new Obstacle(500, 1300, c1,0);
        //arrayList.add(cone);
        Point p6=new Point(500,1400);
        arrayList.add(p6);
        Point p7=new Point(600,1500);
        arrayList.add(p7);
        Point p8=new Point(700,1600);
        arrayList.add(p8);
        Point p9= new Point(800,1700);
        arrayList.add(p9);
    }



    public void draw(Canvas canvas)
    {
        for (int i = 0; i < arrayList.size()-1; i++)
        {
            canvas.drawLine(arrayList.get(i).getX(),arrayList.get(i).getY(),arrayList.get(i+1).getX(),arrayList.get(i+1).getY(),p);
            if(arrayList.get(i) instanceof Diamond)
                ((Diamond) arrayList.get(i)).draw(canvas);
            if(arrayList.get(i) instanceof Obstacle)
                ((Obstacle) arrayList.get(i)).draw(canvas);
        }
    }

    public float getM ()//שיפוע בין 2 נקןדות על הכביש
    {
        if(arrayList.get(1) instanceof Diamond)
            return 0;
        if(arrayList.get(1) instanceof Obstacle)
            return 0;
        float y = arrayList.get(0).getY()-arrayList.get(1).getY();
        float x=((arrayList.get(0).getX()-arrayList.get(1).getX()));
        float m1 =  (y/x);
        if(m1 == 1)
            return 45;
        if(m1 == 0)
            return 0;
        if(m1 == -1)
            return -45;
        return m1;

    }

    public Point getPosition()//מיקום של נקודה על הכביש
    {
        float xPosition=arrayList.get(0).getX();
        float yPosition=arrayList.get(0).getY();
        Point pPosition=new Point(xPosition,yPosition);
        return pPosition;
    }


    public void move() {
        //DeleteFirstCell();
        arrayList.remove(0);
        if (arrayList.get(0) instanceof Diamond){
            arrayList.remove(0);
        }
        if (arrayList.get(0) instanceof Obstacle){
            arrayList.remove(0);
        }
        for (int i = 0; i < arrayList.size(); i++)
        {
            arrayList.get(i).moveX();
        }
        int rndY = rnd.nextInt(3)-1;
        if(arrayList.get(arrayList.size()-1).getY() > 1500)
            rndY = -1;

        float tempX= arrayList.get(arrayList.size()-1).getX() + 100;
        float tempY=(arrayList.get(arrayList.size()-1).getY() + rndY*100);
        Point ppp=new Point(tempX,tempY);
        arrayList.add(ppp);



       if(counter %5 == 0)//יהלום
       {
            Diamond d =new Diamond(tempX ,tempY,dim,0);
           arrayList.add(d);
        }


      if(counter %7 ==0 && counter%5!=0)//קונוס
        {
        Obstacle b =new Obstacle(tempX ,tempY,conus,0);
       arrayList.add(b);
       }
       counter++;

    }


}