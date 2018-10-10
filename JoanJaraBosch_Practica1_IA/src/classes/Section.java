package classes;

import java.util.*;

/**
 * Classe per a generar estats 
 * @author Joan
 */
public class Section{
	private int value,position_x,position_y;
	private float valueToOrder;
	private LinkedList<int[]> road = new LinkedList<int[]>();
	/**
	 * Constructor de la classe section
	 * @param value (valor de l'estat)
	 * @param position_x (posicio x de l'estat)
	 * @param position_y (posicio y de l'estat)
	 * @param road (cami per arribar en aquest estat)
	 */
	public Section(int value,int position_x, int position_y, LinkedList<int[]> road, float valueToOrder) {
		this.value=value;
		this.position_x=position_x;
		this.position_y=position_y;
		this.road=road;
		this.valueToOrder=valueToOrder;
	}
	
	public Section(int value,int position_x, int position_y, LinkedList<int[]> road) {
		this.value=value;
		this.position_x=position_x;
		this.position_y=position_y;
		this.road=road;
	}
	/**
	 * Constructor 2 de la classe section el qual serveix per a afegir a la llista de tractats sense problemes del cami
	 * @param value (valor de l'estat)
	 * @param position_x (posicio x de l'estat)
	 * @param position_y (posicio y de l'estat)
	 */
	public Section(int value,int position_x, int position_y) {
		this.value=value;
		this.position_x=position_x;
		this.position_y=position_y;
	}
	
	public int getValue() {
		return value;
	}

	public int getPosition_x() {
		return position_x;
	}

	public int getPosition_y() {
		return position_y;
	}

	public LinkedList<int[]> getRoad() {
		return road;
	}
	public float getValueToOrder() {
		return valueToOrder;
	}
}
