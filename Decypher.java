/*
sources
https://www.geeksforgeeks.org/caesar-cipher-in-cryptography/
https://www.geeksforgeeks.org/columnar-transposition-cipher/#:~:text=The%20Columnar%20Transposition%20Cipher%20is,in%20columns%20one%20by%20one.
https://github.com/dwyl/english-words
https://www.programcreek.com/2013/02/leetcode-permutations-java/
*/


import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class decypher {
    public static void main(String args []) {
        String toDecypher = "KUHPVIBQKVOSHWHXBPOFUXHRPVLLDDWVOSKWPREDDVVIDWQRBHBGLLBBPKQUNRVOHQEIRLWOKKRDD";
        ArrayList<Integer> asciiNums = new ArrayList<Integer> ();

        asciiNums = convertToASCII(toDecypher); //converted the string to an arraylist of ascii numbers

        ArrayList<Integer> shifted = new ArrayList<Integer> ();

        for (int i = 1; i < 27; i++) { //this shifts the ascii numbers from 1-26 times
            shifted = unShifter(asciiNums, i); //i is the shift amount; shifted works; changed from shifter to unshifter

            for (int j = 2; j < 10; j++) { //this is the key sizes so 2-10
                transDec(shifted, j); //j is the key size
            }

            shifted.clear();
        }
    }
    
    static int findWords (ArrayList<Integer> transpositioned) {
        int counter = 0;
        String sentence = listToString(transpositioned); //convert the arraylist of ascii numbers to a string
        File dictionary = new File("wordsFile.txt"); //the dictionary file we are reading from
        try (Scanner filesc = new Scanner(dictionary)) {
            while(filesc.hasNext()){
                String word = filesc.nextLine().toUpperCase().toString(); //converting to uppercase then checking the string using .contains()
                if (sentence.contains(word)) {
                    counter++;
                }
            }
            filesc.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }

    static String listToString (ArrayList<Integer> transpositioned) {
        StringBuilder sb = new StringBuilder();
        String theSentence = "";
        ArrayList<String> tranStr = new ArrayList<String> ();

        tranStr = convertToChar(transpositioned); //first we have to convert to an arraylist of string then we can change it to a string

        for (int i = 0; i < transpositioned.size(); i++) { 
            String temp = tranStr.get(i).toString();
            sb.append(temp); //we add the strings from the arraylist to the string builder
        }
        theSentence = sb.toString(); //then we convert it to a string

        return theSentence;
    }

    
    static void transDec (ArrayList<Integer> shifted, int keySize) {
        ArrayList<ArrayList<Integer>> combinations = new ArrayList<ArrayList<Integer>> ();

        int blockSize = shifted.size() / keySize; //this is how we figure out how many numbers each block holds
        int highest = 0;
        int highestHigh = 0;

        if ((shifted.size() % keySize) == 0) { //i assumed that the string would be evenly divisible by the keysize because otherwise we would have to buff

            combinations = findCombinations(keySize); //this finds every possible permutation for an arrat [1,2,3,...,n] where n is the key size

            ArrayList<ArrayList<Integer>> blocks = new ArrayList<ArrayList<Integer>> (); //arraylist of blocks
            ArrayList<ArrayList<Integer>> finishedBlocks = new ArrayList<ArrayList<Integer>> ();
            ArrayList<ArrayList<Integer>> midWay = new ArrayList<ArrayList<Integer>> ();

            ArrayList<Integer> aBlock = new ArrayList<Integer> ();
            ArrayList<Integer> aBlock1 = new ArrayList<Integer> ();
            ArrayList<Integer> aBlock2 = new ArrayList<Integer> ();
            ArrayList<Integer> aBlock3 = new ArrayList<Integer> ();
            ArrayList<Integer> aBlock4 = new ArrayList<Integer> ();
            ArrayList<Integer> aBlock5 = new ArrayList<Integer> ();
            ArrayList<Integer> aBlock6 = new ArrayList<Integer> ();
            ArrayList<Integer> aBlock7 = new ArrayList<Integer> ();
    
            ArrayList<Integer> highestCombo = new ArrayList<Integer> ();
            ArrayList<Integer> highestInt = new ArrayList<Integer> ();
    
    
    
            ArrayList<String> backToChar = new ArrayList<String> ();
            ArrayList<Integer> thisComb = new ArrayList<Integer> ();
            ArrayList<Integer> finishedList = new ArrayList<Integer> ();
            //System.out.println(combinations.size());
            //System.exit(0);

            aBlock = fillWithGarbage(aBlock, blockSize); 
            aBlock1 = fillWithGarbage(aBlock1, blockSize); //each one of these is a block
            aBlock2 = fillWithGarbage(aBlock2, blockSize); //I specifically did 7 because I am assuming the
            aBlock3 = fillWithGarbage(aBlock3, blockSize); //key size is 7
            aBlock4 = fillWithGarbage(aBlock4, blockSize); 
            aBlock5 = fillWithGarbage(aBlock5, blockSize); 
            aBlock6 = fillWithGarbage(aBlock6, blockSize); 
            aBlock7 = fillWithGarbage(aBlock7, blockSize); 

            finishedList = fillWithGarbage(finishedList, shifted.size()); //fill the arraylist with 0s to initialize the arraylist
            highestCombo = fillWithGarbage(highestCombo, keySize);
            //int blockIndex = 0;

            for (int blockSetter = 0; blockSetter < keySize; blockSetter++) { //initializing the arraylist of arraylists
                blocks.add(new ArrayList<Integer>());
                finishedBlocks.add(new ArrayList<Integer>());
                midWay.add(new ArrayList<Integer>());
            }

            for (int l = 0; l < shifted.size(); l++) {
                System.out.print(" " + shifted.get(l)); //printing the shifted arraylist before transpositioning
            }

            for (int combInd = 0, index = 0; combInd < combinations.size(); combInd++) {

                thisComb = combinations.get(combInd); //pulling one of the keys out to use for the transposition

                //these for loops individually fill each block with integers from the shifted list
                for (int i = 0; i < blockSize; i++, index++) {  
                    aBlock1.set(i, shifted.get(index));
                }

                for (int i = 0; i < blockSize; i++, index++) {  
                    aBlock2.set(i, shifted.get(index));
                }

                for (int i = 0; i < blockSize; i++, index++) {  
                    aBlock3.set(i, shifted.get(index));
                }

                for (int i = 0; i < blockSize; i++, index++) {  
                    aBlock4.set(i, shifted.get(index));
                }

                for (int i = 0; i < blockSize; i++, index++) {  
                    aBlock5.set(i, shifted.get(index));
                }

                for (int i = 0; i < blockSize; i++, index++) {  
                    aBlock6.set(i, shifted.get(index));
                }

                for (int i = 0; i < blockSize; i++, index++) {  
                    aBlock7.set(i, shifted.get(index));
                }

                //we get the indices from the key
                int ind1 = thisComb.get(0);
                int ind2 = thisComb.get(1);
                int ind3 = thisComb.get(2);
                int ind4 = thisComb.get(3);
                int ind5 = thisComb.get(4);
                int ind6 = thisComb.get(5);
                int ind7 = thisComb.get(6);

                //we move each block to their new index
                blocks.set(ind1 - 1, aBlock1);
                blocks.set(ind2 - 1, aBlock2);
                blocks.set(ind3 - 1, aBlock3);
                blocks.set(ind4 - 1, aBlock4);
                blocks.set(ind5 - 1, aBlock5);
                blocks.set(ind6 - 1, aBlock6);
                blocks.set(ind7 - 1, aBlock7);


                //once the blocks are in their new index we read them left to right like columns and move down one row
                //once each column has given 1 number and add them to a new list called finishedList
                for (int down = 0, totInd = 0; down < aBlock1.size(); down++) {
                    for (int side = 0; side < blocks.size(); side++, totInd++) {
                        finishedList.set(totInd, blocks.get(side).get(down));
                    }
                }
                
                //once the list is filled we send it to findWords to find the number of matches of words from the dicionary
                int counter = findWords(finishedList); 

                //this keeps track of what the highest number of matches is for each combination
                if (counter > highest) {
                    highest = counter;
                }

                index = 0;

                //if the matches is the highest of the combinations then we print out the data and the string
                if (highest > highestHigh) {
                    System.out.println("\n------------------------------------------------------------------------\n");
                    ArrayList<String> highestChar = new ArrayList<String> ();
    
                    highestHigh = highest;
                    highestCombo = thisComb;
                    highestInt = finishedList;
                    highestChar = convertToChar(highestInt);
                    System.out.print("highest counter: " + highest + "\nthe highest high: " + highestHigh + "\nthe key: " + highestCombo + "\nactual string: " + highestChar + "\n");
                    System.out.println("\n------------------------------------------------------------------------\n");

                }


            }

            
        }
        else { //if the string length is not divisible so we just skip

        }

    }

    //used to initialize arraylists
    static ArrayList<Integer> fillWithGarbage (ArrayList<Integer> shifted, int size) {
        for (int i = 0; i < size; i++) {
            shifted.add(0);
        }
        
        return shifted;
    }

    static ArrayList<ArrayList<Integer>> findCombinations (int keySize) {
        //ArrayList<Integer> originalKey = new ArrayList<Integer> ();
        int[] originalKey = {0,0,0,0,0,0,0,0,0,0};

        for (int i = 1; i <= keySize; i++) {
            originalKey[i - 1] = i; //fills the array with i numbers, i being the key size
        }

        //we call the permutation function that return the permutations of the given arraylist
        ArrayList<ArrayList<Integer>> permutations = permute(originalKey, keySize);

        return permutations;
    }

    //finds all possible permutations of a given array
    public static ArrayList<ArrayList<Integer>> permute(int[] num, int size) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();


        result.add(new ArrayList<Integer>());
        for (int i = 0; i < size; i++) {
            ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();

            for (ArrayList<Integer> l : result) {
                for (int j = 0; j < l.size()+1; j++) {
                    l.add(j, num[i]);
                    ArrayList<Integer> temp = new ArrayList<Integer>(l);
                    current.add(temp);

                    l.remove(j);
                }
            }
            result = new ArrayList<ArrayList<Integer>>(current);
        }
        return result;
        }


    //this method shifts the ascii numbers by a given amount called shiftBy
    static ArrayList<Integer> shifter (ArrayList<Integer> asciiNums, int shiftBy) {
        ArrayList<Integer> encoded = new ArrayList<Integer> ();

        for (int i = 0; i < asciiNums.size(); i++) {
            int changed = (asciiNums.get(i) + shiftBy) % 26;
            encoded.add(changed);
        }
        return encoded;
    }

    //This does the opposite of the shifter method, I made both of them to be able to shift
    //back and forth and test the project
    static ArrayList<Integer> unShifter (ArrayList<Integer> asciiNums, int shiftBy) {
        ArrayList<Integer> encoded = new ArrayList<Integer> ();

        for (int i = 0; i < asciiNums.size(); i++) {
            int changed = (asciiNums.get(i) - shiftBy) % 26;
            changed = changed + shiftBy; //the unshifted version was always off by the shiftBy amount...still not sure why
            encoded.add(changed);
        }

        return encoded;
    }

    //converts a given string to an arraylist of integers where it is filled with the ascii values
    static ArrayList<Integer> convertToASCII(String sentence) {
        ArrayList<Integer> asciiCodes = new ArrayList<Integer> ();

        for (int i = 0; i < sentence.length(); i++) {
            int toAdd = (int) sentence.charAt(i);
            toAdd = toAdd - 64; //to make it 1-26, 64 because ascii values for capital and lower case are off by 64
            asciiCodes.add(toAdd);
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
        }

        return charList;
    }
}