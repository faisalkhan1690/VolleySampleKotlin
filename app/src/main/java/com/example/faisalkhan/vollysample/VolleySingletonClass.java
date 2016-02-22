package com.example.faisalkhan.vollysample;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * <h1><u><font color="green">Volley Singleton Class</font></u></h1>
 *
 * <p>Singleton class for Volley. For every operation you should use instance of this class. So that you can cancel and perform
 * other changes on the fly
 * <p>---- Faisal
 */
public class VolleySingletonClass {

    private static RequestQueue requestQueue=null;

    private VolleySingletonClass(Context context){
        requestQueue=Volley.newRequestQueue(context);
    }

    /**
     * You can get instance of VolleySingletonClass using this method
     * @param context Activity context
     * @return RequestQueue(Volley) instance
     */
    public static RequestQueue getInstance(Context context){

        if(requestQueue==null){
            new VolleySingletonClass(context);
        }
        return requestQueue;
    }

}
