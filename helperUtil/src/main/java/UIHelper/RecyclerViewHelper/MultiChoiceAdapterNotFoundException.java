package UIHelper.RecyclerViewHelper;

class MultiChoiceAdapterNotFoundException extends Exception {

    MultiChoiceAdapterNotFoundException() {
        super("The adapter of this RecyclerView is not extending the MultiChoiceAdapter class");
    }
}
