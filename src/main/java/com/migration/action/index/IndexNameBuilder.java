package com.migration.action.index;

/**
 * Created by yuriydazhuk on 10/1/15.
 */
public interface IndexNameBuilder {
	String buildForField(String tableName, String fieldName);
}
