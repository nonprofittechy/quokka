package com.nonprofittechy.quokka.quokkaanagrammer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// Java program to return all unique permutations of a
// given string.
public class Permutation
{
    private ArrayList<String> anagrams = new ArrayList<String>();

    /**
     * Generate an ArrayList of permutations of a given string
     * @param str String to anagram
     * @return list of unique permutations of the string
     */
    public ArrayList<String> permute(String str) {
        this.anagrams.clear();

        char[] chars = str.toCharArray();

        Arrays.sort(chars);

        do {
            this.anagrams.add(String.valueOf(chars));
        } while (nextPermutation(chars)); // Java passes array by reference

        return anagrams;
    }

    // See: https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
    private boolean nextPermutation(char[] array) {
        // Find longest non-increasing suffix
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i])
            i--;
        // Now i is the head index of the suffix

        // Are we at the last permutation already?
        if (i <= 0)
            return false;

        // Let array[i - 1] be the pivot
        // Find rightmost element that exceeds the pivot
        int j = array.length - 1;
        while (array[j] <= array[i - 1])
            j--;
        // Now the value array[j] will become the new pivot
        // Assertion: j >= i

        // Swap the pivot with j
        char temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        // Reverse the suffix
        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        // Successfully computed the next permutation
        return true;
    }
}