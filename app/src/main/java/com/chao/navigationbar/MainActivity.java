package com.chao.navigationbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chao.lib.DefauleNavigationBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DefauleNavigationBar.Builder(this).setTitle("我是标题")
                .setBackClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"点击左边图片",Toast.LENGTH_SHORT).show();
                    }
                }).setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击右边",Toast.LENGTH_SHORT).show();
            }
        }).setRightText("右边文字").builer();
    }
}
