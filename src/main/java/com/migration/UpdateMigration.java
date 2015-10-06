package com.migration;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by yuriydazhuk on 10/2/15.
 */
public class UpdateMigration extends MigrationDirection {
	public UpdateMigration(SQLiteDatabase db, MigrationReader migrationReader) {
		super(db, migrationReader);
	}

	@Override
	public void migrate(int from, int to) throws MigrationException {
		for (int version = from; version <= to; version++) {
			this.migrationParser.parseUpdate(version).beginMigration(this.db);
		}
	}
}
