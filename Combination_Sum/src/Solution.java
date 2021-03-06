import java.util.*;

public class Solution {
    
	public static void main(String arg[]){
		Solution s = new Solution();
		int c[] = {1,2};
		int t = 3;
		System.out.print(s.combinationSum(c, t));
	}
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<List<Integer>>> opt = new ArrayList<List<List<Integer>>>();
        HashSet<List<Integer>> visited = new HashSet<List<Integer>>();
        
        // base case
        List<Integer> zeroLst = new ArrayList<Integer>();
        List<List<Integer>> zeroLayer = new ArrayList<List<Integer>>();
        zeroLayer.add(zeroLst);
        opt.add(zeroLayer);
        
        // iteration
        for(int i = 1;i < target+1;i++){
            List<List<Integer>> newOpt = new ArrayList<List<Integer>>();
            for(int j = 0;j < candidates.length;j++){
                if(i-candidates[j] > 0){
                    for(int u = 0;u < opt.get(i-candidates[j]).size();u++){
                        List<Integer> newLst = new ArrayList<Integer>(opt.get(i-candidates[j]).get(u));
                        // find the in order position for newly added element
                        for(int t = 0;t < newLst.size();t++){
                            if(newLst.get(t) >= candidates[j]){
                                newLst.add(t,candidates[j]);
                                break;
                            }
                            if(t == newLst.size()-1){
                                newLst.add(candidates[j]);
                                break;
                            }
                        }
                        if(!visited.contains(newLst)){
                            visited.add(newLst);
                            newOpt.add(newLst);
                        }
                    }
                }else if(i == candidates[j]){
                    List<Integer> singleLst = new ArrayList<Integer>();
                    singleLst.add(candidates[j]);
                    if(!visited.contains(singleLst)){
                        visited.add(singleLst);
                        newOpt.add(singleLst);
                    }
                }
            }
            opt.add(newOpt);
            // clear the visited set
            visited.clear();
        }
        return opt.get(opt.size()-1);
    }
}