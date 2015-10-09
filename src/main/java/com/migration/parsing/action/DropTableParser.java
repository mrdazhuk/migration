package com.migration.parsing.action;

import com.google.gson.JsonObject;
import com.migration.MigrationException;
import com.migration.action.table.DropTable;

/**
 * Created by Yuriy Dazhuk on 06.10.2015.
 */
public class DropTableParser implements ActionParser<DropTable> {
    @Override
    public DropTable parse(String tableName, JsonObject jsonObject) throws MigrationException {
        return new DropTable(tableName);
    }
}
