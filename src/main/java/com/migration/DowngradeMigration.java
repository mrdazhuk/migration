package com.migration;

import android.database.sqlite.SQLiteDatabase;

import com.migration.parsing.MigrationParser;

/**
 * Created by yuriydazhuk on 10/2/15.
 */
public class DowngradeMigration extends Migration {
	public DowngradeMigration(SQLiteDatabase db, MigrationParser migrationParser) {
		super(db, migrationParser);
	}

	@Override
	public void migrate(int from, int to) throws MigrationException {
		for (int version = from; version > to; version--) {
			this.migrationParser.parseDowngrade(version).beginMigration(this.db);
		}
	}
}
