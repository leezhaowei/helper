package net.lzw.ds.arr;

import java.util.Random;

public class ArrayGenerator {

	public static long[][] generate2DLong(String arrStr) {
		String[] first = arrStr.split(";");
		long[][] arr = new long[first.length][];
		for (int i = 0; i < first.length; i++) {
			String[] second = first[i].split(",");
			arr[i] = parseLong(second);
		}
		return arr;
	}

	private static long[] parseLong(String[] second) {
		long[] arr = new long[second.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Long.parseLong(second[i]);
		}
		return arr;
	}

	/**
	 * @param arrStr
	 *            a,b,c;d,e,f;
	 * @return
	 */
	public static int[][] generate2D(String arrStr) {
		String[] first = arrStr.split(";");
		int[][] arr = new int[first.length][];
		for (int i = 0; i < first.length; i++) {
			String[] second = first[i].split(",");
			arr[i] = parse(second);
		}
		return arr;
	}

	private static int[] parse(String[] second) {
		int[] arr = new int[second.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(second[i]);
		}
		return arr;
	}

	public static int[] generateArr(String arrStr) {
		String[] arr = arrStr.split(" ");
		return parse(arr);
	}

	public static int[][] generateAlgorithmicCrush(int n, int m) {
		int[][] inputs = new int[m][3];
		Random random = new Random();
		int a = -1;
		int b = -1;
		int k = 0;
		int count = 0;
		while (count != m) {
			a = random.nextInt(n);
			b = random.nextInt(n);
			if (a == b) {
				continue;
			}
			inputs[count][0] = Math.min(a, b) + 1;
			inputs[count][1] = Math.max(a, b) + 1;
			k = Math.abs(random.nextInt(100));
			inputs[count][2] = k;
			// System.out.println(inputs[count][0] + ", " + inputs[count][1] + ", " + inputs[count][2]);
			count++;
		}
		// System.out.println(count);
		// System.out.println(inputs.length);

		return inputs;
	}

	public static void main(String[] args) {
		// int[][] arr = generate2D("1,2,3;4,5,6;");
		// System.out.println(arr.length);
		generateAlgorithmicCrush(10, 100);
	}

}
