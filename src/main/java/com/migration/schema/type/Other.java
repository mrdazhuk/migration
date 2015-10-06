package com.migration.schema.type;

import com.migration.schema.type.affinity.Affinities;
import com.migration.schema.type.affinity.Affinity;

/**
 * Created by yuriydazhuk on 10/1/15.
 */
class Other extends DataType {
	private final String sqlTypeName;

	public Other(String sqlTypeName) {
		this.sqlTypeName = sqlTypeName;
	}

	@Override
	protected String getSqlTypeName() {
		return sqlTypeName;
	}

	@Override
	protected Affinity getAffinity() {
		return Affinities.RealAffinity;
	}
}
