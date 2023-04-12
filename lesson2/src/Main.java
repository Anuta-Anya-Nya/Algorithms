public class Main {
    public static void main(String[] args){
        Lin2List list = new Lin2List();
        list.addFirst(20);
        list.addLast(2);
        list.addLast(144);
        list.addLast(1);
        list.addLast(0);
        list.print();
        list.sort();
        // list.removeLast();
        // list.removeFirst();
        // list.removeLast();
        // list.removeFirst();
        list.print();
    }
    

}
