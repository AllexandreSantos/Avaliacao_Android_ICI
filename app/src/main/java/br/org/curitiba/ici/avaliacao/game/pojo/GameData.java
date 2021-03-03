package br.org.curitiba.ici.avaliacao.game.pojo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class GameData {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int victory;
    private int loss;
    private int draw;

    public void setVictory(int victory) {
        this.victory = victory;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    private int gamesPlayed;

    @Ignore
    public GameData(int victory, int loss, int draw) {
        this.victory = victory;
        this.loss = loss;
        this.draw = draw;
        this.gamesPlayed = 1;
    }

    public GameData(int id, int victory, int loss, int draw) {
        this.id = id;
        this.victory = victory;
        this.loss = loss;
        this.draw = draw;
        this.gamesPlayed = 1;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public int getVictory() { return victory; }

    public int getLoss() {
        return loss;
    }

    public int getDraw() {
        return draw;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

}

