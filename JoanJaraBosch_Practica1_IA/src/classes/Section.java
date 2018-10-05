package classes;

import java.util.*;

public class Section {
	private int value,position_x,position_y;
	private LinkedList<int[]> road = new LinkedList<int[]>();
	
	public Section(int value,int position_x, int position_y, LinkedList<int[]> road) {
		this.value=value;
		this.position_x=position_x;
		this.position_y=position_y;
		this.road=road;
	}
	
	public Section(int value,int position_x, int position_y) {
		this.value=value;
		this.position_x=position_x;
		this.position_y=position_y;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getPosition_x() {
		return position_x;
	}

	public void setPosition_x(int position_x) {
		this.position_x = position_x;
	}

	public int getPosition_y() {
		return position_y;
	}

	public void setPosition_y(int position_y) {
		this.position_y = position_y;
	}

	public LinkedList<int[]> getRoad() {
		return road;
	}

	public void setRoad(LinkedList<int[]> road) {
		this.road = road;
	}
	
}
