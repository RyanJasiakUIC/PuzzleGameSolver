import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.engine.support.hierarchical.Node;

class MyTest {
    GameInterface gi = new GameInterface();

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

    String complete = "0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15";
    
	@Test
	void test1() {
		DB_Solver2 db = new DB_Solver2(new NodE(gi.stringToIntArray(games[0])), "heuristicOne");
        assertArrayEquals(db.findSolutionPath().getKey(), gi.stringToIntArray(complete));
	}

    @Test
	void test2() {
		DB_Solver2 db = new DB_Solver2(new NodE(gi.stringToIntArray(games[1])), "heuristicOne");
        assertArrayEquals(db.findSolutionPath().getKey(), gi.stringToIntArray(complete));
	}

    @Test
	void test3() {
		DB_Solver2 db = new DB_Solver2(new NodE(gi.stringToIntArray(games[2])), "heuristicOne");
        assertArrayEquals(db.findSolutionPath().getKey(), gi.stringToIntArray(complete));
	}

    @Test
	void test4() {
		DB_Solver2 db = new DB_Solver2(new NodE(gi.stringToIntArray(games[3])), "heuristicOne");
        assertArrayEquals(db.findSolutionPath().getKey(), gi.stringToIntArray(complete));
	}

    @Test
	void test5() {
		DB_Solver2 db = new DB_Solver2(new NodE(gi.stringToIntArray(games[4])), "heuristicOne");
        assertArrayEquals(db.findSolutionPath().getKey(), gi.stringToIntArray(complete));
	}

    @Test
	void test6() {
		DB_Solver2 db = new DB_Solver2(new NodE(gi.stringToIntArray(games[5])), "heuristicOne");
        assertArrayEquals(db.findSolutionPath().getKey(), gi.stringToIntArray(complete));
	}

    @Test
	void test7() {
		DB_Solver2 db = new DB_Solver2(new NodE(gi.stringToIntArray(games[0])), "heuristicOne");
        assertArrayEquals(db.findSolutionPath().getKey(), gi.stringToIntArray(complete));
	}

    @Test
	void test8() {
		DB_Solver2 db = new DB_Solver2(new NodE(gi.stringToIntArray(games[1])), "heuristicOne");
        assertArrayEquals(db.findSolutionPath().getKey(), gi.stringToIntArray(complete));
	}

    @Test
	void test9() {
		DB_Solver2 db = new DB_Solver2(new NodE(gi.stringToIntArray(games[2])), "heuristicOne");
        assertArrayEquals(db.findSolutionPath().getKey(), gi.stringToIntArray(complete));
	}

    @Test
	void test10() {
		DB_Solver2 db = new DB_Solver2(new NodE(gi.stringToIntArray(games[3])), "heuristicOne");
        assertArrayEquals(db.findSolutionPath().getKey(), gi.stringToIntArray(complete));
	}

}
