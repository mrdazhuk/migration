package com.migration.schema;

import com.migration.MigrationException;

/**
 * Created by yuriydazhuk on 9/29/15.
 */
public class TableNotFoundException extends MigrationException {
	public TableNotFoundException(String detailMessage) {
		super(detailMessage);
	}
}
