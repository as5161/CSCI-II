public class Problem4 {
	public static int min(int x, y) {
		if (x < y) {
			return x;
		} else {
			return y;
		}
	}

	public static void main(String[] args) {
		int i = min(10, 20);
	}
}
