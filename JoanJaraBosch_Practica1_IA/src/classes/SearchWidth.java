package classes;

import java.util.*;

public class SearchWidth {
    private int x , y;
    private int[][] gps;

    public SearchWidth(int x, int y, int[][] gps) {
        this.x=x;
        this.y=y;
        this.gps=gps;
    }

    public LinkedList<int[]> widthAlgorism(Section initialSection, Section finalSection) {
        Queue<Section> pendingToVerify = new LinkedList<Section>();
        pendingToVerify.add(initialSection);
        LinkedList<Section> treated = new LinkedList<Section>();
        LinkedList<int[]> solution = null;
        boolean found = false;

        while (!found && !pendingToVerify.isEmpty()) {
            Section currentlySection = pendingToVerify.remove();
            if((currentlySection.getPosition_x()==finalSection.getPosition_x()) && (currentlySection.getPosition_y()==finalSection.getPosition_y()) ) {
                found = true;
                solution=currentlySection.getRoad();
            }else {
                south(pendingToVerify,currentlySection,treated);
                east(pendingToVerify,currentlySection,treated);
                west(pendingToVerify,currentlySection,treated);
                north(pendingToVerify,currentlySection,treated);
            }
        }
        return solution;
    }

    public void printResult(LinkedList<int[]> solution) {
        ListIterator<int[]> aux = solution.listIterator();
        System.out.println("The road is: ");
        while(aux.hasNext()) {
            int[] road = aux.next();
            System.out.println("Coordenates: "+road[0]+", "+road[1]);
        }
    }

    public void south(Queue<Section> toTreath, Section currently, LinkedList<Section> treatened) {
        int vX = currently.getPosition_x();
        int vY = currently.getPosition_y();

        if((vX+1)<x) {
            if(gps[vX+1][vY]!=-1) {
                int[] aux = {vX+1,vY};
                Section newSection = new Section(gps[vX+1][vY], vX+1, vY);
                if(!isNodeTreated(newSection,treatened)) {
            	    LinkedList<int[]> roadNew = (LinkedList<int[]>) currently.getRoad().clone();
                    roadNew.add(aux);
                    Section newSection2 = new Section(gps[vX+1][vY], vX+1, vY,roadNew);
                    toTreath.add(newSection2);
                    treatened.add(newSection);
                }
            }
        }
    }

    public void east(Queue<Section> toTreath, Section currently,LinkedList<Section> treatened) {
        int vX = currently.getPosition_x();
        int vY = currently.getPosition_y();

        if((vY+1)<y){
            if(gps[vX][vY+1]!=-1) {
                int[] aux = {vX,vY+1};
                Section newSection = new Section(gps[vX][vY+1], vX, vY+1);
                if(!isNodeTreated(newSection,treatened)) {
                    LinkedList<int[]> roadNew = (LinkedList<int[]>) currently.getRoad().clone();
                    roadNew.add(aux);
                    Section newSection2 = new Section(gps[vX][vY+1], vX, vY+1,roadNew);
                    toTreath.add(newSection2);
                    treatened.add(newSection);
                }
            }
        }
    }

    public void west(Queue<Section> toTreath, Section currently,LinkedList<Section> treatened) {
        int vX = currently.getPosition_x();
        int vY = currently.getPosition_y();

        if((vY-1)>=0) {
            if(gps[vX][vY-1]!=-1) {
                int[] aux = {vX,vY-1};
                Section newSection = new Section(gps[vX][vY-1], vX, vY-1);
                if(!isNodeTreated(newSection,treatened)) {
                    LinkedList<int[]> roadNew = (LinkedList<int[]>) currently.getRoad().clone();
                    roadNew.add(aux);
                    Section newSection2 = new Section(gps[vX][vY-1], vX, vY-1,roadNew);
                    toTreath.add(newSection2);
                    treatened.add(newSection);
                }
            }
        }
    }

    public void north(Queue<Section> toTreath, Section currently, LinkedList<Section> treatened) {
        int vX = currently.getPosition_x();
        int vY = currently.getPosition_y();

        if((vX-1)>=0){
            if(gps[vX-1][vY]!=-1) {
                int[] aux = {vX-1,vY};
                Section newSection = new Section(gps[vX-1][vY], vX-1, vY);
                if(!isNodeTreated(newSection,treatened)) {
                	LinkedList<int[]> roadNew = (LinkedList<int[]>) currently.getRoad().clone();
                    roadNew.add(aux);
                    Section newSection2 = new Section(gps[vX-1][vY], vX-1, vY,roadNew);
                    toTreath.add(newSection2);
                    treatened.add(newSection);
                }
            }
        }
    }

    private boolean isNodeTreated(Section node, LinkedList<Section> treated) {
        for (Section actual : treated) {
            if (node.getPosition_x() == actual.getPosition_x() &&
                    node.getPosition_y() == actual.getPosition_y())
                return true;
        }
        return false;
    }

}

