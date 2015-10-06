package com.migration.schema.type;

import com.migration.schema.SqlFormatException;
import com.migration.schema.type.affinity.Affinity;

/**
 * Created by yuriydazhuk on 10/1/15.
 */
public abstract class DataType {
	protected abstract String getSqlTypeName();

	protected abstract Affinity getAffinity();


	public String getFormattedDefaultValue(String value) {
		return getAffinity().formatDefaultValue(value);
	}

	public String getName() {
		return getSqlTypeName();
	}

	public static DataType fromString(String type) throws SqlFormatException {
		switch (type) {
			case "TEXT":
			case "VARCHAR":
				return new Text();
			case "BOOLEAN":
				return new Boolean();
			case "LONG":
				return new Long();
			case "DOUBLE":
				return new Double();
			case "INTEGER":
			case "INT":
				return new Int();
			case "FLOAT":
				return new Float();
			default:
				return new Other(type);
		}
	}
}
