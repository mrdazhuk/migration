package com.migration.parsing;

/**
 * Created by yuriydazhuk on 10/5/15.
 */
public class JsonNodes {
	//Acion
	public static final String UPDATE_NODE = "update";
	public static final String DOWNGRADE_NODE = "downgrade";

	public static final String RAW_NODE = "query";

	//Column
	public static final String NAME_NODE = "name";
	public static final String TYPE_NODE = "type";
	public static final String NULLABLE_NODE = "nullable";
	public static final String DEFAULT_NODE = "default";
	public static final String INDEX_NODE = "index";
	public static final String REFERENCE_NODE = "reference";
	public static final String PRIMARY_NODE = "primary";

	//Table
	public static final String COLUMNS_NODE = "columns";
	public static final String TABLE_NAME_NODE = "table_name";
	public static final String NAMES_NODE = "names";

	//Actions
	public static final String ACTIONS = "action";
	public static final String ADD_COLUMNS_ACTION_NODE = "add_columns";
	public static final String DROP_COLUMNS_ACTION_NODE = "drop_columns";
	public static final String UPDATE_COLUMNS_ACTION_NODE = "update_columns";
	public static final String CREATE_TABLE_ACTION_NODE = "create";
	public static final String RENAME_TABLE_ACTION_NODE = "rename";
	public static final String DROP_TABLE_ACTION_NODE = "drop";
	public static final String RAW_QUERY_ACTION_NODE = "raw";

	private JsonNodes() {
	}


}
