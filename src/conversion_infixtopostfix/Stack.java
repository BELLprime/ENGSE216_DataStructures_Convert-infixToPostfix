package conversion_infixtopostfix;
import java.util.EmptyStackException;

public class Stack {
    private Node top,temp;

    public Stack() {
        this.top=null;
    }
    //push
    public void push(char item) {
        Node nn=new Node(item);
        nn.link=top;      
        top=nn;
    }
    //pop
    public char pop() {
        if (!isEmpty()) {
            temp=top;
            top=top.link;
            temp.link=null;
            return temp.info;
        }
        throw new EmptyStackException();
    }
    //chcek
    public boolean isEmpty() {return top==null;} 
    //show
    public String getContents() {
    String result = "";
    Node travel = top;
    while (travel != null) {
        result += travel.info + " ";
        travel = travel.link;
    }
    return result.trim();
}
    //peek
    public char peek() {
        if (top==null) throw new EmptyStackException();
        return top.info;
    }
}
