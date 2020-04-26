package wheel;

public class Player {

  String playerName;
  int playerNumber;
  int grandTotal;
  int roundBalance;

  public Player() {
    playerName = "";
    playerNumber = 0;
    grandTotal = 0;
    roundBalance = 0;
  }

  public Player(String playerName, int playerNumber, int grandTotal, int roundBalance) {
    this.playerName = playerName;
    this.playerNumber = playerNumber;
    this.grandTotal = grandTotal;
    this.roundBalance = 0;
  }

  public Player(Player p) {
    this.playerName = p.playerName;
    this.playerNumber = p.playerNumber;
    this.grandTotal = p.grandTotal;
    this.roundBalance = p.roundBalance;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public int getPlayerNumber() {
    return playerNumber;
  }

  public void setPlayerNumber(int playerNumber) {
    this.playerNumber = playerNumber;
  }

  public int getGrandTotal() {
    return grandTotal;
  }

  public void setGrandTotal(int grandTotal) {
    this.grandTotal += grandTotal;
  }

  public void addMoney(int m) {
    roundBalance += m;
  }

  public int getRoundBalance() {
    return roundBalance;
  }

  public void setRoundBalance(int roundBalance) {
    this.roundBalance = roundBalance;
  }

  public void display() {
    System.out.println("Player" + "Name: " + playerName + ", Player Number: " + playerNumber + "" + grandTotal + "Round Total" + roundBalance);
  }


}
