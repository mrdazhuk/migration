package com.migration.parsing.action;

import com.google.gson.JsonObject;
import com.migration.ParseException;
import com.migration.action.column.DropColumns;
import com.migration.parsing.node.NamesNodeParser;

/**
 * Created by yuriydazhuk on 9/30/15.
 */
public class DropColumnParser implements ActionParser<DropColumns> {
	@Override
	public DropColumns parse(String tableName, JsonObject jsonObject) throws ParseException {
		return new DropColumns(tableName, new NamesNodeParser().parse(jsonObject));
	}
}
