package com.migration.parsing.node;

import android.text.TextUtils;

import com.google.gson.JsonObject;
import com.migration.MigrationException;
import com.migration.ParseException;
import com.migration.parsing.JsonNodes;
import com.migration.schema.Column;

/**
 * Created by yuriydazhuk on 9/30/15.
 */
public class ColumnNodeParser implements NodeParser<Column, JsonObject> {


	@Override
	public Column parse(JsonObject column) throws MigrationException {
		Column.ColumnBuilder builder = Column.builder();

		if (!column.has(JsonNodes.NAME_NODE)) {
			throw new ParseException(String.format("Parsing error. Missing node '%s'", JsonNodes.NAME_NODE));
		}
		builder.name(column.get(JsonNodes.NAME_NODE).getAsString());
		builder.type(column.has(JsonNodes.TYPE_NODE) ? column.get(JsonNodes.TYPE_NODE).getAsString() : "");

		boolean nullable = column.has(JsonNodes.NULLABLE_NODE) ? column.get(JsonNodes.NULLABLE_NODE).getAsBoolean() : true;
		if (!nullable) {
			builder.notNull();
		}
		builder.defaultValue(column.has(JsonNodes.DEFAULT_NODE) ? column.get(JsonNodes.DEFAULT_NODE).getAsString() : "");

		if (column.has(JsonNodes.INDEX_NODE) && column.get(JsonNodes.INDEX_NODE).getAsBoolean()) {
			builder.indexed();
		}

		if (column.has(JsonNodes.REFERENCE_NODE)) {
			builder.reference(column.get(JsonNodes.REFERENCE_NODE).getAsString());
		}

		return builder.build();
	}
}
