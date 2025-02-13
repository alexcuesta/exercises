package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class ValidParenthesis {

    public static void main(String[] args) {
        runTestCase("()");
        runTestCase("()[]{}");
        runTestCase("(]");
        runTestCase("([])");
    }

    private static void runTestCase(String s) {
        System.out.println(String.format("isValid(%s) = %b", s, isValid(s)));
    }

    public static boolean isValid(String s) {
        // return isValidLessElegant(s);
        return elegant(s);
    }

    public static boolean elegant(String s) {
        Map<Character, Character> pairs = Map.of(
                ')','(',
                ']','[',
                '}','{'
        );

        Deque<Character> stack = new ArrayDeque<>();
        for (int i=0; i<s.length(); i++) {
            char current = s.charAt(i);
            if (pairs.containsValue(current)) {
                stack.push(current);
            } else if (stack.isEmpty() || stack.pop() != pairs.get(current)) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    private static boolean lessElegant(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                    break;
                default:
                    return false;
            }
        }
        return stack.isEmpty();
    }
}