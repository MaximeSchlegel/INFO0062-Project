import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SoccerBall {
    private Polygon[]face;
    private int[] faceSideNumber;
    private Map<Integer, int[][]> edgeNetwork; //Is it the best representation as we will check for confilct for a particular piece
    private int nextface;

    public SoccerBall(int[][] connection) {
        //Initialisation of the ScoccerBall
        this.face = new Polygon[connection.length];
        this.faceSideNumber = new int[connection.length];
        this.edgeNetwork = new HashMap<>();
        for (int i=0; i < connection.length; i++) {
            this.faceSideNumber[i] = connection[i].length;
            for (int j = 0; j < connection[i].length; j++) {
                if (!this.edgeNetwork.containsKey(connection[i][j])) {
                    int[][] edge = new int[2][2];
                    edge[0][0] = i;
                    edge[0][1] = j;
                    this.edgeNetwork.put(j, edge);
                } else {
                    this.edgeNetwork.get(connection[i][j])[1][0] = i;
                    this.edgeNetwork.get(connection[i][j])[1][1] = j;
                }
            }
        }
        this.nextface = 0;
    }

    public void addFace(Polygon p) throws Exception{
        if (p.getType() != this.typeFace()) {
            throw new Exception("Faces Type Don't Match")
        }
    }
    public Polygon popFace() {}
    public int typeFace() {}
    public boolean hasConflict() {}
    public boolean isComplete() {}
    public void display() {
        //TODO
    }
    public static void main(String[] args) {
        SoccerBall ball = new SoccerBall(Data.CONNECTIONS, Data.ELEMENTS_SIDES, Data.NB_ELEMENTS);
        ball.display();
    }
}
