package com.migration.schema;

import com.migration.MigrationException;

/**
 * Created by yuriydazhuk on 9/29/15.
 */
public class SqlFormatException extends MigrationException {
	public SqlFormatException(String detailMessage) {
		super(detailMessage);
	}
}
