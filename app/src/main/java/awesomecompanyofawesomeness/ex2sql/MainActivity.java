package awesomecompanyofawesomeness.ex2sql;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Chris on 18.06.2016
 */
public class MainActivity extends AppCompatActivity {
    DBHelper mydb;
    TextView txtv_m_counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_insertAct =(Button)findViewById(R.id.btn_insertAct);
        Button btn_showtAct =(Button)findViewById(R.id.btn_showAct);
        mydb = new DBHelper(this);
        setCounterMain();


    }

    /**
     * Button bringt zum Insert-Fensterchen
     * @param view
     */
    public void onInsertClicked(View view){
        Intent i = new Intent(getApplicationContext(), InsertActivity.class);
        startActivityForResult(i, 2);


    }

    /**
     * Startet Activity, welche jegliche Datensätze auflistet
     * @param view
     */
    public void onShowClicked(View view){
        Intent i = new Intent(getApplicationContext(), ShowActivity.class);
        startActivityForResult(i, 2);
    }

    /**
     * Damit der Counter auch immer Aktuell ist ;)
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        super.recreate();
    }

    /**
     * Setzt den Counter
     */
    public void setCounterMain(){
        TextView txtv_m_counter = (TextView)findViewById(R.id.txtv_m_counter);
        Cursor res = mydb.getAll();
        if(res.getCount() == 0){
            txtv_m_counter.setText("There are currently no Entrys in the DB");
        }else{
            txtv_m_counter.setText("There are currently " + res.getCount() + " Entry in the DB");
        }
    }

    /**
     * öffnet das Suchfenster
     * @param view
     */
    public void onSearchClicked(View view){
        Intent i = new Intent(getApplicationContext(),SearchActivity.class);
        startActivityForResult(i,2);
    }


}
