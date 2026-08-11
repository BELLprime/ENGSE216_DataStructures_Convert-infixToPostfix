package conversion_infixtopostfix;
public class InfixToPostfix {
    private Stack stack=new Stack();
    private String infix,postfix="";
    //constructor wtih handle
    public InfixToPostfix (String infix) {
        if (infix==null || infix.isEmpty()) 
            throw new IllegalArgumentException("Imput can't be empty!!!");
        
        this.infix = infix.replace(" ", "")
            .replace("**", "^")
            .replace("×", "*")
            .replace("÷", "/")+'\0';//add null
        this.infix=negativeNum(this.infix);
        if(isAlpha(this.infix)) 
            throw new IllegalArgumentException("Letters are't allowed!!!");
        if(missParen(this.infix))
            throw new IllegalArgumentException("Parenthesis is not balance!!!");
        if(duplicateOperator(this.infix)) 
            throw new IllegalArgumentException("There are duplicate operators!!!");
    }
    public void convert() {
        System.out.printf("%-10s %-25s %-10s%n", "Char", "Postfix", "Stack");
        System.out.println("-".repeat(60));
        for (int i=0;i<infix.length();i++) {
            char ch=infix.charAt(i);
            boolean LastOperand = (i+1 >= infix.length()) || !isOperand(infix.charAt(i + 1));
            if (isOperand(ch)) {//condition 1
                postfix+=ch;
                if (LastOperand) postfix += " ";
            } else if (isOperator(ch)) {//conditon 2
                if (stack.isEmpty()) stack.push(ch);//condition 2.1 empty stack
                else {//consition 2.2 compare with stack priority of curr and top
                    if(getPriority(ch) > getPriority(stack.peek()) ) stack.push(ch); //condition 2.2.1
                    else {//condition 2.2.2
                        while (!stack.isEmpty()) {
                            postfix+=stack.pop();
                            postfix += " ";
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
                    postfix += " ";
                }
                if (!stack.isEmpty()) stack.pop();//clear '('
            } else if (ch=='\0') { //condition 5
                while (!stack.isEmpty()) {
                    postfix+=stack.pop();
                    postfix += " ";
                }
            }
            System.out.printf("%-10s %-25s %-15s%n", ch=='\0'? "null" : ch, postfix, stack.getContents());
        }
    }
    //check
    private boolean isOperand (char ch) { return Character.isDigit(ch)||ch=='.' ;}
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
    private boolean isAlpha(String s) {
        boolean check=false;
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                return true;
            }
        }
        return check;
    }
    private boolean missParen(String s){
        boolean check=false;
        int count=0;
        for (int i=0;i<s.length();i++) {
            char c=s.charAt(i);
            if (isOpenParen(c)) count++;
            else if (isCloseParen(c)) {
                count--;
                if (count < 0) return true;//in case  )2*2(
            }
        }
        if (count<0) check=true;
        else if(count >0) check=true;
        return check;
    }
    private boolean duplicateOperator(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (isOperator(s.charAt(i)) && isOperator(s.charAt(i+1))) {
                if (s.charAt(i+1) == '-') continue; 
                return true;
            }
        }
        return false;
    }
    private String negativeNum(String s) { //->  -2+4  ->  0-2+4
        String result="";
        for (int i=0;i<s.length();i++) {
            char c=s.charAt(i);
            if (c=='-') {  //first                  front '('                   front op
                boolean negativeValue = (i==0)||isOpenParen(s.charAt(i-1))||isOperator(s.charAt(i -1));
                if (negativeValue) result += '0';
            }
            result+=c;
        }
        return result;
    }
    //get
    public String getPostfix() {return this.postfix;}
}