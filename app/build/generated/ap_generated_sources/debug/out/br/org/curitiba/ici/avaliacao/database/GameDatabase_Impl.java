package br.org.curitiba.ici.avaliacao.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class GameDatabase_Impl extends GameDatabase {
  private volatile GameDataDao _gameDataDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `GameData` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `victory` INTEGER NOT NULL, `loss` INTEGER NOT NULL, `draw` INTEGER NOT NULL, `gamesPlayed` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"06d2f4ab102f7a1f3979262cce68635d\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `GameData`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsGameData = new HashMap<String, TableInfo.Column>(5);
        _columnsGameData.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsGameData.put("victory", new TableInfo.Column("victory", "INTEGER", true, 0));
        _columnsGameData.put("loss", new TableInfo.Column("loss", "INTEGER", true, 0));
        _columnsGameData.put("draw", new TableInfo.Column("draw", "INTEGER", true, 0));
        _columnsGameData.put("gamesPlayed", new TableInfo.Column("gamesPlayed", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGameData = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGameData = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGameData = new TableInfo("GameData", _columnsGameData, _foreignKeysGameData, _indicesGameData);
        final TableInfo _existingGameData = TableInfo.read(_db, "GameData");
        if (! _infoGameData.equals(_existingGameData)) {
          throw new IllegalStateException("Migration didn't properly handle GameData(br.org.curitiba.ici.avaliacao.game.pojo.GameData).\n"
                  + " Expected:\n" + _infoGameData + "\n"
                  + " Found:\n" + _existingGameData);
        }
      }
    }, "06d2f4ab102f7a1f3979262cce68635d", "ea7fc87e638715b06c7751dbdaeceff9");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "GameData");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `GameData`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public GameDataDao gameDataDao() {
    if (_gameDataDao != null) {
      return _gameDataDao;
    } else {
      synchronized(this) {
        if(_gameDataDao == null) {
          _gameDataDao = new GameDataDao_Impl(this);
        }
        return _gameDataDao;
      }
    }
  }
}
