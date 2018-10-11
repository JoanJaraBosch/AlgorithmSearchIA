package classes;

import java.util.*;
/**
 * Classe dedicada a fer l'algorisme BestFirst 
 * @author Joan
 *
 */
public class A_estrella {
    private int x , y, heuristica;
    private int[][] gps;
    /**
     * Constructor de la classe BestFirst
     * @param x
     * @param y
     * @param gps
     */
    public A_estrella(int x, int y, int[][] gps, int heuristica) {
        this.x=x;
        this.y=y;
        this.gps=gps;
        this.heuristica=heuristica;
    }
    /**
     * Metode general per a trobar el cami, si es que te solucio, seguint l'algorisme bestfirst
     * @param initialSection
     * @param finalSection
     * @return Llista de llista de coordenades de cada estat (el cami)
     */
    public LinkedList<int[]> widthAlgorism(Section initialSection, Section finalSection) {
        LinkedList<Section> pendingToVerify = new LinkedList<Section>();
        pendingToVerify.add(initialSection);
        LinkedList<Section> treated = new LinkedList<Section>();
        LinkedList<int[]> solution = null;
        boolean found = false;

        while (!found && !pendingToVerify.isEmpty()) {
            Section currentlySection = pendingToVerify.removeFirst();
            if((currentlySection.getPosition_x()==finalSection.getPosition_x()) && (currentlySection.getPosition_y()==finalSection.getPosition_y()) ) {
                found = true;
                solution=currentlySection.getRoad();
            }else {
                south(pendingToVerify,currentlySection,treated,finalSection);
                east(pendingToVerify,currentlySection,treated,finalSection);
                north(pendingToVerify,currentlySection,treated,finalSection);
                west(pendingToVerify,currentlySection,treated,finalSection);
            }
        }
        return solution;
    }
    /**
     * Metode per a printar la solució del cami
     * @param solution
     */
    public void printResult(LinkedList<int[]> solution) {
    	if(solution!=null) {
	        ListIterator<int[]> aux = solution.listIterator();
	        System.out.println("The road is: ");
	        while(aux.hasNext()) {
	            int[] road = aux.next();
	            System.out.println("Coordenates: "+road[0]+", "+road[1]);
	        }
    	}else System.out.println("No te solució");
    }
    /**
     * Metode que planteja l'operando (sud), no retorna res, simplement modifica per referencia la llista de pendent si tractats
     * @param toTreath
     * @param currently
     * @param treatened
     */
    public void south(LinkedList<Section> toTreath, Section currently, LinkedList<Section> treatened,Section finalSection) {
        int vX = currently.getPosition_x();
        int vY = currently.getPosition_y();

        Section newSection = new Section(gps[vX][vY], vX, vY);
        if(!isNodeTreated(newSection,treatened)) treatened.add(newSection);
        if((vX+1)<x) {
            if(gps[vX+1][vY]!=-1) {
                int[] aux = {vX+1,vY};
                Section newSection4 = new Section(gps[vX+1][vY], vX+1, vY);
                if(!isNodeTreated(newSection4,treatened)) {
            	    LinkedList<int[]> roadNew = (LinkedList<int[]>) currently.getRoad().clone();
                    roadNew.add(aux);
                    Section newSection2 = new Section(gps[vX+1][vY], vX+1, vY,roadNew);
                    float valorAcc = accumulative(newSection.getValorAcc(), newSection.getValue() , gps[vX+1][vY]);
                    float valueToOrder=heuristica(heuristica,newSection2,finalSection, valorAcc);
                    Section newSection3 = new Section(gps[vX+1][vY], vX+1, vY,roadNew,valueToOrder, valorAcc);
                    if(!isNodeTreated(newSection3, toTreath)) toTreath.add(newSection3);
                    else changeMenor(newSection3, toTreath);
                    orderQueue(toTreath);
                }
            }
        }
    }
    /**
     * Metode que planteja l'operando (est), no retorna res, simplement modifica per referencia la llista de pendent si tractats
     * @param toTreath
     * @param currently
     * @param treatened
     */
    public void east(LinkedList<Section> toTreath, Section currently,LinkedList<Section> treatened, Section finalSection) {
        int vX = currently.getPosition_x();
        int vY = currently.getPosition_y();

        Section newSection = new Section(gps[vX][vY], vX, vY);
        if(!isNodeTreated(newSection,treatened)) treatened.add(newSection);
        if((vY+1)<y){
            if(gps[vX][vY+1]!=-1) {
                int[] aux = {vX,vY+1};
                Section newSection4 = new Section(gps[vX][vY+1], vX, vY+1);
                if(!isNodeTreated(newSection4,treatened)) {
                    LinkedList<int[]> roadNew = (LinkedList<int[]>) currently.getRoad().clone();
                    roadNew.add(aux);
                    Section newSection2 = new Section(gps[vX][vY+1], vX, vY+1,roadNew);
                    float valorAcc = accumulative(newSection.getValorAcc(), newSection.getValue() , gps[vX][vY+1]);
                    float valueToOrder=heuristica(heuristica,newSection2,finalSection, valorAcc);
                    Section newSection3 = new Section(gps[vX][vY+1], vX, vY+1,roadNew,valueToOrder, valorAcc);
                    if(!isNodeTreated(newSection3, toTreath)) toTreath.add(newSection3);
                    else changeMenor(newSection3, toTreath);
                    orderQueue(toTreath);
                }
            }
        }
    }
    /**
     * Metode que planteja l'operando (oest), no retorna res, simplement modifica per referencia la llista de pendent si tractats
     * @param toTreath
     * @param currently
     * @param treatened
     */
    public void west(LinkedList<Section> toTreath, Section currently,LinkedList<Section> treatened, Section finalSection) {
        int vX = currently.getPosition_x();
        int vY = currently.getPosition_y();

        Section newSection = new Section(gps[vX][vY], vX, vY);
        if(!isNodeTreated(newSection,treatened)) treatened.add(newSection);
        if((vY-1)>=0) {
            if(gps[vX][vY-1]!=-1) {
                int[] aux = {vX,vY-1};
                Section newSection4 = new Section(gps[vX][vY-1], vX, vY-1);
                if(!isNodeTreated(newSection4,treatened)) {
                    LinkedList<int[]> roadNew = (LinkedList<int[]>) currently.getRoad().clone();
                    roadNew.add(aux);
                    Section newSection2 = new Section(gps[vX][vY-1], vX, vY-1,roadNew);
                    float valorAcc = accumulative(newSection.getValorAcc(), newSection.getValue() , gps[vX][vY-1]);
                    float valueToOrder=heuristica(heuristica,newSection2,finalSection, valorAcc);
                    Section newSection3 = new Section(gps[vX][vY-1], vX, vY-1,roadNew,valueToOrder, valorAcc);
                    if(!isNodeTreated(newSection3, toTreath)) toTreath.add(newSection3);
                    else changeMenor(newSection3, toTreath);
                    orderQueue(toTreath);
                }
            }
        }
    }
    /**
     * Metode que planteja l'operando (nort), no retorna res, simplement modifica per referencia la llista de pendent si tractats
     * @param toTreath
     * @param currently
     * @param treatened
     */
    public void north(LinkedList<Section> toTreath, Section currently, LinkedList<Section> treatened, Section finalSection) {
        int vX = currently.getPosition_x();
        int vY = currently.getPosition_y();

        Section newSection = new Section(gps[vX][vY], vX, vY);
        if(!isNodeTreated(newSection,treatened)) treatened.add(newSection);
        if((vX-1)>=0){
            if(gps[vX-1][vY]!=-1) {
                int[] aux = {vX-1,vY};
                Section newSection4 = new Section(gps[vX-1][vY], vX-1, vY);
                if(!isNodeTreated(newSection4,treatened)) {
                	LinkedList<int[]> roadNew = (LinkedList<int[]>) currently.getRoad().clone();
                    roadNew.add(aux);
                    Section newSection2 = new Section(gps[vX-1][vY], vX-1, vY,roadNew);
                    float valorAcc = accumulative(newSection.getValorAcc(), newSection.getValue() , gps[vX-1][vY]);
                    float valueToOrder=heuristica(heuristica,newSection2,finalSection, valorAcc);
                    Section newSection3 = new Section(gps[vX-1][vY], vX-1, vY,roadNew,valueToOrder, valorAcc);
                    if(!isNodeTreated(newSection3, toTreath)) toTreath.add(newSection3);
                    else changeMenor(newSection3, toTreath);
                    orderQueue(toTreath);
                }
            }
        }
    }
    /**
     * Metode que serveix per a mirar si un estat, ha estat tractat o no
     * @param node
     * @param treated
     * @return retorna true o fals depenent de si ha estat tractat o no
     */
    public boolean isNodeTreated(Section node, LinkedList<Section> treated) {
        for (Section actual : treated) {
            if (node.getPosition_x() == actual.getPosition_x() &&
                    node.getPosition_y() == actual.getPosition_y())
                return true;
        }
        return false;
    }
    
    public void orderQueue(LinkedList<Section> treated) {
    	Collections.sort(treated, new Comparator<Section>() {
    		@Override
    		public int compare(Section o1, Section o2) {
    			if(o1.getValueToOrder()<o2.getValueToOrder())return -1;
    			else if(o1.getValueToOrder()>o2.getValueToOrder()) return 1;
    			return 0;
    		}
    	});
    }
    
    public float heuristica(int heuristica, Section current, Section finalSection, float acc) {
    	float valor=0;
    	if(heuristica==1) {
    		int x = finalSection.getPosition_x()-current.getPosition_x();
    		x = x*x;
    		int y = finalSection.getPosition_y()-current.getPosition_y();
    		y = y*y;
    		float arrel = x+y;
    		valor = (float) Math.sqrt(arrel);
    	}else if(heuristica==2) {
    		valor = finalSection.getValue()-current.getValue();
    		if(valor<0)valor = valor*-1;
    	}else {
    		double lon1, lon2, lat1, lat2 , Lon, Lat;
    		int R = 6371;
    		double a = current.getValue()/R;
    		double b = finalSection.getValue()/R;
    		lat1 =  Math.asin(a);
    		lat2 =  Math.asin(b);
    		lon1 =  Math.atan2(current.getPosition_y(), current.getPosition_x());
    		lon2 =  Math.atan2(finalSection.getPosition_y(), finalSection.getPosition_x());
    		Lon = lon2-lon1;
    		Lat = lat2-lat2;
    		double aux =  (Math.sin(Lat/2)*Math.sin(Lat/2) + Math.cos(lat1)*Math.cos(lat2)*Math.sin(Lon/2)*Math.sin(Lon/2));
    		double aux2 = 2 * Math.atan2( Math.sqrt(aux), Math.sqrt(1-aux) );
    		valor = (float) (aux2*R);
    	}
    	return valor+acc;
    }
    
    public float accumulative(float valor, int valorInicial, int valorFinal) {
    	float value = 0;
    	if((valorFinal -valorInicial)==0) 	value = valor + 1;
    	else if((valorFinal -valorInicial)<0) value = (float) (valor + 0.5);
    	else value = valor + 1 + (valorFinal -valorInicial);
    	
    	return value;
    }
    
    public void changeMenor(Section actual, LinkedList<Section> toTreath) {
    	for(Section currently : toTreath) {
    		if((currently.getPosition_x() == actual.getPosition_x())&&(currently.getPosition_y() == actual.getPosition_y())&&(currently.getValueToOrder()>actual.getValueToOrder())) {
    			toTreath.remove(currently);
    			toTreath.add(actual);
    		}
    	}
    }
}

