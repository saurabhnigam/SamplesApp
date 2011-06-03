package in.dotSlashA.floatingButton;

import in.dotSlashA.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class FloatingViewApp extends ListActivity { 
  private String TAG="FloatingViewApp";

@Override 
  public void onCreate(Bundle icicle) { 
    super.onCreate(icicle); 
    
    String view = getIntent().getStringExtra("view");
    Log.d(TAG ,"value is "+view);
    if(view!= null)
    {
    	if (view.equals("F"))
    		setContentView(R.layout.float_buton_list_framelayout); 
    	else if (view.equals("R"))
    		setContentView(R.layout.float_buton_list_relativelayout);
    	else if (view.equals("A"))
    	{
    		setContentView(R.layout.float_button_list_absolutelayout);
    		findViewById(R.id.floating_button).bringToFront();
    	}
    		
    		
    }
    
    
 
    String[] mStrings = new String[] {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10", "Item 11", "Item 12"}; 
 
    this.setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, mStrings)); 
  } 
} 
