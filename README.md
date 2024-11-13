ListFrankEinstain
ListFrankEinstain is a custom doubly linked list implementation in Java, with added capabilities for different types of iteration and sorting. This implementation offers several iterators to traverse the list in various orders: ascending, descending, head-to-tail, and tail-to-head.

Features
Doubly Linked List Structure: Each node has references to both the previous and next nodes.
Sorting: The list automatically sorts nodes based on their values in ascending and descending order using auxiliary references.
Multiple Iterators:
iterator(): Default iterator from head to tail.
iteratorAscending(): Iterates in ascending order of node values.
iteratorDescending(): Iterates in descending order of node values.
iteratorPrev(): Iterates from tail to head.
Classes
ListFrankEinstain: The main class containing methods for adding, removing, and accessing nodes.
NodeFrankEnshtain: Represents each node in the linked list, with integer value and references for both ascending and descending order traversal.
Custom Iterators:
Itr, ItrA, ItrD, and ItrP for different traversal methods, each implementing the Iterator<Integer> interface.
Each iterator supports next, remove, and forEachRemaining methods with custom logic for various traversal orders.
Usage
Adding Elements: Use add(int val) to append a new node with the given value to the end of the list.
Removing Elements:
pop(): Removes and returns the last element.
remove(int pos): Removes an element at the specified position.
Iterating Through Elements:
iterator(): For head-to-tail traversal.
iteratorAscending(): For ascending order traversal.
iteratorDescending(): For descending order traversal.
iteratorPrev(): For tail-to-head traversal.
