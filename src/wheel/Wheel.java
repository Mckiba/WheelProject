package wheel;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Wheel extends Card {

  private Node head;
  private Node tail;
  private Node currNode = null;

  public Wheel(Node head, Node tail) {
    this.head = head;
    this.tail = tail;
  }

  public Wheel() {
    head = null;
    tail = null;
  }

  public Wheel(Node head) {
    this.head = head;
  }

  public Node getHead() {
    return head;
  }

  public void setHead(Node head) {
    this.head = head;
  }

  public Node getTail() {
    return tail;
  }

  public void setTail(Node tail) {
    this.tail = tail;
  }


  public void insert(Card data) {

    Node newNode = new Node(data);

    if (head == null) {

      setHead(newNode);
      setTail(newNode);

    } else {

      tail.nextNode = newNode;
      tail = newNode;
      tail.nextNode = head;

    }
  }

  public void display() {

    Node currNode = head;

    if (head == null) {

      System.out.println("List is Empty");

    } else {

      do {
        currNode.display( );
        currNode = currNode.nextNode;

      } while (currNode != head);

    }

  }

  public Card spinWheel() {
    /*
    try {
      System.out.println("Current Position!! " + currNode.data.getType( ) + " " + currNode.data.getValue( ));
    } catch (NullPointerException ignored) {
    }
    */

    //checks if the wheel is empty
    if (head == null) {
      System.out.println("No data found on the Wheel");
    }
    Random rand = new Random( );
    int i = rand.nextInt(50) + (50);
    int pointer = 1;

    if (currNode == null)
      currNode = head;

    //if the current position is less than i current position is the position of the next node
    //when position is equal to i the current position is returned
    while (pointer < i) {
      currNode = currNode.getNextNode( );
      pointer++;
    }
    System.out.println("Wheel is Spinning.................\n");

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace( );
    }

    System.out.println("LANDED ON!! \n" + currNode.data.getType( ) + " Card\nValue: $" + currNode.data.getValue( ));

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace( );
    }

    return currNode.data;
  }
}


