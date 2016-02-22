package com.example.faisalkhan.vollysample;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 *<H1><u><font color="green">Main Activity</font></u></H1>
 *
 * A Demo project for understand how to use Volley in your project for better understanding refer this
 * <a href="http://developer.android.com/training/volley/index.html">Link</a> <p>--- Faisal</p>
 *
 * <p>Here we are using default request provided by Volley. For custom Request operations go to activity CustomMethodsActivity.class</p>
 *
 */
public class DefaultMethodsActivity extends AppCompatActivity {

    public final String TAG=DefaultMethodsActivity.class.getSimpleName();

    private TextView tvResult;
    private ImageView ivResult;
    private RequestQueue requestQueue=null;

    private String lastRequest=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_method);

        tvResult=(TextView)findViewById(R.id.tv_result);
        ivResult = (ImageView) findViewById(R.id.iv_result);

        requestQueue=VolleySingletonClass.getInstance(this);

    }


    /**
     * <p>Sample how to hit and handle response of String Request using StringRequest(Volley)</p>---- Faisal
     *
     * @param view View class object
     */
    public void onStringRequest(View view){
        String url = "http://httpbin.org/html";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response!=null){
                            tvResult.setText(Html.fromHtml(response));
                        }else{
                            tvResult.setText("Response NULL");
                        }
                        ivResult.setVisibility(View.GONE);
                        tvResult.setVisibility(View.VISIBLE);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.getMessage());
            }
        });

        stringRequest.setTag("stringRequest");
        lastRequest="stringRequest";
        requestQueue.add(stringRequest);
    }



    /**
     * <p>Sample how to hit and handle response of String Request using StringRequest using POST(Volley)</p>---- Faisal
     *
     * @param view View class object
     */
    public void onStringRequestPost(View view){
        String url = "http://httpbin.org/post";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response!=null){
                            tvResult.setText(Html.fromHtml(response));
                        }else{
                            tvResult.setText("Response NULL");
                        }
                        ivResult.setVisibility(View.GONE);
                        tvResult.setVisibility(View.VISIBLE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                params.put("site", "code");
                params.put("network", "tutsplus");
                return params;
            }
        };

        postRequest.setTag("postRequest");
        lastRequest="postRequest";
        requestQueue.add(postRequest);
    }


    /**
     * <p>Sample how to hit and handle response of Image Request using ImageRequest(Volley)</p>---- Faisal
     *
     * @param view View class object
     */

    public void onImageRequest(View view){
        String url = "http://i.imgur.com/Nwk25LA.jpg";

        ImageRequest imgRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {

                        ivResult.setVisibility(View.VISIBLE);
                        tvResult.setVisibility(View.GONE);
                        ivResult.setImageBitmap(response);
                    }

                }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ivResult.setBackgroundColor(Color.parseColor("#ff0000"));
                Log.d(TAG, error.getMessage());
            }
        });

        imgRequest.setTag("imgRequest");
        lastRequest="imgRequest";
        requestQueue.add(imgRequest);
    }

    /**
     * <p>Sample how to hit and handle response of Json Request using JsonRequest(Volley)</p>---- Faisal
     *
     * @param view View class object
     */
    public void onJsonRequest(View view){

        String url = "http://httpbin.org/get?site=code&network=tutsplus";

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null) {
                            tvResult.setText(Html.fromHtml(response.toString()));
                        } else {
                            tvResult.setText("Response NULL");
                        }
                        ivResult.setVisibility(View.GONE);
                        tvResult.setVisibility(View.VISIBLE);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.getMessage());
                    }
                });

        jsonRequest.setTag("jsonRequest");
        lastRequest="jsonRequest";
        requestQueue.add(jsonRequest);
    }

    /**
     * <p>Sample how to cancel a specific request in Volley </p>---- Faisal
     *
     * @param view View class object
     */
    public void cancelLastRequest(View view){
        if(lastRequest!=null){
            requestQueue.cancelAll(lastRequest);
            Toast.makeText(DefaultMethodsActivity.this, "Canceled successfully", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(DefaultMethodsActivity.this, "No last request", Toast.LENGTH_SHORT).show();
    }

    /**
     * <p>Sample how to cancel all requests in Volley </p>---- Faisal
     *
     * @param view View class object
     */
    public void cancelAllRequests(View view){
        requestQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                //because are canceling all requests so put true in return type
                return true;
            }
        });
    }
}
