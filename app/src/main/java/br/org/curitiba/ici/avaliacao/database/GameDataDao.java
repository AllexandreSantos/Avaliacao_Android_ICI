package br.org.curitiba.ici.avaliacao.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import br.org.curitiba.ici.avaliacao.game.pojo.GameData;
import br.org.curitiba.ici.avaliacao.game.pojo.GameTotals;

@Dao
public interface GameDataDao {

    @Query("SELECT SUM(victory) as totalWins, SUM(loss) as totalLosses, SUM(draw) as totalDraws, SUM(gamesPlayed) as totalGames FROM gamedata")
    LiveData<GameTotals> getGameTotals();

    @Insert
    void InsertGame(GameData gameData);

    @Query("DELETE FROM gamedata")
    void nukeTable();
}
