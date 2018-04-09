package awesomecompanyofawesomeness.ex2sql;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 *
 */
public class ShowActivity extends AppCompatActivity {
    DBHelper mydb;
    TableLayout tbl;
    TextView txtv_s_counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tbl = (TableLayout)findViewById(R.id.tbl);
        mydb = new DBHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Button btn_deleteAll = (Button)findViewById(R.id.btn_deleteAll);
        drawTable();
    }



    public void showMessageNoData(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true).setTitle("Noone here?")
                .setMessage("No data aviable!").setNeutralButton("OK", null).show();
    }

    public void drawTable(){
        Cursor dbResult = mydb.getAll();
        int rowcount = dbResult.getCount();
        TextView txtv_s_counter =(TextView)findViewById(R.id.txtv_s_counter);
        txtv_s_counter.setText("Results: " + rowcount);
        if(rowcount == 0){
            showMessageNoData();
        }
        TableLayout tbl = (TableLayout)findViewById(R.id.tbl);
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

    public void onClickDeleteAll(View view){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Delete All")
                .setMessage("Are you sure you want to delete all Data?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAll();

                    }
                })
                .setNegativeButton("No", null)
                .show();


    }

    public void showDetails(String id){

        Intent intentDetail = new Intent(this, DetailActivity.class);
        intentDetail.putExtra("ID", id);
        startActivityForResult(intentDetail, 2);
    }

    public void deleteAll(){
        mydb.deleteAll();
        super.recreate();
    }
    public void onClickBack(View view){
        finish();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        String res = data.getStringExtra("resCode");

        if(res.equals("0")){
            super.recreate();

        }else{
            super.recreate();
            Toast.makeText(ShowActivity.this, "Entry " + res + " deleted DB", Toast.LENGTH_LONG).show();
        }


    }

}
