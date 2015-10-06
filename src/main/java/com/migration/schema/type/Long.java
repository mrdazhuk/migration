package com.migration.schema.type;

import com.migration.schema.type.affinity.Affinities;
import com.migration.schema.type.affinity.Affinity;

/**
 * Created by yuriydazhuk on 10/1/15.
 */
class Long extends DataType {
	@Override
	protected String getSqlTypeName() {
		return "BIGINT";
	}

	@Override
	protected Affinity getAffinity() {
		return Affinities.IntegerAffinity;
	}
}
