package wheel;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {

  public static final Player[] players = new Player[4];
  public static int round;
  static Puzzle puzzleObj = new Puzzle( );
  static Wheel list = new Wheel( );
  final Scanner inF = new Scanner(System.in);
  public PlayerList playerList = new PlayerList( );
  public char[] correctLettersGuessed;      //stores the correct letters guessed in the puzzle
  public char[] alphabets;
  int letterCount = 0;
  Player player = new Player( );
  String roundPuzzle;//puzzle for the round
  String roundCategory;
  String word;
  Card card;


  public static void main(String[] args) {
    round = 1;
    Game gameObj = new Game( );
    puzzleObj = new Puzzle( );
    puzzleObj.loadPuzzle( );
    puzzleObj.loadConsonants( );  //Load list of consonants

    Card cardObj = new Card( );
    cardObj.initWheel( );             //initialise the cards
    for (int i = 0; i < 25; i++) {
      list.insert(Card.cards[i]);   //adds all the cards to the list
    }


    gameObj.setPlayers( );
    gameObj.rounds(round);

  }


  void setPlayers() {

    System.out.println("\nWelcome to The Wheel of Fortune\n\n\n");
    String name;
    for (int i = 1; i <= 3; i++) {

      System.out.println("Enter Player Name");
      name = inF.nextLine( );

      players[i] = new Player(name, i, 0, 0);
      playerList.insert(players[i]);

    }
  }

  void rounds(int roundNumber) {
    Random rand = new Random( );
    int i = rand.nextInt(Puzzle.puzzles.length);

    Queue Q = new Queue( );
    roundPuzzle = Puzzle.puzzles[i].puzzle;     //Picks a random puzzle and category from the puzzle array which was read from the file
    roundCategory = Puzzle.puzzles[i].category;
    alphabets = "BCDFGHJKLMNPQRSTVWXYZ".toCharArray( );
    Puzzle.Vowels.removeAll(Puzzle.Vowels);         //removes remaining vowel from the initial round
    puzzleObj.loadVowels( );      //refresh vowels

    correctLettersGuessed = new char[roundPuzzle.length( )];      //creates an a character array of guessed letters of the round puzzle
    Arrays.fill(correctLettersGuessed, '_');                  //fills  array with underscores
    System.out.println("\n\n\nRound Category is: " + roundCategory);
    playGame(list, playerList, Q, player);

  }


  public void playGame(Wheel list, PlayerList pList, Queue Q, Player player) {


    int option;
    boolean lost = true;

    do {
      if (lost) {
        player = pList.PlayerTurn( );   //if player lost their turn this function switches it to the next player in the player circular list
      }
      System.out.println(player.getPlayerName( ) + "'s Turn");
      System.out.println("" + player.getPlayerName( ) + " Your Round Total is $" + player.getRoundBalance( ));

      try {
        System.out.println("\n1.Spin the Wheel\n2.Buy Vowel\n3.Solve\n4.Quit Game\n\n");
        option = inF.nextInt( );
      } catch (InputMismatchException e) {
        inF.next( );
        System.out.println("Invalid Input!. Choose another option");
        option = 0;
      }

      switch (option) {
        case 1:
          card = list.spinWheel( );     //spins wheel and passes its card data value into variable of type Card card
          if (checkCard(player, card, true)) {  // check the contents of card to know if the user should lose his turn or bankrupt
            player.addMoney(card.getValue( ));
            System.out.println(player.getPlayerName( ) + "'s" + " Round Balance: " + player.getRoundBalance( ));
            lost = guessPuzzle(player, Q);
          } else
            playGame(list, pList, Q, player);
          break;
        case 2:
          if (player.getRoundBalance( ) < 250) {
            System.out.println("\nYou do not have sufficient money to purchase vowels!....Vowels cost $250\n");
            lost = false;
          } else {
            lost = buyVowel(Q);
            player.addMoney(-250);
          }
          break;
        case 3:
          if (player.getRoundBalance( ) > 0)
            lost = solve(player);
          else {
            System.out.println("Insufficient Funds to solve!\n");
          }
          break;

        default:
          lost = false;
          break;
      }
    } while (option != 4);
    System.exit(0);
  }

  public boolean guessPuzzle(Player player, Queue Q) {

    letterCount = 0;
    System.out.println("Round Category is: " + roundCategory);//

    System.out.println("\nLetters available:\n");      //Checks Queue and replaces guessed letters with underscores
    for (int i = 0; i <= Q.getSize( ); i++) {
      for (int j = 0; j < alphabets.length; j++) {
        if (Character.toUpperCase((Q.getQueue( )[i])) == Character.toUpperCase(alphabets[j])) {
          alphabets[j] = '_';
        }
      }
    }

    for (char alphabet : alphabets) System.out.print(alphabet + " ");//displays letters available for guessing
    System.out.println("\n Puzzle:    " + currentGuessString( ) + "\n");


    System.out.println("\nGuess a  Consonant");
    char playerGuess = inF.next( ).charAt(0);

    //only allow users to enter consonants
    //check if entered letter is a consonant in the consonant array list
    while (!Puzzle.Consonants.contains(Character.toUpperCase(playerGuess))) {
      System.out.println("Letter Entered is Not a Consonant");
      playerGuess = inF.next( ).charAt(0);
    }
    boolean checkLetter = find(playerGuess, Q);
    while (checkLetter) {
      System.out.println("You already chose that letter Enter another letter!: \n");
      playerGuess = inF.next( ).charAt(0);

      while (!Puzzle.Consonants.contains(Character.toUpperCase(playerGuess))) {
        System.out.println("Letter Entered is Not a Consonant");
        playerGuess = inF.next( ).charAt(0);
      }
      checkLetter = find(playerGuess, Q);
    }
    Q.enQueue(playerGuess);
    boolean found = searchLetter(playerGuess);

    boolean lostTurn;
    if (found) {
      lostTurn = false;
      System.out.println(" ******  Your guess was correct! ****** \n\n\n");
      System.out.println("Puzzle:    " + currentGuessString( ));
      player.setRoundBalance(card.getValue( ) * letterCount + player.getRoundBalance( ));
    } else {
      lostTurn = true;
      System.out.println(" ***** Sorry, that letter is not in the  " + roundCategory + " *****\n\n");
    }
    if (roundPuzzle.equalsIgnoreCase(word)) {
      roundWon(player, playerList);
    }
    return lostTurn;
  }

  public String currentGuessString() {         //will print out the correct letters guessed so far
    StringBuilder toPrint = new StringBuilder( );
    StringBuilder wordPuzzle = new StringBuilder( );

    for (char c : correctLettersGuessed) {
      wordPuzzle.append(Character.toUpperCase(c));          //for each letter at it to word puzzle string builder
      toPrint.append(Character.toUpperCase(c)).append(" "); //for each letter, add it and a space to the string
    }
    word = wordPuzzle.toString( );              //coverts to string so it can be compared with the round puzzle
    return toPrint.toString( );
  }

  public boolean buyVowel(Queue Q) {

    boolean lost;

    System.out.println("Vowels Available: \n\n" + Puzzle.Vowels.toString( ));
    System.out.println("Which vowel would you like to buy\n");
    char vowel = inF.next( ).charAt(0);


    //Checks the Vowel Array list to see of the character entered is a vowel or is available for guessing
    while (!Puzzle.Vowels.contains(Character.toUpperCase(vowel))) {
      System.out.println("Enter one of the available vowels\n");
      vowel = inF.next( ).charAt(0);
    }

    boolean checkVowel = find(vowel, Q);
    //checks the queue to see if vowel is already entered
    while (checkVowel) {
      System.out.println("You already chose that letter\nEnter another letter!: \n\n");
      vowel = inF.next( ).charAt(0);
      checkVowel = find(vowel, Q);
    }
    Puzzle.Vowels.remove(Character.valueOf(vowel));
    Q.enQueue(vowel);         //adds the vowel to the queue
    boolean found;
    found = searchLetter(vowel);
    if (found) {
      System.out.println("Puzzle:    " + currentGuessString( ));
      lost = false;
    } else {
      System.out.println("Puzzle does not contain the vowel " + vowel + "\n\n");
      lost = true;
    }
    return lost;

  }

  boolean searchLetter(char c) {
    boolean found = false;
    for (int j = 0; j < roundPuzzle.length( ); j++) {       //loops for the length of the round puzzle

      //checks to the if the players guess is in the round puzzle
      if (Character.toUpperCase(roundPuzzle.charAt(j)) == Character.toUpperCase(c)) {
        found = true;
        letterCount++;
        correctLettersGuessed[j] = c;  //adds the letter to the correct Letters Guessed array
      }
    }
    return found;
  }

  public boolean solve(Player player) {
    boolean bool = false;
    System.out.println("GUESS THE WORD!!!!!!!");
    String playerSolve = inF.next( );

    if (playerSolve.equalsIgnoreCase(roundPuzzle)) {
      roundWon(player, playerList);
    } else {
      System.out.println("Incorrect Guess\nYou lost your turn\n\n");
      System.out.println("\n" + player.getPlayerName( ) + " Your Round Total is $" + player.getRoundBalance( ));
      bool = true;
    }
    return bool;
  }

  public boolean checkCard(Player player, Card card, boolean turnAvailable) {

    if (card.getType( ).equalsIgnoreCase("Bankruptcy")) {
      player.setRoundBalance(0);
      System.out.println("YOU LOST YOUR MONEY\n\n");
      System.out.println("\n" + player.getPlayerName( ) + " Your Round Total is $" + player.getRoundBalance( ) + "\n");
      turnAvailable = false;

    } else {
      if (card.getType( ).equalsIgnoreCase("Lose a Turn")) {
        System.out.println("YOU LOST YOUR TURN\n\n\n");
        System.out.println("\n" + player.getPlayerName( ) + " Your Round Total is $" + player.getRoundBalance( ) + "\n");
        turnAvailable = false;
      }
    }
    return turnAvailable;
  }

  public boolean find(Character character, Queue Q) {     //searches the Queue for a letter
    boolean found = false;
    for (int i = 0; i < Q.getSize( ); i++) {
      if (Q.getQueue( )[i] == character) {      //if the letter is found  in the Q return true;
        found = true;
      }
    }
    return found;
  }

  public void roundWon(Player player, PlayerList playerList) {

    int option;
    player.setGrandTotal(player.getGrandTotal( ) + player.getRoundBalance( ));
    System.out.println("Congratulations Puzzle Solved\n" + player.playerName + " You Won This Round!!!\n\n");
    System.out.println("Your Grand total is $" + player.getGrandTotal( ) + "\n\n");
    round++;

    if (round <= 3) {
      System.out.println("\n--------------------New round!--------------------\n");
      System.out.println("Round Number " + round);
      playerList.resetRoundTotal( );
      rounds(round);
    }

    if (round > 3) {
      System.out.println("\n\n\n-------------------------------------------------");
      player = playerList.getWinner( );
      System.out.println(player.getPlayerName( ) + " You Won The Game!!!!Congratulations\n");
      System.out.println("Your Grand total is $" + player.getGrandTotal( ) + "\n\n");
      playerList.displayTotals( );
      System.out.println("Thanks for you playing Wheel of Fortune");
      System.out.println("\n\nDo you want to play a new Game or Exit!\n1.Exit\n2.New Game\n");

      try {
        option = inF.nextInt( );
      } catch (InputMismatchException e) {
        inF.next( );
        System.out.println("Invalid Option. Enter a valid option!!");
        option = inF.nextInt( );
      }
      if (option == 2) {
        round = 1;
        main(null);
      } else
        System.exit(0);
    }
  }
}