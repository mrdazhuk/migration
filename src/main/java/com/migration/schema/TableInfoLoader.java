package com.migration.schema;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.migration.MigrationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuriydazhuk on 9/29/15.
 */
public class TableInfoLoader {
	private final SQLiteDatabase database;

	public TableInfoLoader(SQLiteDatabase database) {
		this.database = database;
	}

	public static TableInfoLoader forDB(SQLiteDatabase sqLiteDatabase) {
		return new TableInfoLoader(sqLiteDatabase);
	}

	public Table loadTable(String tableName) throws MigrationException {

		Map<String, String> indexes = getIndexes(database, tableName);
		List<Column> columns = getTableColumns(this.database, tableName, indexes);

		if (columns.size() == 0) {
			throw new TableNotFoundException(String.format("Table '%s' not found", tableName));
		}
		return new Table(tableName, columns);
	}

	private Map<String, String> getIndexes(SQLiteDatabase database, String tableName) throws MigrationException {
		String command = String.format("pragma index_list(%s);", tableName);

		List<String> indexes = executeOnDb(database, command, cursor -> cursor.getString(cursor.getColumnIndex("name")));

		Map<String, String> indexesMap = new HashMap();
		for (String index : indexes) {
			String getIndexInfoCommand = String.format("pragma index_info(%s);", index);
			List<String> indexNames = executeOnDb(database, getIndexInfoCommand, cursor -> cursor.getString(cursor.getColumnIndex("name")));

			if (!indexNames.isEmpty()) {
				indexesMap.put(indexNames.get(0), index);
			}
		}
		return indexesMap;
	}

	private List<Column> getTableColumns(SQLiteDatabase database, String tableName, Map<String, String> indexes) throws MigrationException {
		String command = String.format("pragma table_info(%s);", tableName);
		return executeOnDb(database, command, cursor ->
		{
			Column.ColumnBuilder builder = Column.builder();
			if (cursor.getInt(cursor.getColumnIndex("pk")) != 0) {
				builder.primary();
			}

			builder.defaultValue(cursor.getString(cursor.getColumnIndex("dflt_value")));
			if (cursor.getInt(cursor.getColumnIndex("notnull")) != 0) {
				builder.notNull();
			}

			String columnName = cursor.getString(cursor.getColumnIndex("name"));
			builder.name(columnName);
			builder.type(cursor.getString(cursor.getColumnIndex("type")));

			if (indexes.containsKey(columnName)) {
				builder.indexed();
			}
			return builder.build();
		});
	}

	private <T> List<T> executeOnDb(SQLiteDatabase database, String cmd, Action<Cursor, T> action) throws MigrationException {
		List<T> items = new ArrayList();
		Cursor cur = database.rawQuery(cmd, null);

		while (cur.moveToNext()) {
			items.add(action.doAction(cur));
		}
		cur.close();

		return items;
	}

	interface Action<F, T> {
		T doAction(F from) throws MigrationException;
	}

}
