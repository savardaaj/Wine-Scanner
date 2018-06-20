package alex.winescanner;

import android.os.AsyncTask;
import android.util.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WebScraper extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... url) {

        try {
            //File input = new File("/tmp/input.html");

            Document doc = Jsoup.connect(url[0]).get();
            //Log.d("*****", "document: " + doc);

            Elements linkToDetailPage = doc.select("a#prodItemInfo_link");

            Log.d("*****", "linktoDetailPage: " + linkToDetailPage.text());

            //https://www.wine.com/search/josh%20cellars%20rose/0

            //https://www.wine.com/product/josh-cellars-rose-2016/185622
            String newUrl = "https://www.wine.com" + linkToDetailPage.html();

            Document detailpage = Jsoup.connect(newUrl).get();

            Log.d("*****", "detailPage: " + detailpage.html());

            Elements rating = detailpage.select("span#averageRating_average");

            Log.d("*****", "Rating: " + rating.html());

        }
        catch(Exception e) {
            Log.d("*****", "Error: " + e);
            //Log.d("*****", "msg: " + e.getStackTrace().toString());

        }

        return "";
    }

    @Override
    protected void onPostExecute(String result) {

    }


}
