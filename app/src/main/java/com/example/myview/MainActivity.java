package com.example.myview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.viewutil.DrawCorner;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.test);
        textView.setBackground(DrawCorner.drawCorner(new float[]{0, 0, 20, 20, 20, 20, 0, 0}, new int[]{Color.GREEN, Color.BLUE}));

    }
}