package awesomecompanyofawesomeness.ex2sql;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  Created by Chris on 19.06.2016
 */
public class DetailActivity extends AppCompatActivity {
    EditText editText_d_name, editText_d_surname, editText_d_city, editText_d_street, editText_d_phone, editText_d_mail;
    TextView textView_d_id;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mydb = new DBHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        drawResults(id);

    }



    /**
     * Zeigt deprimierende Nachrichten, wie "Keine Daten vorhanden!"
     * @param title
     * @param Massage
     */
    public void showMessage(String title, String Massage){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Massage);
        builder.show();
    }

    /**
     * Zurück zur Übersicht...
     * @param view
     */
    public void onClickDetailBack(View view){
        Intent intent = new Intent();
        String res = "0";
        intent.putExtra("resCode", res);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * Ersetzt die Werte des Tupels mit den Werten in den einzelnen editTexten
     * @param view
     */
    public void onClickUpdate(View view) {
        editText_d_name = (EditText) findViewById(R.id.editText_d_name);
        editText_d_surname = (EditText) findViewById(R.id.editText_d_surname);
        editText_d_city = (EditText) findViewById(R.id.editText_d_city);
        editText_d_street = (EditText) findViewById(R.id.editText_d_street);
        editText_d_phone = (EditText) findViewById(R.id.editText_d_phone);
        editText_d_mail = (EditText) findViewById(R.id.editText_d_mail);
        textView_d_id = (TextView) findViewById(R.id.textView_d_id);


        if (filledChecker()) {
            mydb.updateData(textView_d_id.getText().toString(), editText_d_name.getText().toString(), editText_d_surname.getText().toString(),
                    editText_d_city.getText().toString(), editText_d_street.getText().toString(),
                    Integer.parseInt(editText_d_phone.getText().toString()), editText_d_mail.getText().toString());
            Toast.makeText(DetailActivity.this, "Entry Updated", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(DetailActivity.this, "Make sure, no Field is empty!", Toast.LENGTH_LONG).show();
        }
    }
    /**
     * Löscht den Tupel und führt zurück zur Übersicht. Generiert eine Mitteilung ob Löschung erfolgeich war
     * @param view
     */
    public void onClickDelete(View view){


        textView_d_id =(TextView)findViewById(R.id.textView_d_id);
        mydb.deleteData(textView_d_id.getText().toString());
        Intent intent = new Intent();
        String res = textView_d_id.getText().toString();
        intent.putExtra("resCode", res);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * Schreibt die Werte des Tupels in die Edit-Felder
     * @param id
     */
    public void drawResults(String id){
        Cursor res = mydb.getDetail(id);
        editText_d_name = (EditText)findViewById(R.id.editText_d_name);
        editText_d_surname = (EditText)findViewById(R.id.editText_d_surname);
        editText_d_city = (EditText)findViewById(R.id.editText_d_city);
        editText_d_street = (EditText)findViewById(R.id.editText_d_street);
        editText_d_phone = (EditText)findViewById(R.id.editText_d_phone);
        editText_d_mail = (EditText)findViewById(R.id.editText_d_mail);
        textView_d_id =(TextView)findViewById(R.id.textView_d_id);
        res.moveToNext();
        textView_d_id.setText(res.getString(0));
        editText_d_name.setText(res.getString(1));
        editText_d_surname.setText(res.getString(2));
        editText_d_city.setText(res.getString(3));
        editText_d_street.setText(res.getString(4));
        editText_d_phone.setText(res.getString(5));
        editText_d_mail.setText(res.getString(6));


    }

    public boolean filledChecker(){
        editText_d_name = (EditText)findViewById(R.id.editText_d_name);
        editText_d_surname = (EditText)findViewById(R.id.editText_d_surname);
        editText_d_city = (EditText)findViewById(R.id.editText_d_city);
        editText_d_street = (EditText)findViewById(R.id.editText_d_street);
        editText_d_phone = (EditText)findViewById(R.id.editText_d_phone);
        editText_d_mail = (EditText)findViewById(R.id.editText_d_mail);
        textView_d_id =(TextView)findViewById(R.id.textView_d_id);
        if(editText_d_name.getText().toString().equals("")){
            return false;
        }
        if(editText_d_surname.getText().toString().equals("")){
            return false;
        }
        if(editText_d_city.getText().toString().equals("")){
            return false;
        }
        if(editText_d_street.getText().toString().equals("")){
            return false;
        }
        if(editText_d_phone.getText().toString().equals("")){
            return false;
        }
        if(editText_d_mail.getText().toString().equals("")){
            return false;
        }
        return true;
    }
}
