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
            System.out.print(ball.nextFace + " - ");

            Polygon polygon = search();
            lastNumberPolygon = 0;
            if (polygon == null) {
                if (!testRotation()) {
                    Polygon polygonPop = ball.popFace();
                    lastNumberPolygon = polygonPop.getElementNumber();
                    polygonPop.reinitialize();
                    listPolygon[lastNumberPolygon].add(polygonPop);
                    System.out.println("Pop face");
                }else {
                    System.out.println("Rotate face");
                }
            }
            else {
                ball.addFace(polygon);
                listPolygon[polygon.getElementNumber()].remove(0);
                System.out.println("Add face");

            }
        }
    }

    private Polygon search() throws Exception{
        for (int index=lastNumberPolygon + 1; index < listPolygon.length; index ++) {
            if (!listPolygon[index].isEmpty() && listPolygon[index].get(0).getType() == ball.typeFace()) {
                Polygon polygon = listPolygon[index].get(0);
                polygon.reinitialize();
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
}

