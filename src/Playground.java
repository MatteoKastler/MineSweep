import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Playground {
	static Field[][] matrix;
	static int w, h;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		init(20, 20, 30);
		while(!finished()) {
			printField();
			System.out.print("next Action: ");
			parseInput(s.nextLine());
		}
		
	}
	
	public static void  init(int width, int height, int bombs) {
		matrix = new Field[height][width];
		h = height;
		w = width;
		//set bombs on random field
		for(int i = 0; i<bombs; i++) {
			int x = ThreadLocalRandom.current().nextInt(0, width);
			int y = ThreadLocalRandom.current().nextInt(0, height);
			if(matrix[y][x] instanceof BombField) {
				i--;
			}else {
				matrix[y][x] = new BombField(false, false);
			}
		}
		//initialize emptyfields on field
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(matrix[i][j] instanceof BombField) {
					continue;
				}else{
					matrix[i][j] = new EmptyField(false,false);
				}
			}
		}
		//set bombcnt for fields near bombs
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(matrix[i][j] instanceof EmptyField) {
					int cnt = 0;
					for(int k = -1; k < 2; k++) {
						for(int l = -1; l < 2; l++) {
							try {
								if(matrix[i+k][j+l] instanceof BombField) {
									cnt++;
								}
							}catch(ArrayIndexOutOfBoundsException e){}
						}
					}
					((EmptyField)matrix[i][j]).setBombsCnt(cnt);
					
				}
			}
		}
	}
	public static void printField() {
		for(int i = -1; i<matrix.length;i++) {
			System.out.print(i + "	");
			for(int j = 0; j<matrix[0].length;j++) {
				if(i == -1) {System.out.print("  "+j%10+" "); continue;}
				if(j == 0) System.out.print("| ");
				System.out.print(matrix[i][j].toString() + " | ");
			}
			System.out.println();
			for(int k = 0; k < (w*4+1); k++) {
				if(k == 0) System.out.print("	");
				System.out.print("_");
			}
			System.out.println();
			System.out.println();
		}
	}
	public static boolean show(int x, int y) {
		matrix[y][x].setOpen(true);
		return matrix[y][x] instanceof BombField;
	}
	
	public static void flagging(int x, int y) {
		matrix[y][x].setFlag(true);
	}
	
	public static boolean finished() {
		for(Field[] r : matrix){
			for(Field f : r) {
				if(f instanceof BombField && f.getOpen()) {
					System.out.println("you lost");
					return true;
				}
				if(f instanceof BombField && !f.getFlag()) return false;
			}
		}
		return true;
	}
	
	public static void parseInput(String input) {
		String[] sinput = input.split("\\s");
		int x = 0,y = 0;
		try {
			y = Integer.parseInt(sinput[2]);
			x = Integer.parseInt(sinput[1]);
		}catch(Exception e) {
			System.out.println("input not valid");
		}
		switch(sinput[0]) {
			case "f":
				matrix[y][x].setFlag(true);
				break;
			case "o":
				if(matrix[y][x] instanceof EmptyField) {
					if(((EmptyField)matrix[y][x]).getBombsCnt() == 0) {
						checkNearFields(x,y);
					}
				}
				matrix[y][x].setOpen(true);
				break;
			default:
				System.out.println("input not valid");
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	public static void checkNearFields(int x, int y) {
		try{
			if(matrix[y][x] instanceof EmptyField && !matrix[y][x].getOpen()) {
				if(((EmptyField)matrix[y][x]).getBombsCnt() == 0) {
					matrix[y][x].setOpen(true);
					for(int i = -1; i < 2; i++) {
						for(int j = -1; j < 2; j++) {
							checkNearFields(j+x, i+y);
						}
					}
				}else{
					matrix[y][x].setOpen(true);
				}
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			return;
		}
	}
}