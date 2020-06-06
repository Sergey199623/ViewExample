package com.example.android.drawableonview;

import androidx.appcompat.app.AppCompatActivity;
import com.example.android.drawableonview.PalleteView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pallete.setOnChangeColorListener(object : PalleteView.OnColorChangedListener(){
            override fun colorChanged(color: int) {
                drawing.setColor(color)
            }
        })
    }
}