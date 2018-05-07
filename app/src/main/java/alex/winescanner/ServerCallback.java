package alex.winescanner;

import org.json.JSONObject;

public interface ServerCallback {
    void onSuccess(JSONObject result);
}
