package com.migration.parsing.node;


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
		return json.get(JsonNodes.RAW_NODE).getAsString();
	}
}
