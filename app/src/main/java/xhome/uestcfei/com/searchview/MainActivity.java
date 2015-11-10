package xhome.uestcfei.com.searchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements  {

    private static final String TAG = "MainActivity";
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        searchView = (SearchView) findViewById(R.id.search);
    }

    public void stop(View view) {
        Log.d(TAG, "stop");
        searchView.stopSearch();
    }

    public void start(View view) {
        Log.d(TAG, "start");
        searchView.startSearch();
    }
}
