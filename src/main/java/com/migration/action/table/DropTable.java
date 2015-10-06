package com.migration.action.table;

import android.database.sqlite.SQLiteDatabase;

import com.migration.action.TableAction;
import com.migration.action.index.DropIndexes;
import com.migration.schema.SqlFormatException;
import com.migration.schema.Table;


/**
 * Created by yuriydazhuk on 9/29/15.
 */
public class DropTable extends TableAction {
	public DropTable(String tableName) {
		super(tableName);
	}

	@Override
	public void applyUpdatesForTable(SQLiteDatabase db, Table table) throws SqlFormatException {
		new DropIndexes(this.tableName, table.getColumns()).applyUpdatesForTable(db, table);
		db.execSQL(String.format("DROP TABLE IF EXISTS %s", this.tableName));
	}
}
