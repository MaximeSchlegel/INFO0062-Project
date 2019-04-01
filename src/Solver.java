//import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.util.ArrayList;


public class Solver {
    private ArrayList<Polygon> listHexagon = new ArrayList<>();
    private ArrayList<Polygon> listPentagon = new ArrayList<>();
    public SoccerBall ball;
    private int lastNumberHexa = -1;
    private int lastNumberPenta = -1;

    public Solver() {
        for (int i = 0; i < Data.NB_ELEMENTS.length; i++) {
            for (int j = 0; j < Data.NB_ELEMENTS[i]; j++){
                if (Data.ELEMENTS_SIDES[i].length == 6) {
                    listHexagon.add(new Polygon(i));
                }
                else {
                    listPentagon.add(new Polygon(i));
                }
            }
        }
        ball = new SoccerBall(Data.CONNECTIONS);
    }

//    public void solve()throws Exception{
//        while (!ball.isComplete()) {
//            if (ball.typeFace() == 6) {
//                if (!testpolygons(listHexagon, lastNumberHexa)) {
//                    Polygon polygon = ball.popFace();
//                    rangePolygon(polygon);
//                }
//            }
//            else {
//                if (!testpolygons(listPentagon, lastNumberPenta)) {
//                    Polygon polygon = ball.popFace();
//                    rangePolygon(polygon);
//                }
//            }
//        }
//    }
//
//    private  boolean testpolygons(ArrayList<Polygon> polygonList, int lastNumber) throws Exception{
//        for (int index=0; index < polygonList.size(); index ++) {
//            Polygon polygon = polygonList.get(index);
//            if (polygon.getElementNumber() > lastNumber && testPolygonOrientation(polygon)) {
////                System.out.println(polygonList);
////                System.out.println(lastNumber);
////                System.out.println(index);
////                System.out.println(polygon.getElementNumber());
////                System.out.println(polygonList.get(index));
//                polygonList.remove(index);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean testPolygonOrientation(Polygon polygon)throws Exception{
//        polygon.reinitialize();
//        while(!polygon.completeRotation()) {
//            ball.addFace(polygon);
//            if (!ball.hasConflict()) {
//                return true;
//            } else {
//                ball.popFace();
//                polygon.rotate();
//            }
//        }
//        return false;
//    }
//
//    private void rangePolygon(Polygon polygon) {
//
//        System.out.println(ball);
//
//        if (polygon.getType() == 6) {
//            listHexagon.add(polygon);
//            lastNumberHexa = polygon.getElementNumber();
//        }
//        else {
//            listPentagon.add(polygon);
//            lastNumberPenta = polygon.getElementNumber();
//        }
//
//    }

}