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
        {.5, .95, .98},
        {.95, .98, 0},
        {.98, .95, 0},
        {.98, .95, 0, 0},
        {.4, .5, .0, .98},
        {.4, .5, .95, .98},
        {.35, .48, .95, .98, 0},
        {.95, .5, 0, .98, .95},
        {.98, .5, 0, .95, .98},
        {.3, .35, .48, .95, .98, 0},
        {.95, .98, 0, .25, .5, .75},
        {.5, .98, .95, .5, 0, 0}
    };

    //returns the number of game map moves determined by the amount of moves there are to reach the end
    static int getNumberOfGameMapMoves(int pathPos){
        return winningGamePaths[pathPos].length;
    }

    //returns the winning game path as long as the level type is not DEFAULT
    static int[] getWinningGamePath(int pathPos){
        if(pathPos != winningGamePaths.length){
            return winningGamePaths[pathPos];
        }
        return null;
    }

    //returns the where to translate on the game map as long as the level type is not DEFAULT
    static double[] getWhereToTranslate(int pathPos){
        if(pathPos != whereToTranslate.length)
        {
            return whereToTranslate[pathPos];
        }
        return new double[]{0, 0, 0, 0};
    }
}
