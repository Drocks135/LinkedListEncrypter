package proj4;

import javax.swing.JOptionPane;

public class DoubleLinkedList<E>  {
	protected NodeD<E> top;      // The first NodeD<E> in the list
	JOptionPane pane = new JOptionPane();
    // This instance variable is not required, however if you
    // find it useful, then you are welcome to use it
	protected NodeD<E> cursor;  // The current NodeD<E> in the list
	private int listLength = -1;

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
        if(top == null) {
			top = new NodeD(data, null, null);
			listLength++;
		}
        else {  //Case 1: List exists
            NodeD temp = top;
            while(temp.getNext() != null)
                temp = temp.getNext();

			temp.setNext(new NodeD(data, null, temp));
			listLength++;
        }

	}

	public char remove(int index){
		NodeD temp = top;
		char c;
		if(top.getNext() == null) {
			top = null;
			c = Character.MIN_VALUE;
		} else if(index == 0) {
			c = (char)temp.getData();
			top = top.getNext();
			listLength--;
		} else {
			for (int i = 0; i < index -  1; i++)
				temp = temp.getNext();
			c = (char)temp.getNext().getData();
			temp.setNext(temp.getNext().getNext());
			listLength--;
		}
		return c;
	}

	public int getListLength(){
		return listLength;
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
