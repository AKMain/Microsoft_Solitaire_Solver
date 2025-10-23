public class Main {

    public static void cardDetectiontestFn(int col,int row){
        System.out.println(SolitareScanner.cardRead(282+172*col+86*row,388-46*row));
    }


    public static void main(String[] args) {


        Pyramid gamev3 = new Pyramid();
        for (int i = 0; i < 24; i++) {
            gamev3.nextCard();
            gamev3.dataDump();
        }
        gamev3.solver();




    }
    // 1 = Hearts, 2 = diamonds, 3 = clubs, 4 = spade

    //            robot.mouseMove(280, 385); //0,0 left bottem
    //            robot.mouseMove(450, 385); //0,1
    //            robot.mouseMove(365, 340); //1,0

}

/*
User id: DMW-CS-86

Currently working on:
-scanning of cards
-card proccessing
-pyramid

To do:
-Freecell
-Klondike
-Spider
-TriePeak

 */