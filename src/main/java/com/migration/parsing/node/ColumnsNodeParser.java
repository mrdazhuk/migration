package com.migration.parsing.node;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.migration.MigrationException;
import com.migration.ParseException;
import com.migration.parsing.JsonNodes;
import com.migration.schema.Column;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuriydazhuk on 9/30/15.
 */
public class ColumnsNodeParser implements NodeParser<List<Column>, JsonObject> {
	@Override
	public List<Column> parse(JsonObject json) throws MigrationException {
		JsonElement columnsJson = json.get(JsonNodes.COLUMNS_NODE);

		if (columnsJson == null || !columnsJson.isJsonArray()) {
			throw new ParseException(String.format("Parsing error. Missing node '%s'", JsonNodes.COLUMNS_NODE));
		}

		List<Column> columns = new ArrayList();
		ColumnNodeParser columnNode = new ColumnNodeParser();

		for (JsonElement columnObject : columnsJson.getAsJsonArray()) {
			columns.add(columnNode.parse(columnObject.getAsJsonObject()));
		}
		return columns;
	}
}
