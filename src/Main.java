class Num {
	int sum = 0;
	int n = 0;
}

class Th1 extends Thread {
	Num num;

	public Th1(Num num) {
		this.num = num;
	}

	public void run() {
		for (int i = 1; i <= 50; i++) {
			synchronized (num) {
				try {
					num.wait();
				} catch (InterruptedException e) {	}
				
				num.n++;
				num.sum += num.n;

				System.out.println(i + " : " + num.n);
			}
		}
	}
}

class Th2 extends Thread {
	Num num;

	public Th2(Num num) {
		this.num = num;
	}

	public void run() {
		for (int i = -1; i >= -50; i--) {
			synchronized (num) {
				num.n++;
				num.sum += num.n;

				System.out.println(i + " : " + num.n);
				num.notify();
			}
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Num num = new Num();
		Th1 th1 = new Th1(num);
		Th2 th2 = new Th2(num);

		th1.start();
		th2.start();

		try {
			th1.join();
			th2.join();
		} catch (InterruptedException e) {
		}

		System.out.println(num.sum);
	}
}