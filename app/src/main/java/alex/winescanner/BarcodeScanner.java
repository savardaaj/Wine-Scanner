package alex.winescanner;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BarcodeScanner extends AppCompatActivity {

    Button scan;
    Button goBack;
    Button reviewSelection;
    TextView tvscan_count;

    View view;

    ScanDataHandler scanDataHandler;
    Wine wine;
    ArrayList<Wine> wineList;
    RequestQueue queue;

    int scanCount;
    String scan_count;

    private void init() {
        wineList = new ArrayList<Wine>();
        wine = new Wine();
        scanDataHandler = new ScanDataHandler();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barcode_capture_layout);
        view = findViewById(android.R.id.content);

        init();

        scan = (Button) findViewById(R.id.btnscan);
        assert scan != null;
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanBarcode(v);
            }
        });

        reviewSelection = (Button) findViewById(R.id.btnreview_selection);
        assert reviewSelection != null;
        reviewSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewSelection(v);
            }
        });

        tvscan_count = (TextView) findViewById(R.id.tvscan_count);

        scanBarcode(view);
    }

    /**
     * event handler for scan button
     * @param view view of the activity
     */
    public void scanBarcode(View view){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        //integrator.setResultDisplayDuration(0);
        //integrator.setWide();  // Wide scanning rectangle, may work better for 1D barcodes
        //integrator.setResultDisplayDuration(1000);
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        integrator.initiateScan();
    }

    /**
     * function handle scan result
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        //get current scan count
        scanCount = Integer.parseInt(tvscan_count.getText().toString());

        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            //TODO: FOR TESTING ONLY
            scanContent = "31259001043";
            if(scanContent != null) {
                //scanDataHandler.sendRequest(scanContent);

                wine = scanDataHandler.getWine();

                Log.d("********beforeWineLoop", "wine" + wine);
                if(wine != null) {
                    wineList.add(wine);

                    //increment scan count
                    scanCount++;
                }
                else {
                    Log.d("Error", "Wine is null");
                }
            }

        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }

        scan_count = "" + scanCount;
        tvscan_count.setText(scan_count);

    }

    public void reviewSelection(View view) {
        Log.d("*****ReviewSelection", "afg");

        String scanContent = "31259001043";

        Ion.with(this)
        .load("https://api.upcitemdb.com/prod/trial/lookup?upc=31259001043")
        .asJsonObject()
        .setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                // do stuff with the result or error
                Log.d("*****gsonResult", "result: " + result);

                goToReviewActivity(result);
            }
        });
    }

    public void goToReviewActivity(JsonObject result) {
        Intent intent = new Intent(this, ReviewSelectionScreen.class);

        wine = scanDataHandler.populateWineObj(wine, result);

        wineList.add(wine);
        intent.putExtra("WineList", wineList);
        intent.putExtra("Wine", wine);
        intent.putExtra("wineCount", 1);
        startActivity(intent);
    }
}
