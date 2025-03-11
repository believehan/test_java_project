import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class Main {
	public static void main(String[] args) {
		FileInputStream input=null;
		FileOutputStream output=null;
		BufferedInputStream bufInObj = null;
		BufferedOutputStream bufOutObj = null;
		long std;
		int data;
		
		std=System.currentTimeMillis();
		try {
			input = new FileInputStream("자바교본정리3.pdf");
			output = new FileOutputStream("copytest1.pdf"); 
					
			for (;;) {
				data = input.read();

				if (data == -1)
					break;

				output.write(data);
			}			
		} catch (IOException e) {
			System.out.println("파일 복사 실패!!");
		} finally {
			try {
				input.close();
				output.close();
			} catch (IOException e2) {
				System.out.println("파일 닫기 실패!!");
			}
		}
		System.out.printf("\ncopytest1 복사시간 %.3f초", (System.currentTimeMillis()-std)/1000.);
		
		std=System.currentTimeMillis();
		try {
			bufInObj = new BufferedInputStream(new FileInputStream("자바교본정리3.pdf"));
			bufOutObj = new BufferedOutputStream(new FileOutputStream("copytest2.pdf")); 
					
			for (;;) {
				data = bufInObj.read();

				if (data == -1)
					break;

				bufOutObj.write(data);
			}			
		} catch (IOException e) {
			System.out.println("파일 복사 실패!!");
		} finally {
			try {
				bufInObj.close();
				bufOutObj.close();
			} catch (IOException e2) {
				System.out.println("파일 닫기 실패!!");
			}
		}
		System.out.printf("\ncopytest2 복사시간 %.3f초", (System.currentTimeMillis()-std)/1000.);
	}
}