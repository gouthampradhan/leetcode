package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gouthamvidyapradhan on 17/10/2017.
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

 Note: The input string may contain letters other than the parentheses ( and ).

 Examples:
 "()())()" -> ["()()()", "(())()"]
 "(a)())()" -> ["(a)()()", "(a())()"]
 ")(" -> [""]

 Solution: O(N x 2 ^ N) generate all combination of unique parentheses and return a list of valid parentheses which
 has the string length maximum

 */
public class RemoveInvalidParentheses {
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        List<String> result = new RemoveInvalidParentheses().removeInvalidParentheses("()())()");
        result.forEach(System.out::println);
    }

    public List<String> removeInvalidParentheses(String s) {
        Set<String> set = new HashSet<>();
        List<String> result = new ArrayList<>();
        result.add("");
        //generate all combinations of unique parentheses
        for(int i = s.length() - 1; i >= 0; i --){
            for(int j = 0, l = result.size(); j < l; j++){
                String curr = s.charAt(i) + result.get(j);
                if(!set.contains(curr)){
                    result.add(curr);
                    set.add(curr);
                }
            }
        }
        //check for max length
        int maxLen = 0;
        for(String r : result){
            if(isValid(r)){
                maxLen = Math.max(maxLen, r.length());
            }
        }
        //prepare the final list
        List<String> finalR = new ArrayList<>();
        for(String r : result){
            if(isValid(r)) {
                if(r.length() == maxLen){
                    finalR.add(r);
                }
            }
        }
        return finalR;
    }

    /**
     * Check if the given string of parentheses is valid or not
     * @param s String of parentheses
     * @return true if valid
     */
    private boolean isValid(String s){
        int count = 0;
        for(int i = 0, l = s.length(); i < l; i ++){
            if(s.charAt(i) == '('){
                count ++;
            } else if(s.charAt(i) == ')'){
                count --;
                if(count < 0) return false;
            }
        }
        return count == 0;
    }
}
