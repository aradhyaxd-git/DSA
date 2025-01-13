
/* TO ADD PLUS ONE , what have i done is
1) store things in string Builder form 
2) convert to long and add one
3) restore to String
4) extract each character , inorder to store in numeric form
    use reslultSt.charAt(i)-'0';
*/
class Solution {
    public int[] plusOne(int[] digits) {
        StringBuilder s= new StringBuilder();
        int n=digits.length;
        for(int i=0;i<n;i++){
            s.append(digits[i]);
        }
        long l= Long.parseLong(s.toString());
        l=l+1;
        String resultStr = String.valueOf(l);
        int[] result = new int[resultStr.length()];
        for (int i = 0; i < resultStr.length(); i++) {
            result[i] = resultStr.charAt(i) - '0';
        }
        return result;
    }
}