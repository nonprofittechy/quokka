package com.nonprofittechy.quokka.quokkaanagrammer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class GADDAG extends Trie {
    private static char separator = '>';

    public String wordlistName;

    public GADDAG(){ root = new Node(Node.root); }

    public String getWordlistName() {
        return wordlistName;
    }

    public void setWordlistName(String name) {
        wordlistName = name;
    }

    @Override
    public void add(String word){
        if (word.length() == 0) return;

        word = word.toLowerCase();

        String prefix;
        char[] ch;
        int i;
        for (i = 1; i < word.length(); i++){
            prefix = word.substring(0,i);
            ch = prefix.toCharArray();
            reverse(ch);
            super.add(new String(ch) + separator + word.substring(i));
        }
        ch = word.toCharArray();
        reverse(ch);
        super.add(new String(ch) + separator + word.substring(i));
    }

    private void reverse(char[] validData){
        for(int i = 0; i < validData.length/2; i++)
        {
            int temp = validData[i];
            validData[i] = validData[validData.length - i - 1];
            validData[validData.length - i - 1] = (char)temp;
        }
    }

    /**
     * Return acceptable words using characters in the provided String
     * @param s String to find angrams of. May be mixed case
     * @return HashSet of valid words composed of one or more letters form the String
     */
    public HashSet<String> findWordsFromString(String s) {
        Character[] ch = toCharacterArray(s.toUpperCase());

        return findWordsWithRackAndHook(ch, ' ');
    }

    public HashSet<String> findWordsFromString(String s, int min_length, int max_length) {
        Character[] ch = toCharacterArray(s.toUpperCase());

        return findWordsWithRackAndHook(ch, ' ', min_length, max_length);
    }

    /**
     *
     * @param rack Should be all uppercase letters
     * @param hook
     * @return Unique list of acceptable words using the letters in the provided rack
     */
    public HashSet<String> findWordsWithRackAndHook(Character[] rack, char hook){
        HashSet<String> words = new HashSet<String>();
        Arrays.sort(rack);
        ArrayList<Character> rackList = new ArrayList<Character>(Arrays.asList(rack));

        if (hook == ' '){
            char tile;
            while (rackList.size() > 1){
                tile = rackList.remove(0);
                findWordsRecurse(words, "",  rackList, tile, root, true);
            }
        } else {
            findWordsRecurse(words, "", rackList,  hook, root, true);
        }
        return words;
    }

    /**
     *
     * @param rack Should be all uppercase letters
     * @param hook
     * @param min_length minimum word length
     * @param max_length maximum word length
     * @return Unique list of acceptable words using the letters in the provided rack
     */
    public HashSet<String> findWordsWithRackAndHook(Character[] rack, char hook, int min_length, int max_length){
        HashSet<String> words = new HashSet<String>();
        Arrays.sort(rack);
        ArrayList<Character> rackList = new ArrayList<Character>(Arrays.asList(rack));

        if (hook == ' '){
            char tile;
            while (rackList.size() > 1){
                tile = rackList.remove(0);
                findWordsRecurse(words, "",  rackList, tile, root, true, min_length, max_length);
            }
        } else {
            findWordsRecurse(words, "", rackList,  hook, root, true, min_length, max_length);
        }
        return words;
    }

    private void findWordsRecurse(HashSet<String> words, String word, ArrayList<Character> rack, char hook, Node cur, boolean direction, int min_length, int max_length) {
        Node hookNode = cur.getChild(hook);

        //Base case
        if (hookNode == null)
            return;

        String hookCh = hook == separator ? "" : String.valueOf(hook); //Empty character if we're the separator
        word = (direction ? hookCh + word : word + hookCh); //Direction-based concatenation

        int size = word.length();

        //if we've reached the end a word, add the word to output
        if (hookNode.getFinite() && size >= min_length && size <= max_length) {
            words.add(word);
        }

        for (char nodeKey : hookNode.getKeys()) {
            if (nodeKey == separator)
                findWordsRecurse(words, word, rack, separator, hookNode, false, min_length, max_length);
            else if (rack.contains(nodeKey)){
                //boolean duplicate = (rack.size() > 0 && (rack.get(nodeKey) == rack.get(rack.indexOf(nodeKey) - 1)));
                ArrayList<Character> newRack = (ArrayList<Character>) rack.clone();
                newRack.remove((Character)nodeKey);
                findWordsRecurse(words, word, newRack, nodeKey, hookNode, direction, min_length, max_length);
            }
        }
    }

    private void findWordsRecurse(HashSet<String> words, String word, ArrayList<Character> rack, char hook, Node cur, boolean direction){
        Node hookNode = cur.getChild(hook);

        //Base case
        if (hookNode == null)
            return;

        String hookCh = hook == separator ? "" : String.valueOf(hook); //Empty character if we're the separator
        word = (direction ? hookCh + word : word + hookCh); //Direction-based concatenation

        //if we've reached the end a word, add the word to output
        if (hookNode.getFinite())
            words.add(word);

        for (char nodeKey : hookNode.getKeys()) {
            if (nodeKey == separator)
                findWordsRecurse(words, word, rack, separator, hookNode, false);
            else if (rack.contains(nodeKey)){
                    //boolean duplicate = (rack.size() > 0 && (rack.get(nodeKey) == rack.get(rack.indexOf(nodeKey) - 1)));
                    ArrayList<Character> newRack = (ArrayList<Character>) rack.clone();
                    newRack.remove((Character)nodeKey);
                    findWordsRecurse(words, word, newRack, nodeKey, hookNode, direction);
                }
        }
    }

    /**
     * @param sourceString
     *            :String as argument
     * @return CharcterArray
     */
    private static Character[] toCharacterArray(String sourceString) {
        char[] charArrays = new char[sourceString.length()];
        charArrays = sourceString.toCharArray();
        Character[] characterArray = new Character[charArrays.length];
        for (int i = 0; i < charArrays.length; i++) {
            characterArray[i] = charArrays[i];
        }
        return characterArray;
    }

}
