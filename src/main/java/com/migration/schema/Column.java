package com.migration.schema;

import android.text.TextUtils;

import com.migration.schema.type.DataType;

/**
 * Created by yuriydazhuk on 9/28/15.
 */
public class Column {
	private boolean primary;
	private boolean index;
	private String name;
	private DataType dataType;
	private String defaultValue = "";
	private boolean nullable = true;

	private Column(Column column) {
		this.dataType = column.getDataType();
		this.primary = column.isPrimary();
		this.name = column.getName();
		this.index = column.isIndexed();
		this.nullable = column.isNullable();
		this.defaultValue = column.getDefaultValue();
	}

	private Column() {
	}

	public boolean isPrimary() {
		return this.primary;
	}

	public boolean isIndexed() {
		return this.index;
	}

	public String getName() {
		return this.name;
	}

	public DataType getDataType() {
		return this.dataType;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public boolean isNullable() {
		return this.nullable;
	}

	public static ColumnBuilder builder() {
		return new ColumnBuilder();
	}

	public String toSqlDefinitionString() throws SqlFormatException {
		StringBuilder builder = new StringBuilder();
		builder.append(this.name);
		builder.append(" " + this.dataType.getName());

		if (this.primary) {
			builder.append("  PRIMARY KEY AUTOINCREMENT");
		} else if (!this.nullable) {
			builder.append(" NOT NULL");

			if (!TextUtils.isEmpty(this.defaultValue)) {
				builder.append(" DEFAULT " + this.dataType.getFormattedDefaultValue(this.defaultValue));
			} else {
				throw new SqlFormatException(String.format("Missing default value for not nullable field '%s'", this.name));
			}
		}
		return builder.toString();
	}

	public static class ColumnBuilder {
		private Column column = new Column();

		private ColumnBuilder() {
		}

		public ColumnBuilder primary() {
			this.column.primary = true;
			this.indexed();
			return this;
		}

		public ColumnBuilder indexed() {
			this.column.index = true;
			return this;
		}

		public ColumnBuilder name(String name) {
			this.column.name = name;
			return this;
		}

		public ColumnBuilder type(String dataType) throws SqlFormatException {
			this.column.dataType = DataType.fromString(dataType);
			return this;
		}

		public ColumnBuilder defaultValue(String defaultValue) {
			this.column.defaultValue = defaultValue;
			return this;
		}

		public ColumnBuilder notNull() {
			this.column.nullable = false;
			return this;
		}

		public Column build() {
			return new Column(this.column);
		}
	}
}
