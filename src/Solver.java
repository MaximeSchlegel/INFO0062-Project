import java.util.ArrayList;


public class Solver {
    private ArrayList<Polygon> listHexagon = new ArrayList<>();
    private ArrayList<Polygon> listPentagon = new ArrayList<>();
    private SoccerBall ball;

    private Solver() {
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

    public void solve()throws Exception{
        while (!ball.isComplete()) {
            if (ball.typeFace() == 6) {

                if (!testpolygons(listHexagon)) {
                    Polygon polygon = ball.popFace();
                    rangePolygon(polygon);
                }
            }
            else {
                if (!testpolygons(listPentagon)) {
                    Polygon polygon = ball.popFace();
                    rangePolygon(polygon);
                }
            }
        }
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

    private  boolean testpolygons(ArrayList<Polygon> polygonList) throws Exception{
        int index = 0;
        while (index < polygonList.size()) {
            Polygon polygon = polygonList.get(index);

            if (testPolygonOrientation(polygon)) {
                listPentagon.remove(index);
                return true;
            }
            else {
                index++;
            }
        }
        return false;
    }

    private void rangePolygon(Polygon polygon) {
        if (polygon.getType() == 6) {
            listHexagon.add(polygon);
        }
        else {
            listPentagon.add(polygon);
        }

    }

    public static void main(String[] args){

        }



}