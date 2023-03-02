import java.util.Random;
import java.util.Scanner;
public class lesson4 {
    public static int SIZE = 3;
    public static int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'x';
    public static final char DOT_O = 'o';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();
    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Pobedil chelovek");
                break;
            }
            if (isMapFull()) {
                System.out.println("Nichya");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Pobeil Iskustveny Intelekt");
                break;
            }
            if (isMapFull()) {
                System.out.println("Nichya");
                break;
            }
        }
        System.out.println("Igra zakonchena");
    }
    public static boolean checkWin(char symb) {
        int hor, ver;
        int diagMain, diagReverse;
        for (int i = 0; i < SIZE; i++) {
            hor = 0;
            ver = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == symb) {                          // проверяем горизонтальные линии на возможную победу
                    hor++;
                } else if (map[i][j] != symb && hor < DOTS_TO_WIN) {
                    hor = 0;
                }
                if (map[j][i] == symb) {                          // проверяем вертикальные линии на возможную победу
                    ver++;
                }   else if (map[j][i] != symb && ver < DOTS_TO_WIN) {
                    ver = 0;
                }
            }
            if (hor >= DOTS_TO_WIN || ver >= DOTS_TO_WIN) {
                System.out.println("По горизонтали или вертикали " + hor + " " + ver);
                return true;
            }
        }

        for (int j = 0; j < SIZE; j++) {
            diagMain = 0;
            for (int i = 0; i < SIZE; i++) {
                int k = j + i;
                if (k < SIZE) {
                    if (map[i][k] == symb) {                      // проверяем главную диагональ от центральной оси вправо на возможную победу
                        diagMain++;
                    } else if (map[i][k] != symb && diagMain < DOTS_TO_WIN) {
                        diagMain = 0;
                    }
                }
                if (diagMain >= DOTS_TO_WIN) {
                    System.out.println("По главной диагонали от центральной оси вправо " + diagMain);
                    return true;
                }
            }
        }
        for (int j = 1; j < SIZE; j++) {
            diagMain = 0;
            for (int i = 0; i < SIZE; i++) {
                int k = j + i;
                if (k < SIZE) {
                    if (map[k][i] == symb) {                      // проверяем главную диагональ от центральной оси вниз на возможную победу
                        diagMain++;
                    } else if (map[k][i] != symb && diagMain < DOTS_TO_WIN) {
                        diagMain = 0;
                    }
                }
                if (diagMain >= DOTS_TO_WIN) {
                    System.out.println("По главной диагонали от центральной оси вниз " + diagMain);
                    return true;
                }
            }
        }
        for (int j = 0; j < SIZE; j++) {
            diagReverse = 0;
            for (int i = 0; i < SIZE; i++) {
                int k = (SIZE - 1) - i;
                int l = j + i;
                if (k >= 0 && l < SIZE) {
                    if (map[l][k] == symb) {                     // проверяем побочную диагональ от центральной оси вниз на возможную победу
                        diagReverse++;
                    } else if (map[l][k] != symb && diagReverse < DOTS_TO_WIN) {
                        diagReverse = 0;
                    }
                }
                if (diagReverse >= DOTS_TO_WIN) {
                    System.out.println("По побочной диагонали от центральной оси вниз " + diagReverse);
                    return true;
                }
            }
        }
        for (int j = 1; j < SIZE; j++) {
            diagReverse = 0;
            for (int i = 0; i < SIZE; i++) {
                int k = (SIZE - 1) - j - i;
                if (k >= 0) {
                    if (map[i][k] == symb) {     // проверяем побочную диагональ от центральной оси влево на возможную победу
                        diagReverse++;
                    } else if (map[i][k] != symb && diagReverse < DOTS_TO_WIN) {
                        diagReverse = 0;
                    }
                }
                if (diagReverse >= DOTS_TO_WIN) {
                    System.out.println("По побочной диагонали от центральной оси влево " + diagReverse);
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Computer pohodil v tochky " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }
    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Vvedite kordinaty v formate X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)
        map[y][x] = DOT_X;
    }
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

