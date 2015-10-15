package com.migration.schema;

import android.text.TextUtils;

import com.migration.schema.type.DataType;

/**
 * Created by yuriydazhuk on 9/28/15.
 */
public class Column implements SqlStatement {
	private boolean primary;
	private boolean index;
	private String name;
	private DataType dataType;
	private String defaultValue = "";
	private boolean nullable = true;


	private Reference reference;

	private Column(Column column) {
		this.dataType = column.getDataType();
		this.primary = column.isPrimary();
		this.name = column.getName();
		this.index = column.isIndexed();
		this.nullable = column.isNullable();
		this.defaultValue = column.getDefaultValue();

		Reference reference = column.getReference();
		if (reference != null) {
			this.reference = new Reference(reference.getTableName(), reference.getReferenceFieldName());
		}
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

	public Reference getReference() {
		return this.reference;
	}

	@Override
	public String toSqlStatement() throws SqlFormatException {
		StringBuilder builder = new StringBuilder();
		builder.append(this.name);
		builder.append(" " + this.dataType.getName());

		if (this.primary) {
			builder.append("  PRIMARY KEY AUTOINCREMENT");
		}
		if (!TextUtils.isEmpty(this.defaultValue)) {
			builder.append(" DEFAULT " + this.dataType.getFormattedDefaultValue(this.defaultValue));
		}
//        } else if (!this.nullable) {
//            throw new SqlFormatException(String.format("Missing default value for not nullable field '%s'", this.name));
//        }
		if (!this.nullable) {
			builder.append(" NOT NULL");
		}

		if (this.reference != null) {
			builder.append(String.format(" REFERENCES %s", this.reference.toSqlStatement()));
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

		public ColumnBuilder reference(String tableName) {
			String[] parts = tableName.split("\\.");
			if (parts.length == 1) {
				this.column.reference = new Reference(tableName);
			} else {
				reference(parts[0], parts[1]);
			}
			return this;
		}

		public ColumnBuilder reference(String tableName, String referenceFieldName) {
			this.column.reference = new Reference(tableName, referenceFieldName);
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
