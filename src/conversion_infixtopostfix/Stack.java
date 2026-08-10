package conversion_infixtopostfix;
import java.util.EmptyStackException;

public class Stack {
    private Node head,top,temp;
    private int count;

    public Stack() {
        this.top=this.head=null;
        this.count=0;
    }
    //push
    public void push(String item) {
        Node nn =new Node(item);
        if (isEmpty()) {head=top=nn;}
        else {
            nn.link=top;
            top=nn;
        }
        count++;
    }
    //pop
    public String pop() {
        if(!isEmpty()) {
            temp=top;  
            if (top.link==null) {
                top=head=null;
            } else {
                top=top.link;
                temp.link=null;
            }
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
}
