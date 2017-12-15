package net.lzw.ds.arr;

public class ArrayUtils {

	public static int hourglass(int[][] arr) {
		int xa = 0;
		int xb = 1;
		int xc = 2;
		int ya = 0;
		int yb = 1;
		int yc = 2;

		int max = Integer.MIN_VALUE;
		String graphic = "";
		int loop = 4;
		int a, b, c, d, e, f, g;
		for (int i = 0; i < loop; i++) {
			for (int j = 0; j < loop; j++) {
				a = arr[xa + i][ya + j];
				b = arr[xa + i][yb + j];
				c = arr[xa + i][yc + j];
				d = arr[xb + i][yb + j];
				e = arr[xc + i][ya + j];
				f = arr[xc + i][yb + j];
				g = arr[xc + i][yc + j];
				int sum = a + b + c + d + e + f + g;
				if (max < sum) {
					max = sum;
					graphic = a + " " + b + " " + c + "\n  " + d + "\n" + e + " " + f + " " + g + "\n";
				}
			}
		}
		System.out.println(graphic);
		return max;
	}

	public static void main(String[] args) {
		testHourglass();
	}

	static void testHourglass() {
		int[][] arr = ArrayGenerator.generate2D("1,1,1,0,0,0;0,1,0,0,0,0;1,1,1,0,0,0;0,0,2,4,4,0;0,0,0,2,0,0;0,0,1,2,4,0;");
		arr = ArrayGenerator
		        .generate2D("-1,-1,0,-9,-2,-2;-2,-1,-6,-8,-2,-5;-1,-1,-1,-2,-3,-4;-1,-9,-2,-4,-4,-5;-7,-3,-3,-2,-9,-9;-1,-3,-1,-2,-4,-5;");
		int max = hourglass(arr);
		System.out.println(max);
		// System.out.println(-1 < -2);
	}

}
