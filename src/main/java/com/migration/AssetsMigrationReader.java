package com.migration;

import android.content.Context;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by yuriydazhuk on 9/29/15.
 */
public class AssetsMigrationReader implements MigrationReader {
	private static final String FILE_NAME = "migrations/%d.json";


	private final Context context;

	public AssetsMigrationReader(Context context) {
		this.context = context;
	}

	@Override
	public JsonElement getMigration(int dbVersion) throws MigrationNotFoundException {
		String fileName = String.format(FILE_NAME, dbVersion);
		try {
			return parseFromFile(context.getAssets().open(fileName));
		} catch (IOException e) {
			throw new MigrationNotFoundException(String.format("Can't load migration %s from assets", fileName), e);
		}
	}

	private JsonElement parseFromFile(InputStream inputStream) throws IOException {
		BufferedReader reader = null;
		try {
			InputStreamReader is = new InputStreamReader((inputStream), "UTF-8");
			return new JsonParser().parse(new BufferedReader(is));
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}
}
