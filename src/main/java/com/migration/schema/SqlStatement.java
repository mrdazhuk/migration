package com.migration.schema;

/**
 * Created by yuriydazhuk on 10/6/15.
 */
public interface SqlStatement {
	String toSqlStatement() throws SqlFormatException;
}
