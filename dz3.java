class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class dz3 {
    private Node head;

    // Добавление элемента в конец списка
    public void append(int data) {
        if (head == null) {
            head = new Node(data);
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = new Node(data);
    }

    // Разворот списка
    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;
        
        while (current != null) {
            next = current.next;   // сохраняем следующий узел
            current.next = prev;   // меняем направление указателя
            prev = current;        // перемещаем указатель prev на текущий узел
            current = next;        // продвигаемся к следующему узлу
        }

        head = prev;
    }

    // Вывод списка
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        dz3 list = new dz3();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);

        System.out.println("Исходный список:");
        list.printList();

        list.reverse();

        System.out.println("Развёрнутый список:");
        list.printList();
    }
}
