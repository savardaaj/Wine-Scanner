package alex.winescanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReviewSelectionScreen extends AppCompatActivity {

    private Button btnMore;
    private Wine wine;
    private ArrayList<Wine> wineList;
    private int wineCount;

    private void init() {
        wineCount = 0;
        wineList = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_selection_screen);

        init();

        getWineDataFromIntent();

        if(wineList.size() > 0) {
            for(int i = 0; i < wineCount; i++) {
                wine = wineList.get(i);
                createWineCard(wine, i);
            }
        }
        else {
            Log.d("wineListEmpty", "Wine List is empty");
        }
        btnMore = (Button) findViewById(R.id.btnMore);

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getWineDataFromIntent() {
        Intent intent = getIntent();
        wineList = (ArrayList<Wine>) intent.getSerializableExtra("WineList");
        wineCount = intent.getIntExtra("wineCount", 0);
    }

    private void createWineCard(Wine wine, int count) {
        //LayoutInflater li = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //TODO: Create wine_component. Populate with wine data
        LayoutInflater li = getLayoutInflater();
        View v = li.inflate(R.layout.wine_card_component, null);

        //View customView = v.findViewById(R.id.wine_card_component);
        //customView.findViewById(R.id.tvWineName).getContext().
        
        //Populate winecard with data
        TextView tv = (TextView) v.findViewById(R.id.tvWineName);
        tv.setText(wine.getTitle());

        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.layoutReviewMain);
        viewGroup.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }



}
