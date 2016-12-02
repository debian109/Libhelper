package UIHelper.RecyclerViewHelper.viewUtil;

public class MultiChoiceAdapterNotFoundException extends Exception {

    public MultiChoiceAdapterNotFoundException() {
        super("The adapter of this RecyclerView is not extending the MultiChoiceAdapter class");
    }
}
