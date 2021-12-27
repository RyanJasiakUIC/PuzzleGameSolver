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
        NodE startState = new NodE(arr);
        startState.setDepth(0);
        DB_Solver2 dbs = new DB_Solver2(startState, "heuristicOne");
        NodE solution = dbs.findSolutionPath();
        if (solution != null) {
            solutionPath = dbs.getSolutionPath(solution);
            max = solutionPath.size();
            // for (NodE n : solutionPath) {
            //     for (int i = 0; i < 16; i++) {
            //         System.out.printf("%4d ", n.getKey()[i]);
            //         if(i == 3 || i == 7 || i == 11)
            //             System.out.print("\n");
            //     }
            //     System.out.print("\n\n");
            // }
            // System.out.print("\n");

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
