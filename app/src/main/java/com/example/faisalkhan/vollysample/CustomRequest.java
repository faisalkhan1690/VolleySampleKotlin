package com.example.faisalkhan.vollysample;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1><u><font color="green">Custom Request</font></u></h1>
 *
 * <p>Volley doesn't provide a method for setting the cookies of a request, nor its priority.
 * It probably will in the future, since it's a serious omission.
 * For the time being, however, you have to extend the Request class.</p>
 *
 * <p>For the priority, you also need to extend the Request class, overriding the getPriority method.</p>
 *
 * <p>---Faisal</p>
 */
public class CustomRequest extends JsonObjectRequest {

    Priority mPriority;

    public CustomRequest(int method, String url, JSONObject jsonRequest,
                         Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    private Map<String, String> headers = new HashMap<>();

    /**
     * For managing cookies, you can play with the headers of the request, overriding the getHeaders method
     *
     * <p>---Faisal</p>
     *
     * @param cookies ArrayList<String>
     */
    public void setCookies(List<String> cookies) {
        StringBuilder sb = new StringBuilder();
        for (String cookie : cookies) {
            sb.append(cookie).append("; ");
        }
        headers.put("Cookie", sb.toString());
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers;
    }

    /**
     * <p><u>Priority.LOW</u>// images, thumbnails, ...</p>
     * <p><u>Priority.NORMAL</u>// residual</p>
     * <p><u>Priority.HIGH</u>// descriptions, lists, ...</p>
     * <p><u>Priority.IMMEDIATE</u>// login, logout, ...</p>
     *
     * @param priority
     */
    public void setPriority(Priority priority) {
        mPriority = priority;
    }

    @Override
    public Priority getPriority() {
        // If you didn't use the setPriority method,the priority is automatically set to NORMAL
        return mPriority != null ? mPriority : Priority.NORMAL;
    }

}
