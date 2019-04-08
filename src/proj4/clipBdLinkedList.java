package proj4;

public class clipBdLinkedList {
    private NodeCB top;
    private NodeCB tail;

    public clipBdLinkedList() {
        tail = top = null;
    }

    public void addClipBoard(int clipBoardNumber, NodeD topOfClipBoard) {
        //Case 0: no list
        if (top == null) {
            top = new NodeCB(clipBoardNumber, null, topOfClipBoard);
            tail = top;
        } else {
            boolean foundClipBoard = false;
            NodeCB temp = top;
            //Case 1: Clipboard number exists
            while (temp != null && !foundClipBoard)
                if (clipBoardNumber == temp.getClipBoardNumber()) {
                    temp.setTopOfClipBoard(topOfClipBoard);
                    foundClipBoard = true;
                } else
                    temp = temp.getNext();


            //Case 2: Clipboard number does not exist
            temp = top;
            while(temp.getNext() != null)
                temp = temp.getNext();

            if (!foundClipBoard) {
                NodeCB temp2 = new NodeCB(clipBoardNumber, null, topOfClipBoard);
                temp.setNext(temp2);
                tail = temp2;
            }
        }
    }

    public String cbToString(int clipBoardNumber) {
        NodeCB temp = top;
        String retVal = "";
        NodeD cur;

            while (temp != null) {
                if (temp.getClipBoardNumber() == clipBoardNumber) {
                    cur = temp.getTopOfClipBoard();
                    while (cur != null) {
                        retVal += cur.getData();
                        cur = cur.getNext();
                    }
                }
                    temp = temp.getNext();
            }
            return retVal;
        }
    }


