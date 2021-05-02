package doyeon.mp.mp;

import android.app.Application;

public class MyApplication extends Application {
    private int gValue;

    public int getgValue(){
        return gValue;
    }

    public void setgValue(int mValue){
        this.gValue = mValue;
    }
}
