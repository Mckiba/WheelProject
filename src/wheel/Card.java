package wheel;

public class Card {

  public static final Card[] cards = new Card[25];

  private String type;
  private int value;


  public Card(String type, int value) {
    this.type = type;
    this.value = value;
  }

  public Card() {
    this.type = "";
    this.value = 0;
  }

  public Card(Card c) {
    this.type = c.type;
    this.value = c.value;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }


  public void initWheel() {

    Wheel list = new Wheel( );

    cards[0] = new Card("Money", 700);
    cards[1] = new Card("Money", 600);
    cards[2] = new Card("Money", 650);
    cards[3] = new Card("Bankruptcy", 0);
    cards[4] = new Card("Free Play", 850);
    cards[5] = new Card("Money", 500);
    cards[6] = new Card("Lose a Turn", 0);
    cards[7] = new Card("Bankruptcy", 0);
    cards[8] = new Card("Lose A Turn", 0);
    cards[9] = new Card("Free Play", 850);
    cards[10] = new Card("Money", 550);
    cards[11] = new Card("Money", 500);
    cards[12] = new Card("Money", 550);
    cards[13] = new Card("Bankruptcy", 0);
    cards[14] = new Card("Money", 550);
    cards[15] = new Card("Lose A Turn", 0);
    cards[16] = new Card("Free Play", 850);
    cards[17] = new Card("Money", 550);
    cards[18] = new Card("Money", 900);
    cards[19] = new Card("Bankruptcy", 0);
    cards[20] = new Card("Money", 650);
    cards[21] = new Card("Free Play", 850);
    cards[22] = new Card("Money", 550);
    cards[23] = new Card("Bankruptcy", 0);
    cards[24] = new Card("Lose A Turn", 0);


  }

  public void display() {
    System.out.println("Card{" + "type='" + type + '\'' + ", value=" + value + '}');
  }


}
