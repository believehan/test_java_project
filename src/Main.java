import java.util.Arrays;
import java.util.Random;

public class Main {
	public static void Rand(int[] data) {
		Random rd = new Random();

		for (int i : data) {
			int v;
			int ch = rd.nextInt(data.length);
			int ch1 = rd.nextInt(data.length);
			
			v = data[ch];
			data[ch] = data[ch1];
			data[ch1]=v;
		}
		System.out.println(Arrays.toString(data));
	}

	// 랜덤메서드

	public static void main(String[] args) {
		int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		System.out.println(Arrays.toString(data));
		Rand(data);
	}
}