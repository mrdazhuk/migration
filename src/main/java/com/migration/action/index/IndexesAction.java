package com.migration.action.index;

import android.database.sqlite.SQLiteDatabase;

import com.migration.OrmLiteIndexNameBuilder;
import com.migration.action.TableAction;
import com.migration.schema.Column;
import com.migration.schema.SqlFormatException;
import com.migration.schema.Table;

import java.util.Arrays;
import java.util.List;


/**
 * Created by yuriydazhuk on 10/1/15.
 */
public abstract class IndexesAction extends TableAction {
	private List<Column> columns;
	private IndexNameBuilder indexNameBuilder= new OrmLiteIndexNameBuilder();//TODO change to injections

	protected abstract void doIndexActionOnColumn(SQLiteDatabase db, Table table, String indexName, String columnName) throws SqlFormatException;

	public IndexesAction(String tableName, Column column) {
		this(tableName, Arrays.asList(column));
	}

	public IndexesAction(String tableName, List<Column> columns) {
		super(tableName);
		this.columns = columns;
	}

	@Override
	public void applyUpdatesForTable(SQLiteDatabase db, Table table) throws SqlFormatException {
		for (Column column : this.columns) {
			if (table.containColumn(column.getName()) && column.isIndexed()) {
				doIndexActionOnColumn(db, table, this.indexNameBuilder.buildForField(tableName, column.getName()), column.getName());
			}
		}
	}



}
