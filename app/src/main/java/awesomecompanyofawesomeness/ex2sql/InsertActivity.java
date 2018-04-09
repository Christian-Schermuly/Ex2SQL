package awesomecompanyofawesomeness.ex2sql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 *Created by Chris on 18.06.2016
 */
public class InsertActivity extends AppCompatActivity {
    EditText editText_i_name, editText_i_surname, editText_i_city, editText_i_street, editText_i_phone, editText_i_mail;
    Button btn_insert;
    DBHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        mydb = new DBHelper(this);

         editText_i_name = (EditText)findViewById(R.id.editText_i_name);
         editText_i_surname = (EditText)findViewById(R.id.editText_i_surname);
         editText_i_city = (EditText)findViewById(R.id.editText_i_city);
         editText_i_street = (EditText)findViewById(R.id.editText_i_street);
         editText_i_phone = (EditText)findViewById(R.id.editText_i_phone);
         editText_i_mail = (EditText)findViewById(R.id.editText_i_mail);
         btn_insert = (Button)findViewById(R.id.btn_insert);
    }


    /**
     * Schreibt die Daten als tupel in die DB
     *
     * Todo: Irgendwas, damit Felder nicht leer sein können...
     * @param view
     */
    public void onClickInsert(View view){
        if(filledChecker()){


            boolean isInserted = mydb.insertData(editText_i_name.getText().toString(), editText_i_surname.getText().toString(),
                    editText_i_city.getText().toString(), editText_i_street.getText().toString(),
                    Integer.parseInt(editText_i_phone.getText().toString()), editText_i_mail.getText().toString());

            if(isInserted = true){
                Toast.makeText(InsertActivity.this, "Entry added to DB", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(InsertActivity.this, "Entry could not be saved!", Toast.LENGTH_LONG).show();
             }
        }else{
            Toast.makeText(InsertActivity.this, "Make sure no Fields are empty!", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Zurück... Ganz klassisch...
     * @param view
     */
    public void onClickBack(View view){
        finish();
    }

    /**
     *
     * @param view
     */
    public void onClickRefresh(View view){
        editText_i_name = (EditText)findViewById(R.id.editText_i_name);
        editText_i_surname = (EditText)findViewById(R.id.editText_i_surname);
        editText_i_city = (EditText)findViewById(R.id.editText_i_city);
        editText_i_street = (EditText)findViewById(R.id.editText_i_street);
        editText_i_phone = (EditText)findViewById(R.id.editText_i_phone);
        editText_i_mail = (EditText)findViewById(R.id.editText_i_mail);

        editText_i_name.setText("");
        editText_i_surname.setText("");
        editText_i_city.setText("");
        editText_i_street.setText("");
        editText_i_phone.setText("");
        editText_i_mail.setText("");

    }

    /**
     *
     * @return true, wenn in jedem Feld etwas steht, ansonnsten false
     */
    public boolean filledChecker(){
        editText_i_name = (EditText)findViewById(R.id.editText_i_name);
        editText_i_surname = (EditText)findViewById(R.id.editText_i_surname);
        editText_i_city = (EditText)findViewById(R.id.editText_i_city);
        editText_i_street = (EditText)findViewById(R.id.editText_i_street);
        editText_i_phone = (EditText)findViewById(R.id.editText_i_phone);
        editText_i_mail = (EditText)findViewById(R.id.editText_i_mail);
        if(editText_i_name.getText().toString().equals("")){
            return false;
        }
        if(editText_i_surname.getText().toString().equals("")){
            return false;
        }
        if(editText_i_city.getText().toString().equals("")){
            return false;
        }
        if(editText_i_street.getText().toString().equals("")){
            return false;
        }
        if(editText_i_phone.getText().toString().equals("")){
            return false;
        }
        if(editText_i_mail.getText().toString().equals("")){
            return false;
        }
        return true;

    }
}
