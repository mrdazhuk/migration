package com.migration;

import android.database.sqlite.SQLiteDatabase;

import com.migration.action.TableAction;

import java.util.List;

/**
 * Created by yuriydazhuk on 9/28/15.
 */
public class VersionMigration {
	private List<TableAction> actions;

	public VersionMigration(List<TableAction> actions) {
		this.actions = actions;
	}

	public void beginMigration(SQLiteDatabase db) throws MigrationException {
		db.execSQL("PRAGMA foreign_keys=OFF");

		for (TableAction action : this.actions) {
			action.applyUpdates(db);
		}

		db.execSQL("PRAGMA foreign_keys=ON");
	}
}
