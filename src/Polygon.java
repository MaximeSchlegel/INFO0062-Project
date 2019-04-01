public class Polygon {
    private int orientation;
    private int sideType;

    public Polygon(int side){
        //Create a polygon based on the type list of its side
        this.sideType = side;
        this.orientation = 0;
    }

    public void rotate() {
        this.orientation++;
    }

    public void reinitialize(){
        this.orientation = 0;
    }

    public boolean completeRotation() {
        return this.orientation >= Data.ELEMENTS_SIDES[this.sideType].length;
    }

    public int getType () {
        return Data.ELEMENTS_SIDES[this.sideType].length;
    }

    public int getSideType(int sideNumber) {
        int[] side = Data.ELEMENTS_SIDES[this.sideType];
        return side[(this.orientation + sideNumber) % side.length];
    }

    public int getElementNumber() {
        return this.sideType;
    }

    public String toString() {
        int o = this.orientation % Data.ELEMENTS_SIDES[this.sideType].length;
        return "Element " + this.sideType + " - Orientation " + o;
    }

//    public static void main(String[] args) {
//        Polygon p = new Polygon(1);
//        System.out.println(p);
//        p.rotate();
//        System.out.println(p);
//        p.rotate();p.rotate();p.rotate();p.rotate();
//        System.out.println(p);
//        p.rotate();
//        System.out.println(p);
//        System.out.println(p.completeRotation());
//        p.reinitialize();
//        System.out.println(p);
//        System.out.println(p.completeRotation());
//    }
}
