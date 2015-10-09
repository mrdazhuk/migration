package com.migration.parsing.action;

import com.google.gson.JsonObject;
import com.migration.MigrationException;
import com.migration.action.QueryAction;

/**
 * Created by yuriydazhuk on 9/30/15.
 */
public interface ActionParser<T extends QueryAction> {
	T parse(String tableName, JsonObject jsonObject) throws MigrationException;
}
