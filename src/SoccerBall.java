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

    public int typeFace() {
        return this.faceSideNumber[this.nextface];
    }
    public void addFace(Polygon p) throws Exception{
        if (p.getType() != this.typeFace()) {
            throw new Exception("Faces' type don't match");
        }
        this.face[this.nextface] = p;
        this.nextface++;
    }

    public Polygon popFace() throws Exception{
        if (this.nextface == 0) {
            throw new Exception("Nothing to pop");
        }
        Polygon p = this.face[this.nextface - 1];
        this.nextface--;
        return p;
    }

    public boolean hasConflict() {
        for (int link : Data.CONNECTIONS[this.nextface - 1]) {
            int[][] edge = this.edgeNetwork.get(link);
            if (this.face[edge[0][0]].getSideType(edge[0][1]) != this.face[edge[1][0]].getSideType(edge[1][1])) {
                return true;
            }
        }
        return false;
    }

    public boolean isComplete() {
        return this.nextface == this.face.length;
    }
    public String toString() {
        StringBuilder repr = new StringBuilder();
        for (int i=0; i < this.face.length; i++) {
            repr.append("Positin " + i + " - ");
            repr.append(this.face);
            repr.append("\n");
        }
        return repr.toString();
    }
    public static void main(String[] args) {
//        SoccerBall ball = new SoccerBall(Data.CONNECTIONS, Data.ELEMENTS_SIDES, Data.NB_ELEMENTS);
//        ball.display();
    }
}
