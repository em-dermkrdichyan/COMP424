import java.util.ArrayList;

class decipher {
    public static void main(String args []) {
        String toDecypher = "KUHPVIBQKVOSHWHXBPOFUXHRPVLLDDWVOSKWPREDDVVIDWQRBHBGLLBBPKQUNRVOHQEIRLWOKKRDD";
        ArrayList<Integer> asciiNums = new ArrayList<Integer> ();
        ArrayList<Integer> tester = new ArrayList<Integer> ();
        ArrayList<Integer> tester2 = new ArrayList<Integer> ();

        ArrayList<String> charList = new ArrayList<String> ();
        String test = "ABCZ";

        //columnar transposition
        //simple shift substitution
        //asciiNums = convertToASCII(toDecypher);
        //charList = convertToChar(asciiNums);

        tester = convertToASCII(test);
        tester2 = encoder(tester, 1);

        /*String shiftedBack = "";
        int shiftNum;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < toDecypher.length(); j++) {
                shiftNum = ( + );
                shiftedBack = shiftedBack.concat("");
            }
        }*/
    }

    static ArrayList<Integer> encoder (ArrayList<Integer> asciiNums, int shiftBy) {
        ArrayList<Integer> encoded = new ArrayList<Integer> ();

        for (int i = 0; i < asciiNums.size(); i++) {
            int changed = (asciiNums.get(i) + shiftBy) % 26;
            encoded.add(changed);
            System.out.println(encoded.get(i));
        }

        return encoded;
    }

    static ArrayList<Integer> convertToASCII(String sentence) {
        ArrayList<Integer> asciiCodes = new ArrayList<Integer> ();

        for (int i = 0; i < sentence.length(); i++) {
            int toAdd = (int) sentence.charAt(i);
            toAdd = toAdd - 64; //to make it 1-26
            asciiCodes.add(toAdd);
            System.out.println(asciiCodes.get(i));
        }
        
        return asciiCodes;
    }

    static ArrayList<String> convertToChar(ArrayList<Integer> asciiNums) {
        ArrayList<String> charList = new ArrayList<String> ();

        for (int i = 0; i < asciiNums.size(); i++) {
            int temp = asciiNums.get(i);
            temp = temp + 64; //to go back from 1-26 to 65-90
            char toAdd = (char) temp;
            charList.add(String.valueOf(toAdd));
            System.out.println(charList.get(i));
        }
        return charList;
    }
}