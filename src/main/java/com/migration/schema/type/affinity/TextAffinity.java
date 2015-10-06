package com.migration.schema.type.affinity;

/**
 * Created by yuriydazhuk on 10/1/15.
 */
class TextAffinity implements Affinity {
	@Override
	public int getRule() {
		return 2;
	}

	@Override
	public String formatDefaultValue(String value) {
		return String.format("'%s'", value);
	}
}
