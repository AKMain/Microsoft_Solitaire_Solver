public class Card {
    int xPixelPosition,yPixelPosition; // top left corner
    int value; // 1 = A,..., 11 = J, 12 = Q, 13 = K
    int suit; // 1 = Hearts, 2 = diamonds, 3 = clubs, 4 = spades

    public Card(int xnew,int ynew,int value,int suit){
        xPixelPosition=xnew;
        yPixelPosition=ynew;
        this.value = value;
        this.suit = suit;
    }
    public Card(int xnew,int ynew){//hidden cards
        xPixelPosition=xnew;
        yPixelPosition=ynew;
        value = 0;
        suit = 0;
    }

    public int getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public int getxPixelPosition() {
        return xPixelPosition;
    }

    public int getyPixelPosition() {
        return yPixelPosition;
    }

    public void setCords(int xnew,int ynew){
        if (xnew>=0)
            xPixelPosition=xnew;
        if (ynew>=0)
            yPixelPosition=ynew;
    }

    public void setSuit(int suit){
        this.suit = suit;
    }

    public void setValue(int value){
        this.value = value;
    }

}
