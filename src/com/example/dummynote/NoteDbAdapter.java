/**
 * 
 */
package com.example.dummynote;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;

/**
 * @author hfli
 *
 */
public class NoteDbAdapter {
	
	private static final String DATABASE_NAME="notes.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE="notes";
	private static final String TABLE_id="_id";
	private static final String TABLE_note="note";
	private static final String TABLE_created="created";
	private static final String TABLE_modified="modified";
	private Context mCtx=null;

	
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;
	private static final String DATABASE_CREATE=
			"create table notes("
		+"_id INTEGER PRIMER KEY,"
		+"note TEXT,"
		+"created INTEGER,"
		+"modified INTEGER"
		+");";
	String[] strCols = new String[]{
			TABLE_id,
			TABLE_note,
			TABLE_created
	};
	
	@SuppressWarnings("unused")
	private static class DatabaseHelper extends SQLiteOpenHelper{

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(DATABASE_CREATE);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS"+DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	public NoteDbAdapter(Context ctx){
		this.mCtx=ctx;
	}
	public NoteDbAdapter open()throws SQLException{
		dbHelper=new DatabaseHelper(mCtx);
		db=dbHelper.getWritableDatabase();
		return this;
	}
	public void close(){
		dbHelper.close();
	}
	public Cursor getall() {
		// TODO Auto-generated method stub
		//return db.rawQuery("SELECT * FROM notes", null);
		return db.query(DATABASE_TABLE, strCols, null,null,null,null,null);
	}
	public long create(String Note){
		Date now = new Date();
		ContentValues args = new ContentValues();
		args.put(TABLE_note,Note);
		args.put(TABLE_created,now.getTime());
		return db.insert(DATABASE_TABLE, null, args);
	}
	public boolean delete(long selectedItemId) {
		// TODO Auto-generated method stub
		return db.delete(DATABASE_TABLE, TABLE_id+"="+selectedItemId, null)>0;
		
	}
	
	

}
