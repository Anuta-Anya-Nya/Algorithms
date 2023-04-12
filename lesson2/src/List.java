import org.w3c.dom.Node;

public class List { // свяный однонаправленный список
    private Node head; // головная нода, начало списка
    private Node tail; // ссылка на последний элемент в списке

    public void add(int value) {// вставка элемента в конец
        Node node = new Node();
        node.value = value;
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node; // следующая нода равна новой
            node.previous = tail; // текущая становится хвостом
            tail = node; // хвос становится нодой
        }
    }

    public void add(int value, Node node) { // вставка элемнта после конкретной ноды
        Node next = node.next;// ссылка на что ссылается нода в данный момент
        Node newNode = new Node();// создаем новую ноду
        node.value = value; // добавляем нее значение
        node.next = newNode; // ссылка на ноду в данный момент заменяется ссылкой на новую ноду
        newNode.previous = node; // в новой ноде предыдущее значение это текущая нода
        if (next == null) {
            tail = newNode; // если добавляем в конец
        } else { // если добавляем не в конец
            next.previous = newNode; // в следующей ноде предыдущея это новая нода
            newNode.next = next; // следуюзая в новой ноде, это следующая за текущей
        }

    }

    // public void delete(Node node){
    //     Node previous = node.previous; //из текущей берем следующую ссылку и предыдущую
    //     Node next = node.next;
    //     if(previous == null){ //элемент находится в начале 
    //         next.previous = null;
    //         head = next;
    //     } else {             
    //         if(next==null){//элемент в конце
    //             previous.next = null;
    //             tail=previous;
    //         } else { //элемент в середине
    //             previous.next = next; //заменяем ссылку на следующую ноду
    //             next.previous = previous; //заменяем ссылку на предыдущую ноду
    //         } 
    //     }  
    // }

    // public Node find(int value) { // поиск
    //     Node currentNode = head; // текущая нода. пока она есть, мы перебираем связанный список
    //     while (currentNode != null) {
    //         if (currentNode.value == value) {
    //             return currentNode;
    //         }
    //     }
    //     return null;
    // } // сложность перебора O(n);

    // public void revert(){ //переворот двусвязного списка
    //     Node currentNode = head; //текущая ссылка
    //     while(currentNode !=null){ 
    //         Node next = currentNode.next;
    //         Node previous = currentNode.previous;
    //         currentNode.next = previous; //ссылки меняются местами
    //         currentNode.previous = next;
            
    //         if (previous == null){ //если предыдущей нет, то это голова. заменяем ее на хвост
    //             tail = currentNode;
    //         } 
    //         if(next == null){//если следующей нет, это последняя, заменяем ее на голову
    //             head = currentNode;
    //         }
    //         currentNode = next; //обновляем такущую ноду
    //     }
    // }

        public void push(int value){ //добавление элемента для стека? обавление элемента в начало списка
            Node node = new Node();
            node.value = value;
            node.next = head;//текущая голова следующий элемент
            head = node; //голова - текущая нода
        }

        public void pushStek(int value){ //добавление элемента для очереди добавление элемента в начало списка
            Node node = new Node();
            node.value = value;
            node.next = head;
            head.previous = node;
            head = node; 
        }



//извлечь данные из стека, последний элемент лежащий наверху
        public Integer pop(){
            Integer result = null;
            if(head != null){
                result = head.value;
                head = head.next;
            }
            return result;
        }

//извлечеие последнего элемента очереди 
        public Integer peek(){
            Integer result = null;
            if(tail!=null){
                result = tail.value;
                tail.previous.next = null;
                tail = tail.previous;
            }
            return result;
        }



    public void revert(){ //так как рекурсивная функция требует много параметров, скрываем ее и создаем другую публичную, которая запускает разворот
        //если список пустой, разворот не нужен, если элемент 1 разворот не нужен, должно быть хотя бы 2 элемента
        if(head != null && head.next !=null){
            Node temp = head; // временно храним голову
            revert(head.next, head);// запускаем рекурсивную фунцию со следующей ноды после головы и в качестве предыдущей - головы
            temp.next = null;//обнуляем следующую точку головы, потому что теперь это хвост
        }
    }

    private void revert(Node currentNode, Node previousNode){ //разворот односвязного списка проще всего сделать рекурсивной функцией, принимающей текущую и предыдущую ноду
        if(currentNode.next == null){ //если следующей ноды нет, то мы в конце списка
            head = currentNode;//последнюю делаем новой
        } else {
            revert(currentNode.next, currentNode);
        }
        currentNode.next = previousNode;//заменяем ссылки
        //previousNode.next = null; замену предыдущей ноды у головы можно сжеать здесь или в публичной функции
    }

    private class Node { // нода (из которой состоят списки)
        private int value; // значение
        private Node next; // ссылка на следующую ноду
        private Node previous;
    }

}
