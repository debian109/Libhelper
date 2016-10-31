package UIHelper.ViewPagerAnimation.Color;

public enum ColorChangeType {

    RGB(0),
    HSV(1);

    private int type;

    /**
     * how to perform the process of changing color
     * @param type
     */
    ColorChangeType(int type) {
        this.type = type;
    }

}
