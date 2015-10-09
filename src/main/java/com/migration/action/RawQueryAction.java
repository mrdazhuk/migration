package com.migration.action;

import android.database.sqlite.SQLiteDatabase;

import com.migration.MigrationException;

/**
 * Created by Yuriy Dazhuk on 06.10.2015.
 */
public class RawQueryAction extends QueryAction {
    private final String rawQuery;

    public RawQueryAction(String rawQuery) {
        this.rawQuery = rawQuery;
    }

    @Override
    public void applyUpdates(SQLiteDatabase db) throws MigrationException {
        db.execSQL(rawQuery);
    }
}
