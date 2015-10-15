package com.migration;

/**
 * Created by yuriydazhuk on 10/8/15.
 */
public interface MigrationDatabaseListener {
	void onStarted();

	void onFinished();
}
