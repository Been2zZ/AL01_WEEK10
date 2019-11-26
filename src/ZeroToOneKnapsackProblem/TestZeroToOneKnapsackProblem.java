package ZeroToOneKnapsackProblem;

import java.io.*;

public class TestZeroToOneKnapsackProblem {
    public static void main(String[] args) throws FileNotFoundException {
        ZeroToOneKnapsackProblem K = new ZeroToOneKnapsackProblem();

        File file = new File("data10_knapsack.txt");

        K.insert(file);
    }
}
