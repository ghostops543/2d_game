package Main;




import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Wave_function_collapse extends JFrame {
    public static int Fwidth;
    public static int Fheight;
    public String[] options = {"Path_full","Path_up","Path_down","Path_left","Path_right",
            "Path_UR", "Path_UL","Path_BR","Path_BL"};
    // maps
    public static int[][] finalMap;
    public static int[][] map;
    public static boolean[][] collapsed;
    //wave function rule map
    public static int[][][] ruleMap;
    final int gridSize = 3;
    static int[][] grass = {{1,1,0,1,1,1,0,0,0,0,0},{1,1,0,0,1,0,0,1,0,0,1},{1,1,0,0,0,0,1,1,1,0,0},{1,1,0,0,0,1,0,0,1,1,0}};
    static int[][] flower_grass = {{1,1,0,1,1,1,0,0,0,0,0},{1,1,0,0,1,0,0,1,0,0,1},{1,1,0,0,0,0,1,1,1,0,0},{1,1,0,0,0,1,0,0,1,1,0}};

    static int[][] path = {{0,0,1,0,0,0,1,0,0,0,0},{0,0,1,0,0,0,0,0,0,1,0},{0,0,1,1,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0,0,1}};

    static int[][] path_t={{0,0,1,0,0,0,0,0,0,0},{0,0,0,1,0,1,0,0,0,0,0},{1,1,0,0,0,0,0,0,0,0,0},{0,0,0,1,1,0,0,0,0,0,0}};
    static int[][] path_tr={{0,0,0,0,0,0,0,0,0,0,1},{0,0,0,1,0,0,0,0,0,0,0},{1,1,0,0,0,0,0,0,0,0,0},{1,1,0,0,0,0,0,0,0,0,0}};
    static int[][] path_tl={{0,0,0,0,0,0,0,0,0,1,0},{1,1,0,0,0,0,0,0,0,0,0},{1,1,0,0,0,0,0,0,0,0,0},{0,0,0,1,0,0,0,0,0,0,0}};

    static int[][] path_b={{1,1,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,1,0,1,0,0},{0,0,1,1,0,0,0,0,0,0,0},{0,0,0,0,0,0,1,1,0,0,0}};
    static int[][] path_br={{1,1,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,1,0,1,0,0},{0,0,0,0,1,0,0,0,0,0,1},{1,1,0,0,0,0,0,0,0,0,0}};
    static int[][] path_bl={{1,1,0,0,0,0,0,0,0,0,0},{1,1,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0,0,1,0},{0,0,0,0,0,0,1,1,0,0,0}};

    static int[][] path_l={{0,0,0,0,0,0,0,0,1,1,0},{1,1,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0,0,1,0},{0,0,1,0,0,0,0,0,0,0,1}};
    static int[][] path_r={{0,0,0,0,0,0,0,1,0,0,1},{0,0,1,0,0,0,0,0,0,1,0},{0,0,0,0,1,0,0,0,0,0,1},{1,1,0,0,0,0,0,0,0,0,0}};




    public static int[][][] rulelist = {grass, flower_grass, path,path_t, path_tr, path_tl, path_b, path_br, path_bl, path_l, path_r};
    static int optionCount = 11;
    //start
    static int start;
    static int startX;
    static int startY;
    static int x;
    static int y;
    static int tile;
    static int collapse_count_true = 0;
    static int collapse_count_false = Fwidth * Fheight;



    public static void createMapArray(int width, int height) {
        finalMap = new int[width][height];
        map = new int[width][height];
        ruleMap = new int[width][height][rulelist[0].length];
        collapsed = new boolean[width][height];
        for (int i = 0; width > 0 && i < width; i++) {
            for (int j = 0; j < height ; j++) {
                for (int k = 0; k < rulelist[0].length; k++) {
                    ruleMap[i][j][k] = 1;
                }
                finalMap[i][j] = -1;
                collapsed[i][j] = false;
                map[i][j]= optionCount;
            }
            System.out.println(Arrays.toString(map[i]));
        }
    }
    public static void wave_function_collapse(int width, int height) {
        createMapArray(width, height);
        Fwidth = width;
        Fheight = height;
        start = 4;
        startX = width / 2;
        startY = height / 2;
        x = startX;
        y = startY;
        tile = start;
        //setTile(start, startX, startY);// sets rule map and map to tile

        while (collapse_count_false != Fwidth * Fheight) {
            collapse_count_true = 0;
            collapse_count_false = width * height;
            for (int i = 0; width > 0 && i < width; i++) {
                for (int j = 0; j < height ; j++) {
                    if (!collapsed[i][j]) {
                        collapse_count_false --;
                    }
                    else if (collapsed[i][j]) {
                        collapse_count_true ++;
                    }
                }
            }
            System.out.println(collapse_count_true + " " + collapse_count_false + " total" + Fwidth * Fheight);
            ruleset(tile, x, y);
            pickNewCollapse();// go through tiles and find least options and colapse one of them
            System.out.println("loop");

        }

        System.out.println("final map \n");
        for (int i = 0; height > 0 && i < height; i++) {
            for (int j = 0; j < width ; j++) {
                System.out.print(finalMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
        System.out.println("new rule map \n");
        for (int i = 0; height > 0 && i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(Arrays.toString(ruleMap[j][i]));
            }
            System.out.println();
        }
        System.out.println("\n");
        System.out.println("new collapsedmap \n");
        for (int i = 0; height > 0 && i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(collapsed[j][i] + ", ");
            }
            System.out.println();
        }


    }
    public static void pickNewCollapse() {
        int min = 100;
        ArrayList<int[]> minCords = new ArrayList<>();
        for(int i = 0 ; i < Fwidth ; i++) {
            for(int j = 0 ; j < Fheight ; j++) {
                if (map[i][j] < min && !collapsed[i][j]) {
                    min = map[i][j];
                    minCords.clear();
                }
                if (map[i][j] == min) {
                    minCords.add(new int[]{i, j});
                }

            }
        }
        Random rand = new Random();


        if (minCords.size() > 0) {
            System.out.println(minCords.size());
            int rand_int = rand.nextInt(minCords.size());
            System.out.println(Arrays.toString(minCords.get(rand_int)));
            System.out.println(rand_int);
            int[] Cords = minCords.get(rand_int);
            x = Cords[0];
            y = Cords[1];
        }
        minCords.clear();
        collapse(x,y,rand);
    }
    public static void collapse(int x, int y, Random rand) {
        System.out.println(collapsed[x][y]);
        while (!collapsed[x][y]) {
            int rand_int2 = rand.nextInt(ruleMap[x][y].length);
            if (ruleMap[x][y][rand_int2] == 1) {
                tile = rand_int2;
                finalMap[x][y] = rand_int2;
                collapsed[x][y] = true;
            }
        }

    }
    public static void ruleset(int tile, int x, int y) {//pulls a tile number and outputs its ruleset for what tiles can connect to its sides
        if(x>0){
            setOption(tile, x-1, y, "left");
        }
        if(x<Fwidth-1){
            setOption(tile, x+1, y, "right");
        }
        if(y>0){
            setOption(tile, x, y-1, "down");
        }
        if(y<Fheight-1){
            setOption(tile, x, y+1,"up");
        }





    }
    public static void setTile(int tile, int x, int y) {
        for(int i = 0; i < rulelist.length; i++) {
            if (rulelist[tile][i] == rulelist[i][tile]){
                ruleMap[x][y][i] = 1;
            }
            else {
                ruleMap[x][y][i] = 0;
            }
        }
        finalMap[x][y] = tile;
        collapsed[x][y] = true;

    }
    public static void setOption(int tile, int x, int y, String direction) {
        int[] tilerule = new int[rulelist[0][0].length];
        switch (direction){
            case "left":
                finalMap[x][y] = tile;
                collapsed[x][y] = true;
                break;
            case "right":
                finalMap[x][y] = tile;
                collapsed[x][y] = true;
                break;
            case "down":
                finalMap[x][y] = tile;
                collapsed[x][y] = true;
                break;
            case "up":
                finalMap[x][y] = tile;
                collapsed[x][y] = true;
                break;
        }
        optionCount = rulelist.length;

        for (int i = 0; i < rulelist.length; i++) {

            if (rulelist[tile][tile] != rulelist[i][tile]) {

                optionCount--;
                tilerule[i] = 0;
            }
            else {
                tilerule[i] = 1;
            }

        }

        map[x][y] = optionCount;
        ruleMap[x][y] = tilerule;
    }
//    public void draw(){
//
//        for (int i = 0; i < Fheight; i++) {
//            for (int j = 0; j < Fwidth; j++) {
//                int[] cell = map[i];
//
//            }
//        }
//
//    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Wave_function_collapse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(400,400));
        frame.setBackground(Color.BLACK);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        wave_function_collapse(10,10);


    }

}
