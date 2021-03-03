package br.org.curitiba.ici.avaliacao.game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import br.org.curitiba.ici.avaliacao.R;
import br.org.curitiba.ici.avaliacao.databinding.ActivityGameBinding;
import br.org.curitiba.ici.avaliacao.game.pojo.GameResult;
import br.org.curitiba.ici.avaliacao.login.LoginActivity;
import br.org.curitiba.ici.avaliacao.statistics.StatisticsActivity;

import static br.org.curitiba.ici.avaliacao.util.Constants.LOGGED;
import static br.org.curitiba.ici.avaliacao.util.Constants.PLAYER_NAME;
import static br.org.curitiba.ici.avaliacao.util.Constants.SHARED_PREFS_KEY;

public class GameActivity extends AppCompatActivity {

    ActivityGameBinding binding;
    GameViewModel viewModel;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game);

        viewModel = ViewModelProviders.of(this).get(GameViewModel.class);

        binding.setViewModel(viewModel);

        sharedPreferences = this.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);

        initViews();

        observeViewModel();
    }

    private void initViews() {
        setSupportActionBar(binding.materialToolbar);
        binding.playerTextView.setText(sharedPreferences.getString(PLAYER_NAME, getString(R.string.player)));
    }

    private void observeViewModel() {

        viewModel.isShowSelectWeaponToast.observe(this, showToast -> {
            //TODO poderia usar padrão action aqui pra tirar a lógica da activity
            //por simplicidade escolhi usar esse padrão
            if (showToast != null){
                if (showToast) showSelectWeaponToast();
            }
        });

        viewModel.playerWeapon.observe(this, playerWeapon -> {
            if (playerWeapon != null) {
                switch (playerWeapon) {
                    case ROCK:
                        binding.playerImage.setImageResource(R.drawable.pedra);
                        break;
                    case PAPER:
                        binding.playerImage.setImageResource(R.drawable.papel);
                        break;
                    case SCISSOR:
                        binding.playerImage.setImageResource(R.drawable.tesoura);
                        break;
                }
            }
        });


        viewModel.opponentWeapon.observe(this, opponentWeapon -> {
            if (opponentWeapon != null) {
                switch (opponentWeapon) {
                    case ROCK:
                        binding.opponentImage.setImageResource(R.drawable.pedra);
                        break;
                    case PAPER:
                        binding.opponentImage.setImageResource(R.drawable.papel);
                        break;
                    case SCISSOR:
                        binding.opponentImage.setImageResource(R.drawable.tesoura);
                        break;
                    default:
                        binding.opponentImage.setImageResource(R.drawable.samurai);
                }
            }
        });

        viewModel.gameResult.observe(this, gameResult -> {
            if (gameResult != null){
                resultDialog(gameResult);
            }
        });
    }

    private void showSelectWeaponToast() {
        Toast.makeText(this, getString(R.string.select_weapon), Toast.LENGTH_SHORT).show();
    }

    private void resultDialog(GameResult gameResult) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(gameResult.getValue())
                .setTitle(R.string.result);

        builder.setPositiveButton(R.string.ok, (dialog, id) -> {
        });

       builder.create().show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.logout_menu_option){
            logout();
            return true;
        }
        if (item.getItemId() == R.id.statistics_menu_option){
            navigateToStatistics();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void navigateToStatistics() {
        startActivity(new Intent(this, StatisticsActivity.class));
    }

    //TODO Poderia fazer algum tipo de injeção de dependência e passar essa lógica pro Viewmodel, mas pelo tamanho do app, vou deixar aqui mesmo
    //Mesmo raciocínio vale para outras classes como LoginActivity e SlpashScreen
    private void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGGED, false);
        editor.apply();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}