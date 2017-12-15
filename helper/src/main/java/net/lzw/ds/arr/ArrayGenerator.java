package net.lzw.ds.arr;

public class ArrayGenerator {

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

	public static void main(String[] args) {
		int[][] arr = generate2D("1,2,3;4,5,6;");
		System.out.println(arr.length);
	}

}
