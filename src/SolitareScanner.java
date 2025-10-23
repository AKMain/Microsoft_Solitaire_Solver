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

                    int pixelRGB = saturation(image.getRGB(j, i));
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


    public static int[] rowsumScanner(int cornerx,int cornery,int length,int width){
        int [] rowsum = new int[length];

        try{
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(cornerx, cornery, width, length);
            BufferedImage image = robot.createScreenCapture(screenRect);

            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++) {

                    // 0 = white, 1 = black, 2 = red
                    rowsum[i]+=saturation(image.getRGB(j, i));

                }

            }

        }catch(AWTException e) {
            System.out.println("AWTException -rowsumScanner");

        }catch (Exception e){
            System.out.println("Exception -rowsumScanner");
        }

        return rowsum;
    }

    public static int suitReader(int x,int y){
        int [] cardIndicator = rowsumScanner(x,y,20,20);

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

    public static int valueReader(int x,int y){

        return 0;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////
    private static int suitReader(int [][] cardIndicator,int yIA){//line where we first see suit

        //simple get the sum of the rows nd this will determin the card type.


        return 0;
    }
    private static int valueReader(int [][] cardIndicator,int yIA){//line where we first see num

        return 0;
    }
    public static int saturation(int RGBValue){
        // 0 = white, 1 = black, 2 = red

        Color pixelColor = new Color(RGBValue);
        int red = pixelColor.getRed();
        int green = pixelColor.getGreen();
        int blue = pixelColor.getBlue();

        if (red!=0 && (green+blue)/red <= 0){
            return 2;
        }else if (green<150 && blue <150 && red <150){
            return 1;
        }

        return 0;
    }

}
