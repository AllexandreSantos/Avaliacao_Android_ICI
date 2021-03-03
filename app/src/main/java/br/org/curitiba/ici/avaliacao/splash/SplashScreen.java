package br.org.curitiba.ici.avaliacao.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import br.org.curitiba.ici.avaliacao.game.GameActivity;
import br.org.curitiba.ici.avaliacao.login.LoginActivity;
import br.org.curitiba.ici.avaliacao.R;

import static br.org.curitiba.ici.avaliacao.util.Constants.LOGGED;
import static br.org.curitiba.ici.avaliacao.util.Constants.SHARED_PREFS_KEY;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                splash();
            }
        }, 2000);

    }

    private void splash() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        boolean isLogged = sharedPreferences.getBoolean(LOGGED, false);
        if (isLogged){
            startActivity(new Intent(this, GameActivity.class));
        } else{
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }

}