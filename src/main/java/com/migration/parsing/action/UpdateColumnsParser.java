package com.migration.parsing.action;

import com.google.gson.JsonObject;
import com.migration.MigrationException;
import com.migration.action.column.UpdateColumns;
import com.migration.parsing.node.ColumnsNodeParser;

/**
 * Created by yuriydazhuk on 10/1/15.
 */
public class UpdateColumnsParser implements ActionParser<UpdateColumns> {
	@Override
	public UpdateColumns parse(String tableName, JsonObject jsonObject) throws MigrationException {
		return new UpdateColumns(tableName, new ColumnsNodeParser().parse(jsonObject));
	}
}
