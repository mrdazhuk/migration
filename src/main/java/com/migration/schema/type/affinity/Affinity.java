package com.migration.schema.type.affinity;

/**
 * Created by yuriydazhuk on 10/1/15.
 */
public interface Affinity {
	int getRule();
	String formatDefaultValue(String value);
}
