class Thread0 extends Thread {
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) { }
				System.out.println(Thread.currentThread().getName());
			
		}
	}
}
class Thread1 extends Thread {
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) { }
				System.out.println(Thread.currentThread().getName());
			
		}
	}
}
public class Main {
	public static void main(String[] args) {
		Thread0 thread0 = new Thread0();
		Thread1 thread1 = new Thread1();
		
		
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		thread0.setPriority(Thread.NORM_PRIORITY);
		thread1.setPriority(Thread.MAX_PRIORITY);
		
		thread0.start();
		thread1.start();
		
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) { }
				System.out.println(Thread.currentThread().getName());
			}
		
	}
}
