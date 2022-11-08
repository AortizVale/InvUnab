package co.edu.unab.invunab.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import co.edu.unab.invunab.R;

public class ShowActivity extends AppCompatActivity {

    WebView wvArchivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        wvArchivo=findViewById(R.id.wv_archivo);

        String URL = getIntent().getStringExtra("urlArchivo");

        wvArchivo.setWebViewClient(new WebViewClient());
        wvArchivo.loadUrl(URL);

    }
}