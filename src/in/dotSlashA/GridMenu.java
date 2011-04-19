package in.dotSlashA;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class GridMenu extends Activity {
  TextView selection;
  ArrayList<String> activityList;
	private String TAG="GridMenu";

  @Override
  public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    setContentView(R.layout.gridlayout);
	    selection=(TextView)findViewById(R.id.selection);
	    GridView g=(GridView) findViewById(R.id.grid);
	    ArrayList<String> activityList = getAllActivities();
	    
	    g.setAdapter(new FunnyLookingAdapter(this,
	                       android.R.layout.simple_list_item_1,
	                       activityList));
	  }

	  private class FunnyLookingAdapter extends ArrayAdapter {
	    Context ctxt;
	    ArrayList<String> listOfActivities;
	    FunnyLookingAdapter(Context ctxt, int resource,
	                       ArrayList<String> activityList) {
	      super(ctxt, resource, activityList);
	      this.listOfActivities = activityList;
	      this.ctxt=ctxt;
	    }
	    public View getView(int position, View convertView,
	                          ViewGroup parent) {
	      TextView label=(TextView)convertView;
	      if (convertView==null) {
	        convertView=new TextView(ctxt);
	        label=(TextView)convertView;
	      }
	      String actName =listOfActivities.get(position);
	      label.setText(actName.substring(actName.lastIndexOf(".")+1, actName.length()));
	      label.setTag(actName);
	      label.setOnClickListener(clicklistener);
	      return(convertView);
	    }
	  }
	  
	  View.OnClickListener clicklistener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
				TextView tv = (TextView)v;
/*				selection.setText(activityList.get(position));*/
				Log.d(TAG , "Clicked on"+v.getTag().toString() );
				launchActivity2( v.getTag().toString());		
		}
	};

	/**sets class name**/
    protected Intent activityIntent( String componentName) {
        return new Intent().setClassName(GridMenu.this, componentName);
    }
    
    /**gets all activities of the app*/
    ArrayList<String> getAllActivities()
    {
    	ArrayList<String> activityList = new ArrayList<String>();
        PackageManager pm = getPackageManager();
        try {
			ActivityInfo  actInfo[]= pm.getPackageInfo("in.dotSlashA",PackageManager.GET_ACTIVITIES).activities;
			for(int i=0;i<actInfo.length;i++)
			{
			String name = actInfo[i].name;
			activityList.add(name);
			//name.substring(name.lastIndexOf(".")+1, name.length()
			}
		} catch (NameNotFoundException e) {
			
			Log.e(TAG, "Error",e);
			e.printStackTrace();
		}
		return activityList;
    }
    
    /**launchActuivity2 which uses intent list to launch clicked activioty*/
    public void launchActivity2(String fullActivityName)
    {
    	Log.d(TAG , "in launchActivity2 of "+fullActivityName);
		Intent launcher = null;
		launcher = activityIntent(fullActivityName);
		startActivityForResult(launcher, 10);
    }
	}
