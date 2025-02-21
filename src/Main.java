import java.util.Scanner;

class Main {
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int n1, n2;
      
      System.out.print("첫 번재 수 입력 : ");
      n1=scanner.nextInt();
      System.out.print("두 번재 수 입력 : ");
      n2=scanner.nextInt();
      
//      System.out.println( n1 == n2 ? "두 수는 같음" : "큰 수는 " + ( (n1 > n2) ? n1 : n2 ) );
      System.out.printf( n1 == n2 ? "\n두 수는 같음" : "\n큰 수는 %d", n1 > n2 ? n1 : n2); 
      
      scanner.close();
   }
}