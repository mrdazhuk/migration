package com.migration;

import com.migration.action.index.IndexNameBuilder;

/**
 * Created by yuriydazhuk on 10/1/15.
 */
public class OrmLiteIndexNameBuilder implements IndexNameBuilder {
	@Override
	public String buildForField(String tableName, String columnName) {
		return String.format("%s_%s_idx", tableName, columnName);
	}
}
