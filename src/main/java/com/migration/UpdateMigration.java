package com.migration;

import android.database.sqlite.SQLiteDatabase;

import com.migration.parsing.MigrationParser;

/**
 * Created by yuriydazhuk on 10/2/15.
 */
public class UpdateMigration extends Migration {
	public UpdateMigration(SQLiteDatabase db, MigrationParser migrationParser) {
		super(db, migrationParser);
	}

	@Override
	public void migrate(int from, int to) throws MigrationException {
		for (int version = from + 1; version <= to; version++) {
			this.migrationParser.parseUpdate(version).beginMigration(this.db);
		}
	}
}
