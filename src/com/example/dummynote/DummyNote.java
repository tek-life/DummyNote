package com.example.dummynote;


import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

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
	
	private void setAdapter(){
		ListAdapter adapter= new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				note_array
				);
		setListAdapter(adapter);
	}

	
}
