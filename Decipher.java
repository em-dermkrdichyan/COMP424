/*
https://www.geeksforgeeks.org/caesar-cipher-in-cryptography/
https://www.geeksforgeeks.org/columnar-transposition-cipher/#:~:text=The%20Columnar%20Transposition%20Cipher%20is,in%20columns%20one%20by%20one.
https://github.com/dwyl/english-words
https://www.programcreek.com/2013/02/leetcode-permutations-java/
*/


//the key is 7315426

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class decipher {
    public static void main(String args []) {
        String toDecypher = "KUHPVIBQKVOSHWHXBPOFUXHRPVLLDDWVOSKWPREDDVVIDWQRBHBGLLBBPKQUNRVOHQEIRLWOKKRDD";
        ArrayList<Integer> asciiNums = new ArrayList<Integer> ();

        asciiNums = convertToASCII(toDecypher); //asciiNums works

        ArrayList<Integer> shifted = new ArrayList<Integer> ();
        ArrayList<Integer> transpositioned = new ArrayList<Integer> ();
        //int matchCount = 0;
        //ArrayList<String> backToChar = new ArrayList<String> ();

        //int counter = 0;

        for (int i = 23; i < 27; i++) { //changing i to 23
            //counter = 0; //reseting the word counter
            shifted = unShifter(asciiNums, i); //i is the shift amount; shifted works; changed from shifter to unshifter

            for (int j = 7; j < 10; j++) { //change 7 to 2
                transpositioned = transDec(shifted, j); //j is the key size
                /*for (int p = 0; p < transpositioned.size(); p++) {
                    System.out.println(transpositioned.get(p));
                }*/

            }
            System.out.println("left the key");

            shifted.clear(); //empty the shifted list before shifting again
        }
    }
    
    static int findWords (ArrayList<Integer> transpositioned) {
        int counter = 0;
        String sentence = listToString(transpositioned);
        File dictionary = new File("wordsFile.txt");
        try (Scanner filesc = new Scanner(dictionary)) {
            while(filesc.hasNext()){
                String word = filesc.nextLine().toUpperCase().toString();
                if (sentence.contains(word)) {
                    counter++;
                    //System.out.println(word);
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

        tranStr = convertToChar(transpositioned);
        /*
        for (int x = 0; x < tranStr.size(); x++) {
            System.out.println(x + " " + tranStr.get(x));
            System.out.println(x + " " + transpositioned.get(x));

        }*/

        for (int i = 0; i < transpositioned.size(); i++) { 
            String temp = tranStr.get(i).toString();
            sb.append(temp);
        }
        theSentence = sb.toString();
        //System.out.println("the string111: " + theSentence);

        return theSentence;
    }

    
    static ArrayList<Integer> transDec (ArrayList<Integer> shifted, int keySize) {
        ArrayList<ArrayList<Integer>> combinations = new ArrayList<ArrayList<Integer>> ();
        ArrayList<ArrayList<Integer>> blocks = new ArrayList<ArrayList<Integer>> ();
        ArrayList<ArrayList<Integer>> finishedBlocks = new ArrayList<ArrayList<Integer>> ();
        ArrayList<ArrayList<Integer>> midWay = new ArrayList<ArrayList<Integer>> ();

        int blockSize = shifted.size() / keySize;
        int highest = 0;
        int temp = 0;
        int highestHigh = 0;

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
        ArrayList<String> highestChar = new ArrayList<String> ();



        ArrayList<String> backToChar = new ArrayList<String> ();

        ArrayList<Integer> thisComb = new ArrayList<Integer> ();

        ArrayList<Integer> finishedList = new ArrayList<Integer> ();

        combinations = findCombinations(keySize);

        if ((shifted.size() % keySize) == 0) {
            //System.out.println(combinations.size());
            //System.exit(0);

            aBlock = fillWithGarbage(aBlock, blockSize); //change this to the actual size
            aBlock1 = fillWithGarbage(aBlock1, blockSize); //change this to the actual size
            aBlock2 = fillWithGarbage(aBlock2, blockSize); //change this to the actual size
            aBlock3 = fillWithGarbage(aBlock3, blockSize); //change this to the actual size
            aBlock4 = fillWithGarbage(aBlock4, blockSize); //change this to the actual size
            aBlock5 = fillWithGarbage(aBlock5, blockSize); //change this to the actual size
            aBlock6 = fillWithGarbage(aBlock6, blockSize); //change this to the actual size
            aBlock7 = fillWithGarbage(aBlock7, blockSize); //change this to the actual size

            finishedList = fillWithGarbage(finishedList, shifted.size());
            highestCombo = fillWithGarbage(highestCombo, keySize);
            int blockIndex = 0;

            for (int blockSetter = 0; blockSetter < keySize; blockSetter++) {
                blocks.add(new ArrayList<Integer>());
                finishedBlocks.add(new ArrayList<Integer>());
                midWay.add(new ArrayList<Integer>());
            }
            //System.out.println("blocl: " + blocks);

            //System.out.println("blockSize: " + blocks.size());
            for (int l = 0; l < shifted.size(); l++) {
                System.out.print(" " + shifted.get(l));
            }
            //System.out.println("\n");
            for (int combInd = 0, index = 0; combInd < combinations.size(); combInd++) {
                //System.out.print("got here index " + combInd);

                thisComb = combinations.get(combInd);

                //System.out.println("got here5");


                for (int i = 0; i < blockSize; i++, index++) {  
                    aBlock1.set(i, shifted.get(index));
                    //blocks.set(outer, aBlock);
                }
                //System.out.println("got here6");

                //System.out.println("block: " + aBlock1);

                for (int i = 0; i < blockSize; i++, index++) {  
                    aBlock2.set(i, shifted.get(index));
                    //blocks.set(outer, aBlock);
                }
                //System.out.println("block: " + aBlock2);

                for (int i = 0; i < blockSize; i++, index++) {  
                    aBlock3.set(i, shifted.get(index));
                    //blocks.set(outer, aBlock);
                }
                //System.out.println("block: " + aBlock3);

                for (int i = 0; i < blockSize; i++, index++) {  
                    aBlock4.set(i, shifted.get(index));
                    //blocks.set(outer, aBlock);
                }
                //System.out.println("block: " + aBlock4);

                for (int i = 0; i < blockSize; i++, index++) {  
                    aBlock5.set(i, shifted.get(index));
                    //blocks.set(outer, aBlock);
                }
                //System.out.println("block: " + aBlock5);

                for (int i = 0; i < blockSize; i++, index++) {  
                    aBlock6.set(i, shifted.get(index));
                    //blocks.set(outer, aBlock);
                }
                //System.out.println("block: " + aBlock6);

                for (int i = 0; i < blockSize; i++, index++) {  
                    aBlock7.set(i, shifted.get(index));
                    //blocks.set(outer, aBlock);
                }
                //System.out.println("block: " + aBlock7);

                //System.out.println("block again : " + aBlock1);
                int ind1 = thisComb.get(0);
                int ind2 = thisComb.get(1);
                int ind3 = thisComb.get(2);
                int ind4 = thisComb.get(3);
                int ind5 = thisComb.get(4);
                int ind6 = thisComb.get(5);
                int ind7 = thisComb.get(6);

                blocks.set(ind1 - 1, aBlock1);
                blocks.set(ind2 - 1, aBlock2);
                blocks.set(ind3 - 1, aBlock3);
                blocks.set(ind4 - 1, aBlock4);
                blocks.set(ind5 - 1, aBlock5);
                blocks.set(ind6 - 1, aBlock6);
                blocks.set(ind7 - 1, aBlock7);


                //System.out.println(thisComb);
                //System.out.println(blocks);

                for (int down = 0, totInd = 0; down < aBlock1.size(); down++) {
                    for (int side = 0; side < blocks.size(); side++, totInd++) {
                        finishedList.set(totInd, blocks.get(side).get(down));
                    }
                }
//////////////////////////////////////////////////////////////////////////////////////////////////
                //System.out.println("start " + finishedList + "comb ind: " + combInd);
//////////////////////////////////////////////////////////////////////////////////////////////////
                int counter = findWords(finishedList);

                //System.out.println("got here");
                System.out.print("/ " + counter + " /");
                if (counter > highest) {
                    highest = counter;
                }
                if (counter > 15) {
                    backToChar = convertToChar(finishedList);
                    //System.out.println("got here2");

                    System.out.println("The number of matches: " + counter);
                    System.out.println("The String: ");

                    for (int k = 0; k < backToChar.size(); k++) {
                        System.out.print(backToChar.get(k));
                    }
                    //System.out.println("got here3");

                    //j = shifted.size();
                }

                index = 0;


            }
            if (highest > highestHigh) {
                highestHigh = highest;
                highestCombo = thisComb;
                highestInt = finishedList;
                highestChar = convertToChar(highestInt);

            }
            System.out.print("highest counter: " + highest + "/\n the highest high: " + highestHigh + "\nthe key: " + highestCombo + "\nactual string: " + highestChar);

        }
        else { //if the string length is not divisible

        }


        return finishedList;
    }

    static ArrayList<Integer> fillWithGarbage (ArrayList<Integer> shifted, int size) {
        for (int i = 0; i < size; i++) {
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

    static ArrayList<ArrayList<Integer>> findCombinations (int keySize) {
        //ArrayList<Integer> originalKey = new ArrayList<Integer> ();
        int[] originalKey = {0,0,0,0,0,0,0,0,0,0};

        for (int i = 1; i <= keySize; i++) {
            originalKey[i - 1] = i;
        }

        ArrayList<ArrayList<Integer>> permutations = permute(originalKey, keySize);
        /*for (int i = 0; i < permutations.size(); i++) {
            System.out.println(permutations.get(i));
        }*/

        return permutations;
    }

    public static ArrayList<ArrayList<Integer>> permute(int[] num, int size) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        //start from an empty list
        result.add(new ArrayList<Integer>());
        for (int i = 0; i < size; i++) {
        //list of list in current iteration of the array num
            ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();
            for (ArrayList<Integer> l : result) {
            // # of locations to insert is largest index + 1
                for (int j = 0; j < l.size()+1; j++) {
                    // + add num[i] to different locations
                    l.add(j, num[i]);
                    ArrayList<Integer> temp = new ArrayList<Integer>(l);
                    current.add(temp);
                    //System.out.println(temp);
                    // - remove num[i] add
                    l.remove(j);
                }
            }
            result = new ArrayList<ArrayList<Integer>>(current);
        }
        return result;
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