import java.util.Stack;

public class Solver {
    private Stack<Polygon>[] availablePolygon;
    private SoccerBall ball;
    private int lastPolygonNumber;

    public Solver(SoccerBall ball) {
        this.availablePolygon = new Stack[Data.NB_ELEMENTS.length];
        for (int i = 0; i < Data.NB_ELEMENTS.length; i++) {
            this.availablePolygon[i] = new Stack<>();
            for (int j = 0; j < Data.NB_ELEMENTS[i]; j++) {
                this.availablePolygon[i].push(new Polygon(i));
            }
        }
        this.ball = ball;
        this.lastPolygonNumber = -1;
    }


    public void solve()throws Exception {
        while (!ball.isComplete()) {

            Polygon polygon = search();
            this.lastPolygonNumber = -1;

            if (polygon == null) {
                if (!testRotation()) {
                    Polygon polygonPoped = ball.popFace();
                    this.lastPolygonNumber = polygonPoped.getElementNumber();
                    polygonPoped.reinitialize();
                    this.availablePolygon[this.lastPolygonNumber].push(polygonPoped);
                }else {
                }
            }
            else {
                ball.addFace(polygon);

            }
        }
    }

    private Polygon search() throws Exception{
        for (int index=this.lastPolygonNumber + 1; index < this.availablePolygon.length; index++) {
            if (!this.availablePolygon[index].isEmpty() && this.availablePolygon[index].get(0).getType() == ball.nextFaceType()) {
                Polygon polygon = this.availablePolygon[index].pop();
                polygon.reinitialize();
                while (!polygon.completeRotation()){
                    if (!ball.testNextFace(polygon)) {
                        return polygon;
                    }
                    else {
                        polygon.rotate();
                    }
                }
                this.availablePolygon[index].push(polygon);
            }
        }
        return null;
    }

    private boolean testRotation() throws Exception{
        do {
            ball.rotateTopFace();
            if (ball.fullRotationTopFace()) {
                return false;
            }
        }
        while (ball.hasConflictTopFace());
        return true;
    }
}

