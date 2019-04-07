package proj4;


public class DoubleLinkedList<E>  {
	protected NodeD<E> top;      // The first NodeD<E> in the list
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

	public NodeD getTop(){
		return top;
	}

	public String getSection(int start, int stop){
		if(top == null){
			return "";
		}
		NodeD temp = top;
		String s = "";
		for(int i = 0; i < start; i++)
			temp = temp.getNext();
		for(int i = start; i <= stop; i++){
			s = s + temp.getData();
			temp = temp.getNext();
		}

		return s;
	}

	public void addToBottom(E data){
	    //Case 0: no list
        if(top == null) {
			top = new NodeD(data, null, null);
			listLength++;
		}
        else if (top.getNext() == null){  //Case 1: 1 Item in list
            top.setNext(new NodeD(data, null, top));
			listLength++;
        } else { //Case 2: Normal list
			NodeD temp = top;
			NodeD temp2;
			while(temp.getNext() != null) //cycle to the end
				temp = temp.getNext();

			temp2 = new NodeD(data, null, temp);
			temp.setNext(temp2);
			listLength++;
		}

	}

	public void insertBefore(E data, int index){
		NodeD temp;
		NodeD temp2;
		//Case 0: No list
		if(top == null) {
			top = new NodeD(data, null, null);
			listLength++;
		}
		if (index == 0) {
			//If the insert is the first item
			temp = top;
			top = new NodeD(data, temp, null);
			temp.setPrev(top);
			listLength++;
		} else {
			temp = top;
			for(int i = 0; i < index; i++)
				temp = temp.getNext();

			temp2 = new NodeD(data, temp, temp.getPrev());
			temp.getPrev().setNext(temp2);
			temp.setPrev(temp2);
			listLength++;
		}



	}

	/******************************************************************
	 * @param index The index of the object to be removed
	 * @return the character that is being removed
	 *****************************************************************/
	public char remove(int index){
		NodeD temp = top;
		NodeD temp2;
		char c;
		//Case 0: Deleting last item in list
		if(top.getNext() == null) {
			top = null;
			c = Character.MIN_VALUE;
			//Case 1: removing the first item in the list
		} else if(index == 0) {
			c = (char)temp.getData();
			top = top.getNext();
			top.setPrev(null);
			listLength--;
		} else {
			for (int i = 0; i < index; i++)
				temp = temp.getNext();
			c = (char)temp.getData();

			//Case 2: removing a middle item in the list
			if (temp.getNext() != null){
				temp2 = temp.getNext();
				temp = temp.getPrev();
				temp.setNext(temp2);
				temp2.setPrev(temp);
			} //Case 3: removing the end item in the list
			else {
				temp2 = temp;
				temp = temp.getPrev();
				temp.setNext(null);
				temp2.setPrev(null);

			}
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

	public String toBackString() {
		String retVal = "";
		NodeD<E> cur = top;
		while (cur.getNext() != null) {
			cur = cur.getNext();
		}
		while (cur != null) {
			retVal += cur.getData();
			cur = cur.getPrev();
		}

		return retVal;
	}

	// Create the rest of the needed methods.


}
