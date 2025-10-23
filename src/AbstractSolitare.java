abstract public class AbstractSolitare {
    protected boolean allCardsKnown;
    protected Card [][] board; //cards that can be seen on the board.
    protected Card [] deck; //this is for hidden cards/cards not on board directly
    protected int insideDeckTracker; //tracker for where we are in the deck.

    public AbstractSolitare(int deckSize,int boardRows,int boardCols){
        board = new Card[boardRows][boardCols];
        deck = new Card[deckSize];
        insideDeckTracker=0;
        allCardsKnown=false;
    }

    public abstract void fillInBoard(); //this is meant to update the board and fill it in.
    public abstract void updateDeck(); //meant to update the deck as game goes on.

    public abstract void solver();//function that solves the game

    public abstract void dataDump();//this is mean to print off the board.

}
