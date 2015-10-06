package com.migration.action.table;

import android.database.sqlite.SQLiteDatabase;

import com.migration.MigrationException;
import com.migration.StatementsUtils;
import com.migration.action.TableAction;
import com.migration.action.index.CreateIndexes;
import com.migration.schema.Column;
import com.migration.schema.SqlFormatException;
import com.migration.schema.Table;

import java.util.List;

/**
 * Created by yuriydazhuk on 9/29/15.
 */
public class CreateTable extends TableAction {
	private final List<Column> columns;

	public CreateTable(String tableName, List<Column> columns) {
		super(tableName);
		this.columns = columns;
	}

	@Override
	public void applyUpdatesForTable(SQLiteDatabase db, Table table) throws MigrationException {
		db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (%s);", this.tableName, extractColumnsStatementString()));
		new CreateIndexes(this.tableName, this.columns).applyUpdatesForTable(db, loadTable(db));
	}

	private String extractColumnsStatementString() throws SqlFormatException {
		return StatementsUtils.concatStatements(this.columns, from -> ((Column) from).toSqlStatement());
	}

	@Override
	protected boolean shodLoadDbInfo() {
		return false;
	}
}
