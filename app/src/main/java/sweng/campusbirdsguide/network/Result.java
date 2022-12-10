package sweng.campusbirdsguide.network;

import com.android.volley.VolleyError;

public interface Result {
    void onSuccess(String string);
    void onError(VolleyError volleyError);
}
