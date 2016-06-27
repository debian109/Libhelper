package SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by Nam Tran on 10-Dec-15.
 */
public class DataBaseSQL extends SQLiteOpenHelper {

    protected String Table1,Table2,Table3;

    public DataBaseSQL(Context context,String DATABASENAME,String Table1,String Table2,String Table3, int version) {
        super(context,DATABASENAME,null,version);
        this.Table1=Table1;
        this.Table2=Table2;
        this.Table3=Table3;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        CreateTableSQL tableSQL = new CreateTableSQL();
        List<String> ListString = tableSQL.CreateTableSQL(Table1, Table2, Table3);
        for (int i = 0; i < ListString.size(); i++)
        {
            db.execSQL(ListString.get(i));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (newVersion)
        {
            case 3 :
            {
                if (oldVersion == 2)
                    break;
            }
            case 2 :
                CreateTableSQL tableSQL = new CreateTableSQL();
                        List<String> ListString = tableSQL.CreateTableSQL(Table1, Table2, Table3);
                            for (int i = 0; i < ListString.size(); i++)
                            {
                                db.execSQL(ListString.get(i));
                            }
            default:
                break;
        }
    }
}
