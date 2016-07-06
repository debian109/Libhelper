package namtran.helperutil.ActivityExample.FancyButtonActivityExample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import namtran.helperutil.R;

public class FancyButtonActivity extends ListActivity implements AdapterView.OnItemClickListener {

    String[] listItems = {"XML buttons", "Programmatically Buttons",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fancy_button);

        setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems));
        getListView().setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch(position){
            case 0 :
                Intent intentXML = new Intent(FancyButtonActivity.this,XmlButtons.class);
                startActivity(intentXML);

                break;
            case 1 :
                Intent intentProg = new Intent(FancyButtonActivity.this,ProgramButtons.class);
                startActivity(intentProg);
                break;
            default: throw new IllegalArgumentException("Hold up, hold my phone :)");
        }
    }
}
