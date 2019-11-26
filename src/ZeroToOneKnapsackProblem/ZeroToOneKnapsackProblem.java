package ZeroToOneKnapsackProblem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ZeroToOneKnapsackProblem {

    static List<Item> item = new ArrayList<>();
    static int[][] table = null;
    static int n = 0;

    public void insert(File F) throws FileNotFoundException {
            /** File read & insert */
        FileReader fr = new FileReader(F);
        BufferedReader br = new BufferedReader(fr);

        String[] temp = null;

        try {
            String line = "";
            while ((line = br.readLine()) != null) {
                temp = line.split(",");
                Item I = new Item(Integer.parseInt(temp[0]),
                        Integer.parseInt(temp[1]),
                        Integer.parseInt(temp[2]));
                item.add(I);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println(e);
        }

        n = item.size();

        /** 출력문 */
        print();
    }

    private int OPT(int i, int w) {
        if (i == 0)
            return 0;
        else if (item.get(i - 1).Weight > w)
            return OPT(i - 1, w);
        else
            return Math.max(OPT(i - 1, w),
                    item.get(i - 1).Value + OPT(i - 1, w - item.get(i-1).Weight));
    }

    private void print() {
        Scanner sc = new Scanner(System.in);

        System.out.print("배낭의 사이즈를 입력하세요.(0~50) : ");
        int w = sc.nextInt();

        table = new int[n + 1][w + 1];

        /** table 배열에 값 저장 */
        for(int i = 1; i < table.length; i++)
            for(int j = 1; j < table[i].length; j++)
                table[i][j] = OPT(i, j);

        /** table 출력 */
        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table[i].length; j++)
                System.out.printf("%3d",table[i][j]);
            System.out.println();
        }

        /** max 출력 */
        System.out.print("max : " + table[n][w] + "\nitem : ");

        Stack stack = new Stack();

        for(int i = n; i > 0; i--) {
            if(table[i][w] != table[i - 1][w]) {
                stack.push(item.get(i - 1).ItemNum);
                w -= item.get(i - 1).Weight;    // w에서 현재 가중치 빼줌
            }
        }

        /** item 출력 */
        while(!stack.isEmpty())
            System.out.print(stack.pop() + " ");
    }
}
