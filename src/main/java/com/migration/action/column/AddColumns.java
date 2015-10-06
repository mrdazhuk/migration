package com.migration.action.column;

import android.database.sqlite.SQLiteDatabase;

import com.migration.action.TableAction;
import com.migration.action.index.CreateIndexes;
import com.migration.schema.Column;
import com.migration.schema.SqlFormatException;
import com.migration.schema.Table;

import java.util.List;

/**
 * Created by yuriydazhuk on 9/28/15.
 */
public class AddColumns extends TableAction {
	private List<Column> columns;

	public AddColumns(String tableName, List<Column> columns) {
		super(tableName);
		this.columns = columns;
	}

	@Override
	public void applyUpdatesForTable(SQLiteDatabase db, Table table) throws SqlFormatException {
		for (Column column : this.columns) {
			if (!table.containColumn(column.getName())) {
				db.execSQL(String.format("ALTER TABLE %s ADD COLUMN %s", this.tableName, column.toSqlDefinitionString()));
			}
		}
		new CreateIndexes(this.tableName, this.columns).applyUpdatesForTable(db, table);
	}
}
