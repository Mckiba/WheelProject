package wheel;

public class PlayerNode {

  Player data;
  PlayerNode nextNode;


  public PlayerNode(Player data, PlayerNode nextNode) {
    this.data = data;
    this.nextNode = nextNode;
  }


  public PlayerNode() {
    data = new Player( );
    nextNode = null;
  }

  public PlayerNode(Player data) {
    this.data = data;
    this.nextNode = null;
  }

  public PlayerNode(PlayerNode nextNode) {
    this.nextNode = nextNode;
    this.data = nextNode.data;
  }


  public Player getData() {
    return data;
  }

  public void setData(Player data) {
    this.data = data;
  }

  public PlayerNode getNextNode() {
    return nextNode;
  }

  public void setNextNode(PlayerNode nextNode) {
    this.nextNode = nextNode;
  }

  public void display() {


    System.out.println("Player: " + getData( ).getPlayerName( ) + "              " + "Grand Total $" + getData( ).getGrandTotal( ));

  }
}
