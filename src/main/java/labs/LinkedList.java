package labs;

import lombok.Getter;

public class LinkedList<T> {

    private Node head;
    private Node tail;
    private int counter;

    private static class Node <T> {

        @Getter private T value;
        @Getter private Node prev;
        @Getter private Node next;

        public Node(T value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    public LinkedList() { }

    public void addLast(T object) {
        Node<T> node = new Node<>(object, tail, null);
        if (tail != null) {
            tail.next = node;
        } else {
            head = node;
        }
        tail = node;
        counter++;
    }

    public void add(T object) {
        addLast(object);
    }

    public void addFirst(T object) {
        Node<T> node = new Node<>(object, null, head);
        if (head != null) {
            head.prev = node;
        } else {
            tail = node;
        }
        head = node;
        counter++;
    }

    public void removeFirst() {
        head = head.next;
        head.prev = null;
        counter--;
    }

    public void removeLast() {
        tail = tail.prev;
        tail.next = null;
        counter--;
    }

    public T get(T object) {
        Node<T> current = head;
        while (current != null) {
            if (current.value.equals(object)) {
                break;
            }
            current = current.next;
        }
        if (current == null) {
            return null;
        } else {
            return current.value;
        }
    }

    public int size() {
        return counter;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LinkedList --> ");
        Node current = head;
        while (current != null) {
            sb.append(current.toString()).append(", ");
            current = current.next;
        }
        return sb.toString();
    }
}
