/*
https://www.geeksforgeeks.org/caesar-cipher-in-cryptography/
https://www.geeksforgeeks.org/columnar-transposition-cipher/#:~:text=The%20Columnar%20Transposition%20Cipher%20is,in%20columns%20one%20by%20one.
*/

import java.util.ArrayList;

class decipher {
    public static void main(String args []) {
        System.out.println("first line in main");

        String toDecypher = "KUHPVIBQKVOSHWHXBPOFUXHRPVLLDDWVOSKWPREDDVVIDWQRBHBGLLBBPKQUNRVOHQEIRLWOKKRDD";
        ArrayList<Integer> asciiNums = new ArrayList<Integer> ();
        
        
        ArrayList<Integer> tester = new ArrayList<Integer> ();
        ArrayList<Integer> tester2 = new ArrayList<Integer> ();
        ArrayList<Integer> tester3 = new ArrayList<Integer> ();

        ArrayList<String> charList = new ArrayList<String> ();
        String test = "ABCZ";

        System.out.println("calling the func");
        tester = intToList(4213, 4);


        asciiNums = convertToASCII(toDecypher);

        
        /*tester = convertToASCII(test);
        tester2 = shifter(tester, 1);
        charList = convertToChar(tester2);

        tester3 = unShifter(tester, 1);
        charList = convertToChar(tester3);*/
        
        ArrayList<Integer> shifted = new ArrayList<Integer> ();
        ArrayList<Integer> transpositioned = new ArrayList<Integer> ();


        for (int i = 1; i < 27; i++) {
            shifted = shifter(asciiNums, i); //i is the shift amount
            for (int j = 0; j < shifted.size(); j++) {
                transpositioned = transDec(shifted, j); //j is the key size
            }


            shifted.clear(); //empty the shifted list before shifting again
        }
    }        
    
    static ArrayList<Integer> transDec (ArrayList<Integer> shifted, int keySize) {
        ArrayList<Integer> combinations = new ArrayList<Integer> ();
        ArrayList<Integer> encoded = new ArrayList<Integer> ();
        ArrayList<Integer> splitUp = new ArrayList<Integer> ();


        combinations = findCombinations(keySize);

        if ((shifted.size() % keySize) == 0) {
            encoded = fillWithGarbage(encoded);
            for (int i = 0; i < combinations.size(); i++) {
                int key = combinations.get(i);
                splitUp = intToList(key, keySize);

                for (int k = 0, f = 0; k < shifted.size(); k = k + (shifted.size()/keySize), f++) { //k is the index of the starting character of each block, f keeps track of index of inside the key
                    int posOfSection = splitUp.get(k);

                    for (int j = 0; j < shifted.size()/keySize; j++) { //j is individual index of each block
                        int indexToSet = splitUp.get(f) - 1;
                        encoded.set(indexToSet + j, shifted.get(k + j)); //k + j because thats the block starting index + index inside of the block

                    }
                }
                //test the combination here

            }
        }
        else { //if the string length is not divisible

        }


        return encoded;
    }
    static ArrayList<Integer> fillWithGarbage (ArrayList<Integer> shifted) {
        for (int i = 0; i < shifted.size(); i++) {
            shifted.add(0);
        }
        
        return shifted;
    }

    static ArrayList<Integer> intToList (int keyInt, int keySize) {
        ArrayList<Integer> comboReverse = new ArrayList<Integer> ();
        ArrayList<Integer> combo = new ArrayList<Integer> ();

        int toAdd = 0;

        for (int i = 0; i < keySize; i++) {
            toAdd = keyInt % 10;
            keyInt = keyInt / 10;
            comboReverse.add(toAdd);
        }
        for (int i = keySize - 1; i >= 0; i--) {
            toAdd = comboReverse.get(i);
            combo.add(toAdd);
        }

        return combo;
    }

    static ArrayList<Integer> findCombinations (int keySize) {
        ArrayList<Integer> combinations = new ArrayList<Integer> ();
        ArrayList<Integer> originalKey = new ArrayList<Integer> ();








        return combinations;
    }


    static ArrayList<Integer> shifter (ArrayList<Integer> asciiNums, int shiftBy) {
        ArrayList<Integer> encoded = new ArrayList<Integer> ();

        for (int i = 0; i < asciiNums.size(); i++) {
            int changed = (asciiNums.get(i) + shiftBy) % 26;
            encoded.add(changed);
            //System.out.println(encoded.get(i));
        }
        //System.out.println();

        return encoded;
    }

    static ArrayList<Integer> unShifter (ArrayList<Integer> asciiNums, int shiftBy) {
        ArrayList<Integer> encoded = new ArrayList<Integer> ();

        for (int i = 0; i < asciiNums.size(); i++) {
            int changed = (asciiNums.get(i) - shiftBy) % 26;
            changed = changed + shiftBy; //the unshifted version was always off by the shiftBy amount...still not sure why
            encoded.add(changed);
            //System.out.println(encoded.get(i));
        }
        //System.out.println();

        return encoded;
    }

    static ArrayList<Integer> convertToASCII(String sentence) {
        ArrayList<Integer> asciiCodes = new ArrayList<Integer> ();

        for (int i = 0; i < sentence.length(); i++) {
            int toAdd = (int) sentence.charAt(i);
            toAdd = toAdd - 64; //to make it 1-26
            asciiCodes.add(toAdd);
            //System.out.println(asciiCodes.get(i));
        }
        //System.out.println();
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
            //System.out.println(charList.get(i));
        }
        //System.out.println();

        return charList;
    }
}