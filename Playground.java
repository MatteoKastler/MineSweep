package mineSweep;
import java.util.concurrent.ThreadLocalRandom;


public class Playground {
	static Field[][] matrix;
	static int w, h;

	public static void main(String[] args) {
		init(20, 20, 30);
		printField();
	}
	
	public static void  init(int width, int height, int bombs) {
		matrix = new Field[height][width];
		h = height;
		w = width;
		
		for(int i = 0; i<bombs; i++) {
			int x = ThreadLocalRandom.current().nextInt(0, width); //+1
			int y = ThreadLocalRandom.current().nextInt(0, height); //+1
			if(matrix[y][x] instanceof BombField) {
				i--;
			}else {
				matrix[y][x] = new BombField(false, false);
			}
		}
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(matrix[i][j] instanceof BombField) {
					continue;
				}else{
					matrix[i][j] = new EmptyField(false,false);
				}
			}
		}
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
							}catch(ArrayIndexOutOfBoundsException e){
							}
						}
					}
					((EmptyField)matrix[i][j]).setBombsCnt(cnt);
					
				}
			}
		}
	}
	public static void printField() {
		for(Field [] row : matrix) {
			for(Field f : row) {
				System.out.print(f.toString() + " | ");
			}
			System.out.println();
			for(int i = 0; i < (w*4); i++) {
				System.out.print("_");
			}
			System.out.println();
			System.out.println();
		}
	}
}
