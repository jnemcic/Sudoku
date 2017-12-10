package com.josipa;

class helpers {

    void print(int[][] a) {

        System.out.println(" ");
        for (int i = 0; i < 9; ++i) {
            if (i != 0 && i % 3 == 0) {
                System.out.println(".....................");
            }
            for (int j = 0; j < 9; ++j) {
                if (j != 0 && j % 3 == 0) {
                    System.out.print("| ");
                }
                if (a[i][j] != 0) {
                    System.out.print(a[i][j] + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }
}

class InitialBoard {

    int[][] Generate() {
        int[][] a = new int[9][9];
        int k = 1;
        int s;
        int l = 1;

        for (int i = 0; i < 9; ++i) {
            s = k;
            for (int j = 0; j < 9; ++j) {
                if (k <= 9) {
                    a[i][j] = k;
                    ++k;
                } else {
                    k = 1;
                    a[i][j] = k;
                    ++k;
                }
            }
            if (i % 3 == 2) {
                ++l;
                k = l;
            } else {
                k = s + 3;
            }
        }
        return a;
    }
}

class TransformInitial {

    int[][] transpose(int[][] a) {

        for (int i = 0; i < 9; ++i) {
            for (int j = i; j < 9; ++j) {
                int temp = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = temp;
            }
        }
        return a;
    }

    int[][] region12(int[][] a, boolean row) {

        //changes 1. and 2. row region (true) or column region (false)
        int first = 3;
        int second = 9;
        if (!row) {
            int temp = first;
            first = second;
            second = temp;
        }
        for (int i = 0; i < first; ++i) {
            for (int j = 0; j < second; ++j) {
                if (row) {
                    int temp = a[i][j];
                    a[i][j] = a[i + 3][j];
                    a[i + 3][j] = temp;
                } else {
                    int temp = a[i][j];
                    a[i][j] = a[i][j + 3];
                    a[i][j + 3] = temp;
                }
            }
        }
        return a;
    }

    int[][] region23row(int[][] a) {

        //changes 2. and 3. row region
        for (int i = 3; i < 6; ++i) {
            for (int j = 0; j < 9; ++j) {
                int temp = a[i][j];
                a[i][j] = a[i + 3][j];
                a[i + 3][j] = temp;
            }
        }
        return a;
    }

    int[][] region23column(int[][] a) {

        //changes 2. and 3. column region
        for (int i = 0; i < 9; ++i) {
            for (int j = 3; j < 6; ++j) {
                int temp = a[i][j];
                a[i][j] = a[i][j + 3];
                a[i][j + 3] = temp;
            }
        }
        return a;
    }


    int[][] region13(int[][] a, boolean row) {

        //changes 1. and 3. row region (true) or column region (false)
        int first = 3;
        int second = 9;
        if (!row) {
            int temp = first;
            first = second;
            second = temp;
        }
        for (int i = 0; i < first; ++i) {
            for (int j = 0; j < second; ++j) {
                if (row) {
                    int temp = a[i][j];
                    a[i][j] = a[i + 6][j];
                    a[i + 6][j] = temp;
                } else {
                    int temp = a[i][j];
                    a[i][j] = a[i][j + 6];
                    a[i][j + 6] = temp;
                }
            }
        }
        return a;
    }

    int[][] intern(int[][] a, int k, int s, boolean row) {

        if (s != 0 && s != 3 && s != 6) {
            System.out.print("Second parameter defines region to transform " +
                    "and is given by the first row of that region. " +
                    "Please choose between s = 0, 3 and 6.");
            return a;
        }
        if (row) {
            switch (k) {
                case 1:
                    for (int j = 0; j < 9; ++j) {
                        int temp1 = a[s][j];
                        a[s][j] = a[s + k][j];
                        a[s + k][j] = temp1;
                    }
                    break;
                case 2:
                    for (int j = 0; j < 9; ++j) {
                        int temp2 = a[s][j];
                        a[s][j] = a[s + k][j];
                        a[s + k][j] = temp2;
                    }
                    break;
                case 3:
                    for (int j = 0; j < 9; ++j) {
                        int temp3 = a[s + 1][j];
                        a[s + 1][j] = a[s + 2][j];
                        a[s + 2][j] = temp3;
                    }
                    break;
            }
        } else {
            switch (k) {
                case 1:
                    for (int j = 0; j < 9; ++j) {
                        int temp1 = a[j][s];
                        a[j][s] = a[j][s + k];
                        a[j][s + k] = temp1;
                    }
                    break;
                case 2:
                    for (int j = 0; j < 9; ++j) {
                        int temp2 = a[j][s];
                        a[j][s] = a[j][s + k];
                        a[j][s + k] = temp2;
                    }
                    break;
                case 3:
                    for (int j = 0; j < 9; ++j) {
                        int temp3 = a[j][s + 1];
                        a[j][s + 1] = a[j][s + 2];
                        a[j][s + 2] = temp3;
                    }
                    break;
            }
        }
        return a;
    }

    int[][] globalValueChange(int[][] a){

        int x = (int)(Math.random()*9 + 1);
        int y = (int)(Math.random()*9 + 1);;

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (a[i][j] == x || a[i][j] == y) {
                    if (a[i][j] != x) {
                        a[i][j] = x;
                    } else {
                        a[i][j] = y;
                    }
                }
            }
        }
        return a;
    }
}

class RandomTransformation {

    int[][] randomTrans(int[][] a) {
        TransformInitial transformedBoard = new TransformInitial();

        for (int i = 0; i < 500; ++i) {
            int random = (int) (Math.random() * 26 + 1);

            switch (random) {
                case 1:
                    a = transformedBoard.transpose(a);
                    break;
                case 2:
                    a = transformedBoard.region12(a, true);
                    break;
                case 3:
                    a = transformedBoard.region12(a, false);
                    break;
                case 4:
                    a = transformedBoard.region13(a, true);
                    break;
                case 5:
                    a = transformedBoard.region13(a, false);
                    break;
                case 6:
                    a = transformedBoard.region23column(a);
                    break;
                case 7:
                    a = transformedBoard.region23row(a);
                    break;
                case 8:
                    a = transformedBoard.intern(a, 1, 0, true);
                    break;
                case 9:
                    a = transformedBoard.intern(a, 1, 0, false);
                    break;
                case 10:
                    a = transformedBoard.intern(a, 1, 3, true);
                    break;
                case 11:
                    a = transformedBoard.intern(a, 1, 3, false);
                    break;
                case 12:
                    a = transformedBoard.intern(a, 1, 6, true);
                    break;
                case 13:
                    a = transformedBoard.intern(a, 1, 6, false);
                    break;
                case 14:
                    a = transformedBoard.intern(a, 2, 0, true);
                    break;
                case 15:
                    a = transformedBoard.intern(a, 2, 0, false);
                    break;
                case 16:
                    a = transformedBoard.intern(a, 2, 3, true);
                    break;
                case 17:
                    a = transformedBoard.intern(a, 2, 3, false);
                    break;
                case 18:
                    a = transformedBoard.intern(a, 2, 6, true);
                    break;
                case 19:
                    a = transformedBoard.intern(a, 2, 6, false);
                    break;
                case 20:
                    a = transformedBoard.intern(a, 3, 0, true);
                    break;
                case 21:
                    a = transformedBoard.intern(a, 3, 0, false);
                    break;
                case 22:
                    a = transformedBoard.intern(a, 3, 3, true);
                    break;
                case 23:
                    a = transformedBoard.intern(a, 3, 3, false);
                    break;
                case 24:
                    a = transformedBoard.intern(a, 3, 6, true);
                    break;
                case 25:
                    a = transformedBoard.intern(a, 3, 6, false);
                    break;
                case 26:
                    a = transformedBoard.transpose(a);
            }
        }
        int k = 200;
        while (k > 0) {
            a = transformedBoard.globalValueChange(a);
            --k;
        }
        return a;
    }
}

class CreatingChallenge {

    int[][] deleteValues(int[][] a, int difficulty){

        for (int k = 0; k < difficulty; ++k) {
            int i = (int)(Math.random() * 9);
            int j = (int)(Math.random() * 9);
            a[i][j] = 0;
        }
        return a;
    }
}

class Solver {
    boolean sudokuSolver(int[][] a) {

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (a[i][j] == 0) {
                    for (int k = 1; k < 10; ++k) {
                        a[i][j] = k;
                        if (boardValid(a, i, j, k) && sudokuSolver(a)) {
                            return true;
                        } else {
                            a[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean boardValid(int[][] a, int ii, int jj, int k) {
        for (int j = 0; j < 9; ++j) {
            if (a[ii][j] == k && j != jj) {
                return false;
            }
        }
        for (int i = 0; i < 9; ++i) {
            if (a[i][jj] == k && i != ii) {
                return false;
            }
        }

        int s, r;
        if (ii < 3) {
            s = 0;
        } else if (ii < 6) {
            s = 3;
        } else {
            s = 6;
        }

        if (jj < 3) {
            r = 0;
        } else if (jj < 6) {
            r = 3;
        } else {
            r = 6;
        }

        for (int i = s; i < (s + 3); ++i) {
            for (int j = r; j < (r + 3); ++j) {
                if (a[i][j] == k && i !=ii && j != jj) {
                    return false;
                }
            }
        }
        return true;
    }
}

public class Main {

    public static void main(String arg[]) {

        InitialBoard board = new InitialBoard();
        int[][] a = board.Generate();
        helpers printing = new helpers();

        RandomTransformation randTransf = new RandomTransformation();
        a = randTransf.randomTrans(a);

        CreatingChallenge challenge = new CreatingChallenge();
        //difficulty:
        //low = 50
        //medium = 55
        //high = 60
        a = challenge.deleteValues(a, 60);
        printing.print(a);

        Solver solve = new Solver();
        solve.sudokuSolver(a);
        printing.print(a);
    }
}
