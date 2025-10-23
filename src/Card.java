public class Card {
    int xPixelPosition,yPixelPosition; // top left corner
    int value; // 1 = A,..., 11 = J, 12 = Q, 13 = K
    int suit; // 1 = Hearts, 2 = diamonds, 3 = clubs, 4 = spades

    //x,y should be close to the top left corner
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

    public Card(){
        xPixelPosition=0;
        yPixelPosition=0;
        value = 0;
        suit = 0;
    }

    public boolean NanCard(){
        return value==0 || suit==0;

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

    public String toString(){
        String temp = "";

        if (value==0){
            temp+="-";
        }else if (value==11){
            temp+="J";
        }else if (value==12){
            temp+="Q";
        }else if (value==13){
            temp+="K";
        }else if (value==1){
            temp+="A";
        }else{
            temp+=""+value;
        }


        if (suit==1){
            temp+="H";
        }else if (suit==2){
            temp+="D";
        }else if (suit==3){
            temp+="C";
        }else if (suit==4){
            temp+="S";
        }else{
            temp+="-";
        }


        return temp;
    }

    public String debugPrintOff(){
        return "Position: "+xPixelPosition+","+yPixelPosition+"\nValue: "+value+"\nSuit: "+suit;
    }

    public boolean equals(Card obj){
        return value == obj.getValue() && suit == obj.getSuit();
    }
}
