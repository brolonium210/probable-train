
import java.util.*;

public class LempelZiv {
    /**
     * Take uncompressed input as a text string, compress it, and return it as a
     * text string.
     */

    record Tuple(int offset,int length,String c){}

    static boolean findCharHelper(List<Tuple> myList,String myStr){
        for(Tuple tup:myList){
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
        StringBuilder output= new StringBuilder();
        int cursor = 0;
        int windowSize = 100;
        while(cursor < input.length()){
            int length = 0;
            int prevMatch = 0;
            String myPattern = input.substring(cursor,cursor+length);
            String myWindow = input.substring(cursor-length,cursor);
            int match = myWindow.indexOf(myPattern);
            if(match > -1){
                prevMatch = match;
                length++;
            }else{

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
