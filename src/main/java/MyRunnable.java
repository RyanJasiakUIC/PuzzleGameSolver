import java.util.ArrayList;
import java.util.concurrent.*;

import javafx.scene.control.*;

public class MyRunnable implements Runnable {

    Coordinate[] initial_coords;
    Button[][] buttons;
    Future<ArrayList<NodE>> future;
    ArrayList<NodE> states;

    MyRunnable(Button[][] _buttons, Coordinate _initial_coords[], Future<ArrayList<NodE>> _future) {
        buttons = _buttons;
        initial_coords = _initial_coords;
        future = _future;
    }

    @Override
    public void run() {
        System.out.println("bruh why");
        states = null;
        boolean notFinished = true;
        while (notFinished) {
            System.out.println("Not finished!");
            try {
                states = future.get();
                if (states != null) {
                    System.out.println("Here bruh!!!!!!!");
                    notFinished = false;
                    startDoingTheOtherCrap();
                }
            } catch (Exception e) {}
        }
        
    }

    void setButtons(NodE n) {
        int[] a = n.getKey();
        int c = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    buttons[i][j].setText(Integer.toString(a[c++]));
                } catch (Exception e) {
                    // System.out.println("BRUH WY IS IT NULL " + e.getsage());
                }
            }
        }
    }

    void startDoingTheOtherCrap() {
        int count = 0;
        for (NodE n : states) {
            for (int i = 0; i < 16; i++) {
                System.out.printf("%4d ", n.getKey()[i]);
                if(i == 3 || i == 7 || i == 11)
                    System.out.print("\n");
            }
            System.out.print("\n");
            setButtons(n);
            if (++count == 10) break;
            System.out.print("\n\n");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Couldn't sleep ?? INSOMIA!!!!");
            }
        }
        System.out.print("\n");

    }
}
