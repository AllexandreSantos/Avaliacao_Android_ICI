package br.org.curitiba.ici.avaliacao.login;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import br.org.curitiba.ici.avaliacao.R;
import br.org.curitiba.ici.avaliacao.databinding.ActivityLoginBinding;
import br.org.curitiba.ici.avaliacao.game.GameActivity;

import static br.org.curitiba.ici.avaliacao.util.Constants.LOGGED;
import static br.org.curitiba.ici.avaliacao.util.Constants.PLAYER_NAME;
import static br.org.curitiba.ici.avaliacao.util.Constants.SHARED_PREFS_KEY;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        initViews();
    }

    private void initViews() {
        binding.loginButton.setOnClickListener(view ->{
            name = Objects.requireNonNull(binding.editName.getText()).toString();
            validateData(view);
        });
    }

    private void validateData(View view) {
        if (name.isEmpty()){
            showSnack(view);
        } else {
            saveName();
            startActivity(new Intent(this, GameActivity.class));
            finish();
        }
    }

    private void saveName() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGGED, true);
        editor.putString(PLAYER_NAME, name);
        editor.apply();
    }

    private void showSnack(View view){
        Snackbar snackbar = Snackbar
                .make(view, getString(R.string.name_is_mandatory), Snackbar.LENGTH_SHORT)
                .setAction(getString(R.string.ok), v -> {

                });
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        snackbar.show();
    }

}