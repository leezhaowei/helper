package com.zwli.util;

/**
 * According to <a href="https://github.com/google/guava">guava</a>
 * @author zwli
 */
public class TableSize {

	private static final int MAX_POWER_OF_TWO = 1 << (Integer.SIZE - 2);
	private static final int MAX_TABLE_SIZE = MAX_POWER_OF_TWO;
	// Represents how tightly we can pack things, as a maximum.
	private static final double DESIRED_LOAD_FACTOR = 0.7;
	// If the set has this many elements, it will "max out" the table size
	private static final int CUTOFF = (int) (MAX_TABLE_SIZE * DESIRED_LOAD_FACTOR);

	public static int chooseTableSize(int setSize) {
		if (setSize <= 1) {
			return setSize;
		}
		// Correct the size for open addressing to match desired load factor.
		if (setSize < CUTOFF) {
			// Round up to the next highest power of 2.
			int tableSize = Integer.highestOneBit(setSize - 1) << 1;
			while (tableSize * DESIRED_LOAD_FACTOR < setSize) {
				tableSize <<= 1;
			}
			return tableSize;
		}
		// The table can't be completely full or we'll get infinite reprobes
		return MAX_TABLE_SIZE;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int size = 100;
		for (int i = 0; i < size; i++) {
			int tableSize = chooseTableSize(i);
			System.out.println(tableSize);
		}
	}

}
