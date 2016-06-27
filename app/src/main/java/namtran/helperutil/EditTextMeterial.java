package namtran.helperutil;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import UIHelper.MaterialEditText;
import UIHelper.Validation.RegexpValidator;
import namtran.helperutil.BasicActivity.BaseActivity;

/**
 * Created by Nam Tran on 31-Dec-15.
 */
public class EditTextMeterial extends BaseActivity {
    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.edittextmeterial_layout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEnableBt();
        initSingleLineEllipsisEt();
        initSetErrorEt();
        initValidationEt();
    }

    private void initEnableBt() {
        final EditText basicEt = (EditText) findViewById(R.id.basicEt);
        final Button enableBt = (Button) findViewById(R.id.enableBt);
        enableBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicEt.setEnabled(!basicEt.isEnabled());
                enableBt.setText(basicEt.isEnabled() ? "DISABLE" : "ENABLE");
            }
        });
    }

    private void initSingleLineEllipsisEt() {
        EditText singleLineEllipsisEt = (EditText) findViewById(R.id.singleLineEllipsisEt);
        singleLineEllipsisEt.setSelection(singleLineEllipsisEt.getText().length());
    }

    private void initSetErrorEt() {
        final EditText bottomTextEt = (EditText) findViewById(R.id.bottomTextEt);
        final Button setErrorBt = (Button) findViewById(R.id.setErrorBt);
        setErrorBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomTextEt.setError("1-line Error!");
            }
        });
        final Button setError2Bt = (Button) findViewById(R.id.setError2Bt);
        setError2Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomTextEt.setError("2-line\nError!");
            }
        });
        final Button setError3Bt = (Button) findViewById(R.id.setError3Bt);
        setError3Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomTextEt.setError("So Many Errors! So Many Errors! So Many Errors! So Many Errors! So Many Errors! So Many Errors! So Many Errors! So Many Errors!");
            }
        });
    }

    private void initValidationEt() {
        final MaterialEditText validationEt = (MaterialEditText) findViewById(R.id.validationEt);
        validationEt.addValidator(new RegexpValidator("Only Integer Valid!", "\\d+"));
        final Button validateBt = (Button) findViewById(R.id.validateBt);
        validateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validate
                validationEt.validate();
            }
        });
    }
}
