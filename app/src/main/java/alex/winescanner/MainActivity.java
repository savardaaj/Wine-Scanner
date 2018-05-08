package alex.winescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("********", "Inside main oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_options_menu, menu);
        return true;
    }

    //Called when user taps the Compare Wines button
    public void startCamera(View view) {
        //do something in response to button
        Intent intent = new Intent(this, AndroidCameraApi.class);
        startActivity(intent);
    }

    //Called when user taps the Compare Wines button
    public void scanBarcodes(View view) {
        Log.d("********", "Inside main scanBarcodes");
        //do something in response to button
        Intent intent = new Intent(this, BarcodeScanner.class);
        startActivity(intent);
    }

}
