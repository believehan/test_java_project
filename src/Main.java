/*
 *  문자열은 재할당이 불가하므로 문자열 변경 메서드는 원본 데이터의 변경이 아닌 변경된 새로운 String 객체를 생성
 */
public class Main {
   public static void main(String[] args) {
      String str = "   Made In Korea  ";
      String str1 = "Finghting!!";
      
      System.out.println(str);
      System.out.println(str.toLowerCase());	// String toLowerCase() : 모든 영문자를 소문자로 변경한 문자열 리턴.
      System.out.println(str);
      String str2=str.toUpperCase();			// String toUpperCase() : 모든 영문자를 대문자로 변경한 문자열 리턴.
      System.out.println(str2+"\n");
     
      System.out.println(str.trim()); 			// String trim() : 해당 문자열의 중간 공백을 제외한 앞, 뒤 공백을 제거한 문자열 리턴
      System.out.println(str+str1); 
      System.out.println(str.concat(str1).concat("Peace!!"));	// String concat(String str) : 문자열과 문자열을 연결한 문자열 리턴.
      System.out.println(str + "\n\n");
   }
}