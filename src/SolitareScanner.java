import java.awt.*;
import java.awt.image.BufferedImage;

public class SolitareScanner {
    // 1 = Hearts, 2 = dimonds, 3 = clubs, 4 = speads

    public static Card cardRead(int x,int y){
        int [][] cardIndicator = new int[20][40];
//280, 385
//295, 425
        try{
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(x, y, 20, 40);
            BufferedImage image = robot.createScreenCapture(screenRect);

            int sum;

            for (int i = 0; i < 40; i++) {
                sum=0;
                for (int j = 0; j < 20; j++) {

                    int pixelRGB = saturation(image.getRGB(j, i),true);
                    // 0 = white, 1 = black, 2 = red

                    cardIndicator[j][i]=pixelRGB;

                    sum+=pixelRGB;

//                    System.out.print(pixelRGB+"\t");

                }
                System.out.print(sum+",");

            }

        }catch(AWTException e) {
            System.out.println("Error in valueReader -robot");

        }catch (Exception e){
            System.out.println("Error in valueReader");
        }

        //now using the array we need to find the number + suit.

        return new Card(0,0);
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
                    System.out.print(unit+"\t");
                }
                System.out.println();
            }

        }catch(AWTException e) {
            System.out.println("AWTException -rowsumScanner");

        }catch (Exception e){
            System.out.println("Exception -rowsumScanner");
        }

        return rowsum;
    }

    public static int suitReader(int x,int y){

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

        return valueReader(rowsumScanner(x,y,25,20,false));
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
                {5, 5, 7, 7, 6, 8, 6, 7, 8, 11, 13, 13, 8, 8, 8, 6, 0},
                {3, 7, 9, 8, 4, 4, 4, 4, 5, 5, 5, 4, 4, 4, 11,11,10,0},
                {6, 8, 9, 4, 3, 4, 5, 7, 7, 7, 4, 4, 4, 7, 9, 8, 3, 0},
                {4, 5, 5, 6, 7, 7, 7, 7, 8, 8,13,13, 7, 4, 4, 2, 0, 0},
                {7, 8, 9, 9, 3, 3, 6, 8, 9, 6, 4, 4, 4, 6, 9, 8, 5, 0},
                {4, 7, 8, 5, 3, 4, 6, 9,10, 8, 7, 7, 8, 7, 9, 7, 4, 0},
                {11,11,11,4, 3, 4, 4, 4, 3, 4, 3, 4, 4, 4, 3, 3, 0, 0},
                {3, 7, 9, 8, 8, 7, 8, 7, 7, 9, 8, 7, 7, 8,10, 9, 5, 0},
                {7, 9, 8, 6, 7, 7, 8,10,10, 7, 4, 3, 4, 8, 7, 2, 0, 0},
                {10,15,15,13,12,11,12,12,12,12,11,12,11,14,12,10,0, 0},
                {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 6, 6, 4, 0, 0},
                {7, 11,12,8, 8, 8, 7, 7, 7, 7, 8, 8,10,11,10, 9, 4, 3},
                {6, 7, 7, 6, 7, 7, 7, 7, 8, 7, 7, 7, 8, 7, 7, 0, 0, 0},
        };

System.out.println("cardIndicator length: "+cardIndicator.length+"\t"+suitsTemplate[0].length);

        int [] differences=new int[13];

        int suitIndex=0,cardIndicatorIndex=0;

        while(suitIndex<17 && suitIndex+cardIndicatorIndex<22){

            if (cardIndicator[cardIndicatorIndex]==0){
                cardIndicatorIndex++;
            }else{
                for (int i = 0; i < 13; i++) {
                    differences[i]+=Math.abs(suitsTemplate[i][suitIndex]-cardIndicator[suitIndex+cardIndicatorIndex]);
                }
                suitIndex++;
            }

        }

        int minIndex=0,minVal = differences[0];

        for (int i = 1; i < 13; i++) {
            if (minVal>differences[i]){
                minIndex=i;
                minVal=differences[i];
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

}
