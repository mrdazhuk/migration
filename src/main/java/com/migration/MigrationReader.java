package com.migration;

import com.google.gson.JsonElement;

/**
 * Created by yuriydazhuk on 9/29/15.
 */
public interface MigrationReader {
	JsonElement getMigration(int dbVersion) throws MigrationNotFoundException;
}
