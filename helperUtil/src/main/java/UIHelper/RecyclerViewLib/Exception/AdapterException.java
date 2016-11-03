package UIHelper.RecyclerViewLib.Exception;

public class AdapterException extends Exception {

    public AdapterException() {
        super("The adapter of this RecyclerView is not extending the MultiChoiceAdapter class");
    }
}
