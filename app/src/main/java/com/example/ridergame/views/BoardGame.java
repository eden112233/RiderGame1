package com.example.ridergame.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
// import androidx.compose.ui.graphics.Paint;

import com.example.ridergame.Buttons;
import com.example.ridergame.Car;
import com.example.ridergame.MyPoint;
import com.example.ridergame.R;
import com.example.ridergame.Road;

public class BoardGame extends View {
   private Car car;
   private Road rodeAndObstacles;
   private Buttons jumpButton, goButton;
   Handler handler;//מטפל - מקשר בין הthread לboardgame
   ThreadGame threadGame;//הפנייה לthread שכתבנו
   float m1;
   public boolean isRun = true;
  // Paint p = new Paint();

   public BoardGame(Context context) {
      super(context);
      Bitmap bitmapdiamond = BitmapFactory.decodeResource(getResources(), R.drawable.diamond);
      Bitmap bitmapCone = BitmapFactory.decodeResource(getResources(), R.drawable.cone);
      rodeAndObstacles = new Road(bitmapdiamond, bitmapCone, getContext());
      m1 = rodeAndObstacles.getM();
      Bitmap bitmapCar = BitmapFactory.decodeResource(getResources(), R.drawable.img);
      bitmapCar = Bitmap.createScaledBitmap(bitmapCar, (int) 250, (int) 250, true);
      car = new Car(400, 400, bitmapCar);
      car.setM1(m1);
      MyPoint pPosition = rodeAndObstacles.getPosition();
      car.setPosition(pPosition);
      Bitmap bitmapjump = BitmapFactory.decodeResource(getResources(), R.drawable.jump);
      jumpButton = new Buttons(bitmapjump, 100, 100);
      Bitmap bitmapgo = BitmapFactory.decodeResource(getResources(), R.drawable.go);
      goButton = new Buttons(bitmapgo, 300, 100);

      threadGame=new ThreadGame();// יצירת עצם מהמחלקה threadgame ע"י זימון פעולה בונה
      threadGame.start();// מריץ בתור שרד נפרד ולא במקביל עם עוד שרדים. not threadGame.run().

      handler = new Handler(new Handler.Callback() {
         @Override
         public boolean handleMessage(@NonNull Message message) {
            rodeAndObstacles.move();
            MyPoint pPosition = rodeAndObstacles.getPosition();
            car.setPosition(pPosition);

            m1 = rodeAndObstacles.getM();
            car.setM1(m1);
            car.update();  // Update car position before redrawing
            invalidate(); //מוחק את הבורד גיים וקורא לondraw
            return false;
         }
      });
   }

   @Override
   protected void onDraw(Canvas canvas) {
      //canvas.drawText("Score:", 1000, 4000, p);
      super.onDraw(canvas);
      Bitmap sky = BitmapFactory.decodeResource(getResources(), R.drawable.sky);
      sky = Bitmap.createScaledBitmap(sky, canvas.getWidth(), canvas.getHeight(), false);
      canvas.drawBitmap(sky, 0, 0, null);
      jumpButton.draw(canvas);
      goButton.draw(canvas);
      rodeAndObstacles.draw(canvas);
      car.draw(canvas);

     // road.checkCollision(car);
   }

   @Override
   public boolean onTouchEvent(MotionEvent event) {
      if (event.getAction() == MotionEvent.ACTION_DOWN) {
         if (jumpButton.didUserTouchMe(event.getX(), event.getY())) {
            car.jump();
         }
         isRun = true;
      }
      if (event.getAction() == MotionEvent.ACTION_MOVE) {
         isRun = true;
      }
      if (event.getAction() == MotionEvent.ACTION_UP) {
     //    isRun = false;
      }
      invalidate();
      return true;
   }

   public class ThreadGame extends Thread {
      @Override
      public void run() {//השרד רץ כל הזמן במקביל
         super.run();//פקודה של מערכת ההפעלה שבמקרה שמוחקים את הthread כשהוא ישן היא אומרת לה תנסי להעיר את הthread, אם לא תצליחי-לפני שהתוכנית תתעופף, תבואי לcatch שיתפוס אותו
         while (true) {//לולאה אינסופית
            try {
               sleep(1500);
               if (isRun)
                  handler.sendEmptyMessage(0);

            } catch (InterruptedException e) {
               throw new RuntimeException(e);
            }
         }
      }
   }
}
