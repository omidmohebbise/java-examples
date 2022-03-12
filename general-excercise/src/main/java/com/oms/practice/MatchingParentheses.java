package com.oms.practice;

import java.util.List;
import java.util.Stack;

public class MatchingParentheses {
    public static void main(String[] args) {
        System.out.println(validate("Salam (omif(sd[f]))"));
    }

    public static boolean validate(String input) {
        List<Character> openChars = List.of('(', '[', '{', '<');
        List<Character> closeChars = List.of(')', ']', '}', '>');
        Stack<Character> characters = new Stack<>();
        for (char ch : input.toCharArray()) {
            if (openChars.contains(ch))
                characters.push(ch);
            else if (closeChars.contains(ch) && !validateOpenAndCloseCharacter(characters.pop(), ch)) {
                return false;
            }
        }
        return characters.isEmpty();
    }

    private static boolean validateOpenAndCloseCharacter(Character old, char ch) {
        if ((old.equals('(') && ch != ')') || (old.equals('[') && ch != ']') || (old.equals('{') && ch != '}') || (old.equals('<') && ch != '>'))
            return false;
        else
            return true;
    }
}
