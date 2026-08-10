package conversion_infixtopostfix;
public class InfixToPostfix {
    private Stack stack=new Stack();
    private String infix,postfix="";

    public InfixToPostfix (String infix) {
        this.infix = infix+'\0';//add null
    }
    
    public void convert() {
        for (int i=0;i<infix.length();i++) {
            char ch=infix.charAt(i);

            if (isOperand(ch)) {//condition 1
                postfix+=ch;
            } else if (isOperator(ch)) {//conditon 2
                if (stack.isEmpty()) stack.push(ch);//condition 2.1 empty stack
                else {//consition 2.2 compare with stack priority of curr and top
                    if(getPriority(ch) > getPriority(stack.peek()) ) stack.push(ch); //condition 2.2.1
                    else {//condition 2.2.2
                        while (!stack.isEmpty()) {
                            postfix+=stack.pop();
                            if (stack.isEmpty() || getPriority(ch) > getPriority(stack.peek()) ) { //check empty before peek.
                                //if stack isEmpty ,no need to getpriotiry,otherwise code will throw error.
                                stack.push(ch); 
                                break;
                            }
                        }
                    }
                }
            } 
            else if(isOpenParen(ch)) stack.push(ch); //condition 3
            else if(isCloseParen(ch)) {//condition 4
                while (!stack.isEmpty() && stack.peek() != '(' ) {
                    postfix+=stack.pop();
                }
                if (!stack.isEmpty()) stack.pop();
            } else if (ch=='\0') { //condition 5
                while (!stack.isEmpty()) {
                    postfix+=stack.pop();
                }
            }
        }
    }
    //check
    private boolean isOperand (char ch) { return Character.isDigit(ch);}
    private boolean isOperator (char ch) { 
        return ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='%'||ch=='^';
    }
    private boolean isOpenParen(char ch) {return ch == '(';}
    private boolean isCloseParen(char ch) {return ch == ')';}
    private int getPriority (char op) {
        return switch (op) {
            case '^' -> 3;
            case '*','%','/' -> 2;
            case '+','-' -> 1;
            case '(' -> 0;
            default -> -1;   
        };
    }
    //get
    public String getPostfix() {return this.postfix;}
}
