package conversion_infixtopostfix;
public class Node {
    char info;
    Node link;
    
    public Node(char item) {
        this.info=item;
        this.link=null;
    }
}
