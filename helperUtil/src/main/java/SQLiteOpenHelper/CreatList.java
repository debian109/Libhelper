package SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nam Tran on 10-Dec-15.
 */
public class CreatList {
    protected List<String> list;

    public CreatList() {
        this.list = new ArrayList<>();
    }

    public List<String> getList()
    {
        return this.list;
    }
}
