package wheel;

public class PlayerList {

  PlayerNode head;
  PlayerNode tail;

  private PlayerNode CurPosition = null;

  //Primary Constructor
  public PlayerList(PlayerNode head, PlayerNode tail) {
    this.head = head;
    this.tail = tail;
  }

  //default Constructor
  public PlayerList() {
    head = null;
    tail = null;
  }

  public PlayerNode getHead() {
    return head;
  }

  public void setHead(PlayerNode head) {
    this.head = head;
  }

  public PlayerNode getTail() {
    return tail;
  }

  public void setTail(PlayerNode tail) {
    this.tail = tail;
  }


  public void insert(Player data) {

    PlayerNode newNode = new PlayerNode(data);

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

    PlayerNode currNode = head;

    if (head == null) {
      System.out.println("List is Empty");
    } else {
      do {
        currNode.display( );
        currNode = currNode.nextNode;
      } while (currNode != head);
    }
  }

  public Player PlayerTurn() {
    if (head == null) {
      System.out.println("No Player found on record");
      return null;
    }
    if (CurPosition == null) {
      CurPosition = head;
    } else {
      //pass current position to next node which switches to the next player
      CurPosition = CurPosition.getNextNode( );
    }
    //returns data for the next player
    return CurPosition.getData( );
  }

  public Player getWinner() {
    PlayerNode current = head;
    //Initializing max to initial node data
    Player max = head.data;

    do {
      //If current node's data is greater than max
      //Then replace value of max with current node's data
      if (max.getGrandTotal( ) < current.data.getGrandTotal( )) {
        max = current.data;
      }
      current = current.nextNode;
    } while (current != head);

    System.out.println("Player with Highest Grand Total is " + max.playerName);
    return max;
  }

  public void resetRoundTotal() {
    PlayerNode current = head;
    if (head == null) {
      System.out.println("Player List is empty");
    } else {

      //sets the round balance for each node to zero
      do {
        current.getData( ).setRoundBalance(0);
        current = current.getNextNode( );
      } while (current != head);
      System.out.println( );
    }
  }

  public void displayTotals() {

    PlayerNode current = head;
    if (head == null) {
      System.out.println("Player List is empty");
    } else {
      System.out.println("Player" + "        " + "Grand Total");
      do {
        System.out.println("" + current.getData( ).getPlayerName( ) + "        " + current.getData( ).getGrandTotal( ));

        current = current.getNextNode( );
      } while (current != head);
    }
  }
}