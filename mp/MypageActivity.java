package doyeon.mp.mp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MypageActivity extends Activity {
    ProgressHandler handler;
    TextView Date;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 \n  HH시 mm분 ss초");
    String time;

    private int step_count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);
        //시계
        Date = (TextView) findViewById(R.id.threadDate);
        handler = new ProgressHandler();
        runTime();

        //걸음수
        MyApplication myApp = (MyApplication)getApplication();
        step_count=myApp.getgValue();

        TextView tv = findViewById(R.id.stepCount);
        tv.setText("오늘 총 "+step_count+" 걸음 걸었어요!");
        tv.setTextColor(Color.BLACK);

        ImageView mypageBtm = (ImageView)findViewById(R.id.imageBtm);
        mypageBtm.setImageResource(R.drawable.walk);

        //원차트
        circleChart circleC = findViewById(R.id.circlechart);
        circleC.Point = step_count;
        circleC.invalidate();
    }

    public void runTime(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        time = sdf.format(new Date(System.currentTimeMillis()));
                        Message message = handler.obtainMessage();
                        handler.sendMessage(message);

                        Thread.sleep(1000);
                    } catch (InterruptedException ex){}
                }
            }
        });
        thread.start();
    }
    class ProgressHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Date.setText(time);
            Date.setTextSize(35);

        }
    }

}
class circleChart extends View {
    public circleChart(Context context) {
        super(context);
    }

    public circleChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public circleChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float Point=0.0f;
    public void onDraw(Canvas canvas){
        int x = canvas.getWidth()/6;
        int y = canvas.getWidth()*5/6;

        final float START_POINT = -90f;
        final float ANGLE_PER_SCORE = 360.f/100.0f;

        float angle = Point*ANGLE_PER_SCORE;

        RectF rectF = new RectF(x,x-50,y,y-50);

        Paint p = new Paint();

        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(y/7);
        p.setAlpha(0x00);
        p.setColor(Color.rgb(189,189,189));

        //회색 부분 설정
        canvas.drawArc(rectF, START_POINT , -360 + angle, false, p);

        //강조되는 빨간 부분
        p.setColor(Color.rgb(216,27,96));
        p.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(rectF, START_POINT, angle, false, p);

        //순서대로 회색 강아지
        Bitmap dogPic1 = BitmapFactory.decodeResource(getResources(),R.drawable.lv1);
        canvas.drawBitmap(dogPic1,x/6-20, 5*x,null);
        Bitmap dogPic2 = BitmapFactory.decodeResource(getResources(),R.drawable.lv2);
        canvas.drawBitmap(dogPic2,x+40, 5*x,null);
        Bitmap dogPic3 = BitmapFactory.decodeResource(getResources(),R.drawable.lv3);
        canvas.drawBitmap(dogPic3,x*2+60, 5*x,null);
        Bitmap dogPic4 = BitmapFactory.decodeResource(getResources(),R.drawable.lv4);
        canvas.drawBitmap(dogPic4,x*3+70, 5*x,null);
        Bitmap dogPic5 = BitmapFactory.decodeResource(getResources(),R.drawable.lv5);
        canvas.drawBitmap(dogPic5,x*4+70, 5*x,null);


        //수정 - 칼라강아지로
        if (Point >10 ){
            dogPic1 = BitmapFactory.decodeResource(getResources(),R.drawable.plv1);
            canvas.drawBitmap(dogPic1,x/6-20, 5*x,null);}
        if (Point> 20 ) {
            dogPic2 = BitmapFactory.decodeResource(getResources(), R.drawable.plv3);
            canvas.drawBitmap(dogPic2, x + 40, 5*x, null);}
        if (Point > 30) {
            dogPic3 = BitmapFactory.decodeResource(getResources(), R.drawable.plv2);
            canvas.drawBitmap(dogPic3, x * 2 + 60, 5*x, null);}
        if (Point > 40) {
            dogPic4 = BitmapFactory.decodeResource(getResources(), R.drawable.plv4);
            canvas.drawBitmap(dogPic4, x * 3 + 70, 5*x, null);}
        if (Point >= 50) {
            dogPic5 = BitmapFactory.decodeResource(getResources(), R.drawable.plv5);
            canvas.drawBitmap(dogPic5, x * 4 + 70, 5*x, null); }


        Paint t = new Paint();
        t.setColor(Color.BLACK);
        t.setTextSize(y / 7);

        canvas.drawText(String.valueOf((int) Point) + "%", x * 2+ x/2 , x + (y - x) / 2 , t);



    }
}