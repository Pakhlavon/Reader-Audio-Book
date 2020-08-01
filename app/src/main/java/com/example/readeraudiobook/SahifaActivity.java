package com.example.readeraudiobook;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Base64;
import android.view.TextureView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.util.Locale;

import static android.speech.tts.TextToSpeech.LANG_MISSING_DATA;
import static android.speech.tts.TextToSpeech.LANG_NOT_SUPPORTED;
import static android.speech.tts.TextToSpeech.QUEUE_ADD;
import static android.speech.tts.TextToSpeech.QUEUE_FLUSH;
import static android.speech.tts.TextToSpeech.SUCCESS;

public class SahifaActivity extends AppCompatActivity {
    private WebView webview;
    private TextToSpeech tts;
    private TextView txt;
    String desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sahifa);

//        webview = findViewById(R.id.webview);
        txt = findViewById(R.id.txt);
        desc = getIntent().getStringExtra("desc");
//        webview.clearCache(true);
//        webview.clearHistory();
//        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        desc = Base64.encodeToString(desc.getBytes(),
//                Base64.NO_PADDING);
//        webview.loadData(desc, "text/html", "base64");

        txt.setText(desc);
        txt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onClick(View view) {
                txt.startAnimation(AnimationUtils.loadAnimation(SahifaActivity.this,R.anim.animation));
                spellText(SahifaActivity.this);
            }
        });
    }
    private void ConvertTextToSpeech(String str) {

        if (str == null || "".equals(str)) {
            tts.speak(str, QUEUE_FLUSH, null);
        }
        else
            tts.speak(str, QUEUE_FLUSH, null);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void spellText(Context context) {
        onPause();
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {

                if (status == SUCCESS) {
                    int result = tts.setLanguage(Locale.KOREAN);

                    if (result == LANG_MISSING_DATA || result == LANG_NOT_SUPPORTED) {
                        ConvertTextToSpeech(desc);
                    } else {
                            ConvertTextToSpeech(desc);
                    }
                }
            }
        });
    }
    public void onPause() {
        super.onPause();
        if (tts != null) {
            tts.stop();
        }
    }
}