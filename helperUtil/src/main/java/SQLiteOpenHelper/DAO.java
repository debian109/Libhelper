package SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Nam Tran on 10-Dec-15.
 */
public class DAO {
    protected DataBaseSQL db;
    protected Context context;
    public void setContext(Context context,String DATABASENAME,String Table1,String Table2,String Table3, int version)
    {
        db = new DataBaseSQL(context,DATABASENAME,Table1,Table2,Table3,version);
        this.context = context;
    }
    public Cursor load_database (String table , String[] column)
    {
        return db.getReadableDatabase().query(table,column,null,null,null,null,null);
    }
    public Cursor load_database1 (String table , String[] column, String selection)
    {
        return db.getReadableDatabase().query(table,column,selection,null,null,null,null);
    }

    public long insert(String table,ContentValues contentValues)
    {
        return db.getWritableDatabase().insert(table,null,contentValues);
    }
    public long update(String table,ContentValues contentValues,String selection,String[] args)
    {
        return db.getWritableDatabase().update(table,contentValues,selection,args);
    }
    public long delete(String table,String whereClause , String[] args)
    {
        return db.getWritableDatabase().delete(table,whereClause,args);
    }
}
