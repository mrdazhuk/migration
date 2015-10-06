package com.migration.schema.type;

import com.migration.schema.type.affinity.Affinities;
import com.migration.schema.type.affinity.Affinity;

/**
 * Created by yuriydazhuk on 10/1/15.
 */
class Text extends DataType {
	@Override
	protected String getSqlTypeName() {
		return "VARCHAR";
	}

	@Override
	protected Affinity getAffinity() {
		return Affinities.TextAffinity;
	}
}
