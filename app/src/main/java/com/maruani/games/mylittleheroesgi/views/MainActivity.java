package com.maruani.games.mylittleheroesgi.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import com.maruani.games.mylittleheroesgi.R;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Runnable runnable = () -> navigateToHome();
    Handler handler = new Handler(Looper.getMainLooper());
    handler.postDelayed(runnable, 2000);
  }

  private void navigateToHome() {
    Intent intent = new Intent(this, HomeActivity.class);
    startActivity(intent);
  }
}
