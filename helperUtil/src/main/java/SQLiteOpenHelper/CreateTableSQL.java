package SQLiteOpenHelper;

import java.util.List;

/**
 * Created by Nam Tran on 10-Dec-15.
 */
public class CreateTableSQL extends CreatList{
    public List<String> CreateTableSQL(String Table1,String Table2 ,String Table3)
    {
        if (Table1 != null)
        {
            list.add(Table1);
        }
        else if (Table2 != null)
        {
            list.add(Table2);
        }
        else if (Table3 != null)
        {
            list.add(Table3);
        }
        return list;
    }
}
