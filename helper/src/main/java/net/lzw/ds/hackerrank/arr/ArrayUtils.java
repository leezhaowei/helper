package net.lzw.ds.hackerrank.arr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ArrayUtils {

	static void log(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (i < arr.length - 1) {
				System.out.print(" ");
			} else {
				System.out.println();
			}
		}
	}

	static void log(long[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (i < arr.length - 1) {
				System.out.print(" ");
			} else {
				System.out.println();
			}
		}
	}

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

	public static void dynamicArr(long[][] input) {
		long n = input[0][0];
		long q = input[0][1];

		Map<Long, List<Long>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			map.put(Long.valueOf(i), new ArrayList<>());
		}
		long lastAn = 0;

		long[] query = null;
		for (int i = 0; i < q; i++) {
			query = input[i + 1];
			if (query == null || query.length == 0) {
				return;
			}
			if (query.length != 3) {
				throw new IllegalArgumentException();
			}
			long type = query[0];
			long idxList = (query[1] ^ lastAn) % n;
			List<Long> list = map.get(Long.valueOf(idxList));
			if (type == 1) {
				list.add(query[2]);
			} else if (type == 2) {
				int idx = ((int) query[2]) % list.size();
				lastAn = list.get(idx);
				System.out.println(lastAn);
			}
		}
	}

	public static int[] leftRotation(int[] a, int d) {
		log(a);
		if (a.length == 1) {
			return a;
		}
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < d; i++) {
			list.add(a[i]);
		}
		for (int i = a.length - 1; i > d - 1; i--) {
			list.addFirst(a[i]);
		}
		int[] r = new int[a.length];
		for (int i = 0; i < r.length; i++) {
			r[i] = list.removeFirst().intValue();
		}
		log(r);
		return r;
	}

	public static void algorithmicCrush(int n, int m, int[][] inputs) {
		// int n = 10;
		// int m = 10;
		// int[][] inputs = ArrayGenerator.generateAlgorithmicCrush(n, m);
		long[] arr = new long[n];
		long max = Long.MIN_VALUE;
		for (int idx = 0; idx < m; idx++) {
			int a = inputs[idx][0];
			int b = inputs[idx][1];
			int k = inputs[idx][2];

			for (int i = a - 1; i < b; i++) {
				arr[i] += k;
				if (arr[i] > max) {
					max = arr[i];
				}
			}
		}
		// log(arr);
		System.out.println(max);
	}

	public static void algorithmicCrush2(int n, int m, int[][] inputs) {
		long[] arr = new long[n];
		long max = Long.MIN_VALUE;
		for (int idx = 0; idx < m; idx++) {
			int a = inputs[idx][0];
			int b = inputs[idx][1];
			int k = inputs[idx][2];

			arr[a - 1] += k;
			if (b < n) {
				arr[b] -= k;
			}
		}
		// log(arr);

		long tmp = 0;
		for (int i = 0; i < n; i++) {
			tmp += arr[i];
			// System.out.println(tmp);
			if (tmp > max) {
				max = tmp;
			}
		}
		System.out.println(max);
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		// testHourglass();
		// testDynamicArr();
		// testLeftRotation();
		// test();
		testAlgorithmicCrush();

		long end = System.currentTimeMillis() - start;
		System.out.println("\nTime: " + end);
	}

	static void testAlgorithmicCrush() {
		int n = 10000;
		int m = 5000000;
		int[][] inputs = ArrayGenerator.generateAlgorithmicCrush(n, m);

		long start = 0;
		long end = 0;

		start = System.currentTimeMillis();
		algorithmicCrush2(n, m, inputs);
		end = System.currentTimeMillis() - start;
		System.out.println("\nTime: " + end);

		start = System.currentTimeMillis();
		algorithmicCrush(n, m, inputs);
		end = System.currentTimeMillis() - start;
		System.out.println("\nTime: " + end);
	}

	static void test() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		ArrayList<String> list = new ArrayList<String>(n);
		for (int i = 0; i < n + 1; i++) {
			String line = in.nextLine();
			if (line != null && line.trim().length() > 0) {
				list.add(line);
			}
		}
		int q = in.nextInt();
		ArrayList<String> targetList = new ArrayList<String>();
		for (int i = 0; i < q + 1; i++) {
			String target = in.nextLine();
			if (target == null || target.trim().length() == 0) {
				continue;
			}
			targetList.add(target);
		}
		int[] countArr = new int[q];
		for (int i = 0; i < q; i++) {
			String target = targetList.get(i);
			for (String line : list) {
				if (line.equals(target)) {
					int count = countArr[i];
					count++;
					countArr[i] = count;
				}
			}
		}
		for (int i = 0; i < q; i++) {
			System.out.println(countArr[i]);
		}

		in.close();
	}

	static void testLeftRotation() {
		int[] input = ArrayGenerator.generateArr("17 9 7 12 3 15 19 1 14 5 2 11 13 6 20 10 8 4 18 16");
		leftRotation(input, 6);
	}

	static void testDynamicArr() {
		// Scanner s = new Scanner(System.in);
		// String str = s.nextLine();
		// System.out.println(str);
		// s.close();
		long[][] input = ArrayGenerator.generate2DLong("2,5;1,0,5;1,1,7;1,0,3;2,1,0;2,1,1;");
		input = ArrayGenerator.generate2DLong(
				"100,100;1,345255357,205970905;1,570256166,75865401;1,94777800,645102173;1,227496730,16649450;1,82987157,486734305;1,917920354,757848208;1,61379773,817694251;1,330547128,112869154;1,328743001,855677723;1,407951306,669798718;1,21506172,676980108;1,49911390,342109400;1,980306253,305632965;2,736380701,402184046;2,798108301,416334323;1,254839279,1370035;1,109701362,2800586;1,374257441,165208824;1,624259835,477431250;1,629066664,454406245;1,135821145,763845832;1,480298791,138234911;1,553575409,835718837;1,13022848,624652920;1,974893519,882630870;1,137832930,216177975;1,453349691,969255659;1,138396076,91038209;1,699822497,941751038;1,116800806,64071662;1,815273934,8835809;1,658534867,657771609;1,183760807,179377441;1,93984556,636425656;1,231506463,836238924;1,214074594,709571211;1,649641434,509698468;2,523656673,709717705;2,261443586,330808140;1,75924023,449768243;1,849537714,354568873;2,641743742,124196560;1,19829224,995759639;1,244389335,108315212;1,877758467,421383626;1,573278148,474192994;2,561031511,448889978;1,773294404,980994962;2,33088709,716646168;1,760927835,441983538;1,273540687,783321829;1,5933845,384358662;1,543628702,372160176;2,136400466,910559291;2,546568738,393221347;1,812227065,694221123;1,311065574,103905420;2,571344361,185289590;1,99638520,173318136;1,854060080,407068012;2,980658213,778573744;2,412539660,476853104;1,530145366,36493537;1,604875364,100141497;2,650812104,64817757;1,141060009,766603553;1,598398952,418245941;1,262344011,431865586;2,56413893,546484833;1,400736735,673588153;1,642955232,803851098;1,917782037,935143105;1,658284524,745224102;1,103202288,501551287;1,162144918,12888783;1,16486753,67467208;1,671120703,941541277;1,47399694,77707668;1,122011395,946116527;1,924363862,886726236;2,657761235,540240467;1,203175991,279882007;2,304620923,162838413;1,440453449,117964712;2,941868853,887850334;1,293198923,466812643;1,461688477,532794906;1,315016797,733095902;1,265008751,913972757;1,887405255,139170948;2,754223230,426836947;1,945967814,852589735;1,168866283,309720694;1,373861145,94596540;2,984122495,20702849;2,233835636,180460242;1,172787631,643823473;1,273611372,616819555;1,196104599,690080895;1,527554061,434103342;");
		// System.out.println(input);
		dynamicArr(input);
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
