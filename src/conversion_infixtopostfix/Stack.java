package conversion_infixtopostfix;
import java.util.EmptyStackException;

public class Stack {
    private Node top,temp;
    private int count;

    public Stack() {
        this.top=null;
        this.count=0;
    }
    //push
    public void push(char item) {
        Node nn=new Node(item);
        nn.link=top;      
        top=nn;
        count++;
    }
    //pop
    public char pop() {
        if (!isEmpty()) {
            temp=top;
            top=top.link;
            temp.link=null;
            count--;
            return temp.info;
        }
        throw new EmptyStackException();
    }
    //chcek
    public boolean isEmpty() {return top==null;} 
    //show
    public void showAll() {
        System.out.print("Stack: ");
        showReverse(top);
        System.out.println("<--TOP");
    }
    public void showReverse(Node node) {
        if (node==null) {return;}
        showReverse(node.link);//recursion
        System.out.print(node.info+" ");
    }
    //peek
    public char peek() {
        if (top==null) throw new EmptyStackException();
        return top.info;
    }
}
