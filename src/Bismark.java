import java.util.Scanner;
import java.util.Random;

public class Bismark {

    public static void main(String[] args) {

        // initialize pgrid
        char[][] pgrid = new char[5][5];
        for (int i = 0; i < pgrid.length; i++)
            for (int j = 0; j < pgrid.length; j++)
                pgrid[i][j] = ' ';

        // welcome method
        System.out.println("Programming Fundamentals");
        System.out.println("Name: Gabriel DaSilva");
        System.out.println("PROGRAMMING ASSIGNMENT 2\n");
        System.out.println("Welcome to Bismarck!\n");


        // print initial pgrid
        System.out.println("  12345 ");
        System.out.println(" *******");
        for (int i = 0; i < pgrid.length; i++) {
            System.out.print(i+1);
            System.out.print("*");
            for (int j = 0; j < pgrid.length; j++) {
                System.out.print(pgrid[i][j]);
            }
            System.out.print("*");
            System.out.println();
        }
        System.out.println(" *******");

        // ship orientation
        Scanner sc = new Scanner(System.in) ;
        char dir = ' ';
        do {
            System.out.print("Choose the Prince of Wales' orientation (v or h):");
            dir = sc.next().charAt(0);
        } while (dir != 'v' && dir != 'h');
        System.out.println();

        // pship location
        int ploc;
        boolean validLoc = false;
        do {
            System.out.print("Choose the leftmost or uppermost point of the ship (vh):");
            ploc = sc.nextInt();
            int row = ploc/10;
            int col = ploc%10;

            if (dir == 'v') {
                if ((row >= 1 && row <=3) && (col >= 1 && col <= 5)) {
                    validLoc = true;
                    pgrid[row - 1][col - 1] = '%';
                    pgrid[row][col - 1] = '%';
                    pgrid[row + 1][col - 1] = '%';
                }
            } else if (dir == 'h') {
                if ((row >= 1 && row <=5) && (col >= 1 && col <= 3)) {
                    validLoc = true;
                    pgrid[row - 1][col - 1] = '%';
                    pgrid[row - 1][col] = '%';
                    pgrid[row - 1][col + 1] = '%';
                }
            }
        } while (!validLoc);

        // print pgrid
        System.out.println("  12345 ");
        System.out.println(" *******");
        for (int i = 0; i < pgrid.length; i++) {
            System.out.print(i+1);
            System.out.print("*");
            for (int j = 0; j < pgrid.length; j++) {
                System.out.print(pgrid[i][j]);
            }
            System.out.print("*");
            System.out.println();
        }
        System.out.println(" *******");

        // initialize cgrid
        char[][] cgrid = new char[5][5];
        for (int i = 0; i < cgrid.length; i++)
            for (int j = 0; j < cgrid.length; j++)
                cgrid[i][j] = ' ';

        // random computer ship orientation
        char corientation;
        Random rand = new Random(17);
        int rorientation = rand.nextInt(2);
        if (rorientation == 0)
            corientation = 'v';
        else
            corientation = 'h';


        // random cship location
        if (corientation == 'v') {
            int cvrow = rand.nextInt(3);
            int cvcol = rand.nextInt(5);
            cgrid[cvrow][cvcol] = '%';
            cgrid[cvrow + 1][cvcol] = '%';
            cgrid[cvrow + 2][cvcol] = '%';
        } else {
            int hvrow = rand.nextInt(5);
            int hvcol = rand.nextInt(3);
            cgrid[hvrow][hvcol] = '%';
            cgrid[hvrow][hvcol + 1] = '%';
            cgrid[hvrow][hvcol + 2] = '%';
        }




        // game loop
        int guess;
        int pscore = 0;
        int cscore = 0;
        boolean end = false;
        do {

            // player turn
            if (pscore != 3) {
                System.out.print("Enter your guess (vh):");
                guess = sc.nextInt();
                int grow = guess / 10;
                int gcol = guess % 10;
                if (cgrid[grow - 1][gcol - 1] == '%') {
                    cgrid[grow - 1][gcol - 1] = ' ';
                    pscore++;
                    System.out.println("You hit the Bismarck!\n");

                } else {
                    System.out.println("You missed!\n");
                }
            } else {
                break;
            }

            // computer turn
            int crowguess = rand.nextInt(5);
            int ccolguess = rand.nextInt(5);
            int cdisplayrowguess = crowguess + 1;
            int cdisplaycolguess = ccolguess + 1;
            System.out.println("Bismarck aimed at " + cdisplayrowguess + " " + cdisplayrowguess);
            if (pgrid[crowguess][ccolguess] == '%') {
                pgrid[crowguess][ccolguess] = ' ';
                cscore++;
                System.out.println("Bismarck hit the Prince of Wales!\n");
            } else {
                System.out.println("Bismarck missed!\n");
            }


        } while(pscore != 3 && cscore !=3);

        // display winner
        if (pscore == 3) {
            System.out.print("Congratulations! You sank the Bismarck!");
        } else {
            System.out.print("Game Over! The Bismarck sank the Prince of Wales!");
        }
    }






}