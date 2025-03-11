/*
 * < 동기화 메서드와 동기화 블럭의 차이>
 * 
 * 	동기화 블럭은 공유객체를 지정하여 동기화 블럭이 설정되어 있는 스레드가 점우함을 명시함으로
 */
class Data {
	int[] ar;
	int idx;

	public Data(int size) {
		ar = new int[size];
		idx = -1;
	}

	public void outputData() {
		for (int i = 0; i < ar.length; i++) {
			System.out.println(ar[i]);
		}
	}
}

class InputOne extends Thread {
	Data data;

	public InputOne(Data data) {
		this.data = data;
	}

	public void run() {
		int time;
		for (;;) {
			synchronized (data) {
				data.notify();		// 스레드 대기 해제와 대기의 순서는 유지하되 대기 해제의 시점을 당행과 같이 스레드 종료 제어 시점 이전으로
									// 당김으로써 40행에서 대기된 최종 스레드를 차 스레드로 전환된 작전 스레드가 종료시점 이전에 대기 해제를
									// 히여 최종 스레드에 대한 무한 대기 현상을 해소시킬 수 있고 이에 따라 최종 스레드에 대한 제한적 스레드
									// 대기에 대한 추가 제어 구조 불필요.
				
				if (data.idx >= data.ar.length - 1) {	// 36행의 인덱스 생성식 위치 변화에 따른 스레드 종료 제어 시점 변경과 스레드 제어
					break;								// 알고리즘에 따라 당인덱스가 배열인덱스 종료값 이상이라는 것은 40행에서 최종
				}										// 스레드가 대기된 상태에서 대기 해제된 직전 스레드가 차 스레드로 전환되어 최종 
														// 스레드의 공유 인덱스를 동시에 보유한 상태가 되므로 스레드 종료. 최종 스레드
				data.idx++;								// 또한 차 스레드로 전환된 직전 스레드의 27행에서의 대기 해제로 대기가
				data.ar[data.idx] = 1;					// 해제되면서 루프를 돌아 공유 인덱스를 유지한 채로 스레드 종료.
				
				try {
					data.wait(0);
				} catch (InterruptedException e) {
				}
			}
		}
	}
}

class InputTwo extends Thread {
	Data data;

	public InputTwo(Data data) {
		this.data = data;
	}

	public void run() {
		for (;;) {
			synchronized (data) {
				data.notify();

				if (data.idx >= data.ar.length - 1) {
					break;
				}
				
				data.idx++;
				data.ar[data.idx] = 2;
				
				try {
					data.wait(0);
				} catch (InterruptedException e) { 	}
			}
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Data data = new Data(20);
		InputOne inputOne = new InputOne(data);
		InputTwo inputTwo = new InputTwo(data);

		inputOne.start();
		inputTwo.start();

		try {
			inputOne.join();
			inputTwo.join();
		} catch (InterruptedException e) {
		}
		data.outputData();
	}
}