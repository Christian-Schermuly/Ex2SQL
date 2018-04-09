package awesomecompanyofawesomeness.ex2sql;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Chris on 20.06.2016
 */
public class SearchActivity extends AppCompatActivity {
    DBHelper mydb;
    EditText edit_search;
    TableLayout tbl;
    RadioButton rad_name, rad_surname, rad_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mydb = new DBHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


    }

    /**
     * Sucht.... Und macht Tabellen
     * @param view
     */
        public void search(View view){





            clearSearchField();
            TableLayout tbl = (TableLayout)findViewById(R.id.tbl);

            Cursor dbResult = search();
            int rowcount = dbResult.getCount();
            if(rowcount == 0){
                //showMessage("Error", "No Data");
            }

            // for(int rows = 0; rows < rowcount; rows++){
            TableRow tableRowHeader = new TableRow(this);
            tbl.addView(tableRowHeader);
            TextView textViewHeaderName = new TextView(this);
            textViewHeaderName.setTypeface(Typeface.DEFAULT_BOLD);
            textViewHeaderName.setText("Name");
            tableRowHeader.addView(textViewHeaderName);

            TextView textViewHeaderSurName = new TextView(this);
            textViewHeaderSurName.setTypeface(Typeface.DEFAULT_BOLD);
            textViewHeaderSurName.setText("Surname");
            tableRowHeader.addView(textViewHeaderSurName);

            TextView textViewHeaderCity = new TextView(this);
            textViewHeaderCity.setTypeface(Typeface.DEFAULT_BOLD);
            textViewHeaderCity.setText("City");
            tableRowHeader.addView(textViewHeaderCity);

            TextView textViewHeaderInfo = new TextView(this);
            textViewHeaderInfo.setTypeface(Typeface.DEFAULT_BOLD);
            textViewHeaderInfo.setText("Click for Details or Update");
            tableRowHeader.addView(textViewHeaderInfo);

            while(dbResult.moveToNext()) {
                TableRow tableRow = new TableRow(this);
                tbl.addView(tableRow);
                TextView textViewName = new TextView(this);
                textViewName.setText(dbResult.getString(1));
                tableRow.addView(textViewName);

                TextView textViewSurname = new TextView(this);
                textViewSurname.setText (dbResult.getString(2));
                tableRow.addView(textViewSurname);

                TextView textViewCity = new TextView(this);
                textViewCity.setText (dbResult.getString(3));
                tableRow.addView(textViewCity);

                Button infobutton = new Button(this);
                infobutton.setText("Show Details");
                tableRow.addView(infobutton);
                final String id = dbResult.getString(0);
                infobutton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        showDetails(id);
                    }
                });

            }



    }

    public void clearSearchField(){
        TableLayout tbl = (TableLayout)findViewById(R.id.tbl);
        int count = tbl.getChildCount();
        for(int i = 0; i<count;i++){
            View child = tbl.getChildAt(i);
            if(child instanceof TableRow)((ViewGroup)child).removeAllViews();
        }

    }

    /**
     * Die eigentliche Suche. Gibt Entscheided, ewlches suchkriterium genutzt werden soll und liefert...
     * @return ... die Datensätze zurück, die dazu gefunden wurden
     */
    public Cursor search(){

        RadioButton rad_name=(RadioButton)findViewById(R.id.rad_name);
        RadioButton rad_surname=(RadioButton)findViewById(R.id.rad_surname);
        RadioButton rad_city=(RadioButton)findViewById(R.id.rad_city);
        EditText edit_search=(EditText)findViewById(R.id.edit_search);

        if(rad_name.isChecked()){
            Cursor dbResult = mydb.searchForName(edit_search.getText().toString());
            return dbResult;
        }
        if(rad_surname.isChecked()){
            Cursor dbResult = mydb.searchForSurname(edit_search.getText().toString());
            return dbResult;
        }
        if(rad_city.isChecked()){
            Cursor dbResult = mydb.searchForCity(edit_search.getText().toString());
            return dbResult;
        }

        Cursor dbResult = mydb.searchForName("test");


        return dbResult;
    }

    /**
     * Radiobuttons...
     * @param view
     */
    public void onRadNameClicked(View view){
        RadioButton rad_name=(RadioButton)findViewById(R.id.rad_name);
        RadioButton rad_surname=(RadioButton)findViewById(R.id.rad_surname);
        RadioButton rad_city=(RadioButton)findViewById(R.id.rad_city);

        rad_name.setChecked(true);
        rad_surname.setChecked(false);
        rad_city.setChecked(false);

    }
    public void onRadSurnameClicked(View view){
        RadioButton rad_name=(RadioButton)findViewById(R.id.rad_name);
        RadioButton rad_surname=(RadioButton)findViewById(R.id.rad_surname);
        RadioButton rad_city=(RadioButton)findViewById(R.id.rad_city);

        rad_name.setChecked(false);
        rad_surname.setChecked(true);
        rad_city.setChecked(false);

    }
    public void onRadCityClicked(View view){
        RadioButton rad_name=(RadioButton)findViewById(R.id.rad_name);
        RadioButton rad_surname=(RadioButton)findViewById(R.id.rad_surname);
        RadioButton rad_city=(RadioButton)findViewById(R.id.rad_city);

        rad_name.setChecked(false);
        rad_surname.setChecked(false);
        rad_city.setChecked(true);

    }

    /**
     *
     * @param view
     */
    public void onBackClicked(View view){
        finish();
    }

    /**
     *
     * @param id
     */
    public void showDetails(String id){

        Intent intentDetail = new Intent(this, DetailActivity.class);
        intentDetail.putExtra("ID", id);
        startActivityForResult(intentDetail, 2);
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        String res = data.getStringExtra("resCode");

        if(res.equals("0")){
            super.recreate();

        }else{
            super.recreate();
            Toast.makeText(SearchActivity.this, "Entry " + res + " deleted DB", Toast.LENGTH_LONG).show();
        }


    }
}
