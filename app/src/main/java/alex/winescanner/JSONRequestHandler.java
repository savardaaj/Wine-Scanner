package alex.winescanner;

import android.app.DownloadManager;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class JSONRequestHandler extends AsyncTask<Void, Void, Void>{

    private JSONObject jsonResult;
    private RequestQueue queue;
    private String upcURL;
    //private ServerCallback callback;

    public void setRequestQueue(RequestQueue queue) {
        this.queue = queue;
    }

    public void setUpcURL(String upcUrl) {
        this.upcURL = upcUrl;
    }

    public JSONObject getJsonResult() {
        return jsonResult;
    }

    public void sendRequest() {

        RequestFuture<JSONObject> future = RequestFuture.newFuture();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, upcURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        jsonResult = response;
                        Log.d("*****Response","Response be: "+ jsonResult);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        queue.add(jsonObjectRequest);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            sendRequest();
        } catch (Exception e) {
            Log.d("*****Error","Error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

}
