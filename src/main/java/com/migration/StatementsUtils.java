package com.migration;

import com.migration.schema.SqlFormatException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuriydazhuk on 9/30/15.
 */
public class StatementsUtils {
	private StatementsUtils() {
	}

	public interface Action<F, T> {
		T doAction(F from)throws SqlFormatException ;
	}

	public interface Filter<F> extends Action<F, Boolean> {
		Boolean doAction(F from);
	}

	public static <F> String concatStatements(List<F> source, Action action) throws SqlFormatException {
		StringBuilder builder = new StringBuilder();

		int size = source.size();

		for (int i = 0; i < size; i++) {
			builder.append(action.doAction(source.get(i)));
			if (i + 1 < size) {
				builder.append(", ");
			}
		}
		return builder.toString();
	}

	public static <F> List<F> filter(List<F> source, Filter filter) throws SqlFormatException {
		List<F> output = new ArrayList();
		for (F item : source) {
			if (filter.doAction(item)) {
				output.add(item);
			}
		}
		return output;
	}
}
