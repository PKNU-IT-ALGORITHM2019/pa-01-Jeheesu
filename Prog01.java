package assign01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Prog01 {
    public static int capacity = 200000;
	public static Array [] words = new Array[capacity];	
    public static int size = 0;
    public static int index = 0;
	public static Scanner inFile;
	public static int sameNumber = 0;
	public static int upcnt = 0;
    public static int downcnt = 0;
	
    public static void main(String[] args) {
    	
    	Scanner kb = new Scanner(System.in);
    	
    	while(true) {
    		System.out.print("$ ");
    		String command = kb.next();
    		String str;
    		if(command.equals("read")) {
    			str = kb.next();
    			readFile(str);
    		}
    		else if(command.equals("size")) {
    			System.out.println(size);
    		}    		
    		else if(command.equals("find")) {
    			str=kb.nextLine();
    			str=str.replaceAll("'", "").replaceAll(" ", "");
    			index = 0;
    			int cnt = vocaFind(str, 0, size-1);
    			if(index==-1) {
    				notFound(cnt);
				}
				else {
					down(cnt);
					up(cnt);
					System.out.println("Founds " + (sameNumber+1) + " items.");
					for(int i=cnt-downcnt; i<=cnt+upcnt; i++)
						System.out.println(words[i].voca+" "+words[i].part+ " "+words[i].comment);
				}
    		}
    		else if(command.equals("exit"))
    			break;
    	}
    	kb.close();
    }
  
	private static void notFound(int cnt) {
		if(cnt < 0) {
			System.out.println("Not Found.");
			System.out.println("- - -");
			System.out.println(words[cnt+1].voca+" "+words[cnt+1].part);
		}
		else {
			System.out.println("Not Found.");
			System.out.println(words[cnt].voca+" "+words[cnt].part);
			System.out.println("- - -");
			System.out.println(words[cnt+1].voca+" "+words[cnt+1].part);
		}
	}

	public static void up(int correctWord) {
		int i = correctWord + 1;
		for(int x = i; x<size; x++) {
			if(words[x].voca.compareToIgnoreCase(words[correctWord].voca) == 0) {
				sameNumber++;
				upcnt++;
			}
		}
	}

	public static void down(int correctWord) {
		int i = correctWord - 1;
		for(int y = i; y>=0; y--) {
			if( words[i].voca.compareToIgnoreCase(words[correctWord].voca) == 0 ) {
				sameNumber++;
				downcnt++;
			}
		}
	}

	public static int vocaFind(String str, int start, int end) {
		if(start > end) {
			index = -1;
			return end;
		}
		int mid = (start+end)/2;
		if (words[mid].voca.compareToIgnoreCase(str) == 0)
			return mid;
		else if (words[mid].voca.compareToIgnoreCase(str) > 0)
			return vocaFind(str, start, mid-1);
		else
			return vocaFind(str, mid+1, end);
	}

	public static void readFile(String FileName) {
		String str;
		try {
			inFile = new Scanner(new File(FileName));
			while(inFile.hasNext()) {
				str = inFile.nextLine();
				if(str.equals( "" ))
					continue;
				int start = str.indexOf( "(" );
				int end = str.indexOf( ")" );
				if(start<0 || end < 0)
					return;
				String voca = str.substring(0, start - 1);
				String part = str.substring(start - 1, end + 1);
				String comment = str.substring(end + 1);
				words[size] = new Array(voca, part, comment);
				size++;
			}
			inFile.close();
		} catch (FileNotFoundException e) {
			System.out.println( "No file." );
			System.exit(0);
		}
		
	}

}