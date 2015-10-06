package com.migration.schema;

import java.util.List;

/**
 * Created by yuriydazhuk on 9/29/15.
 */
public class Table {
	private List<Column> columns;
	private String name;

	public Table(String name, List<Column> columns) {
		this.name = name;
		this.columns = columns;
	}

	public boolean containColumn(String name) {
		for (Column column : this.columns) {
			if (column.getName().equals(name)) {
				return true;//TODO refactor this
			}
		}
		return false;
	}

	public List<Column> getColumns() {
		return this.columns;
	}

	public String getName() {
		return this.name;
	}
}
