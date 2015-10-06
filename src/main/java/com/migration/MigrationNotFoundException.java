package com.migration;

/**
 * Created by yuriydazhuk on 9/29/15.
 */
public class MigrationNotFoundException extends MigrationException {
	public MigrationNotFoundException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
