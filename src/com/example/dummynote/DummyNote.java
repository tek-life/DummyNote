package com.example.dummynote;


import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;


public class DummyNote extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		getListView().setEmptyView(findViewById(R.id.empty));
		setAdapter();
	}
	

	private String[] note_array={
		/*	"Gasolin",
			"Haifeng Li",
			"Lin Yang",
			"MagicIon"
			*/
	};
	private NoteDbAdapter mDbHelper;
	private Cursor mNotesCursor;
	protected static final int MENU_INSERT = Menu.FIRST;
	private int mNoteNumber = 1;
	public static final int MENU_DELETE = Menu.FIRST+1;
	
	private void fillData(){
		mNotesCursor = mDbHelper.getall();
		startManagingCursor(mNotesCursor);
		
		String[] from = new String[]{"note"};
		int[] to = new int[]{android.R.id.text1};
		
		SimpleCursorAdapter adapter= new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1,
				mNotesCursor,from,to);
		setListAdapter(adapter);			
	}
	private void setAdapter(){
		mDbHelper = new NoteDbAdapter(this);
		mDbHelper.open();
		fillData();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		menu.add(0,MENU_INSERT,0,"新增纪事");
		menu.add(0,MENU_DELETE,0,"删除纪事");
		return super.onCreateOptionsMenu(menu);
	} 
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreatePanelMenu(int, android.view.Menu)
	 */
	@Override
	public boolean onCreatePanelMenu(int featureId, Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreatePanelMenu(featureId, menu);
	}
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case MENU_INSERT:
			String noteName = "Note" + mNoteNumber++;
			mDbHelper.create(noteName);
			fillData();
			return true;
		case MENU_DELETE:
			mDbHelper.delete(getListView().getSelectedItemId());
			fillData();
			return true;		
		}
		return super.onOptionsItemSelected(item);
	}

	
}
