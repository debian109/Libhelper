package UIHelper.RecyclerViewLib.Listener;

public interface SelectionListener {

    void OnItemSelected(int selectedPosition, int itemSelectedCount, int allItemCount);

    void OnItemDeselected(int deselectedPosition, int itemSelectedCount, int allItemCount);

    void OnSelectAll(int itemSelectedCount, int allItemCount);

    void OnDeselectAll(int itemSelectedCount, int allItemCount);
}