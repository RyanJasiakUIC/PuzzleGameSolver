//
// Sorry, it's pretty crappy, I know :(
//
// Been a rough past couple weeks...
//

import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.*;

import javax.swing.Action;

import javafx.animation.PauseTransition;

// import org.w3c.dom.NodE;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class GUIController implements Initializable {

    String[] games = {"1 5 2 3 4 9 6 7 8 10 14 11 12 13 0 15",
                     "1 5 2 3 4 6 0 7 8 9 14 11 12 10 13 15",
                     "1 6 5 3 4 14 9 7 8 10 0 11 12 13 2 15",
                     "1 6 5 3 4 14 9 0 8 10 2 7 12 13 15 11",
                     "4 5 14 3 6 0 1 9 8 10 2 7 12 13 15 11",
                     "4 5 14 3 6 10 1 9 8 13 2 7 12 15 0 11",
                     "4 15 0 3 6 5 1 9 8 10 7 14 12 13 11 2",
                     "4 15 1 3 6 10 5 9 8 7 11 14 12 0 13 2",
                     "6 4 1 3 0 15 13 5 7 10 11 9 8 12 2 14",
                     "0 12 4 3 6 7 1 5 10 13 15 14 8 2 9 11"};

    GameInterface gi;

    ArrayList<NodE> states;

    ExecutorService ex;

    Future<ArrayList<NodE>> future;

    ArrayList<ArrayList<Integer>> states_to_display;

    UserInterface ui;

    Button[][] buttons;

    ArrayList<Button> gameSelectors;

    String[][] complete;

    Coordinate initial_coords[];

    SolverCall solvercall;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane boardGP;

    @FXML
    private Button newGameButton;

    @FXML
    private Button quitButton;

    @FXML
    private Button solveButton;

    @FXML
    private HBox selectorHBox;

    @FXML
    private HBox selectorHBox2;

    @FXML
    private Button seeSol;


    Button gameSelectorButton(int i) {
        Button b = new Button("Game " + i);
        b.setOnAction(e -> gameSelectorAction(i));
        b.setStyle("-fx-font-size: 14px; -fx-background-color: #65a39b; -fx-font-family: 'Verdana';");
        return b;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        seeSol.setDisable(true);
        gi = new GameInterface();
        gameSelectors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Button be = gameSelectorButton(i);
            selectorHBox.getChildren().add(be);
            gameSelectors.add(be);
        }
        for (int i = 5; i < 10; i++) {
            Button be = gameSelectorButton(i);
            selectorHBox2.getChildren().add(be);
            gameSelectors.add(be);
        }
        
        initial_coords = new Coordinate[16];
        double d = 20.0;
        double d2 = 10.0;
        buttons = new Button[4][4];
        complete = new String[4][4];
        boardGP.setPadding(new Insets(d, d, d, d));
        boardGP.setHgap(d2);
        boardGP.setVgap(d2);
        // int tempboard[] = gi.stringToIntArray("0 14 13 12 15 9 5 8 11 7 4 1 3 10 6 2");
        int tempboard[] = gi.stringToIntArray("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15");
        System.out.println("BRUH");
        int c = 0;
        for (int i = 0; i < 4; i++) {   
            for (int j = 0; j < 4; j++) {
                complete[i][j] = Integer.toString(c);
                initial_coords[c] = new Coordinate(i, j, c);
                Button b = gameButton(i, j, tempboard[c++]);
                b.setDisable(true);
                buttons[i][j] = b;
                System.out.println(i + " : " + j);
                boardGP.add(b, j, i);
            }
        }
        complete[3][3] = "";
    }


    void gameSelectorAction(int n) {
        System.out.println("Game Selector");
        for (Button b: gameSelectors)
            b.setDisable(true);
        for (Button[] _b: buttons)
            for (Button b: _b)
                b.setDisable(false);
        int tempboard[] = gi.stringToIntArray(games[n]);
        System.out.println("BRUH");
        int c = 0;
        for (int i = 0; i < 4; i++) {   
            for (int j = 0; j < 4; j++) {
                initial_coords[c] = new Coordinate(i, j, c);
                Button b = gameButton(i, j, tempboard[c++]);
                b.setDisable(false);
                buttons[i][j] = b;
                System.out.println(i + " : " + j);
                boardGP.add(b, j, i);
            }
        }
    }

    Button gameButton(int r, int c, int count) {
        String s = "";
        if (count != 0) s = Integer.toString(count);
        Button b = new Button(s);
        b.setStyle("-fx-background-color: #d2cce5;");
        b.setOnAction(e -> gameButtonAction(r, c));
        b.setMaxSize(50, 50);
        b.setMinSize(50, 50);
        return b;
    }

    void swapCoordinates(int r1, int c1, int r2, int c2) {
        int n1 = r1*4 + c1;
        int n2 = r2*4 + c2;
        Coordinate temp = initial_coords[n1];
        initial_coords[n1] = initial_coords[n2];
        initial_coords[n2] = temp;
    }

    void gameButtonAction(int r, int c) {
        // System.out.println("r: " + r + " c: " + c);
        int _r = r, _c = c;
        if (r+1 < 4 && buttons[r+1][c].getText().equals(""))
            _r++;
        else if (r-1 > -1 && buttons[r-1][c].getText().equals(""))  
            _r--;
        else if (c+1 < 4 && buttons[r][c+1].getText().equals(""))  
            _c++;
        else if (c-1 > -1 && buttons[r][c-1].getText().equals(""))
            _c--;
        // System.out.println("_r: " + _r + " _c: " + _c);
        if (r == _r && c == _c) {
            // System.out.println("Returning!!");
            return;
        }
        swapCoordinates(r, c, _r, _c);
        buttons[_r][_c].setText(buttons[r][c].getText());
        buttons[r][c].setText("");
        if (puzzleIsComplete())
            System.out.println("Woohoo!");
    }

    boolean puzzleIsComplete() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // System.out.println("button text: " + buttons[i][j].getText() + " complete text: " + complete[i][j]);
                // if (!(Integer.parseInt(buttons[i][j].getText().toString()) == Integer.parseInt(complete[i][j]))) {
                if (!(buttons[i][j].getText().toString().equals(complete[i][j]))) {
                    // System.out.println("Returning false :(");
                    return false;
                }
            }
        }
        return true;
    }

    public void startNewGame(ActionEvent e) throws IOException {
        seeSol.setDisable(true);
        System.out.println("BRUH");
        for (Button[] _b : buttons)
            for (Button b : _b)
                b.setDisable(true);
        for (Button b : gameSelectors)
            b.setDisable(false);
    }

    public void quitGame(ActionEvent e) throws IOException {
        Platform.exit();
    }

    void setButtons(ArrayList<Integer> n) {
        for (int i = 0; i < 16; i++) {
            System.out.printf("%4d ", n.get(i));
            if(i == 3 || i == 7 || i == 11)
                System.out.print("\n");
        }
        String temp = "";
        System.out.print("\n\n");
        int c = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // try {
                    System.out.print(n.get(c) + " ");
                    // temp = (n.get(c) == 0) ? "" : Integer.toString(n.get(c));
                    if (n.get(c) == 0) {
                        temp = "";
                        // System.out.println("EMPTY STRING");
                    } else {
                        temp = Integer.toString(n.get(c));
                        // System.out.println("NUMBER");
                    }
                    buttons[i][j].setText(temp);
                    c++;
                    // System.out.println("IN HERE????");
                // } catch (Exception e) {
                //     // System.out.println("BRUH WY IS IT NULL " + e.getsage());
                // }
            }
            System.out.println("");
        }
    }

    void printNodE(NodE n) {
        System.out.println("*** PRINTING NodE ***");
        int z = 0;
        for (int i : n.getKey()) {
            System.out.print(i + " ");
            if (++z%4 == 0) {
                System.out.println("");
                z = 0;
            }
        }
        System.out.println("*** DONE PRINTING NodE ***");
    }

    ArrayList<Integer> NodEtoArray(NodE n) {
        System.out.println("*** PRINTING NodE ***");
        ArrayList<Integer> al = new ArrayList<>();
        for (int i : n.getKey()) {
            System.out.print(i + " ");
            al.add(i);
        }
        System.out.println("*** DONE PRINTING NodE ***");
        return al;
    }

    

    public void DB_Solver(ActionEvent e) throws IOException {
        solvercall = new SolverCall(initial_coords);
		ex = Executors.newFixedThreadPool(1);
        future = ex.submit(solvercall);
        // Thread _t = new Thread(new MyRunnable(buttons, initial_coords, future));
        System.out.println("Starting new thread....");
        // _t.start();
        Thread t = new Thread(() -> {
            boolean notFinished = true;
            states = null;
            while (notFinished) {
                System.out.println("Not finished!");
                try {
                    states = future.get();
                    if (states != null) {
                        System.out.println("Here bruh!!!!!!!");
                        notFinished = false;
                        int count = 0;
                        states_to_display = new ArrayList<>();
                        for (NodE n : states) {
                            states_to_display.add(NodEtoArray(n));
                        }
                        seeSol.setDisable(false);
                    }
                } catch (Exception _e) {}
            }
        });
        t.start();
    }

    //
    // RIP ME
    //
    // Couldn't make it animate ..... :(
    //
    public void seeSolution(ActionEvent e) throws IOException {
        int count = 0;
        int size = states_to_display.size();
        for (int i = 0; i < size; i++) {
            setButtons(states_to_display.get(i));
            // // n.toString();
            // // Platform.runLater(new Runnable() {
            // //     @Override
            // //     public void run() {
            //         setButtons(states_to_display.get(i));
            // //     }
            // // });
            // PauseTransition pause = new PauseTransition(Duration.seconds(2));
            // pause.setOnFinished(_e -> {
            //     // final int k = 0;
            //     setButtons(states_to_display.get(0));
            //     System.out.println("Printing Array:::");
            //     System.out.print(states_to_display.get(0));
            //     System.out.println("done!");
            // });
            // pause.play();
            // pause = new PauseTransition(Duration.seconds(2));
            // // PauseTransition pause = new PauseTransition(Duration.seconds(2));
            // count++;
            // if (count == size || count == 10) break;
            // pause.setOnFinished(_e -> {
            //     // final int k = 0;
            //     setButtons(states_to_display.get(1));
            //     System.out.println("Printing Array:::");
            //     System.out.print(states_to_display.get(1));
            //     System.out.println("done!");
            // });
            // pause.play();
            // pause = new PauseTransition(Duration.seconds(2));
            // count++;
            // if (count == size || count == 10) break;
            // pause.setOnFinished(_e -> {
            //     // final int k = 0;
            //     setButtons(states_to_display.get(2));
            //     System.out.println("Printing Array:::");
            //     System.out.print(states_to_display.get(2));
            //     System.out.println("done!");
            // });
            // pause.play();
            // pause = new PauseTransition(Duration.seconds(2));
            // count++;
            // if (count == size || count == 10) break;
            // pause.setOnFinished(_e -> {
            //     // final int k = 0;
            //     setButtons(states_to_display.get(3));
            //     System.out.println("Printing Array:::");
            //     System.out.print(states_to_display.get(3));
            //     System.out.println("done!");
            // });
            // pause.play();
            // pause = new PauseTransition(Duration.seconds(2));
            // count++;
            // if (count == size || count == 10) break;
            // pause.setOnFinished(_e -> {
            //     // final int k = 0;
            //     setButtons(states_to_display.get(4));
            //     System.out.println("Printing Array:::");
            //     System.out.print(states_to_display.get(4));
            //     System.out.println("done!");
            // });
            // pause.play();
            // pause = new PauseTransition(Duration.seconds(2));
            if (++count == 10) break;
        }
        System.out.print("\n");
    }
}
