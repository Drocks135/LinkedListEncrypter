package proj4;

public class DoubleLinkedList<E>  {
	protected NodeD<E> top;      // The first NodeD<E> in the list

    // This instance variable is not required, however if you
    // find it useful, then you are welcome to use it
	protected NodeD<E> cursor;  // The current NodeD<E> in the list

	public DoubleLinkedList() {
		top = null;
		cursor = null;
	}

	public E get(int position) {
		cursor = top;
		for (int i = 0; i < position; i++)
			cursor = cursor.getNext();
		return cursor.getData();

	}

	public void addToBottom(E data){
	    //Case 0: no list
        if(top == null)
            top = new NodeD(data, null, null);
        else {  //Case 1: List exists
            NodeD temp = top;
            while(temp.getNext() != null)
                temp = temp.getNext();

			temp.setNext(new NodeD(data, null, temp));

        }

	}

	public String toString() {
		String retVal = "";
		NodeD<E> cur = top;
		while (cur != null) {
			retVal += cur.getData();
			cur = cur.getNext();
		}

		return retVal;
	}


	// Create the rest of the needed methods.


}
