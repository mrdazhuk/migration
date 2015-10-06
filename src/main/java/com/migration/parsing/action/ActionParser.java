package com.migration.parsing.action;

import com.google.gson.JsonObject;
import com.migration.MigrationException;
import com.migration.action.TableAction;

/**
 * Created by yuriydazhuk on 9/30/15.
 */
public interface ActionParser<T extends TableAction> {
	T parse(String tableName, JsonObject jsonObject) throws MigrationException;
}
