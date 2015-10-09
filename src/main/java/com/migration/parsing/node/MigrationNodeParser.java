package com.migration.parsing.node;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.migration.MigrationException;
import com.migration.ParseException;
import com.migration.VersionMigration;
import com.migration.action.QueryAction;
import com.migration.parsing.action.DropTableParser;
import com.migration.parsing.JsonNodes;
import com.migration.parsing.action.ActionParser;
import com.migration.parsing.action.AddColumnsParser;
import com.migration.parsing.action.CreateTableParser;
import com.migration.parsing.action.DropColumnParser;
import com.migration.parsing.action.RawQueryParser;
import com.migration.parsing.action.RenameTableParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuriydazhuk on 9/30/15.
 */
public class MigrationNodeParser implements NodeParser<VersionMigration, JsonElement> {

    private final String mode;

    public MigrationNodeParser(String mode) {
        this.mode = mode;
    }

    @Override
    public VersionMigration parse(JsonElement json) throws MigrationException {
        List<QueryAction> actions = new ArrayList();

        JsonObject object = json.getAsJsonObject();
        if (object.has(this.mode)) {
            JsonElement updates = object.get(this.mode);
            if (!updates.isJsonArray()) {
                throw new ParseException(String.format("The node '%s' must be a json array", this.mode));
            }

            for (JsonElement update : updates.getAsJsonArray()) {
                JsonObject jsonObject = update.getAsJsonObject();

                String tableName = "";
                if (jsonObject.has(JsonNodes.TABLE_NAME_NODE)) {
                    tableName = jsonObject.get(JsonNodes.TABLE_NAME_NODE).getAsString();
                }
                actions.add(getActionParser(jsonObject).parse(tableName, jsonObject));
            }
        }
        return new VersionMigration(actions);
    }

    private ActionParser getActionParser(JsonObject jsonObject) throws ParseException {
        String action = jsonObject.get("action").getAsString();

        switch (action) {
            case JsonNodes.ADD_COLUMNS_ACTION_NODE:
                return new AddColumnsParser();
            case JsonNodes.DROP_COLUMNS_ACTION_NODE:
                return new DropColumnParser();
            case JsonNodes.UPDATE_COLUMNS_ACTION_NODE:
                //return new UpdateColumnsParser();
                throw new RuntimeException("Not implemented");
            case JsonNodes.CREATE_TABLE_ACTION_NODE:
                return new CreateTableParser();
            case JsonNodes.RENAME_TABLE_ACTION_NODE:
                return new RenameTableParser();
            case JsonNodes.DROP_TABLE_ACTION_NODE:
                return new DropTableParser();
            case JsonNodes.RAW_QUERY_ACTION_NODE:
                return new RawQueryParser();
        }
        throw new ParseException("No action found " + action);
    }
}
