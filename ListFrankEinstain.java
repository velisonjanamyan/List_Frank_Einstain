package out;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class ListFrankEinstain{
    private int size;
    private NodeFrankEnshtain tail;
    private NodeFrankEnshtain head;
    private NodeFrankEnshtain ascendingHead;
    private NodeFrankEnshtain descendingHead;
    private final List<NodeFrankEnshtain> list = new LinkedList<>();
    private boolean sortFlag = false;


    public void add(int val) {
        if (tail == null) {
            head = new NodeFrankEnshtain(val);
            tail = head;
            return;
        }


        tail.next = new NodeFrankEnshtain(val, null, tail);
        this.tail = tail.next;
        size++;
        sortFlag = true;
    }

    public NodeFrankEnshtain pop() {
        NodeFrankEnshtain val = tail;
        if (tail != null) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            }
            size--;
            sortFlag = true;
        }
        return val;
    }


    public void remove(int pos) {
        if (pos < 0 && pos > size) {
            return;
        }

        NodeFrankEnshtain tmp = this.head;

        while (pos != 0) {
            if (tmp.next == null) {
                System.out.println("Invalid possition");
                break;
            }
            tmp = tmp.next;
            pos--;
        }

        tmp.prev.next = tmp.next;
        if (tmp.next != null) {
            tmp.next.prev = tmp.prev;
        }
        size--;
        sortFlag = true;
    }

    private void listInit() {
        list.clear();
        NodeFrankEnshtain tmp = head;
        while (tmp != null) {
            list.add(tmp);
            tmp = tmp.next;
        }
    }


    private void sort() {
        NodeFrankEnshtain tmp = this.head;
        NodeFrankEnshtain min = this.head;
        NodeFrankEnshtain max = this.head;

        listInit();
        while (tmp != null) {
            if (tmp.val > max.val) {max = tmp;}
            if (tmp.val < min.val) {min = tmp;}
            tmp.ascending = this.maxValNode(tmp);
            tmp = tmp.next;
        }

        listInit();
        tmp = this.head;
        while (tmp != null) {
            tmp.descending = this.minValNode(tmp);
            tmp = tmp.next;
        }


        this.ascendingHead = min;
        this.descendingHead = max;
        this.sortFlag = false;
    }


    private NodeFrankEnshtain minValNode(NodeFrankEnshtain node) {
        NodeFrankEnshtain res = null;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) != node && list.get(i).val <= node.val) {
                if (res == null) {
                    res = list.get(i);
                } else if (res.val > list.get(i).val) {
                    res = list.get(i);
                }
            }
        }

        if (res != null) {
            list.remove(res);
        }
        return res;

    }

    private NodeFrankEnshtain maxValNode(NodeFrankEnshtain node) {
        NodeFrankEnshtain res = null;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) != node && list.get(i).val >= node.val) {
                if (res == null) {
                    res = list.get(i);
                } else if (res.val > list.get(i).val) {
                    res = list.get(i);
                }
            }
        }

        if (res != null) {
            list.remove(res);
        }
        return res;
    }


    public Iterator<Integer> iterator() {
        return new itr();
    }

    class itr implements Iterator<Integer> {
        NodeFrankEnshtain head = ListFrankEinstain.this.head;
        NodeFrankEnshtain lastRet = null;


        @Override
        public boolean hasNext() {
            return this.head != null;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                return null;
            }
            lastRet = head;
            head = head.next;
            return lastRet.val;
        }

        @Override
        public void remove() {
            if (lastRet == null) {
                throw new RuntimeException("Invalid operation");
            }
            if (lastRet.prev == null) {
                ListFrankEinstain.this.head = ListFrankEinstain.this.head.next;
                ListFrankEinstain.this.head.prev = null;
                return;
            }

            lastRet.prev.next = lastRet.next;
            if (lastRet.prev.next == null) {
                return;
            }
            lastRet.next.prev = lastRet.prev;
            lastRet = null;
        }

        @Override
        public void forEachRemaining(Consumer<? super Integer> action) {
            if (action == null) {
                throw new RuntimeException("Invalid Operation");
            }

            while (head != null) {
                action.accept(head.val);
                head = head.next;
            }
        }
    }

    public Iterator<Integer> iteratorAscending() {
        return new ItrA();
    }

    public class ItrA implements Iterator<Integer> {
        NodeFrankEnshtain head;
        NodeFrankEnshtain lastRet;

        ItrA() {
            if (ListFrankEinstain.this.sortFlag) {
                ListFrankEinstain.this.sort();
            }
            this.head = ListFrankEinstain.this.ascendingHead;
            this.lastRet = null;
        }


        @Override
        public boolean hasNext() {
            return this.head != null;
        }

        @Override
        public Integer next() {
            if (!this.hasNext()) throw new RuntimeException("Invalid Operation: next()");

            this.lastRet = this.head;
            this.head = this.head.ascending;
            return lastRet.val;
        }

        @Override
        public void remove() {
            if (this.lastRet == null) throw new RuntimeException("Invalid Operation: remove()");

            if (lastRet.prev == null) {
                ListFrankEinstain.this.ascendingHead = ListFrankEinstain.this.ascendingHead.next;
                ListFrankEinstain.this.ascendingHead.prev = null;
                return;
            }

            lastRet.prev.next = lastRet.next;
            if (lastRet.next == null) {
                return;
            }
            lastRet.next.prev = lastRet.prev;
            lastRet = null;
        }

        @Override
        public void forEachRemaining(Consumer<? super Integer> action) {
            if (action == null) throw new RuntimeException("Invalid Operation: forEachRemaining()");

            while (head != null) {
                action.accept(head.val);
                head = head.next;
            }
        }
    }

    public Iterator<Integer> iteratorDescending() {
        return new ItrD();
    }

    class ItrD implements Iterator<Integer> {
        NodeFrankEnshtain head;
        NodeFrankEnshtain lastRet;

        ItrD() {
            if (ListFrankEinstain.this.sortFlag) {
                ListFrankEinstain.this.sort();
            }
            this.head = ListFrankEinstain.this.descendingHead;
            this.lastRet = null;
        }

        @Override
        public boolean hasNext() {
            return this.head != null;
        }

        @Override
        public Integer next() {
            if (!this.hasNext()) throw new RuntimeException("Invalid Operation: next()");

            this.lastRet = this.head;
            this.head = this.head.descending;
            return lastRet.val;
        }

        @Override
        public void remove() {
            if (this.lastRet == null) throw new RuntimeException("Invalid Operation: remove()");

            if (lastRet.prev == null) {
                ListFrankEinstain.this.descendingHead = ListFrankEinstain.this.descendingHead.next;
                ListFrankEinstain.this.descendingHead.prev = null;
                return;
            }

            lastRet.prev.next = lastRet.next;
            if (lastRet.next == null) {
                return;
            }
            lastRet.next.prev = lastRet.prev;
            lastRet = null;
        }

        @Override
        public void forEachRemaining(Consumer<? super Integer> action) {
            if (action == null) throw new RuntimeException("Invalid Operation: forEachRemaining()");

            while (head != null) {
                action.accept(head.val);
                head = head.next;
            }
        }
    }

    public Iterator<Integer> iteratorPrev() {
        return new ItrP();
    }

    class ItrP implements Iterator<Integer> {
        NodeFrankEnshtain tail = ListFrankEinstain.this.tail;
        NodeFrankEnshtain lastRet = null;


        @Override
        public boolean hasNext() {
            return this.tail != null;
        }

        @Override
        public Integer next() {
            if (!hasNext()) return null;

            lastRet = this.tail;
            this.tail = this.tail.prev;
            return lastRet.val;
        }

        @Override
        public void remove() {
            if (lastRet == null) {
                throw new RuntimeException("Invalid operation");
            }
            if (lastRet.next == null) {
                ListFrankEinstain.this.tail = ListFrankEinstain.this.tail.prev;
                ListFrankEinstain.this.tail.next = null;
                return;
            }

            lastRet.next.prev = lastRet.prev;
            if (lastRet.prev == null) {
                return;
            }
            lastRet.prev.next = lastRet.next;
            lastRet = null;

        }

        @Override
        public void forEachRemaining(Consumer<? super Integer> action) {
            if (action == null) {
                throw new RuntimeException("Invalid Operation");
            }

            while (this.tail != null) {
                action.accept(this.tail.val);
                this.tail = this.tail.next;
            }
        }
    }


    public static void main(String[] args) {
        ListFrankEinstain obj = new ListFrankEinstain();
        obj.add(5);
        obj.add(3);
        obj.add(6);
        obj.add(6);
        obj.add(6);
        obj.add(6);
        obj.add(7);


        Iterator<Integer> itrA = obj.iteratorAscending();
        Iterator<Integer> itrD = obj.iteratorDescending();
        Iterator<Integer> itrH = obj.iteratorAscending();
        Iterator<Integer> itrP = obj.iteratorDescending();

        while (itrH.hasNext()) {
            System.out.print(itrH.next() + " ");
        }
        System.out.println();
        while (itrP.hasNext()) {
            System.out.print(itrP.next() + " ");
        }
        System.out.println();
        while (itrA.hasNext()) {
            System.out.print(itrA.next() + " ");
        }
        System.out.println();
        while (itrD.hasNext()) {
            System.out.print(itrD.next() + " ");
        }
        System.out.println();

    }
}
