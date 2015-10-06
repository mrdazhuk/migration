package com.migration.parsing.action;

import com.google.gson.JsonObject;
import com.migration.MigrationException;
import com.migration.action.table.CreateTable;
import com.migration.parsing.node.ColumnsNodeParser;

/**
 * Created by yuriydazhuk on 9/30/15.
 */
public class CreateTableParser implements ActionParser<CreateTable> {
	@Override
	public CreateTable parse(String tableName, JsonObject jsonObject) throws MigrationException {
		return new CreateTable(tableName, new ColumnsNodeParser().parse(jsonObject));
	}
}
