import java.awt.*;
import java.awt.event.InputEvent;

public class Pyramid extends AbstractSolitare{
    //7 by 7 pyramid
    //2 slots for cards

    //deck hiddin

    //            robot.mouseMove(280, 385); //0,0 left bottem
    //            robot.mouseMove(450, 385); //0,1
    //            robot.mouseMove(365, 340); //1,0
    //            robot.mouseMove(685, 600); //l card
    //            robot.mouseMove(910, 600); //r card
    //            robot.mouseMove(855, 630); //middle
    //            robot.mouseMove(1173, 869); //undo all
    //            robot.mouseMove(1355, 839); //undo

    private Card left;
    private Card right;
    //after a move left becomes right.

    public Pyramid(){
        super(72, 7, 7);
        for (int i = 0; i < 72; i++) {
            deck[i]=new Card();
        }
        fillInBoard();
        deck[0]=SolitareScanner.cardRead(685, 601);
        left=deck[48]=deck[24]=deck[0];
    }

    @Override public void fillInBoard(){//could be hard coded or could be more abstracted using SolitareScanner class
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7-i; j++) {
                board[i][j]=SolitareScanner.cardRead(282+172*j+86*i,388-46*i);
            }
        }
    }
    @Override public void updateDeck(){

    }
    @Override public void solver(){

    }

    @Override public void dataDump(){

        for (int i = 6; i >=0; i--) {
            for (int j = 0; j < 7-i ; j++) {
                System.out.print(board[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("Left: "+left+"\tRight: "+right+"\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 24; j++) {
                System.out.print(deck[i*24+j]+"\t");
            }
            System.out.println();
        }


    }

    public void nextCard(){
        try{
            Robot robot = new Robot();
            right = left;

            robot.mouseMove(855, 630);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            insideDeckTracker++;
            if (insideDeckTracker<25){
                Thread.sleep(200);//need to find the correct speed
            }

            if (insideDeckTracker%24==0 && insideDeckTracker!=0){
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                right= new Card();
            }
            if (insideDeckTracker<24 && deck[insideDeckTracker].NanCard()){
                deck[insideDeckTracker+48]=deck[insideDeckTracker+24]=deck[insideDeckTracker]=SolitareScanner.cardRead(685, 601);
            }else{
                while(deck[insideDeckTracker].NanCard()){
                    insideDeckTracker++;
                }
            }
            left=deck[insideDeckTracker];

        }catch (Exception e){
            System.out.println("Exception -nextCard");
        }
    }

}
