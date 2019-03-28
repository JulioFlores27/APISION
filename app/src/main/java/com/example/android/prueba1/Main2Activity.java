package com.example.android.prueba1;



import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//https://www.youtube.com/watch?v=LQE0FZABed4

public class Main2Activity extends AppCompatActivity {
    DataBaseHelper myDb;
    EditText txtName, txtSurName, txtMarks, txtId;

    TextView txtResult;

    Button btnClick;
    Button btnClickRead;
    Button btnClickUpdate;
    Button btnClickDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myDb = new DataBaseHelper(this);
        txtName = (EditText) findViewById(R.id.idName);
        txtSurName = (EditText) findViewById(R.id.idSurame);
        txtMarks = (EditText) findViewById(R.id.idMarks);

        txtId = (EditText) findViewById(R.id.idID);



        btnClick = (Button) findViewById(R.id.idBtn);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe();
            }
        });

        btnClickUpdate = (Button) findViewById(R.id.idBtnUpdate);

        btnClickUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMeUpdate();
            }
        });

        btnClickDelete = (Button) findViewById(R.id.idBtnDelete);

        btnClickDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMeDelete();
            }
        });


        txtResult = (TextView) findViewById(R.id.idResult);
        btnClickRead = (Button) findViewById(R.id.idBtnRead);

        btnClickRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMeRead();
            }
        });






    }



    private void ClickMe() {

        if (txtName.getText().toString().equals("") || txtSurName.getText().toString().equals("") || txtMarks.getText().toString().equals("")){
            Toast.makeText(this, "Llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
        else {
        String name = txtName.getText().toString();
        String surname = txtSurName.getText().toString();
        String marks = txtMarks.getText().toString();
        Boolean result = myDb.insertData(name, surname, marks);

        if (result == true) {
            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data insertion failed", Toast.LENGTH_SHORT).show();
        }
    }}

    private void ClickMeRead() {
        Cursor res = myDb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if (res != null && res.getCount()>0){
            while (res.moveToNext()){
                stringBuffer.append("Id: "+res.getString(0)+"\n");
                stringBuffer.append("Name: "+res.getString(1)+"\n");
                stringBuffer.append("Surname: "+res.getString(2)+"\n");
                stringBuffer.append("Marks: "+res.getString(3)+"\n"+"\n");
            }
            txtResult.setText(stringBuffer.toString());
            Toast.makeText(this, "Data retrieved successfully", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No data to retrieve", Toast.LENGTH_SHORT).show();
        }
    }

    private void ClickMeUpdate() {
        String id = txtId.getText().toString();
        String name = txtName.getText().toString();
        String surname = txtSurName.getText().toString();
        String marks = txtMarks.getText().toString();
        Boolean result = myDb.updateData(id, name, surname, marks);
        if (result == true) {
            Toast.makeText(this, "Data updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No rows affected", Toast.LENGTH_SHORT).show();
        }
    }

    private  void ClickMeDelete(){
        String id = txtId.getText().toString();
        int result = myDb.deleteData(id);
        Toast.makeText(this, result+ " : rows affected", Toast.LENGTH_SHORT).show();

    }



}





    //DataBaseHelper myDb;
//    myDb.getAllData();

//    DataBaseHelper myDb;
//    myDb.insertData()



//    private Button btnCreateDataBase, btnInsertData;
//    private  MySqliteOpenHelper mySqliteOpenHelper;
//    private SQLiteDatabase mDatabase;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//
//        btnCreateDataBase = (Button) findViewById(R.id.btnCreateDataBase);
//        btnCreateDataBase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //Create the DataBase here
//                mySqliteOpenHelper = new MySqliteOpenHelper(getApplicationContext());
//                mDatabase = mySqliteOpenHelper.getReadableDatabase();
//                Cursor cursor = mDatabase.rawQuery("select * from employee ;", null);
//                System.out.println("Main2Activity.onClick: " + cursor.getColumnCount());
//
//            }
//        });
//
//        btnInsertData = (Button) findViewById(R.id.btnInsertData);
//        btnInsertData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                insertData();
//
//            }
//        });
//
//    }
//
//    private void insertData()
//    {
//    String name = "John";
//    int id = 1;
//    String table_name = "employee";
//        ContentValues values = new ContentValues();
//        values.put("id", id);
//        values.put("name", name);
//
//        long rowId = 0;
//
//        if (mDatabase != null)
//        {
//            rowId = mDatabase.insert(table_name, null, values);
//
//            if (rowId != -1)
//            {
//                Toast.makeText(Main2Activity.this, "Inserted successfully ", Toast.LENGTH_SHORT).show();
//            } else
//                {
//                Toast.makeText(Main2Activity.this, "Error inserting", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//    else
//        {
//            Toast.makeText(Main2Activity.this, "Database is null", Toast.LENGTH_SHORT).show();
//        }
//
//    }
//}
