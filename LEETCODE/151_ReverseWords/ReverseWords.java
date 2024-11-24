import java.util.Arrays;

class Solution {
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+"); // This will handle multiple spaces between words

        int start= 0;
        int end= words.length-1;
        while (start < end) {
            String temp = words[start];
            words[start] = words[end];
            words[end] = temp;
            start++;
            end--;
        }
        
        // Join the reversed words with a space separator
        return String.join(" ", words);
    }
}