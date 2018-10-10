package main;
import java.util.*;

import classes.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scaner = new Scanner (System.in);
		int x=-1, y=-1; //inicialitzem per a que entri al bucle on canviarem la x i la y per tal de tindre unes coordenades d'inici
		int dimX, dimY; //variables de dimensio de la matriu
		int[][] map =  {{1,0,-1,1,3,2,3,4,3,1},{2,1,-1,2,4,2,2,4,2,2},{5,3,-1,2,3,2,-1,3,3,3},			//Inicialitzem el mapa d'alçades
				{3,3,1,3,4,3,-1,1,2,2},{2,2,2,3,6,4,-1,1,2,1},{-1,-1,-1,-1,3,3,3,-1,0,2,-1},
				{-1,-1,-1,-1,2,4,-1,2,2,-1},{2,3,4,3,1,3,-1,3,2,-1},{3,5,6,5,2,3,-1,5,3,-1},
				{5,6,7,6,4,4,-1,6,4,5}};
		System.out.println("Quin valor vols per a la dimensio de la matriu en el eix de les X?: ");
		dimX =scaner.nextInt();
		System.out.println("Quin valor vols per a la dimensio de la matriu en el eix de les Y?: ");
		dimY =scaner.nextInt();
		while(x>dimX || x<0 || y>dimY || y<0) {
			System.out.println("Quin valor vols per al inici en el eix X?: ");
			x =scaner.nextInt();
			System.out.println("Quin valor vols per al inici en el eix Y?: ");
			y =scaner.nextInt();
			if(map[x][y]==-1) y=-1;
		}
		int[] initial = {x,y}; // inicialitzem el cami inicial
		LinkedList<int[]> initialRoad= new LinkedList<int[]>();
		initialRoad.add(initial);
		Section initialSection = new Section(map[x][y], x,y, initialRoad);
		x=-1;
		y=-1;
		while(x>dimX || x<0 || y>dimY || y<0) {
			System.out.println("Quin valor vols per al final en el eix X?: ");
			x =scaner.nextInt();
			System.out.println("Quin valor vols per al final en el eix Y?: ");
			y =scaner.nextInt();
		}
		
		Section finalSection = new Section( map[x][y], x,y, null);
		LinkedList<int[]> solution = null;
		int heuristica=-1;
		int algorisme = -1;
		
		while(algorisme>2 || algorisme<=0) {
			System.out.println("Quin algorisme vols utilitzar?\n\t1-Best First.\n\t2-A*");
			algorisme =scaner.nextInt();
		}
		
		while(heuristica>3 || heuristica<=0) {
			System.out.println("Quina heuristica vols utilitzar?\n\t1-Heuristica camí recte.\n\t2-Heuristica diferencia alçada final i actual\n\t3-Hauristica formula hervesiana.");
			heuristica =scaner.nextInt();
		}
		
		if(algorisme==1) {
			BestFirst gps = new BestFirst(dimX,dimY,map,heuristica); //creem l'bjecete per a fer l'algorisme best first
			solution = gps.widthAlgorism(initialSection,finalSection); //cridem la funcio per a fer l'algorisme d'amplada
			gps.printResult(solution); //si te solucio la printara
		}else {
			A_estrella gps = new A_estrella(dimX,dimY,map,heuristica); //creem l'bjecete per a fer l'algorisme best first
			solution = gps.widthAlgorism(initialSection,finalSection); //cridem la funcio per a fer l'algorisme d'amplada
			gps.printResult(solution); //si te solucio la printara
		}
		scaner.close();
	}

}
