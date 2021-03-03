package br.org.curitiba.ici.avaliacao.statistics;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import br.org.curitiba.ici.avaliacao.database.GameDatabase;
import br.org.curitiba.ici.avaliacao.game.pojo.GameTotals;
import br.org.curitiba.ici.avaliacao.util.AppExecutors;

public class StatisticsViewModel extends AndroidViewModel {

    GameDatabase database;

    LiveData<GameTotals> gameTotalsLiveData;

    LiveData<Double> winRatio;

    public StatisticsViewModel(@NonNull Application application) {
        super(application);
        database = GameDatabase.getInstance(application);
        gameTotalsLiveData = database.gameDataDao().getGameTotals();
        transform();
    }

    void transform(){
        winRatio = Transformations.map(gameTotalsLiveData, gameTotals -> {
            if (gameTotals.totalDraws + gameTotals.totalLosses + gameTotals.totalWins == 0) return .0;
            return calculate(gameTotals) ;
        });
    }

    private Double calculate(GameTotals gameData) {
       return ( Double.valueOf(gameData.totalWins) / Double.valueOf (gameData.totalDraws + gameData.totalLosses + gameData.totalWins) ) * 100;
    }


    public void wipeData(){
        AppExecutors.getInstance().diskIO().execute(() -> database.gameDataDao().nukeTable());
    }

}
