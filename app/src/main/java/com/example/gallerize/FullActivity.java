package com.example.gallerize;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullActivity extends AppCompatActivity {

    private static final String EXTRA = "bebas";

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen_layout);
        imageView = findViewById(R.id.fullscreen);


        Uri uri = Uri.parse(getIntent().getStringExtra(EXTRA));
        Glide.with(this).load(uri).placeholder(R.drawable.imgholder).centerInside().into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static Intent newIntent(Context context, Uri uri){
        Intent intent = new Intent(context, FullActivity.class);
        intent.putExtra(EXTRA, uri.toString());
        return intent;
    }
}
