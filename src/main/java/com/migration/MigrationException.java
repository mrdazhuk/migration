package com.migration;

/**
 * Created by yuriydazhuk on 10/1/15.
 */
public class MigrationException extends Exception {
	public MigrationException(String detailMessage) {
		super(detailMessage);
	}

	public MigrationException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
