package com.example.grift.flaxgt_individual_project_4;

final class GameArrays {
    //winning paths paths matrix
    private final static int[][] winningGamePaths = new int[][]{
        {R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp},
        {R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_upward_black_24dp},
        {R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_left_black_24dp},
        {R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_left_black_24dp, R.drawable.ic_arrow_upward_black_24dp},
        {R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_upward_black_24dp, R.drawable.ic_arrow_right_black_24dp},
        {R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp},
        {R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_upward_black_24dp},
        {R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_upward_black_24dp, R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_downward_black_24dp},
        {R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_left_black_24dp, R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp},
        {R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_upward_black_24dp},
        {R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_upward_black_24dp, R.drawable.ic_arrow_left_black_24dp, R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp},
        {R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_left_black_24dp, R.drawable.ic_arrow_upward_black_24dp, R.drawable.ic_arrow_left_black_24dp}
    };

    //matrix describing how to translate across the board (values 0-1)
    private final static double[][] whereToTranslate = new double[][]{
        //essentially, the part of the screen where I want to translate to
        {.5, 1, 1},
        {1, 1, 0},
        {1, 1, 0},
        {1, 1, 0, 0},
        {.4, .5, .0, 1},
        {.5, .5, 1, 1},
        {.3, .5, 1, 1, 0},
        {1, .5, 0, 1, 1},
        {1, .5, 0, 1, 1},
        {.3, .3, .5, 1, 1, 0},
        {1, 1, 0, .5, .75, .75},
        {.5, 1, 1, .5, 0, 0}
    };

    //returns the number of game map moves determined by the amount of moves there are to reach the end
    public static int getNumberOfGameMapMoves(int pathPos){
        return winningGamePaths[pathPos].length;
    }

    //returns the winning game path as long as the level type is not DEFAULT
    public static int[] getWinningGamePath(int pathPos){
        if(pathPos != winningGamePaths.length){
            return winningGamePaths[pathPos];
        }
        return null;
    }

    //returns the where to translate on the game map as long as the level type is not DEFAULT
    public static double[] getWhereToTranslate(int pathPos){
        if(pathPos != whereToTranslate.length)
        {
            return whereToTranslate[pathPos];
        }
        return new double[]{0, 0, 0, 0};
    }
}
