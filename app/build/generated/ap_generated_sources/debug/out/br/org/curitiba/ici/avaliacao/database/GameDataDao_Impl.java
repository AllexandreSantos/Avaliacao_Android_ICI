package br.org.curitiba.ici.avaliacao.database;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker.Observer;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import br.org.curitiba.ici.avaliacao.game.pojo.GameData;
import br.org.curitiba.ici.avaliacao.game.pojo.GameTotals;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class GameDataDao_Impl implements GameDataDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfGameData;

  private final SharedSQLiteStatement __preparedStmtOfNukeTable;

  public GameDataDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGameData = new EntityInsertionAdapter<GameData>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `GameData`(`id`,`victory`,`loss`,`draw`,`gamesPlayed`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GameData value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getVictory());
        stmt.bindLong(3, value.getLoss());
        stmt.bindLong(4, value.getDraw());
        stmt.bindLong(5, value.getGamesPlayed());
      }
    };
    this.__preparedStmtOfNukeTable = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM gamedata";
        return _query;
      }
    };
  }

  @Override
  public void InsertGame(GameData gameData) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfGameData.insert(gameData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void nukeTable() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfNukeTable.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfNukeTable.release(_stmt);
    }
  }

  @Override
  public LiveData<GameTotals> getGameTotals() {
    final String _sql = "SELECT SUM(victory) as totalWins, SUM(loss) as totalLosses, SUM(draw) as totalDraws, SUM(gamesPlayed) as totalGames FROM gamedata";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<GameTotals>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected GameTotals compute() {
        if (_observer == null) {
          _observer = new Observer("gamedata") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfTotalWins = _cursor.getColumnIndexOrThrow("totalWins");
          final int _cursorIndexOfTotalLosses = _cursor.getColumnIndexOrThrow("totalLosses");
          final int _cursorIndexOfTotalDraws = _cursor.getColumnIndexOrThrow("totalDraws");
          final int _cursorIndexOfTotalGames = _cursor.getColumnIndexOrThrow("totalGames");
          final GameTotals _result;
          if(_cursor.moveToFirst()) {
            _result = new GameTotals();
            _result.totalWins = _cursor.getInt(_cursorIndexOfTotalWins);
            _result.totalLosses = _cursor.getInt(_cursorIndexOfTotalLosses);
            _result.totalDraws = _cursor.getInt(_cursorIndexOfTotalDraws);
            _result.totalGames = _cursor.getInt(_cursorIndexOfTotalGames);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}
