
import java.util.*;

public class LempelZiv {
    /**
     * Take uncompressed input as a text string, compress it, and return it as a
     * text string.
     */

    record OLC(int offset,int lengthOfMatch,String c){}

    static boolean findCharHelper(List<OLC> myList,String myStr){
        for(OLC tup:myList){
            if(tup.c.equals(myStr) ){
                return true;
            }
        }
        return false;
    }

//    static boolean lookForwardHelper(int index,String input){
//                String lookForward = input.substring(index,index+1);
//                String window = input.substring(0,index-1);
//                for(int i=window.length()-1;i>0;i--){
//                    if(window.charAt(i) == lookForward.charAt(0) && window.charAt(i+1) == lookForward.charAt(1)){
//
//                    }
//                }
//    }


    public static String compress(String input) {
        // TODO fill this in.
        List<OLC> myDict = new ArrayList<>();
        List<OLC> outDict = new ArrayList<>();
        //will need to figure out window
        int count = 0;
        while(count<3) {



        for (int i = 0; i < input.length() - count && i >= 0; i++) {
            String temp = input.substring(i, i+count);
            if (!findCharHelper(myDict, temp)) {
                myDict.add(new OLC(0, temp.length()-1, temp));
            }
        }
            count++;
        }

        count=0;
        while(count<3) {
            for (int i = 0; i < input.length(); i++) {
                String temp = input.substring(i - count, i);
                if(findCharHelper(myDict,temp)){

                }
            }
        }


        return "";
    }

    /**
     * Take compressed input as a text string, decompress it, and return it as a
     * text string.
     */
    public static String decompress(String compressed) {
        // TODO fill this in.
        return "";
    }

    /**
     * The getInformation method is here for your convenience, you don't need to
     * fill it in if you don't want to. It is called on every run and its return
     * value is displayed on-screen. You can use this to print out any relevant
     * information from your compression.
     */
    public String getInformation() {
        return "";
    }
}
