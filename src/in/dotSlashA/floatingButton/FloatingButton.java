package in.dotSlashA.floatingButton;

import in.dotSlashA.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FloatingButton extends ListActivity{

	 
	  @Override 
	  public void onCreate(Bundle icicle) { 
	    super.onCreate(icicle); 
	    setContentView(R.layout.float_button_menu); 
	 
	    String[] mStrings = new String[] {"Floating View Using FrameLayout",
	    		"Floating View Using RelativeLayout",
	    		"Floating View Using AbsoluteLayout"}; 
	 
	    this.setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, mStrings)); 
	  } 

	  @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		startFloatinViewActivity(position);
		
	}

	  /***launches FloatinViewActivity as per the position*/
	private void startFloatinViewActivity(int i) {
		Intent intent = new Intent(FloatingButton.this , FloatingViewApp.class);
		switch (i) {
		case 0:
			//i.e.Floating View Using FrameLayout
			intent.putExtra("view", "F");
			startActivity(intent);
			break;
		case 1:
			//i.e.Floating View Using RelativeLayout
			intent.putExtra("view", "R");
			startActivity(intent);
			break;
		case 2:
			//i.e.Floating View Using AbsoluteLayout
			intent.putExtra("view", "A");
			startActivity(intent);
			break;
			

		default:
			break;
		}
		
	}
	  
	  
	
}
