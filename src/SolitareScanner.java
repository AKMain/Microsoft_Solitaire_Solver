import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.InputEvent;
public class SolitareScanner {
    static boolean debug = false;

    // 1 = Hearts, 2 = dimonds, 3 = clubs, 4 = speads

    public static Card cardRead(int x,int y){


        if (debug){
            System.out.println();
        }

        int width = 18;
        int hight = 40;

        ArrayList<Integer> cardGaps = new ArrayList<>();

        try{
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(x, y, width, hight);
            BufferedImage image = robot.createScreenCapture(screenRect);

            int sumold=0,sumnew=0;



            for (int i = 0; i < hight; i++) {
                sumnew=0;
                for (int j = 0; j < width; j++) {
                    sumnew+=saturation(image.getRGB(j, i),false);

                    if (debug){
                        System.out.print(saturation(image.getRGB(j, i),false)+"\t");
                    }

                }

                if (debug){
                    System.out.println(sumnew);
                }

                if (sumnew!=0 && sumold==0){
                    cardGaps.add(i-1);
                }
                sumold=sumnew;

            }

        }catch(AWTException e) {
            System.out.println("AWTException -cardRead");

        }catch (Exception e){
            System.out.println("Exception -cardRead");
        }



        int val = valueReader(x,y+ cardGaps.get(0));
        int suit = suitReader(x,y+ cardGaps.get(1));

        if (debug){
            System.out.println(cardGaps);
            System.out.println(new Card(x,y,val,suit));
        }

        return new Card(x,y,val,suit);
    }


    public static int[] rowsumScanner(int cornerx,int cornery,int length,int width,boolean twoColour){
        int [] rowsum = new int[length];

        try{
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(cornerx, cornery, width, length);
            BufferedImage image = robot.createScreenCapture(screenRect);

            int unit;

            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++) {
                    unit = saturation(image.getRGB(j, i),twoColour);

                    // 0 = white, 1 = black, 2 = red
                    rowsum[i]+=unit;

                }

            }

        }catch(AWTException e) {
            System.out.println("AWTException -rowsumScanner");

        }catch (Exception e){
            System.out.println("Exception -rowsumScanner");
        }

        if (debug){
            for (int i = 0; i < rowsum.length; i++) {
                System.out.print(rowsum[i]+", ");
            }
            System.out.println();
        }


        return rowsum;
    }

    public static int suitReader(int x,int y){
        if (debug){
            System.out.print("Suit: ");
        }

//        try{
//            Robot robot = new Robot();
//            Rectangle screenRect = new Rectangle(x, y, 20, 20);
//            BufferedImage image = robot.createScreenCapture(screenRect);
//
//            for (int i = 0; i < 20; i++) {
//                for (int j = 0; j < 20; j++) {
//
//                    int pixelRGB = saturation(image.getRGB(j, i));
//                    // 0 = white, 1 = black, 2 = red
//
//                    cardIndicator[i]+=pixelRGB;
////                    System.out.print(pixelRGB+"\t");
//                }
//
//            }
//
//        }catch(AWTException e) {
//            System.out.println("Error in valueReader -robot-suitReader");
//
//        }catch (Exception e){
//            System.out.println("Error in valueReader-suitReader");
//        }

        // 1 = Hearts, 2 = diamonds, 3 = clubs, 4 = spade
        return suitReader(rowsumScanner(x,y,20,20,true));

    }

    public static int valueReader(int x,int y){
        if (debug){
            System.out.print("Value: ");
        }
        return valueReader(rowsumScanner(x,y,22,20,false));
    }
/////////////////////////////////////////////////////////////////////////////////////////////////
    private static int suitReader(int [] cardIndicator){

        // 1 = Hearts, 2 = diamonds, 3 = clubs, 4 = spades
        int [][] suitsTemplate = {  {12,20,22,24,24,22,22,18,14,10,6,2,0,0},
                                    {2, 4, 6, 10,14,16,18,16,14,10,6,4,0,0},
                                    {4, 6, 7, 7, 7, 11,13,13,13,9, 2,3,3,3},
                                    {1, 3, 5, 7, 9, 11,12,12,12,11,7,1,3,0}};

        int [] differences=new int[4];

        int suitIndex=0,cardIndicatorIndex=0;

        while(suitIndex<14 && suitIndex+cardIndicatorIndex<20){

            if (cardIndicator[cardIndicatorIndex]==0){
                cardIndicatorIndex++;
            }else{
                for (int i = 0; i < 4; i++) {
                    differences[i]+=Math.abs(suitsTemplate[i][suitIndex]-cardIndicator[suitIndex+cardIndicatorIndex]);
                }
                suitIndex++;
            }

        }

        int minIndex=0,minVal = differences[0];

        for (int i = 1; i < 4; i++) {
            if (minVal>differences[i]){
                minIndex=i;
                minVal=differences[i];
            }
        }

        return minIndex+1;

    }
    private static int valueReader(int [] cardIndicator){

        // 1 = A,..., 11 = J, 12 = Q, 13 = K
        int [][] suitsTemplate = {
                {5, 6, 6, 7, 6, 6, 8, 8, 7, 9,12,12,11, 8, 8, 7, 0, 0},//A (alt0 5, 5, 7, 7, 6, 8, 6, 7, 8, 11, 13, 13, 8, 8, 8, 6, 0) (alt1 5, 6, 6, 7, 6, 6, 7, 7, 6,11,12,13, 8, 7, 6, 0, 0, 0)
                {7, 9, 6, 4, 4, 3, 4, 4, 4, 4, 4, 4, 4,11,11, 8, 0, 0},//2 (alt0 3, 7, 9, 8, 4, 4, 4, 4, 5, 5, 5, 4, 4, 4, 11,11,10,0)
                {6, 8, 9, 4, 3, 4, 5, 7, 7, 7, 4, 4, 4, 7, 9, 8, 3, 0},//3
                {4, 5, 5, 6, 7, 7, 7, 7, 8, 8,13,13, 7, 4, 4, 2, 0, 0},//4
                {8, 8, 8, 3, 4, 4, 7, 9,10, 4, 4, 4, 4, 8, 9, 8, 0, 0},//5 (alt0 7, 8, 9, 9, 3, 3, 6, 8, 9, 6, 4, 4, 4, 6, 9, 8, 5, 0) (alt1 8, 8, 4, 2, 2, 3, 8, 9, 4, 4, 4, 4, 3, 9, 8, 5, 0, 0)
                {3, 7, 8, 6, 4, 4, 7,10,10, 8, 8, 7, 8, 8, 9, 7, 5, 0},//6 (alt0 4, 7, 8, 5, 3, 4, 6, 9,10, 8, 7, 7, 8, 7, 9, 7, 4, 0)
                {11,11,11,4, 3, 4, 4, 4, 3, 4, 3, 4, 4, 4, 3, 3, 0, 0},//7
                {5, 8, 8, 7, 7, 6, 8, 7, 8, 6, 6, 6, 7, 10, 9, 5, 0, 0},//8 (alt0 3, 7, 9, 8, 8, 7, 8, 7, 7, 9, 8, 7, 7, 8,10, 9, 5, 0)
                {2, 7, 9, 6, 8, 6, 6, 8,10,10, 6, 3, 3, 4, 8, 7, 2, 0},//9 (alt0 7, 9, 8, 6, 7, 7, 8,10,10, 7, 4, 3, 4, 8, 7, 2, 0, 0)
                {10,15,15,13,12,11,12,12,12,12,11,12,11,14,12,10,0, 0},//10
                {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 6, 6, 4, 0, 0},//11
                {7, 11,12,8, 8, 8, 7, 7, 7, 7, 8, 8,10,11,10, 9, 4, 3},//12
                {7, 8, 8, 9, 8, 8, 9, 8, 8, 8, 9, 8, 9, 9, 9, 6, 0, 0},//13 (alt0 6, 7, 7, 6, 7, 7, 7, 7, 8, 7, 7, 7, 8, 7, 7, 0, 0, 0)
        };


        int [] differences=new int[13];

        int suitIndex=0,cardIndicatorIndex=0;

        while(suitIndex<17 && suitIndex+cardIndicatorIndex<22){

            if (cardIndicator[cardIndicatorIndex]==0){
                cardIndicatorIndex++;
            }else if (cardIndicator[suitIndex+cardIndicatorIndex]!=0){
                for (int i = 0; i < 13; i++) {
                    differences[i]+=Math.pow(suitsTemplate[i][suitIndex]-cardIndicator[suitIndex+cardIndicatorIndex],2);
                }
                suitIndex++;
            }else{
                break;
            }

        }

        int minIndex=0,minVal = differences[0];

        for (int i = 1; i < 13; i++) {
            if (minVal>differences[i]){
                minIndex=i;
                minVal=differences[i];
            }
        }

        if (debug){
            for (int i = 0; i < 13; i++) {
                System.out.println("Difference with "+(i+1)+" is: "+differences[i]);
            }

        }

        return minIndex+1;

    }
    public static int saturation(int RGBValue,boolean twoColours){
        // 0 = white, 1 = black, 2 = red

        Color pixelColor = new Color(RGBValue);
        int red = pixelColor.getRed();
        int green = pixelColor.getGreen();
        int blue = pixelColor.getBlue();

        if (twoColours){
            if (red!=0 && (green+blue)/red <= 0){
                return 2;
            }else if (green<150 && blue <150 && red <150){
                return 1;
            }
        }else if ((green<150 && blue <150 && red <150) || (red!=0 && (green+blue)/red <= 0)){
            return 1;
        }


        return 0;
    }

    public static void undoMove(){
        try{
            Robot robot = new Robot();
            robot.mouseMove(1489, 861);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);

        }catch (Exception e){
            System.out.println("Exception -undoMove");
        }
    }
    public static void undoAllMove(){
        try{
            Robot robot = new Robot();
            robot.mouseMove(1172, 865);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);

        }catch (Exception e){
            System.out.println("Exception -undoAllMove");
        }
    }
}
