package com.migration.parsing.action;

import com.google.gson.JsonObject;
import com.migration.MigrationException;
import com.migration.action.column.AddColumns;
import com.migration.parsing.node.ColumnsNodeParser;

/**
 * Created by yuriydazhuk on 9/30/15.
 */
public class AddColumnsParser implements ActionParser<AddColumns> {
	@Override
	public AddColumns parse(String tableName, JsonObject jsonObject) throws MigrationException {
		return new AddColumns(tableName, new ColumnsNodeParser().parse(jsonObject));
	}
}
