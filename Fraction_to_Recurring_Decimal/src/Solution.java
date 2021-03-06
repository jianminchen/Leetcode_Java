import java.util.List;
import java.util.ArrayList;


public class Solution {
	public static void main(String args[]){
		Solution s = new Solution();
		System.out.println(s.fractionToDecimal(Integer.MIN_VALUE, 1));
	}
    public String fractionToDecimal(int numerator, int denominator) {
    	String result = "";
        List<Long> buf = new ArrayList<Long>();
        int length = 0;
        int begin = 0;
        boolean negative = (double)numerator * (double)denominator < 0.0;
        long n = (long)numerator;
        long d = (long)denominator;
        n = n > 0?n:-n;
        d = d > 0?d:-d;
        
        // integer part
        result = Long.toString(n/d);
        n = n%d;
        if(n!=0){
        result += '.';
        
        // fraction part
        while(true){
            n *= 10;

            // deal with repeated part
            if(!buf.isEmpty()){
                while(begin < buf.size()){
                    if(buf.get(begin)==n){
                        length = buf.size()-begin;
                        break;
                    }
                    begin++;
                }
                if(begin >= buf.size())
                    begin = 0;
            }
            buf.add(n);
            if(length!= 0)
                break;
            
            result += (char)(n/d+'0');
            n = n%d;
            if(n==0){break;}
        }
        }
        
        // add parentheses if necessary
        if(length!=0)
            result = result.substring(0,result.indexOf(".")+1+begin) + "(" + result.substring(result.indexOf(".")+1+begin,result.length())+")";
            
        // add sign
        if(negative)
            result = "-"+result;
       
        return result;
    }
}
