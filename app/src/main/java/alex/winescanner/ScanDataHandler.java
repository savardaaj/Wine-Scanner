package alex.winescanner;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

public class ScanDataHandler {

    //call UPC Item DB for UPC Codes: http://www.upcitemdb.com/upc/31259001043
    //Wine Bottles require a UPC Code!
    private JSONRequestHandler jsonRH;
    private JSONObject jsonResult;
    private String upcURL = "";
    private Wine wine;
    private boolean notPopulated = true;

    public ScanDataHandler() {
        Log.d("********ScanDataHandler", "Inside ScanDataHandler");

        wine = new Wine();
        jsonResult = new JSONObject();
        jsonRH = new JSONRequestHandler();
    }

    public void sendRequest(RequestQueue queue, String upcID) {
        Log.d("********sendRequest", "Entering sendRequest: " + upcID);
        try {
            JSONController jsonC = new JSONController();
            jsonC.fetchData(queue, new JSONController.DataCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        jsonResult = result;
                        //populateWineObj(wine, jsonResult);
                        Log.d("*****Onsuccessresult", "result: " + result);
                    }
                    catch (Exception e) {
                        Log.e("*****Error", "error: " + e.getStackTrace().toString());
                    }

                }
            });

        }
        catch (Exception e) {
            Log.d("*****Exception", "Error: " + e.getMessage());
        }

    }

    public Wine getWine() {
        return wine;
    }

    public Wine populateWineObj(Wine wine, JsonObject jsonResult) {
        Log.d("populateWineObj", "msg" + jsonResult);
        try {

            Gson gson = new Gson();
            //this populates teh model with header info and a list of wine object
            JsonResponseModel jsonModel = gson.fromJson(jsonResult, JsonResponseModel.class);
            if(jsonModel.items.get(0) != null) {
                wine = jsonModel.items.get(0);
            }
            else {
                Log.d("*****Empty", "Wine is null");
            }
        }
        catch (Exception e) {
            Log.d("*****Error", "Error" + e.getMessage());
        }
        return wine;
    }

}
