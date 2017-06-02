package com.dl.glidesourcedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.glide_base_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.glide_base_btn:
                startBaseUseActivity();
                break;
            default:
                startBaseUseActivity();
        }
    }

    private void startBaseUseActivity() {
        Intent intent = new Intent(this,GlideBaseUseActivity.class);
        startActivity(intent);
    }
}
