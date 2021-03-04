package br.org.curitiba.ici.avaliacao.game;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.Objects;
import java.util.Random;

import br.org.curitiba.ici.avaliacao.R;
import br.org.curitiba.ici.avaliacao.database.GameDatabase;
import br.org.curitiba.ici.avaliacao.game.pojo.GameData;
import br.org.curitiba.ici.avaliacao.game.pojo.GameResult;
import br.org.curitiba.ici.avaliacao.game.pojo.Weapon;
import br.org.curitiba.ici.avaliacao.util.AppExecutors;

import static br.org.curitiba.ici.avaliacao.util.Constants.LOGGED;
import static br.org.curitiba.ici.avaliacao.util.Constants.PLAYER_NAME;
import static br.org.curitiba.ici.avaliacao.util.Constants.SHARED_PREFS_KEY;

public class GameViewModel extends AndroidViewModel {

    MutableLiveData<Weapon> playerWeapon = new MutableLiveData<>(null);

    MutableLiveData<Weapon> opponentWeapon = new MutableLiveData<>(null);

    private boolean isPlaying = false;

    MutableLiveData<Boolean> isShowSelectWeaponToast = new MutableLiveData<>(null);

    MutableLiveData<GameResult> gameResult = new MutableLiveData<>(null);

    private GameData gameData;

    private final GameDatabase database;

    SharedPreferences sharedPreferences;

    public GameViewModel(@NonNull Application application) {
        super(application);

        sharedPreferences = application.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);

        database = GameDatabase.getInstance(application);
    }

    public void logout(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGGED, false);
        editor.apply();
    }

    public String getPlayerName(){
        return sharedPreferences.getString(PLAYER_NAME, getApplication().getString(R.string.player));
    }

    public void setPlayerWeapon(Weapon playerWeapon) {
        if (isPlaying) return;

        isShowSelectWeaponToast.setValue(false);
        this.playerWeapon.setValue(playerWeapon);
        opponentWeapon.setValue(Weapon.JACK);
    }

    public void play() {
        if (playerWeapon.getValue() == null) {
            isShowSelectWeaponToast.setValue(true);
            return;
        } else {
            isShowSelectWeaponToast.setValue(false);
        }

        isPlaying = true;
        generateOpponentWeapon();
        calculateResult();
        isPlaying = false;
        saveGame();
    }

    private void generateOpponentWeapon() {
        Random random = new Random();
        int randomInt = random.nextInt(3);
        switch (randomInt) {
            case 0:
                opponentWeapon.setValue(Weapon.ROCK);
                break;
            case 1:
                opponentWeapon.setValue(Weapon.PAPER);
                break;
            case 2:
                opponentWeapon.setValue(Weapon.SCISSOR);
        }
    }

    private void calculateResult() {
        switch (Objects.requireNonNull(playerWeapon.getValue())){
            case ROCK:
                switch (Objects.requireNonNull(opponentWeapon.getValue())){
                    case ROCK:
                        gameResult.setValue(GameResult.DRAW);
                        break;
                    case PAPER:
                        gameResult.setValue(GameResult.LOST);
                        break;
                    case SCISSOR:
                        gameResult.setValue(GameResult.WON);
                        break;
                } break;
            case PAPER:
                switch(Objects.requireNonNull(opponentWeapon.getValue())) {
                    case ROCK:
                        gameResult.setValue(GameResult.WON);
                        break;
                    case PAPER:
                        gameResult.setValue(GameResult.DRAW);
                        break;
                    case SCISSOR:
                        gameResult.setValue(GameResult.LOST);
                        break;
                } break;
            case SCISSOR:
                switch(Objects.requireNonNull(opponentWeapon.getValue())) {
                    case ROCK:
                        gameResult.setValue(GameResult.LOST);
                        break;
                    case PAPER:
                        gameResult.setValue(GameResult.WON);
                        break;
                    case SCISSOR:
                        gameResult.setValue(GameResult.DRAW);
                        break;
                } break;
        }

    }

    private void saveGame() {
        switch (Objects.requireNonNull(gameResult.getValue())){
            case WON:
                gameData = new GameData(1, 0, 0);
                break;
            case LOST:
                gameData = new GameData(0, 1, 0);
                break;
            case DRAW:
                gameData = new GameData(0, 0, 1);
                break;
        }
        AppExecutors.getInstance().diskIO().execute(() -> database.gameDataDao().InsertGame(gameData));
    }

}
