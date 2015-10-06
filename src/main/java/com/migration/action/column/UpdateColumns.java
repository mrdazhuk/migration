package com.migration.action.column;

import android.database.sqlite.SQLiteDatabase;

import com.migration.action.TableAction;
import com.migration.schema.Column;
import com.migration.schema.SqlFormatException;
import com.migration.schema.Table;

import java.util.List;


/**
 * Created by yuriydazhuk on 10/1/15.
 */
public class UpdateColumns extends TableAction {
	private final List<Column> columns;

	public UpdateColumns(String tableName, List<Column> columns) {
		super(tableName);
		this.columns = columns;
	}

	@Override
	public void applyUpdatesForTable(SQLiteDatabase db, Table table) throws SqlFormatException {
		if (this.columns.isEmpty()) {
			throw new SqlFormatException("No columns for update");
		}

		//for()
	}
}
