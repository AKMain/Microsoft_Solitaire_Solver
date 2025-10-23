public class Pyramid extends AbstractSolitare{
    //7 by 7 pyramid
    //2 slots for cards

    //deck hiddin
    //

    private Card left;
    private Card right;
    //after a move left becomes right.

    public Pyramid(){
        super(24, 7, 7);
        fillInBoard();
    }

    @Override public void fillInBoard(){//could be hard coded or could be more abstracted using SolitareScanner class
        //            robot.mouseMove(280, 385); //0,0 left bottem
        //            robot.mouseMove(450, 385); //0,1
        //            robot.mouseMove(365, 340); //1,0
        //            robot.mouseMove(685, 600); //l card
        //            robot.mouseMove(910, 600); //r card
        //            robot.mouseMove(855, 630); //middle
        //            robot.mouseMove(1173, 869); //undo all
        //            robot.mouseMove(1355, 839); //undo
    }
    @Override public void updateDeck(){

    }

    @Override public void solver(){

    }



}
