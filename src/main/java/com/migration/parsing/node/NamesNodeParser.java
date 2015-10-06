package com.migration.parsing.node;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.migration.ParseException;
import com.migration.parsing.JsonNodes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuriydazhuk on 9/30/15.
 */
public class NamesNodeParser implements NodeParser<List<String>, JsonObject> {

	@Override
	public List<String> parse(JsonObject json) throws ParseException {
		JsonElement namesJson = json.get(JsonNodes.NAMES_NODE);

		if (namesJson == null || !namesJson.isJsonArray()) {
			throw new ParseException(String.format("Parsing error. Missing node '%s'", JsonNodes.NAMES_NODE));
		}

		List<String> names = new ArrayList();

		for (JsonElement columnObject : namesJson.getAsJsonArray()) {
			JsonElement nameJson = columnObject.getAsJsonObject().get(JsonNodes.NAME_NODE);
			if (nameJson == null) {
				throw new ParseException(String.format("Parsing error. Missing node '%s'", JsonNodes.NAME_NODE));
			}
			names.add(nameJson.getAsString());
		}
		return names;
	}
}
