package com.example.codelyokophone;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static Context myContext;

    public static Context getContext() {
        return myContext;
    }

    public static void setContext(Context myContext) {
        myContext = myContext;
    }

}
