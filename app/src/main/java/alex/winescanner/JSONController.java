package alex.winescanner;

import android.app.Application;
import android.support.v7.util.AsyncListUtil;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class JSONController extends Application {

    private static final String TAG = JSONController.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static JSONController mInstance;

    private JSONObject jsonResult;

    public interface DataCallback {
        void onSuccess(JSONObject result);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized JSONController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void fetchData(RequestQueue queue, final DataCallback callback) {

        String upcURL = "https://api.upcitemdb.com/prod/trial/lookup?upc=31259001043";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, upcURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
                        //jsonResult = response;
                        //Log.d("*****Response","Response be: "+ jsonResult);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        queue.add(jsonObjectRequest);
        //JSONController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
