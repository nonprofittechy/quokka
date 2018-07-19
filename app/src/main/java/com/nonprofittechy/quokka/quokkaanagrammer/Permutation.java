package com.nonprofittechy.quokka.quokkaanagrammer;

import java.util.HashSet;

// Java program to print all permutations of a
// given string.
public class Permutation
{
    /*
    public static void main(String[] args)
    {
        String str = "ABC";
        int n = str.length();
        Permutation permutation = new Permutation();
        permutation.permute(str, 0, n-1, HashSet<String> res);
    }
    */

    /**
     * permutation function
     * @param str string to calculate permutation for
     * @param l starting index
     * @param r end index
     */
    public HashSet<String> permute(String str, int l, int r, HashSet res)
    {
        if (l == r)
            res.add(str);
            // System.out.println(str);
        else
        {
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                permute(str, l+1, r, res);
                str = swap(str,l,i);
            }
        }

        return res;
    }

    /**
     * Swap Characters at position
     * @param a string value
     * @param i position 1
     * @param j position 2
     * @return swapped string
     */
    public String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

}

// This code is contributed by Mihir Joshi

