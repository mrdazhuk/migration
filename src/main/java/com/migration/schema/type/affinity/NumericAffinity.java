package com.migration.schema.type.affinity;

/**
 * Created by yuriydazhuk on 10/1/15.
 */
class NumericAffinity implements Affinity{
	@Override
	public int getRule() {
		return 5;
	}

	@Override
	public String formatDefaultValue(String value) {
		return value;
	}
}
