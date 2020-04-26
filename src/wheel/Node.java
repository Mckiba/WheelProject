package wheel;

public class Node {

  Card data;
  Node nextNode;


  public Node() {
    this.data = new Card( );
    this.nextNode = null;
  }

  public Node(Card data, Node nextNode) {
    this.data = data;
    this.nextNode = nextNode;
  }

  public Node(Card data) {
    this.data = data;
    this.nextNode = null;
  }

  public Node(Node nextNode) {
    this.nextNode = nextNode;
    this.data = nextNode.data;
  }

  public Card getData() {
    return data;
  }

  public void setData(Card data) {
    this.data = data;
  }

  public Node getNextNode() {
    return nextNode;
  }

  public void setNextNode(Node nextNode) {
    this.nextNode = nextNode;
  }

  public void display() {
    System.out.println("Card {" + "Type = " + getData( ).getType( ) + " Value = " + getData( ).getValue( ));
  }


}
