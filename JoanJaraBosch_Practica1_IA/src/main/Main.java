package main;
import java.util.*;

import classes.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scaner = new Scanner (System.in);
		int x=-1, y=-1;
		int dimX, dimY;
		int[][] map =  {{1,0,-1,1,3,2,3,4,3,1},{2,1,-1,2,4,2,2,4,2,2},{5,3,-1,2,3,2,-1,3,3,3},
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
		int[] initial = {x,y};
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
		SearchWidth gps = new SearchWidth(dimX,dimY,map);
		solution = gps.widthAlgorism(initialSection,finalSection);
		if (!solution.isEmpty()) gps.printResult(solution);
		else if (initialSection.equals(finalSection)) {
			System.out.println("No hi ha camí perque l'inici és el final.");
		}else {
			System.out.println("No té solució.");
		}
		scaner.close();
	}

}
