package com.migration.action.column;

import android.database.sqlite.SQLiteDatabase;

import com.migration.StatementsUtils;
import com.migration.action.TableAction;
import com.migration.action.index.CreateIndexes;
import com.migration.action.index.DropIndexes;
import com.migration.action.table.CreateTable;
import com.migration.action.table.DropTable;
import com.migration.action.table.RenameTable;
import com.migration.schema.Column;
import com.migration.schema.SqlFormatException;
import com.migration.schema.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuriydazhuk on 9/29/15.
 */
public class DropColumns extends TableAction {
	private List<String> columns = new ArrayList();

	public DropColumns(String tableName, List<String> columns) {
		super(tableName);
		this.columns = columns;
	}

	@Override
	public void applyUpdatesForTable(SQLiteDatabase db, Table table) throws SqlFormatException {
		List<Column> newColumns = removeColumns(table.getColumns());


		String oldTempTableName = this.tableName + "_old";

		new DropIndexes(this.tableName, table.getColumns()).applyUpdatesForTable(db, table);
		new RenameTable(this.tableName, oldTempTableName).applyUpdatesForTable(db, table);
		new CreateTable(tableName, newColumns).applyUpdatesForTable(db, table);
		new CreateIndexes(this.tableName, newColumns).applyUpdatesForTable(db, table);

		String columnsStatement = getCreateColumnsStatement(newColumns);
		db.execSQL(String.format("INSERT INTO %s (%s) SELECT %s FROM %s", this.tableName, columnsStatement, columnsStatement, oldTempTableName));

		new DropTable(oldTempTableName).applyUpdatesForTable(db, table);
	}

	private String getCreateColumnsStatement(List<Column> newColumns) throws SqlFormatException {
		return StatementsUtils.concatStatements(newColumns, from -> ((Column) from).getName());
	}

	private List<Column> removeColumns(List<Column> columns) throws SqlFormatException {
		return StatementsUtils.filter(columns, column -> !this.columns.contains(((Column) column).getName()));
	}
}
