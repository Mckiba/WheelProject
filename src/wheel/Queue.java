package wheel;

public class Queue {

  private char[] queue;
  private int size;
  private int front;
  private int back;


  public Queue() {
    this.queue = new char[26];
    this.size = 0;
    this.back = 0;
    this.front = 0;
  }

  public void enQueue(char data) {

    queue[size] = data;
    back = back + 1;
    size = size + 1;

  }

  public boolean isEmpty() {
    return size == 0;
  }

  public char[] getQueue() {
    return queue;
  }

  public void setQueue(char[] queue) {
    this.queue = queue;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }


  public void show() {
    if (queue == null)
      System.out.println("\nAll letters are available\n");

    else {
      for (int i = 0; i < size; i++) {
        System.out.print(queue[i] + "\0");
      }
    }
  }

}
