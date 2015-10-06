package com.migration.action.table;

import android.database.sqlite.SQLiteDatabase;

import com.migration.action.TableAction;
import com.migration.action.index.CreateIndexes;
import com.migration.action.index.DropIndexes;
import com.migration.schema.SqlFormatException;
import com.migration.schema.Table;

/**
 * Created by yuriydazhuk on 9/29/15.
 */
public class RenameTable extends TableAction {
	private String newTableName;

	public RenameTable(String name, String newName) {
		super(name);
		this.newTableName = newName;
	}

	@Override
	public void applyUpdatesForTable(SQLiteDatabase db, Table table) throws SqlFormatException {
		new DropIndexes(this.tableName, table.getColumns()).applyUpdatesForTable(db, table);
		db.execSQL(String.format("ALTER TABLE %s RENAME TO %s", this.tableName, this.newTableName));
		new CreateIndexes(this.newTableName, table.getColumns()).applyUpdatesForTable(db, table);
	}
}
