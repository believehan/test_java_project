import java.util.ArrayList;
import java.util.LinkedList;

public class Main {         
   public static void main(String[] args) {
      ArrayList<String> arList = new ArrayList<String>();		// 배열리스트는 메모리의 인접한 공간에 자료를 연속적으로 배치하는 반면 연결리스트는
      LinkedList<String> lkList = new LinkedList<String>();		// 힙의 입의의 공간에 각각의 자료를 기억시키되 링크로 연결하여 순서를 구분. 이에 따라
      															// 삽입, 삭제 시 배열리스트는 지속적인 자료의 복사가 이루어 지는 반면, 연결리스트는 
      															// 해당 링크의 연결 순서만 조작하면 되므로 상대적 속도가 빠름. 이에 반해 자료를 읽어
      															// 들일 때는 배열리스트의 경우 자료의 연속성으로 인해 자료크리에 인덱스만 곱하면 쉽게
      															// 구할 수 있는 반면 연결리스트는 처음부터 순서에 맞추어 링크를 따라가면 차례대며 검색
      															// 해야하므로 상대적 읽는 속도가 느림.
      
      long start=System.currentTimeMillis();	// long currentTimeMillis() : System클래스의 정적 메서드로써 특정 연도의 날짜와 시간을
      											// 기점으로 현재까지 경과한 시간을 1000분의 1초 단위로 쪼개어 일반화 된 초 리턴. 이는 사람이 인지할 수
      											// 없는 일차원적인 시간표현이 되므로 일반적으로 시간의 조사 보다는 조사 시점간의 경과 시간을 조사하는
      											// 목적으로 활용
      for (int i = 0; i < 100000; i++) {
		arList.add(0,String.valueOf(i));		// ArrayList arList의 인덱스 0의 위치에 반복적으로 문자열 값 삽입. 반복적인 삽입에 따른 연쇄적인
												// 값 복사 발생. 제네릭 타입이 String으로 설정되어 있으므로 기본형 ㅑ를 참조형으로 변환하기 위해
	}											// valueOf()메서드이용.
      											// String valueOf(int i) : String클래스의 정적 메서드로 인수로 전달된 기본형 값을 참조형인
      											// 문자열로 변환하여 리턴
      long end=System.currentTimeMillis();
      System.out.println("ArrayList의 자료 추가 작업시간 : " + (end-start)/1000. + "초");
      
      start=System.currentTimeMillis();
      for (int i = 0; i < 100000; i++) {
		arList.get(i);						// 배열 리스트는 인덱스에 타입크기를 곱하여 요소 검색하므로
      }										// 연결 리스트에 비해 상대적인 검색속도가 빠름.
      end=System.currentTimeMillis();
      System.out.println("ArrayList의 자료 읽기 작업시간 : " + (end-start)/1000. + "초");
      
      start=System.currentTimeMillis();
      for (int i = 0; i < 100000; i++) {
    	  lkList.add(0,String.valueOf(i));	// 연결 리스트는 순자적인 자료의 추가 시 마다 마지막 저장된 데이터의 참조를 별도로 저장. 따라서 이후
      }										// 요소의 끝에 새로운 자료를 추가 시 해당 추가 요소의 전위 링크에 직전 마지막 별도로 저장된 데이터의
      										// 참조를 지정. 당행과 같이 지정된 위치에 자료를 삽입 시에는 삽입할 자료의 참조를 직전 데이터의 후위
      										// 링크에 넣고 삽입할 자료의 전위 링크에 직전 데이터의 참조를 또한 삽입할 위치에 존재하는 기존 요소의
      										// 참조를 삽입할 자료의 후위 링크에 지정하고 마지막으로 삽입할 위치에 존재하는 기존 요소의 전위 링크에
      										// 삽입할 자료의 참조를 지정하는 비교적 단순한 조작만으로 데이터 추가가 가능함으로써 자료의 추가,
      										// 삽입 속도가 빠른 것이 장점.
      end=System.currentTimeMillis();
      System.out.println("LinkedList의 자료 추가 작업시간 : " + (end-start)/1000. + "초");
      
      start=System.currentTimeMillis();
      for (int i = 0; i < 100000; i++) {
    	  lkList.get(i);						// 연결리스트는 개별 요소를 검색시 마다 무조건 최초 위치부터 따라가며 개별 
      }											// 요소의 위치까지 매번 이동해야 하므로 검색 속도가 상당히 느린것이 단점.
      end=System.currentTimeMillis();
      System.out.println("LinkedList의 자료 읽기 작업시간 : " + (end-start)/1000. + "초");
      
   }   
}