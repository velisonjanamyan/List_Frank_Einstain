package out;

public class NodeFrankEnshtain {
    NodeFrankEnshtain prev;
    NodeFrankEnshtain next;
    NodeFrankEnshtain ascending;
    NodeFrankEnshtain descending;
    int val;

    NodeFrankEnshtain(int val) {
        this.prev = null;
        this.ascending = null;
        this.descending = null;
        this.next = null;
        this.val = val;
    }

    NodeFrankEnshtain(int val, NodeFrankEnshtain next, NodeFrankEnshtain prev) {
        this.prev = prev;
        this.ascending = null;
        this.descending = null;
        this.next = next;
        this.val = val;
    }

}
