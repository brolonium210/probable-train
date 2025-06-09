
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LempelZiv {
    /**
     * Take uncompressed input as a text string, compress it, and return it as a
     * text string.
     */

    public static List<Tuple> myDict = new ArrayList<>();

    record Tuple(int offset,int length,String c){
        @Override
        public String toString(){
            return "["+offset+"|"+length+"|"+c+"]";
        }

    }

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
        int windowSize = 8;
        while(cursor < input.length()){
            int length = 0;
            int prevMatch = -1;
            String myWindow;
            while(true) {
                String myPattern = input.substring(cursor, Math.min(cursor + length, input.length()));
                if (length == 0) {
                    length++;
                    continue;
                }
//                String myPattern = input.substring(cursor, cursor + length);

                int windowStart = Math.max(0, cursor - windowSize);
                myWindow = input.substring(windowStart, cursor);

                int match = myWindow.indexOf(myPattern);
                if (match > -1) {
                    prevMatch = match;
                    length++;
                } else {
                    int offset = (prevMatch > -1) ? (cursor - (windowStart + prevMatch)) : 0;
//                    offset = cursor - (windowStart + prevMatch);
                    Tuple temp = new Tuple(offset, length - 1, input.substring(cursor + length - 1, cursor + length));
                    myDict.add(temp);
                    output.append(temp);
                    cursor += length;
                    break;
                }
            }

        }


        return String.valueOf(output);
    }

    /**
     * Take compressed input as a text string, decompress it, and return it as a
     * text string.
     */

    public static List<Tuple> decompressHelp(String compressed){
        List<Tuple> myDict = new ArrayList<>();

//          \[ — square bracket
//          (\d+) — offset
//          \| — pipe
//          (\d+) —  length
//          \| — pipe
//          (.*?) — next char
//          \] — square bracket

        Pattern pattern = Pattern.compile("\\[(\\d+)\\|(\\d+)\\|(.*?)\\]");
        Matcher matcher = pattern.matcher(compressed);
        while(matcher.find()) {
        String offset = matcher.group(1);
        String length = matcher.group(2);
        String c = matcher.group(3);
        int IOffset = Integer.parseInt(offset);
        int Ilength = Integer.parseInt(length);
        Tuple temp = new Tuple(IOffset,Ilength,c);
        myDict.add(temp);
}
        return myDict;
    }

    public static String decompress(String compressed) {
        // TODO fill this in.
//        System.out.println("this :: "+compressed);
        List<Tuple> myDict = decompressHelp(compressed);
        StringBuilder output= new StringBuilder();
        int cursor = 0;
        for(Tuple t:myDict){
            if(t.offset == 0 && t.length == 0){
                output.append(t.c);
            }else{
//                String temp = ;
//                temp.(t.c);
                output.append(output.substring(cursor-t.offset,(cursor-t.offset)+t.length));
                if(t.c != null){
                    output.append(t.c);
                }
                cursor+=t.length;
            }
            cursor++;
        }
        return String.valueOf(output);
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
