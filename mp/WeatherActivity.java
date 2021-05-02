package doyeon.mp.mp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);

        WeatherConnection weatherConnection = new WeatherConnection();
        //파싱을 위해 파싱된 데이터 리턴받음
        AsyncTask<String, String, String> result = weatherConnection.execute("","");

        TextView textView2 = (TextView)findViewById(R.id.textView2);
        TextView textView1 = (TextView)findViewById(R.id.textView1);

        Typeface typeface=Typeface.createFromAsset(getAssets(), "font.ttf");
        textView2.setTypeface(typeface);
        textView1.setTypeface(typeface);

        WeatherConnection2 weatherConnection2 = new WeatherConnection2();
        AsyncTask<String, String, String> result2 = weatherConnection2.execute("","");
        WeatherConnection3 weatherConnection3 = new WeatherConnection3();
        AsyncTask<String, String, String> result3 = weatherConnection3.execute("","");

        System.out.println("RESULT");
        ImageView weatherInfo =(ImageView) findViewById(R.id.weatherInfo);
        ImageView icon =(ImageView)findViewById(R.id.icon);
        Bitmap icon1 = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.msunny);
        Bitmap icon2 = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.mrain);
        Bitmap icon3 = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.mcloud);

        Bitmap icon11 = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.we1);
        Bitmap icon12 = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.we2);
        Bitmap icon13 = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.we3);

        BitmapDrawable bitmap1 = new BitmapDrawable(getResources(), icon1);
        BitmapDrawable bitmap2 = new BitmapDrawable(getResources(), icon2);
        BitmapDrawable bitmap3 = new BitmapDrawable(getResources(), icon3);

        BitmapDrawable rain = new BitmapDrawable(getResources(), icon11);

        BitmapDrawable sunny = new BitmapDrawable(getResources(), icon12);
        BitmapDrawable cloud = new BitmapDrawable(getResources(), icon13);

        try{
            String msg = result.get();
            System.out.println(msg);
            if(msg.matches(".*흐림.*")){  // 데이터에 흐림이 포함된 경우
                weatherInfo.setBackground(bitmap3);
                icon.setBackground(cloud);
            }else if(msg.matches(".*비.*")){  // 비가 포함된 경우
                weatherInfo.setBackground(bitmap2);
                icon.setBackground(rain);
            }else if(msg.matches(".*맑음.*")){  // 맑음이 포함된 경우
                weatherInfo.setBackground(bitmap1);
                icon.setBackground(sunny);
            }else{
                weatherInfo.setBackground(bitmap3);
                icon.setBackground(cloud);
            }
            String msg2 = result2.get();//출력 날씨 멘트
            String msg3 = result3.get();//출력 날씨 멘트

            System.out.println(msg2);
            textView2.setText(msg2.toString());
            textView1.setText(msg3.toString());


        }catch (Exception e){
        }
    }
    public class WeatherConnection extends AsyncTask<String, String, String>{
        // 백그라운드에서 작업하게 한다
        @Override
        protected String doInBackground(String... params) {

            // Jsoup을 이용한 날씨데이터 Pasing하기.
            try{
                String path = "https://weather.naver.com/rgn/townWetr.nhn?naverRgnCd=09170555";
                Document document = Jsoup.connect(path).get();
                Elements elements = document.select("div.fl em strong");//온도와 날씨
                System.out.println(elements);
                //Element targetElement = elements.get(0);
                String text = elements.text();

                System.out.println(text);

                return text;

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
    public class WeatherConnection2 extends AsyncTask<String, String, String> {
        protected String doInBackground(String... params) {

            try {
                String path = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=%EB%82%A0%EC%94%A8";
                Document document = Jsoup.connect(path).get();

                Elements elements = document.select("span.todaytemp");//날씨
                Element targetElement = elements.get(0);

                String text = targetElement.text()+"℃";
                System.out.println(text);
                return text;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public class WeatherConnection3 extends AsyncTask<String, String, String> {
        protected String doInBackground(String... params) {

            try {
                String path2 = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=%EC%B2%AD%ED%8C%8C%EB%8F%99+%EB%82%A0%EC%94%A8";
                Document document2 = Jsoup.connect(path2).get();
                Elements elements2 = document2.select("ul.info_list li p");//날씨

                Element targetElement2 = elements2.get(0);

                String text = targetElement2.text();

                System.out.println(text);

                return text;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
