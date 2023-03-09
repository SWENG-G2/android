package sweng.campusbirdsguide.network;

import com.android.volley.VolleyError;

public interface Result {
    void onSuccess(String response);

    void onError(VolleyError volleyError);
}
