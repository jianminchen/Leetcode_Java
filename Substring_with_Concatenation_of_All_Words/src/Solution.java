import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {
	
	public static void main(String arg[]){
		String S = "barfoobarfoofoobartoo";
		String[] L = {"bar","foo"};
		Solution s = new Solution();
		System.out.print(s.findSubstring(S, L));
	}
	
	public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> output = new ArrayList<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean used[] = new boolean[L.length];
        int start = -1;
        boolean inProcess = false;
        int count = 0;
        for(int i = 0;i < S.length();i++){
            boolean found = false;
            for(int j = 0;j < L.length;j++){
                if(i+L[j].length() < S.length()){
                    if(S.substring(i,i+L[j].length()).equals(L[j]) && !used[j]){
                        found = true;
                        used[j] = true;
                        count++;
                        // check if valid
                        if(count == L.length){
                            output.add(start);
                            if(!queue.isEmpty()){ // it should always be not empty
                                int index = queue.poll();
                                count--;
                                used[index] = false;
                                start += L[index].length();
                            }
                        }
                        if(!inProcess){
                            start = i;
                            inProcess = true;
                        }
                        i += L[j].length()-1;
                        queue.add(j);
                        break;
                    }
                }
            }
            if(!found){
                inProcess = false;
                count = 0;
            }
}
        
        
        return output;
    }
    
    private boolean allUsed(boolean[] A){
        for(int i = 0;i < A.length;i++)
            if(!A[i])
                return false;
        return true;
    }
}