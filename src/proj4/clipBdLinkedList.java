package proj4;

public class clipBdLinkedList {
    private NodeCB top;
    private NodeCB tail;

    public clipBdLinkedList() {
        tail = top = null;
    }

    public void addClipBoard(int clipBoardNumber, NodeD topOfClipBoard){
        //Case 0: no list
        if (top == null) {
            top = new NodeCB(clipBoardNumber, null, topOfClipBoard);
            tail = top;
        } else {
            boolean foundClipBoard = false;
            NodeCB temp = top;
            //Case 1: Clipboard number exists
            do {
                if(clipBoardNumber == temp.getClipBoardNumber()) {
                    temp.setTopOfClipBoard(topOfClipBoard);
                    foundClipBoard = true;
                }
                temp = temp.getNext();
            } while(temp.getNext() != null);

            //Case 2: Clipboard number does not exist
            if(!foundClipBoard){
                NodeCB temp2 = new NodeCB(clipBoardNumber, null, topOfClipBoard);
                temp.setNext(temp2);
                tail = temp2;
            }
        }
    }

}
