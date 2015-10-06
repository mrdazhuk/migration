package com.migration.schema;

import android.text.TextUtils;

/**
 * Created by yuriydazhuk on 10/6/15.
 */
class Reference implements SqlStatement {
	private String tableName;
	private String referenceFieldName;

	public Reference(String tableName, String referenceFieldName) {
		this.tableName = tableName;
		this.referenceFieldName = referenceFieldName;
	}

	public Reference(String tableName) {
		this(tableName, "");
	}

	public String getTableName() {
		return this.tableName;
	}

	public String getReferenceFieldName() {
		return this.referenceFieldName;
	}

	@Override
	public String toSqlStatement() throws SqlFormatException {
		if(TextUtils.isEmpty(this.referenceFieldName)){
			throw  new SqlFormatException("Reference table name is empty");
		}

		return TextUtils.isEmpty(this.referenceFieldName) ? this.tableName : String.format("%s.%s", this.tableName, this.referenceFieldName);
	}
}
