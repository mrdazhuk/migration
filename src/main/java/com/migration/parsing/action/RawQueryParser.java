package com.migration.parsing.action;

import com.google.gson.JsonObject;
import com.migration.MigrationException;
import com.migration.action.RawQueryAction;
import com.migration.parsing.node.RawNodeParser;

/**
 * Created by Yuriy Dazhuk on 06.10.2015.
 */
public class RawQueryParser implements ActionParser<RawQueryAction> {
    @Override
    public RawQueryAction parse(String tableName, JsonObject jsonObject) throws MigrationException {
        return new RawQueryAction(new RawNodeParser().parse(jsonObject));
    }
}
