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
		thread1.setPriority(Thread.MAX_PRIORITY);	// setPriority(int, newPriority) : 스레드 객체에 대한 우선순위
													// 지정. 스레드 실행 전 먼저 변경하는 것이 원칙. 여기서는 실습 환경상
		thread0.start();							// sleep을 통한 스레대 대기를 발생시켜 스레드 실행 후 변경을 해도 문재가 없는
		thread1.start();							// 것 처럼 보이나 이는 실습을 위한 가상 환경에 대한 인위적 처리일뿐, 실제
													// 멀티스레드 환경에서 스레드를 기동 후 우선순위를 조정하면 이미 개별 스레드들이
													// 우선순위 변경전의 상태로 임의의 시점까지 진행이 처리된 상태에서 변경을
		for (int i = 0; i < 5; i++) {				// 함으고써 그 변경되는 시점을 예측 불가한 상태가 될 수 있음.
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) { }
				System.out.println(Thread.currentThread().getName());
			}
		
	}
}
