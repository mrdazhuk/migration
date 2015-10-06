package com.migration.action;

import android.database.sqlite.SQLiteDatabase;

import com.migration.MigrationException;
import com.migration.schema.SqlFormatException;
import com.migration.schema.Table;
import com.migration.schema.TableInfoLoader;

/**
 * Created by yuriydazhuk on 9/29/15.
 */
public abstract class TableAction extends QueryAction {
    protected String tableName;

    protected abstract void applyUpdatesForTable(SQLiteDatabase db, Table table) throws MigrationException;

    public TableAction(String tableName) {
        this.tableName = tableName;
    }

    protected boolean shodLoadDbInfo() {
        return true;
    }

    protected final Table loadTable(SQLiteDatabase db) throws MigrationException {
        return TableInfoLoader.forDB(db).loadTable(this.tableName);
    }

    @Override
    public void applyUpdates(SQLiteDatabase db) throws MigrationException {
        Table table = shodLoadDbInfo() ? loadTable(db) : null;
        applyUpdatesForTable(db, table);
    }

}
