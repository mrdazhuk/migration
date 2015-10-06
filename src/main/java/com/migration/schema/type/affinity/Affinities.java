package com.migration.schema.type.affinity;

/**
 * Created by yuriydazhuk on 10/1/15.
 */
public class Affinities {
	public static Affinity IntegerAffinity = new IntegerAffinity();
	public static Affinity NumericAffinity = new NumericAffinity();
	public static Affinity RealAffinity = new RealAffinity();
	public static Affinity TextAffinity = new TextAffinity();

	Affinities() {
	}
}
