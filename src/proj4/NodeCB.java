package proj4;

public class NodeCB {
    private int clipBoardNumber;
    private NodeD<Character> topOfClipBoard;
    private NodeCB next;

    public NodeCB(int clipBoardNumber, NodeCB next, NodeD topOfClipBoard) {
        this.clipBoardNumber = clipBoardNumber;
        this.next = next;
        this.topOfClipBoard = topOfClipBoard;
    }

    public NodeCB getNext(){
         return next;
    }

    public void setNext(NodeCB next){
        this.next = next;
    }

    public int getClipBoardNumber() {
        return clipBoardNumber;
    }

    public void setClipBoardNumber(int clipBoardNumber) {
        this.clipBoardNumber = clipBoardNumber;
    }

    public NodeD getTopOfClipBoard() {
        return topOfClipBoard;
    }

    public void setTopOfClipBoard(NodeD topOfClipBoard) {
        this.topOfClipBoard = topOfClipBoard;
    }
}
