package com.migration.parsing.node;

import com.google.gson.JsonElement;
import com.migration.MigrationException;

/**
 * Created by yuriydazhuk on 9/30/15.
 */
interface NodeParser<T, J extends  JsonElement> {
	T parse(J json) throws  MigrationException;
}
