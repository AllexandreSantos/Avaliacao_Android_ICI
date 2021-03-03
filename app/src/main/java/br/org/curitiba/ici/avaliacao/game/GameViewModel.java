package br.org.curitiba.ici.avaliacao.game;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Objects;
import java.util.Random;

import br.org.curitiba.ici.avaliacao.game.entities.GameResult;
import br.org.curitiba.ici.avaliacao.game.entities.Weapon;

public class GameViewModel extends ViewModel {

    MutableLiveData<Weapon> playerWeapon = new MutableLiveData<>(null);

    MutableLiveData<Weapon> opponentWeapon = new MutableLiveData<>(null);

    private boolean isPlaying = false;

    MutableLiveData<Boolean> isShowSelectWeaponToast = new MutableLiveData<>();

    MutableLiveData<GameResult> gameResult = new MutableLiveData<>(null);

    public void setPlayerWeapon(Weapon playerWeapon) {
        if (isPlaying) return;
        opponentWeapon.setValue(Weapon.JACK);
        this.playerWeapon.setValue(playerWeapon);
        isShowSelectWeaponToast.setValue(false);
    }

    public void play() {
        isPlaying = true;
        if (playerWeapon.getValue() == null) {
            isShowSelectWeaponToast.setValue(true);
            return;
        } else {
            isShowSelectWeaponToast.setValue(false);
        }

        generateOpponentWeapon();
        calculateResult();
        isPlaying = false;
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

}
