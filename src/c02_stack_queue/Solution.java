package c02_stack_queue;
import java.util.Stack;
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0;i < s.length();i ++){
            Character c = s.charAt(i);
            if (c == '(' || c == '{' || c == '['){
                stack.push(c);
            }else{
                if (stack.isEmpty()){
                    return false;
                }
                if (c == ')' && stack.pop() != '('){
                    return false;
                }
                if (c == '}' && stack.pop() != '{'){
                    return false;
                }
                if (c == ']' && stack.pop() != '['){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
