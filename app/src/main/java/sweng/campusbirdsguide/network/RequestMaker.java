package sweng.campusbirdsguide.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RequestMaker {
    private final RequestQueue queue;

    public RequestMaker(Context applicationContext) {
        this.queue = Volley.newRequestQueue(applicationContext);
    }

    public void query(String url, Result result) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, result::onSuccess, result::onError);

        queue.add(stringRequest);
    }
}
