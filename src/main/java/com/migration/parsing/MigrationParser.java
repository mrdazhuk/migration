package com.migration.parsing;

import com.migration.MigrationException;
import com.migration.MigrationReader;
import com.migration.VersionMigration;
import com.migration.parsing.node.MigrationNodeParser;

/**
 * Created by yuriydazhuk on 9/29/15.
 */
public class MigrationParser {
	private final MigrationReader reader;

	public MigrationParser(MigrationReader reader) {
		this.reader = reader;
	}

	public VersionMigration parseUpdate(int version) throws MigrationException {
		return readMigrationForNode(JsonNodes.UPDATE_NODE, version);
	}


	public VersionMigration parseDowngrade(int version) throws MigrationException {
		return readMigrationForNode(JsonNodes.DOWNGRADE_NODE, version);
	}

	private VersionMigration readMigrationForNode(String mode, int version) throws MigrationException {
		return new MigrationNodeParser(mode).parse(this.reader.getMigration(version));
	}
}
