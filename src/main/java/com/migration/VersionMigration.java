package com.migration;

import android.database.sqlite.SQLiteDatabase;

import com.migration.action.QueryAction;

import java.util.List;

/**
 * Created by yuriydazhuk on 9/28/15.
 */
public class VersionMigration {
	private List<QueryAction> actions;

	public VersionMigration(List<QueryAction> actions) {
		this.actions = actions;
	}

	public void beginMigration(SQLiteDatabase db) throws MigrationException {
		db.execSQL("PRAGMA foreign_keys=OFF");

		for (QueryAction action : this.actions) {
			action.applyUpdates(db);
		}

		db.execSQL("PRAGMA foreign_keys=ON");
	}
}
