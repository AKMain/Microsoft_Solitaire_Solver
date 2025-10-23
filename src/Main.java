public class Main {



    public static void main(String[] args) {
//        System.out.println("\n10S\n");
        SolitareScanner.cardRead(910, 600);
//        System.out.println("\n8H\n");
//        SolitareScanner.cardRead(280, 385);
//        System.out.println("\n5C\n");
//        SolitareScanner.cardRead(450, 385);
//        System.out.println("\nAC\n");
//        SolitareScanner.cardRead(365, 340);


        int suit = SolitareScanner.suitReader(910, 623);
        if (suit == 1){
            System.out.println(" H");
        }else if (suit == 2){
            System.out.println(" D");
        }else if (suit == 3){
            System.out.println(" C");
        }else{
            System.out.println(" S");
        }


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