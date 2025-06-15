/* 1432. Max Difference You Can Get From Changing an Integer

You are given an integer num. You will apply the following steps to num two separate times:

Pick a digit x (0 <= x <= 9).
Pick another digit y (0 <= y <= 9). Note y can be equal to x.
Replace all the occurrences of x in the decimal representation of num by y.
Let a and b be the two results from applying the operation to num independently.

Return the max difference between a and b.

Note that neither a nor b may have any leading zeros, and must not be 0*/

class Solution {
    public int maxDiff(int num) {
        //Step 1:
            //sabse phele maximum karo --> maximum tabhi hoga jab number ko 99999 wala bana do 
       char original[]= String.valueOf(num).toCharArray();
       char x[]= original.clone();
        char maxToReplace=0;
        for(char ch: original){
            //phela character dhundho jo 9 na ho --> 9 hai to wo already max hai na is wajh se
            if(ch!='9'){
                maxToReplace=ch;
                break;
            }
        }
        //now make the maxixmum out of it
        for(int i=0;i<x.length;i++){
            if(x[i]==maxToReplace){
                x[i]='9';
            }
        }

        String maxi= new String(x);

        //now step 2: minimum banao --> but ismei thoda sa jhol hai

        char y[]= original.clone();
        char minToReplace= y[0]; //sbse phela most siginifacnt digit ko replace karne ke liye dekh rhe hai
        char replaceWith='1'; //default minimum digit 1 lia hai, taaki number humesha minimise hojaye

        //agar phela digit mera 1 nahi hai, to usko 1 se replace karna safe hai kyuki hum logo ko leading zeroes nahi banane hai kahi bhi 
        if(y[0]!='1'){
            replaceWith='1'; //1 bana do first digit ko     
        }
        else{
            //lekin agar phela digit 1 hai already
                // usko 0 se replace nahi kar sakte hai ( as we dont want any leading zeroes ) 

                    //---> in this case, we will consider the digits aage ki jisko 0 se replace kar sake 
            for(int i=1;i<y.length;i++){
                //aisi koi digit dhundho jo 0 ya 1 nahi ho , taaki use safely 0 se replace kar sake
                //1 bhi na ho kyuki fir 0 ajayega leading postion par
                if(y[i]!='0' && y[i]!= minToReplace){
                    minToReplace=y[i];
                    replaceWith='0';
                    break;
                }
            }
        }

        for(int i=0;i<y.length;i++){
            if(y[i]==minToReplace){
                y[i]=replaceWith;
            }
        }

        String mini= new String(y);

        return Integer.parseInt(maxi)-Integer.parseInt(mini);
    }
}