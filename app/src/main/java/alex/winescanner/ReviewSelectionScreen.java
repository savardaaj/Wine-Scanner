package alex.winescanner;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReviewSelectionScreen extends AppCompatActivity {

    //TODO: Change to be dynamic
    private final String wine_searcher_url = "https://www.wine-searcher.com/find/joseph+carr+josh+cellars+rose/2016#t1";

    private Button btnMore;
    private Wine wine;
    private ArrayList<Wine> wineList;
    //private TextView tv_remove;

    private ViewGroup mConstraintLayout;

    private void init() {
        Log.d("********", "Inside reviewselectionscreen init");
        wineList = new ArrayList<Wine>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("********", "Inside reviewselectionscreen onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_selection_screen);

        btnMore = (Button) findViewById(R.id.btnMore);

        //if moved lower causes a null pointer exception
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mConstraintLayout = (ViewGroup) findViewById(R.id.layoutReviewMain);

        init();
        getWineDataFromIntent();
        getWineDataFromHTML();

        Log.d("*********", "WineInfo: " + wineList.size());
        for(int i = 0; i < wineList.size(); i++) {
            wine = wineList.get(i);
            createWineCard(wineList.get(i));
        }
    }

    private void createComponentListeners() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_scan1:
                //scanBarcode(this);
                return true;
            case R.id.menu_scan_many:
                //scanBarcodes();
                return true;
            case R.id.menu_history:
                //showHistory();
                return true;
            case R.id.menu_settings:
                //showSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getWineDataFromIntent() {
        Log.d("********", "Inside reviewselectionscreen getWineDataFromIntent");
        Intent intent = getIntent();
        wineList = (ArrayList<Wine>) intent.getSerializableExtra("WineList");
        wine = (Wine) intent.getSerializableExtra("Wine");
        Log.d("*****Data", "wineListafterintent: " + wineList.get(0));
        Log.d("*****Data", "wineafterintent: " + wine.getTitle());
    }

    private void getWineDataFromHTML() {
        Log.d("*****", "inside getWineDataFromHTML: ");
        try {
            //File input = new File("/tmp/input.html");
            Document doc = Jsoup.connect(wine_searcher_url).get();
            Log.d("*****", "document: " + doc);

            Element content = doc.getElementById("container");
            Elements ratings = doc.select("[itemprop=ratingValue]");

            for(Element rating : ratings) {
                wine.setRatings(rating.text());
                Log.d("*****", "Rating: " + rating.text());
            }
        }
        catch(Exception e) {
            Log.d("*****", "Error: " + e.getMessage());
            //Log.d("*****", "msg: " + e.getStackTrace().toString());

        }

    }

    private void createWineCard(Wine wine) {
        Log.d("********", "inside reviewselectionscreen CreateWineCard");
        View combinedLayout = LayoutInflater.from(this).inflate(R.layout.wine_card_component, mConstraintLayout, false);

        //Create all components of imported component
        TextView tvWineName = (TextView) combinedLayout.findViewById(R.id.tvWineName);
        ImageView imageView = (ImageView) combinedLayout.findViewById(R.id.ivWine_Picture);
        RatingBar ratingBar = (RatingBar) combinedLayout.findViewById(R.id.ratingBar);
        TextView tvWineDesc = (TextView) combinedLayout.findViewById(R.id.tvWineDesc);
        TextView tvWineSource = (TextView) combinedLayout.findViewById(R.id.tvWineSource);
        TextView tvWinePoints = (TextView) combinedLayout.findViewById(R.id.tvWinePoints);
        TextView tvRatingsCount = (TextView) combinedLayout.findViewById(R.id.tvRatingsCount);
        TextView tv_remove = (TextView) combinedLayout.findViewById(R.id.tv_remove);

        //set values of imported components
        tvWineName.setText(wine.getTitle());
        Log.d("********", "inside reviewselectionscreen CreateWineCard" + wine.getTitle());
        //imageView.setImageDrawable(wine.getImages());
        //ratingBar.setNumStars(wine.getRating());
        tvWineDesc.setText(wine.getDescription());

        //tvWineSource.setText();
        //tvWinePoints.setText();
        tvRatingsCount.setText(wine.getRatings());

        this.setContentView(combinedLayout);
    }

    //TODO: Remove card from view and arraylist
    public void removeWineCard(View v) {
        Log.d("*****", "Entered removeWineCard");
    }


}
