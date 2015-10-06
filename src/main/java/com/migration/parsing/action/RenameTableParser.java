package com.migration.parsing.action;

import com.google.gson.JsonObject;
import com.migration.MigrationException;
import com.migration.action.table.RenameTable;
import com.migration.parsing.node.TableNameNodeParser;

/**
 * Created by yuriydazhuk on 10/1/15.
 */
public class RenameTableParser implements ActionParser<RenameTable> {
	@Override
	public RenameTable parse(String tableName, JsonObject jsonObject) throws MigrationException {
		return new RenameTable(tableName, new TableNameNodeParser().parse(jsonObject));
	}
}
