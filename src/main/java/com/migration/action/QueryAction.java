package com.migration.action;

import android.database.sqlite.SQLiteDatabase;

import com.migration.MigrationException;

/**
 * Created by Yuriy Dazhuk on 06.10.2015.
 */
public abstract class QueryAction {
    public abstract void applyUpdates(SQLiteDatabase db) throws MigrationException;
}
