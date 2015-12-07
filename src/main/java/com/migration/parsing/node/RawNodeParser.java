package com.migration.parsing.node;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.migration.ParseException;
import com.migration.parsing.JsonNodes;

/**
 * Created by yuriydazhuk on 9/30/15.
 */
public class RawNodeParser implements NodeParser<String, JsonObject> {

	@Override
	public String parse(JsonObject json) throws ParseException {
		if (!json.has(JsonNodes.RAW_NODE)) {
			throw new ParseException(String.format("Missing parameter '%s' for rename table", JsonNodes.RAW_NODE));
		}
		JsonElement rawElement = json.get(JsonNodes.RAW_NODE);
		if (rawElement.isJsonArray()) {
			StringBuilder stringBuilder = new StringBuilder();
			for (JsonElement element : rawElement.getAsJsonArray()) {
				stringBuilder.append(element.getAsString());
			}
			return stringBuilder.toString();
		} else {
			return rawElement.getAsString();
		}
	}
}
