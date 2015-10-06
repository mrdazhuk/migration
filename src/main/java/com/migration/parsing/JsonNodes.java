package com.migration.parsing;

/**
 * Created by yuriydazhuk on 10/5/15.
 */
public class JsonNodes {
	//Acion
	public static final String UPDATE_NODE = "update";
	public static final String DOWNGRADE_NODE = "downgrade";

	//Column
	public static final String NAME_NODE = "name";
	public static final String TYPE_NODE = "type";
	public static final String NULLABLE_NODE = "nullable";
	public static final String DEFAULT_NODE = "default";
	public static final String INDEX_NODE = "index";
	public static final String REFERENCE_NODE = "reference";

	//Table
	public static final String COLUMNS_NODE = "columns";
	public static final String TABLE_NAME_NODE = "table_name";
	public static final String NAMES_NODE = "names";

	//Actions
	public static final String ADD_COLUMNS_ACTION_NODE = "add_columns";
	public static final String DROP_COLUMNS_ACTION_NODE = "drop_columns";
	public static final String UPDATE_COLUMNS_ACTION_NODE = "update_columns";
	public static final String CREATE_TABLE_ACTION_NODE = "create";
	public static final String RENAME_TABLE_ACTION_NODE = "rename";

	private JsonNodes() {
	}


}
