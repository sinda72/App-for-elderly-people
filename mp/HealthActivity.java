package doyeon.mp.mp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HealthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        //Typeface typeface = Typeface.createFromAsset(getAssets(), "font.ttf");
        //textView2.setTypeface(typeface);
        //TextView textView1 = (TextView)findViewById(R.id.textView1);
        //textView1.setTypeface(typeface);

        Gallery gallery = (Gallery)findViewById(R.id.gallery1);

        MyGalleryAdapter galAdapter = new MyGalleryAdapter(this);
        gallery.setAdapter(galAdapter);
    }

    public class MyGalleryAdapter extends BaseAdapter {
        Context context;

        public MyGalleryAdapter(Context c) { context = c; }

        Integer[] healthID = {R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4,
                R.drawable.h5, R.drawable.h6, R.drawable.h7, R.drawable.h8, R.drawable.h9};

        Resources res = getResources();

        public int getCount() {
            return healthID.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, final View convertView, ViewGroup parent) {
            final ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new Gallery.LayoutParams(350,150));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,10,5,-10);
            imageView.setImageResource(healthID[position]);

            final int pos = position;
            imageView.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {

                    WebView wv = (WebView) findViewById(R.id.webvw);
                    wv.setWebViewClient(new WebViewClient());

                    WebSettings webSettings = wv.getSettings();
                    webSettings.setJavaScriptEnabled(true);

                    if(pos==0)
                        wv.loadUrl("https://blog.naver.com/chilkabfood/221228792536");
                    else if(pos==1)
                        wv.loadUrl("https://blog.naver.com/chilkabfood/221228080957");
                    else if(pos==2)
                        wv.loadUrl("https://blog.naver.com/chilkabfood/221187935320");
                    else if(pos==3)
                        wv.loadUrl("https://blog.naver.com/chilkabfood/221353447080");
                    else if(pos==4)
                        wv.loadUrl("https://blog.naver.com/chilkabfood/221287537770");
                    else if(pos==5)
                        wv.loadUrl("https://blog.naver.com/chilkabfood/221402305084");
                    else if(pos==6)
                        wv.loadUrl("https://m.post.naver.com/viewer/postView.nhn?volumeNo=8972478&memberNo=21526956");
                    else if(pos==7)
                        wv.loadUrl("https://blog.naver.com/chilkabfood/221045239310");
                    else
                        wv.loadUrl("https://blog.naver.com/chilkabfood/221413003232");


                    return false;
                }

            });

            return imageView;
        }
    }
}



