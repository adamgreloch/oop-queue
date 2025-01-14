package pl.edu.mimuw.queue;

public abstract class AbstractIntQueue {

  protected IntQueueNode queue;
  protected int size;

  public AbstractIntQueue() {
    this.queue = null;
    this.size = 0;
  }

  public AbstractIntQueue(int value) {
    this.queue = new IntQueueNode(value);
    this.size = 1;
  }

  /**
   * Adds element to the queue.
   *
   * @throws NullPointerException if the specified element is null
   */
  public abstract void offer(Integer x);

  /**
   * @return the head of this queue, or {@code null} if this queue is empty
   * and don't remove the element from the queue
   */
  public Integer peek() {
    if (this.isEmpty()) return null;
    return this.queue.value();
  }

  /**
   * @return the head of this queue, or {@code null} if this queue is empty
   * and remove the element from the queue
   */
  public Integer poll() {
    if (this.isEmpty()) return null;

    Integer head = this.queue.value();
    IntQueueNode next;

    if (this.queue.next() != null) {
      next = this.queue.next();
      this.queue = new IntQueueNode(next.value(), next.next(), this.queue.prev());
    } else
      this.queue = null;

    this.size--;

    return head;
  }

  /**
   * @return readable representation of ordered queue elements
   */

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder(this.getType() + " [");
    IntQueueNode peeked = this.queue;

    while (peeked != null) {
      if (peeked != this.queue) result.append(" ");
      result.append(peeked.value());
      peeked = peeked.next();
    }

    result.append("]");

    return result.toString();
  }

  /**
   * @return the number of elements in this queue
   */
  public int size() {
    return this.size;
  }

  /**
   * @return true if queue is empty, false otherwise
   */
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * @return queue type, i.e. LIFO
   */
  public abstract String getType();
}
