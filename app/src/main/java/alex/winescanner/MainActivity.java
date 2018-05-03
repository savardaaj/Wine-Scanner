package alex.winescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Called when user taps the Compare Wines button
    public void startCamera(View view) {
        //do something in response to button
        Intent intent = new Intent(this, AndroidCameraApi.class);
        startActivity(intent);
    }

    //Called when user taps the Compare Wines button
    public void scanBarcodes(View view) {
        //do something in response to button
        Intent intent = new Intent(this, BarcodeScanner.class);
        startActivity(intent);
    }

}
