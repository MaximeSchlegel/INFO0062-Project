//import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.SequenceInputStream;
import java.util.ArrayList;

public class Solver {
    private ArrayList<Polygon>[] listPolygon;
    public SoccerBall ball;
    private int lastNumberPolygon;

    public Solver(Data data) {
        listPolygon = new ArrayList[data.NB_ELEMENTS.length];
        for (int i = 0; i < data.NB_ELEMENTS.length; i++) {
            listPolygon[i] = new ArrayList<>();
            for (int j = 0; j < data.NB_ELEMENTS[i]; j++) {
                listPolygon[i].add(new Polygon(i));
            }
        }
        ball = new SoccerBall(data.CONNECTIONS);
        lastNumberPolygon = 0;
    }


    public void solve()throws Exception {
        while (!ball.isComplete()) {
//            System.out.println(ball);

            Polygon polygon = search();

            if (polygon == null) {
                if (!testRotation()) {
                    Polygon polygonPop = ball.popFace();
                    lastNumberPolygon = polygonPop.getElementNumber();
                    System.out.println("remove " + lastNumberPolygon);
                    listPolygon[lastNumberPolygon].add(polygonPop);
                }
            }
            else {
                System.out.println("Ajout " + polygon.getElementNumber());
                ball.addFace(polygon);
                listPolygon[polygon.getElementNumber()].remove(0);
                lastNumberPolygon = 0;
            }
        }
    }

    private Polygon search() throws Exception{
        for (int index=lastNumberPolygon; index < listPolygon.length; index ++) {

//            System.out.println(index);
            if (!listPolygon[index].isEmpty() && listPolygon[index].get(0).getType() == ball.typeFace()) {
                Polygon polygon = listPolygon[index].get(0);
//                System.out.println(polygon);
                while (!polygon.completeRotation()){
                    if (!ball.testFace(polygon)) {
                        return polygon;
                    }
                    else {
                        polygon.rotate();
                    }
                }
            }
        }
        return null;
    }

    private boolean testRotation() throws Exception{
        do {
            ball.rotateTop();
            if (ball.completeRotationTop()) {
                return false;
            }
        }
        while (ball.hasConflict());

        return true;
    }






















//    public void solve()throws Exception{
//        while (!ball.isComplete()) {
//            System.out.println(ball);
//            if (ball.typeFace() == 6) {
//                if (!testpolygons(listHexagon, lastNumberHexa)) {
//                    if (!testRotation()) {
//                        Polygon polygon = ball.popFace();
//                        rangePolygon(polygon);
//                    }
//                }
//            }
//
//            else {
//                if (!testpolygons(listPentagon, lastNumberPenta)) {
//                    if (!testRotation()) {
//                        Polygon polygon = ball.popFace();
//                        rangePolygon(polygon);
//                    }
//                }
//            }
//        }
//    }

//



















/*    private  boolean testpolygons(ArrayList<Polygon> polygonList, int lastNumber) throws Exception{
        for (int index=0; index < polygonList.size(); index ++) {
            Polygon polygon = polygonList.get(index);
            if (polygon.getElementNumber() > lastNumber && testPolygonOrientation(polygon)) {
//                System.out.println(polygonList);
//                System.out.println(lastNumber);
//                System.out.println(index);
//                System.out.println(polygon.getElementNumber());
//                System.out.println(polygonList.get(index));
                polygonList.remove(index);
                return true;
            }
        }
        return false;
    }

    private boolean testPolygonOrientation(Polygon polygon)throws Exception{
        polygon.reinitialize();
        while(!polygon.completeRotation()) {
            ball.addFace(polygon);
            if (!ball.hasConflict()) {
                return true;
            } else {
                ball.popFace();
                polygon.rotate();
            }
        }
        return false;
    }

    private void rangePolygon(Polygon polygon) {



        if (polygon.getType() == 6) {
            listHexagon.add(polygon);
            lastNumberHexa = polygon.getElementNumber();
        }
        else {
            listPentagon.add(polygon);
            lastNumberPenta = polygon.getElementNumber();
        }

    }*/
}

