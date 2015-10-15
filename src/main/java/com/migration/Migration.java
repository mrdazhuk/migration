package com.migration;

import android.database.sqlite.SQLiteDatabase;

import com.migration.parsing.MigrationParser;

/**
 * Created by yuriydazhuk on 10/2/15.
 */
public abstract class Migration {
	protected final MigrationParser migrationParser;
	protected final SQLiteDatabase db;

	public abstract void migrate(int from, int to) throws MigrationException;

	public Migration(SQLiteDatabase db, MigrationParser migrationParser) {
		this.migrationParser = migrationParser;
		this.db = db;
	}
}
