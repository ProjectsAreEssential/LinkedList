import java.util.List;

public class LinkedList<E> {
   
   // Instance fields
   private Node<E> head;
   private Node<E> tail;
   private int size;
   
   // Constructor
   public LinkedList() {
      this.head = null;
      this.tail = null;
      this.size = 0;
   }
   
   // Size
   public int size() {
      return size;
   }
   
   // Is empty
   public boolean isEmpty() {
      return size == 0;
   }
   
   // Add
   public void add(E element) {
      Node<E> newNode = new Node<>(element);
      if (head == null) {
         tail = newNode;
         head = tail;
      } else {
         tail.next = newNode;
         tail = newNode;
      }
      size++;
   }
   
   // Add all
   public void addAll(int index, List<E> list) {
      if (index < 0 || index > size) {
         throw new IndexOutOfBoundsException();
      } else if (list.isEmpty()) {
         return;
      } else if (index == 0) {
         Node<E> headNode = null;
         Node<E> lastNode = null;
         
         for (E element : list) {
            Node<E> newNode = new Node<E>(element);
            if (headNode == null) {
               headNode = newNode;
               lastNode = headNode;
            } else {
               lastNode.next = newNode;
               lastNode = newNode;            
            }
         }
         lastNode.next = head;
         head = headNode;
         
         if (lastNode.next == null) {
            tail = lastNode;
         }
         
         size += list.size();
      } else {
         Node<E> prev = getNode(index - 1);
         
         Node<E> headNode = null;
         Node<E> lastNode = null;
         for (E element : list) {
            Node<E> newNode = new Node<>(element);
            if (headNode == null) {
               headNode = newNode;
               lastNode = headNode;
            } else {
               lastNode.next = newNode;
               lastNode = newNode;
            }
         }
         
         lastNode.next = prev.next;
         prev.next = headNode;
         
         if (lastNode.next == null) {
            tail = lastNode;
         }
         
         size += list.size();
      }
   }
   
   // Contains all
   public boolean containsAll(List<E> list) {
      if (list.isEmpty()) {
         return true;
      }
      
      for (E element : list) {
         Node<E> curr = head;
         boolean found = false;
         
         while (curr != null) {
            if (curr.data.equals(element)) {
               found = true;
               break;
            }
            curr = curr.next;
         }
         if (!found) {
            return false;
         }
      }
      return true;
   }
   
   @Override
   // Equals
   public boolean equals(Object o) {
      if (!(o instanceof LinkedList<?>)) {
         return false;
      }
      
      LinkedList<?> other = (LinkedList<?>) o;
      if (other.size != size) {
         return false;
      }
      
      Node<E> curr1 = head;
      Node<?> curr2 = other.head;
      
      while (curr1 != null && curr2 != null) {
         if (!curr1.data.equals(curr2.data)) {
            return false;
         }
         curr1 = curr1.next;
         curr2 = curr2.next;
      }
      return (curr1 == null && curr2 == null);
   }
   
   // Last index of
   public int lastIndexOf(Object o) {
      int index = 0;
      int lastIndex = -1;
      
      Node<E> curr = head;
      while (curr != null) {
         if (curr.data.equals(o)) {
            lastIndex = index;
         }
         index++;
         curr = curr.next;
      }
      return lastIndex;
   }
   
   // Remove
   public boolean remove(Object o) {
      if (isEmpty()) {
         return false;
      } 
            
      Node<E> curr = head;
      Node<E> prev = null;
      
      while (curr != null) {
         if ((curr.data == null && o == null) || curr.data != null && curr.data.equals(o)) {
            if (prev == null) {
               head = curr.next;
               if (curr == tail) {
                  tail = null;
               }
            } else {
               prev.next = curr.next;
               if (curr == tail) {
                  tail = prev;
               }
            }
            
            size--;
            return true;
         }
         
         prev = curr;
         curr = curr.next;
      }
      
      return false;
   }
   
   // Retain all
   public void retainAll(List<E> list) {
      if (isEmpty()) {
         return;
      } else if (list.isEmpty()) {
         head = null;
         tail = null;
         size = 0;
         return;
      }
      
      Node<E> curr = head;
      Node<E> prev = null;
      
      while (curr != null) {
         if (list.contains(curr.data)) {
            prev = curr;
            curr = curr.next;
         } else {
            if (curr == head) {
               head = curr.next;
            } else {
               prev.next = curr.next;
            }
            
            if (curr == tail) {
               tail = prev;
            }
            
            size--;
            
            if (prev == null) {
               curr = head;
            } else {
               curr = prev.next;
            }
         }
      }
      
      if (size == 0) {
         head = null;
         tail = null;
      } 
   }
   
   // To array
   public Object[] toArray() {
      Object[] result = new Object[size];
      
      Node<E> curr = head;
      int index = 0;
      
      while (curr != null) {
         result[index] = curr.data;
         index++;
         curr = curr.next;
      }
      
      return result;
   }
   
   // Private Get node
   private Node<E> getNode(int index) {
      if (index < 0 || index >= size) {
         throw new IndexOutOfBoundsException();
      }
      Node<E> curr = head;
      
      for (int i = 0; i < index; i++) {
         curr = curr.next;
      }
      return curr;
   }
   
   // Inner node class
   private static class Node<E> {
      
      // Instance fields
      private E data;
      private Node<E> next;
      
      // Constructor
      Node(E data) {
         this.data = data;
         this.next = null;
      }
   }
}