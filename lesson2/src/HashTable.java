public class HashTable<T, K> {
    private Basket<T, K>[] bakets;
    private static final int BASKET_COUNT = 16;
    private static final double LOAD_FACTOR = 0.75; //коэффициент загрузки бакетов
    private int size = 0; //переменная, которая регулируется по мере заполнения таблицы

    public HashTable() {
        this(BASKET_COUNT);
    }

    public HashTable(int size) {
        bakets = (Basket<T, K>[]) new Object[size];
    }

    public K getValue(T key) {
        int index = calcIndex(key);
        Basket<T, K> baket = bakets[index];
        if (baket != null) {
            return baket.find(key);
        }
        return null;
    }

    private int calcIndex(T key) {
        int baketIndex = Math.abs(key.hashCode()) % bakets.length;
        return baketIndex;
    }

    private void recalculate(){//оптимицация, если уровень заполнения высокий, должна перестраиваться хэш таблица
        Basket<T, K>[] old = bakets;//слепок старых баскетов до увеличения. массив старых бакетов
        bakets = (Basket<T, K>[]) new Object[old.length * 2]; //присваеваем новое увеличенное количество корзин
        for(int i=0; i<old.length; i++){ //берем каждую карзину в массиве корзин
            Basket basket = old[i];//фиксируем текущую корзины
            Basket.Node node = basket.head; //головная нода из этой корзины
            while(node != null){ //каждый раз добавляем каждую ноду в новую корзину
                put((T)node.entity.key, (K)node.entity.value);
                node = node.next;
            }
            old[i]=null;//зануляем старую корзину чтобы сборщик мусора убрал ненужное
        }
    }

    public boolean put(T key, K value) {
        if(bakets.length * LOAD_FACTOR < size){ //каждый раз при добавлении надо проверять, надо ли переформировать количество бакетов
            recalculate();
        }
        int index = calcIndex(key);
        Basket<T, K> basket = bakets[index]; //создаем основу баскет, связный список
        if (basket == null) {
            basket = new Basket<>(); //делаем новый обьект баскет
            bakets[index] = basket; // обращаемся к массиву баскетов и кладем туда созданный баскет
        }
        Entity<T, K> entity = new Entity<>();
        entity.key = key;
        entity.value = value;
        boolean add = basket.add(entity);
        if(add){ //если элемент добавлен, то надо увеличить размер
            size++;
        }
        return add;

    }

    public boolean remove(T key){
        int index = calcIndex(key);
        Basket<T, K> basket = bakets[index];
        boolean dell = basket.delete(key);
        if(dell){
            size--;
        }
        return dell;
    }


    private static class Entity<T, K> {
        T key;
        K value;
    }

    private class Basket<T, K> {
        Node head;

        public K find(T key) {
            Node node = head;
            while (node != null) {
                if (node.entity.key.equals(key)) {
                    return node.entity.value;
                } else {
                    node = node.next;
                }
            }
            return null;
        }

        public boolean add(Entity entity) {
            Node node = new Node();
            node.entity = entity;

            if (head != null) {
                Node currentNode = head;
                while (currentNode != null) {
                    if (currentNode.entity.equals(entity)) {
                        return false;
                    }
                    if (currentNode.next == null) {
                        currentNode.next = node;
                        return true;
                    }
                    currentNode = currentNode.next;
                }
            } else {
                head = node;
                return true;
            }
            return false;
        }

        public boolean delete(T key) {
            if (head != null) {
                if (head.entity.key.equals(key)) {
                    head = head.next;
                    return true;
                } else {
                    Node currentNode = head;
                    while (currentNode.next != null) {
                        if (currentNode.next.entity.key.equals(key)) {
                            currentNode.next = currentNode.next.next;
                            return true;
                        }
                        currentNode = currentNode.next;
                    }
                    return false;
                }
            }
            return false;
        }

        private class Node {
            Node next;
            Entity<T, K> entity;
        }

    }
}
