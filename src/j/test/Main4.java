package j.test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by j on 2017/8/1.
 */
import java.util.Stack;

public class Main4 {
    public static void main(String[]args){
        Scanner in=new Scanner(System.in

        );
        String line=in.nextLine();
        in.close();
        System.out.println(countStaragy(line));

    }
    private static int countStaragy(String str){
        int count=1;
        if(!str.isEmpty()){
            Stack<Character> stack=new Stack<>();
            for(int index=0;index<str.length();index++){
                char item;
                if((item=str.charAt(index))=='('){
                    stack.push(item);
                }else if(item==')'){
                    if(stack.isEmpty()){
                        count=0;
                        break;
                    }
                    count*=stack.size();
                    stack.pop();

                }
            }
            if(!stack.isEmpty()){
                count=0;
            }
        }
        return count;
    }
}

