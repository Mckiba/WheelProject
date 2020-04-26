package wheel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Puzzle {

  public static Puzzle[] puzzles = new Puzzle[24];
  public static ArrayList<Character> Vowels = new ArrayList<>( );
  public static ArrayList<Character> Consonants = new ArrayList<>( );

  String category;
  String puzzle;

  public Puzzle() { //default constructor
    category = "";
    puzzle = "";
  }

  public Puzzle(String category, String puzzle) {  //Primary Constructor
    this.category = category;
    this.puzzle = puzzle;
  }

  public void loadPuzzle() { //loads puzzle from file
    int i = 1;
    try {
      Scanner fileObj = new Scanner(new File("File.txt"));
      while (fileObj.hasNext( )) {
        category = fileObj.next( );
        puzzle = fileObj.next( );
        //fileObj.next();
        puzzles[i] = new Puzzle(category, puzzle);
        i++;
      }
      fileObj.close( );
    } catch (FileNotFoundException e) {
      System.err.println("Could Not Load File");
    }
  }


  public void displayPuzzles() {
    for (int i = 1; i <= puzzles.length; i++) {
      System.out.println("  " + puzzles[i].category + "   " + puzzles[i].puzzle);
    }
  }

  public void loadVowels() {
    Vowels.add('A');
    Vowels.add('E');
    Vowels.add('I');
    Vowels.add('O');
    Vowels.add('U');
  }

  void loadConsonants() {
    Consonants.add('B');
    Consonants.add('C');
    Consonants.add('D');
    Consonants.add('F');
    Consonants.add('G');
    Consonants.add('H');
    Consonants.add('J');
    Consonants.add('K');
    Consonants.add('L');
    Consonants.add('M');
    Consonants.add('N');
    Consonants.add('P');
    Consonants.add('Q');
    Consonants.add('R');
    Consonants.add('S');
    Consonants.add('T');
    Consonants.add('V');
    Consonants.add('X');
    Consonants.add('Y');
    Consonants.add('Z');
    Consonants.add('-');
  }
}
