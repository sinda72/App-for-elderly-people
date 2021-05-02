package doyeon.mp.mp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ImageView iv_dal = (ImageView)findViewById(R.id.dal);
        Animation anim_dal = AnimationUtils.loadAnimation(this,R.anim.moving_moon1);
        iv_dal.startAnimation(anim_dal);


        AnimationDrawable animation_drawable = new AnimationDrawable();

        BitmapDrawable frame1 = (BitmapDrawable)getResources().getDrawable(R.drawable.d1);
        BitmapDrawable frame2 = (BitmapDrawable)getResources().getDrawable(R.drawable.d2);
        BitmapDrawable frame3 = (BitmapDrawable)getResources().getDrawable(R.drawable.d6);

        animation_drawable.addFrame(frame1, 500);
        animation_drawable.addFrame(frame2, 500);
        animation_drawable.addFrame(frame3, 500);

        iv_dal.setBackgroundDrawable(animation_drawable);
        animation_drawable.start();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

}
