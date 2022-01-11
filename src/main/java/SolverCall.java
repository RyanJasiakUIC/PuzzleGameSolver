import java.util.concurrent.*;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.*;

public class SolverCall implements Callable<ArrayList<NodE>> {

    Coordinate[] coords;
    ArrayList<NodE> solutionPath;
    int iter;
    int max;

    SolverCall(Coordinate[] _coords) {
        System.out.println("Starting solvercall()...");
        coords = _coords;
        iter = 0;
        max = 0;
        solutionPath = null;
        int arr[] = Coordinate.getArrayOfVals(coords);
        for (int i : arr) System.out.print(i + ", ");
        NodE startState = new NodE(arr);
        startState.setDepth(0);
        DB_Solver2 dbs = new DB_Solver2(startState, "heuristicOne");
        NodE solution = dbs.findSolutionPath();
        if (solution != null) {
            solutionPath = dbs.getSolutionPath(solution);
            max = solutionPath.size();
        }
    }


    @Override
    public ArrayList<NodE> call() throws Exception {
        if (solutionPath != null) {
            return solutionPath;
        }
        return null;
    }
}
