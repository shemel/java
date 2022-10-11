// DCT und IDCT Transformation mit Vergleich
// Sebastian Hemel,21.06.2010

import java.lang.Math;


public class dctTransformation {
	
	int f[][];
	static int g[][];
	static int inv[][];
	
	public dctTransformation(int f[][]) {
		this.f = f;
	}

	public static void main(String[] args) {

		int array[][] = { 
				{ 255, 239, 199, 175, 160, 170, 232, 255 },
				{ 239, 198, 215, 206, 181, 151, 127, 228 },
				{ 199, 215, 241, 225, 192, 158, 124, 141 },
				{ 175, 206, 225, 214, 186, 154, 121, 94 },
				{ 159, 181, 192, 186, 166, 140, 110, 84 },
				{ 170, 151, 158, 154, 140, 118, 92, 122 },
				{ 232, 127, 124, 121, 110, 92, 81, 221 },
				{ 255, 228, 142, 95, 86, 123, 220, 255 } 
				};
	    
	    
		// Neue Matrix wird aus den original Daten erstellt
		
		
		// Original Matrix ausgeben
		System.out.println("Original Matrix:");
		System.out.println("----------------");
		outputArray(array);
		System.out.println("");
		
		// Erstellen der DCT Matrix
		System.out.println("DCT Matrix:");
		System.out.println("----------------");	
		dctTransformation dct = new dctTransformation(array);
		dct.transformDCT();
		dct.outputArray(g);
		System.out.println("");
		
		// Erstellen der IDTC Matrix (Ruecktransformation)
		System.out.println("IDCT Matrix:");
		System.out.println("----------------");	
		dct.inverseDCT();
		dct.outputArray(inv);
		System.out.println("");
		
	}
	
	// Ausgabe der Matrix
	public static void outputArray(int[][] array) {
		int rowSize = array.length;
		int columnSize = array[0].length;
	     
		//System.out.println("Row size= " + array.length);
		//System.out.println("Column size = " + array[0].length);

		for(int i = 0; i < rowSize; i++) {
			System.out.print("[");
			for(int j = 0; j < columnSize; j++) {
				System.out.print(" " + array[i][j]);
			}
			System.out.println(" ]");
		}
		System.out.println();
	}
	

	public int[][] transformDCT() {
		g = new int[8][8];

		for ( int i = 0; i < 8; i++ ) {
			for ( int j = 0; j < 8; j++ ) {
				double ge = 0.0;
				for ( int x = 0; x < 8; x++ ) {
					for ( int y = 0; y < 8; y++ ) {
						double cg1 = (2.0*(double)x+1.0)*(double)i*Math.PI/16.0;
						double cg2 = (2.0*(double)y+1.0)*(double)j*Math.PI/16.0;

						ge += ((double)f[x][y]) * Math.cos(cg1) * Math.cos(cg2);

					}
				}						
				double ci = ((i==0)?1.0/Math.sqrt(2.0):1.0);
				double cj = ((j==0)?1.0/Math.sqrt(2.0):1.0);
				ge *= ci * cj * 0.25;
				g[i][j] = (int)Math.round(ge);
			}
		}
		return g;
	}


	public int[][] inverseDCT() {
		inv = new int[8][8];

		for ( int x = 0; x < 8; x++ ) {
			for ( int y = 0; y < 8; y++ ) {
				double ge = 0.0;
				for ( int i = 0; i < 8; i++ ) {
					double cg1 = (2.0*(double)x + 1.0)*(double)i*Math.PI/16.0;
					double ci = ((i==0)?1.0/Math.sqrt(2.0):1.0);
					for ( int j = 0; j < 8; j++ ) {
						double cg2 = (2.0*(double)y + 1.0)*(double)j*Math.PI/16.0;
						double cj = ((j==0)?1.0/Math.sqrt(2.0):1.0);
						double cij4 = ci*cj*0.25;
						ge += cij4 * Math.cos(cg1) * Math.cos(cg2) * (double)g[i][j];
					}
				}
				inv[x][y] = (int)Math.round(ge);
			}
		}
		return inv;
	}
	
}
