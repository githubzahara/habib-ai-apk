package com.habib.ai;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    static final String URL = "http://127.0.0.1:5000";
    static final String ERR =
        "<meta name=viewport content='width=device-width'>"
        + "<body style='background:#111;color:#eee;font-family:sans-serif;padding:24px'>"
        + "<h2>ሰርቨሩ አልበራም</h2>"
        + "<p>Termux ውስጥ <code>bash ~/habib_ai/start.sh</code> አስኬድ፣ ከዚያ ድጋሚ ሞክር።</p>"
        + "<a style='color:#6cf;font-size:20px' href='" + URL + "'>ድጋሚ ሞክር</a>";

    WebView web;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        web = new WebView(this);
        setContentView(web);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView v, WebResourceRequest r, WebResourceError e) {
                if (r.isForMainFrame()) {
                    v.loadDataWithBaseURL(null, ERR, "text/html", "utf-8", null);
                }
            }
        });
        web.loadUrl(URL);
    }

    @Override
    public void onBackPressed() {
        if (web.canGoBack()) web.goBack(); else super.onBackPressed();
    }
}
