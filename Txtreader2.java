package discrete;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Txtreader2 {
	public int n=0;//함수번호
	ArrayList <int[][]>arrays = new ArrayList<>();
	static Scanner scan = new Scanner(System.in);
	public int[][] array;
	public int go;
	public int value;
	public int m=0;
	public void readtxt(String fileName) {
		try {
			Scanner scan = new Scanner(new File(fileName));
			while (scan.hasNextLine()) {
				int nodenum = scan.nextInt();
				n++;//위에서 함수갯수가 들어왔으니
				scan.nextLine();//첫 공백을 제거 해준다
				array = new int[nodenum][nodenum];
				for (int i = 0; i < nodenum; i++) {//nodenum바이nodenum을 리턴
					String str = scan.nextLine();
					String[] temp = str.split(" ");
					m = Integer.parseInt(temp[0].trim()) - 1;// 방위치+무조건 존재 인덱스로 변환
					for (int j = 1; j < temp.length; j+=2) {// 첫번째꺼는 넣으면 안된다
						go = Integer.parseInt(temp[j].trim());
						value=Integer.parseInt(temp[j+1].trim());
						array[m][go - 1] = value;
					}
				}
				arrays.add(array);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("찾을수 없음!");
		}
		
	}
	public int FunctionNum() {//
		return n;
	}
	public int[][] array(int n) {//자기자신은 0 
		return this.arrays.get(n);
	}

}
