import sun.security.util.PolicyUtil;

import java.net.Socket;
import java.net.SocketOption;
import java.util.HashMap;
import java.util.Map;

public class SoccerBall {
    private Polygon[]face;
    private int[] faceSideNumber;
    private Map<Integer, int[][]> edgeNetwork;
    public int nextFace;

    public SoccerBall() {
        //Initialisation of the ScoccerBall
        this.face = new Polygon[Data.CONNECTIONS.length];
        this.faceSideNumber = new int[Data.CONNECTIONS.length];
        this.edgeNetwork = new HashMap<>();

        for (int i=0; i < Data.CONNECTIONS.length; i++) {
            this.faceSideNumber[i] = Data.CONNECTIONS[i].length;
            for (int j = 0; j < Data.CONNECTIONS[i].length; j++) {
                if (!this.edgeNetwork.containsKey(Data.CONNECTIONS[i][j])) {
                    int[][] edge = new int[2][2];
                    edge[0][0] = i;
                    edge[0][1] = j;
                    this.edgeNetwork.put(Data.CONNECTIONS[i][j], edge);
                } else {
                    this.edgeNetwork.get(Data.CONNECTIONS[i][j])[1][0] = i;
                    this.edgeNetwork.get(Data.CONNECTIONS[i][j])[1][1] = j;
                }
            }

        }
        this.nextFace = 0;
    }

    public int typeFace() {
        return this.faceSideNumber[this.nextFace];
    }

    public void addFace(Polygon p) throws Exception{
        if (p.getType() != this.typeFace()) {
            throw new Exception("Faces' type don't match");
        }
        this.face[this.nextFace] = p;
        this.nextFace++;
    }

    public Polygon popFace() throws Exception{
        if (this.nextFace == 0) {
            throw new Exception("Nothing to pop");
        }
        Polygon p = this.face[this.nextFace - 1];
        this.face[this.nextFace - 1] = null;
        this.nextFace--;
        return p;
    }

    public boolean hasConflict() {
        for (int link : Data.CONNECTIONS[this.nextFace - 1]) {
            int[][] edge = this.edgeNetwork.get(link);
            if (this.face[edge[0][0]] != null && this.face[edge[1][0]] != null
                    && this.face[edge[0][0]].getSideType(edge[0][1]) == this.face[edge[1][0]].getSideType(edge[1][1])) {
                return true;
            }
        }
        return false;
    }

    public boolean isComplete() {
        return this.nextFace == this.face.length;
    }

    public void rotateTop() {
        this.face[this.nextFace - 1].rotate();
    }

    public boolean completeRotationTop() {
        return this.face[this.nextFace - 1].completeRotation();
    }

    public boolean testFace(Polygon polygon) throws Exception {
        this.addFace(polygon);
        boolean conflic = this.hasConflict();
        this.popFace();
        return conflic;
    }

    public String toString() {
        StringBuilder repr = new StringBuilder();
        for (int i=0; i < this.face.length; i++) {
            repr.append("Position " + (i + 1) + " - ");
            repr.append(this.face[i]);
            repr.append("\n");
        }
        return repr.toString();
    }

    public static void main(String[] args) throws Exception {
        SoccerBall ball1 = new SoccerBall();

        Polygon poly1 = new Polygon(10);
        ball1.addFace(poly1);
        System.out.println(ball1.hasConflict());

        Polygon poly2 = new Polygon(0);
        poly2.rotate();
        poly2.rotate();
        ball1.addFace(poly2);
        System.out.println(ball1.hasConflict());

        Polygon poly3 = new Polygon(0);
        ball1.addFace(poly3);
        System.out.println(ball1.hasConflict());

        Polygon poly4 = new Polygon(1);
        poly4.rotate();
        poly4.rotate();
        poly4.rotate();
        poly4.rotate();
        poly4.rotate();
        ball1.addFace(poly4);
        System.out.println(ball1.hasConflict());

        Polygon poly5 = new Polygon(1);
        poly5.rotate();
        poly5.rotate();
        poly5.rotate();
        poly5.rotate();
        ball1.addFace(poly5);
        System.out.println(ball1.hasConflict());

        Polygon poly6 = new Polygon(6);
        ball1.addFace(poly6);
        System.out.println(ball1.hasConflict());

        Polygon poly7 = new Polygon(10);
        poly7.rotate();
        poly7.rotate();
        poly7.rotate();
        ball1.addFace(poly7);
        System.out.println(ball1.hasConflict());

        Polygon poly8 = new Polygon(2);
        poly8.rotate();
        poly8.rotate();
        ball1.addFace(poly8);
        System.out.println(ball1.hasConflict());

        Polygon poly9 = new Polygon(10);
        poly9.rotate();
        poly9.rotate();
        poly9.rotate();
        poly9.rotate();
        ball1.addFace(poly9);
        System.out.println(ball1.hasConflict());

        Polygon poly10 = new Polygon(2);
        ball1.addFace(poly10);
        System.out.println(ball1.hasConflict());

        Polygon poly11 = new Polygon(11);
        poly11.rotate();
        poly11.rotate();
        poly11.rotate();
        ball1.addFace(poly11);
        System.out.println(ball1.hasConflict());

        Polygon poly12 = new Polygon(3);
        poly12.rotate();
        ball1.addFace(poly12);
        System.out.println(ball1.hasConflict());

        Polygon poly13 = new Polygon(12);
        ball1.addFace(poly13);
        System.out.println(ball1.hasConflict());

        Polygon poly14 = new Polygon(5);
        poly14.rotate();
        poly14.rotate();
        poly14.rotate();
        ball1.addFace(poly14);
        System.out.println(ball1.hasConflict());

        Polygon poly15 = new Polygon(11);
        poly15.rotate();
        poly15.rotate();
        ball1.addFace(poly15);
        System.out.println(ball1.hasConflict());

        Polygon poly16 = new Polygon(4);
        ball1.addFace(poly16);
        System.out.println(ball1.hasConflict());

        Polygon poly17 = new Polygon(5);
        poly17.rotate();
        poly17.rotate();
        ball1.addFace(poly17);
        System.out.println(ball1.hasConflict());

        Polygon poly18 = new Polygon(11);
        poly18.rotate();
        poly18.rotate();
        ball1.addFace(poly18);
        System.out.println(ball1.hasConflict());

        Polygon poly19 = new Polygon(6);
        poly19.rotate();
        poly19.rotate();
        poly19.rotate();
        poly19.rotate();
        ball1.addFace(poly19);
        System.out.println(ball1.hasConflict());

        Polygon poly20 = new Polygon(12);
        ball1.addFace(poly20);
        System.out.println(ball1.hasConflict());

        Polygon poly21 = new Polygon(7);
        poly21.rotate();
        poly21.rotate();
        poly21.rotate();
        ball1.addFace(poly21);
        System.out.println(ball1.hasConflict());

        Polygon poly22 = new Polygon(13);
        poly22.rotate();
        poly22.rotate();
        ball1.addFace(poly22);
        System.out.println(ball1.hasConflict());

        Polygon poly23 = new Polygon(9);
        poly23.rotate();
        poly23.rotate();
        poly23.rotate();
        poly23.rotate();
        ball1.addFace(poly23);
        System.out.println(ball1.hasConflict());

        Polygon poly24 = new Polygon(12);
        ball1.addFace(poly24);
        System.out.println(ball1.hasConflict());

        Polygon poly25 = new Polygon(9);
        poly25.rotate();
        poly25.rotate();
        poly25.rotate();
        poly25.rotate();
        poly25.rotate();
        ball1.addFace(poly25);
        System.out.println(ball1.hasConflict());

        Polygon poly26 = new Polygon(13);
        ball1.addFace(poly26);
        System.out.println(ball1.hasConflict());

        Polygon poly27 = new Polygon(4);
        poly27.rotate();
        ball1.addFace(poly27);
        System.out.println(ball1.hasConflict());

        Polygon poly28 = new Polygon(3);
        ball1.addFace(poly28);
        System.out.println(ball1.hasConflict());

        Polygon poly29 = new Polygon(8);
        poly29.rotate();
        poly29.rotate();
        ball1.addFace(poly29);
        System.out.println(ball1.hasConflict());

        Polygon poly30 = new Polygon(8);
        poly30.rotate();
        poly30.rotate();
        poly30.rotate();
        poly30.rotate();
        ball1.addFace(poly30);
        System.out.println(ball1.hasConflict());

        Polygon poly31 = new Polygon(7);
        poly31.rotate();
        ball1.addFace(poly31);
        System.out.println(ball1.hasConflict());

        Polygon poly32 = new Polygon(13);
        poly32.rotate();
        poly32.rotate();
        poly32.rotate();
        ball1.addFace(poly32);
        System.out.println(ball1.hasConflict());

        System.out.println(ball1.isComplete());
        System.out.println(ball1);


        SoccerBall ball2 = new SoccerBall();
        Solver s = new Solver(ball2);
        s.solve();
        System.out.println(ball2);
    }
}
