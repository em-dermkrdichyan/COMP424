import java.util.ArrayList;

class decipher {
    public static void main(String args []) {
        String toDecypher = "KUHPVIBQKVOSHWHXBPOFUXHRPVLLDDWVOSKWPREDDVVIDWQRBHBGLLBBPKQUNRVOHQEIRLWOKKRDD";
        ArrayList<Integer> asciiNums = new ArrayList<Integer> ();
        
        
        ArrayList<Integer> tester = new ArrayList<Integer> ();
        ArrayList<Integer> tester2 = new ArrayList<Integer> ();
        ArrayList<Integer> tester3 = new ArrayList<Integer> ();

        ArrayList<String> charList = new ArrayList<String> ();
        String test = "ABCZ";

        //columnar transposition
        //simple shift substitution
        //asciiNums = convertToASCII(toDecypher);

        
        tester = convertToASCII(test);
        tester2 = shifter(tester, 1);
        charList = convertToChar(tester2);

        tester3 = unShifter(tester, 1);
        charList = convertToChar(tester3);
        
        ArrayList<Integer> shifted = new ArrayList<Integer> ();

        for (int i = 1; i < 27; i++) {
            shifted = shifter(asciiNums, i);
            for (int j = 0; j < shifted.size(); j++) {
                //column method
            }
            shifted.clear(); //empty the shifted list before shifting again
        }
    }

    static ArrayList<Integer> shifter (ArrayList<Integer> asciiNums, int shiftBy) {
        ArrayList<Integer> encoded = new ArrayList<Integer> ();

        for (int i = 0; i < asciiNums.size(); i++) {
            int changed = (asciiNums.get(i) + shiftBy) % 26;
            encoded.add(changed);
            System.out.println(encoded.get(i));
        }
        System.out.println();

        return encoded;
    }

    static ArrayList<Integer> unShifter (ArrayList<Integer> asciiNums, int shiftBy) {
        ArrayList<Integer> encoded = new ArrayList<Integer> ();

        for (int i = 0; i < asciiNums.size(); i++) {
            int changed = (asciiNums.get(i) - shiftBy) % 26;
            changed = changed + shiftBy; //the unshifted version was always off by the shiftBy amount...still not sure why
            encoded.add(changed);
            System.out.println(encoded.get(i));
        }
        System.out.println();

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
        System.out.println();
        //this is a test comment
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
        System.out.println();

        return charList;
    }
}