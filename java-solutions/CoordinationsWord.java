public class CoordinationsWord {
    private int lineNumber;
    private int positionInLine;

    public CoordinationsWord(int lineNumber, int positionInLine) {
        this.lineNumber = lineNumber;
        this.positionInLine = positionInLine;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getPositionInLine() {
        return positionInLine;
    }
}
