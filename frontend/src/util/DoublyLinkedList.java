package util;

import java.util.Comparator;
import java.util.Iterator;

public class DoublyLinkedList<E> implements Iterable<E> {

    private final Node head, tail;
    private int size = 0;


    public class Node {
        private E e;
        private Node prev, next;

        public Node(E e, Node prev, Node next) {
            this.e = e;
            this.prev = prev;
            this.next = next;
        }

        public E getElement() {
            if(this == head || this == tail)
                throw new IllegalStateException();

            return e;
        }

        private void setElement(E e) {
            if(this == head || this == tail)
                throw new IllegalStateException();

            this.e = e;
        }
    }


    public DoublyLinkedList() {
        this.head = new Node(null, null, null);
        this.tail = new Node(null, head, null);
        this.head.next = tail;
    }

    private void validateNode(Node node) {
        if(node == null)
            throw new IllegalArgumentException("node cannot be null");

        if(node.next == null)
            throw new IllegalArgumentException("node has already been removed");
    }

    private Node position(Node node) {
        if(node == head || node == tail)
            return null;

        return node;
    }

    public Node before(Node node) {
        validateNode(node);
        return position(node.prev);
    }

    public Node after(Node node) {
        validateNode(node);
        return position(node.next);
    }

    private Node addBetween(E e, Node prev, Node next) {

        validateNode(prev);
        validateNode(next);

        if(prev.next != next)
            throw new IllegalArgumentException("the next node of prev is not node next");

        Node node = new Node(e, prev, next);

        prev.next = node;
        next.prev = node;

        ++size;

        return node;
    }

    public Node addBefore(Node node, E e) {

        validateNode(node);

        return addBetween(e, node.prev, node);
    }

    public Node addAfter(Node node, E e) {

        validateNode(node);

        return addBetween(e, node, node.next);
    }

    public Node addFirst(E e) { return addAfter(head, e); }

    public Node addLast(E e) { return addBefore(tail, e); }

    public E remove(Node node) {

        validateNode(node);

        node.prev.next = node.next;
        node.next.prev = node.prev;

        E element = node.getElement();

        node.setElement(null);
        node.prev = node.next = null;

        --size;
        return element;
    }

    public E removeFirst() { return remove(head.next); }

    public E removeLast(){ return remove(tail.prev); }

    public E set(Node node, E e) {
        validateNode(node);

        E element = node.getElement();
        node.setElement(e);

        return element;
    }

    @Override
	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator();
	}

	private class DoublyLinkedListIterator implements Iterator<E>{

		Node current = head.next;

		@Override
		public boolean hasNext() {
			return current != tail;
		}

		@Override
		public E next() {
			E element = current.getElement();
			current = current.next;
			return element;
		}

	}

    public void sort(Comparator<E> c) {
    }
/*
    public Node merge(Node first, Node second) {

        if(first == null) {
          return second;
        }
        if(second == null) {
          return first;
        }
        if(first.e < second.e) {
          first.next = merge(first.next, second);
          first.next.prev = first;
          first.prev = null;
          return first;
        } else {
          second.next = merge(first, second.next);
          second.next.prev = second;
          second.prev = null;
          return second;
        }

    }

    public Node mergeSort(Node node) {
        if(node == null || node.next == null) {
            return node;
        }
        Node second = split(node);

        node = mergeSort(node);
        second = mergesort(second);

        return merge(node, second);
    }


//Split DDL into 2 DLL's half size
    public Node split(Node head) {
      Node f = head;
      Node s = head;

      while(f.next != null && f.next.next != null) {
        f = f.next.next;
        s = s.next;
      }
      Node temp = s.next;
      s.next = null;
      return temp;
    }
*/

}
