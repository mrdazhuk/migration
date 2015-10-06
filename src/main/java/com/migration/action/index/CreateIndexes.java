package com.migration.action.index;

import android.database.sqlite.SQLiteDatabase;

import com.migration.schema.Column;
import com.migration.schema.SqlFormatException;
import com.migration.schema.Table;

import java.util.List;

/**
 * Created by yuriydazhuk on 9/28/15.
 */
public class CreateIndexes extends IndexesAction {
	public CreateIndexes(String tableName, Column column) {
		super(tableName, column);
	}

	public CreateIndexes(String tableName, List<Column> columns) {
		super(tableName, columns);
	}

	@Override
	protected void doIndexActionOnColumn(SQLiteDatabase db, Table table, String indexName, String columnName) throws SqlFormatException {
		db.execSQL(String.format("CREATE INDEX IF NOT EXISTS `%s` ON `%s` ( `%s` )", indexName, this.tableName, columnName));
	}
}
