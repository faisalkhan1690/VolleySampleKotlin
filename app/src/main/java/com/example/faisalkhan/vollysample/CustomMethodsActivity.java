package com.example.faisalkhan.vollysample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *<H1><u><font color="green">Main Activity</font></u></H1>
 *
 * A Demo project for understand how to use Volley in your project for better understanding refer this
 * <a href="http://developer.android.com/training/volley/index.html">Link</a> <p>--- Faisal</p>
 *
 * <p>Volley doesn't provide a method for setting the cookies of a request, nor its priority.
 * It probably will in the future, since it's a serious omission.
 * For the time being, however, you have to extend the Request class. and we are going to use this class here in this activity.</p>
 * <p>For normal request Method please go in DefaultmethodsActivity.class</p>
 *
 */
public class CustomMethodsActivity extends AppCompatActivity {

    private TextView tvResult;
    public final String TAG=CustomMethodsActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_methods);

        tvResult=(TextView)findViewById(R.id.tv_result);

    }

    /**
     * <p>By default volley does not provide cookies for that we have extended JsonRequest </p>
     *
     * <p></p>---- Faisal
     *
     * @param view View class object
     */
    public void CustomJsonRequestWithCookies(View view){

        String url = "http://httpbin.org/get?site=code&network=tutsplus";

        CustomRequest jsonRequest = new CustomRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null) {
                            tvResult.setText(Html.fromHtml(response.toString()));
                        } else {
                            tvResult.setText("Response NULL");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.getMessage());
                    }
                });

        List<String> cookies = new ArrayList<>();
        cookies.add("site=code");
        cookies.add("network=tutsplus");

        jsonRequest.setCookies(cookies);
        jsonRequest.setPriority(Request.Priority.NORMAL);
        Volley.newRequestQueue(this).add(jsonRequest);
    }
}
