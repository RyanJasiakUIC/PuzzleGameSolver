public class Coordinate {
    int x;
    int y;
    int val;

    public Coordinate(int _x, int _y, int _val) {
        x = _x;
        y = _y;
        val = _val;
    }

    public static int[] getArrayOfVals(Coordinate[] coords) {
        int[] arr = new int[16];
        for (int i = 0; i < 16; i++) {
            arr[i] = coords[i].val;
        }
        return arr;
    }
}
