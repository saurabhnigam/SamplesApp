package in.dotSlashA;

import in.dotSlashA.R.id;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

/***Main menu*/
public class GridMenu extends Activity {
  TextView selection;
  ArrayList<String> activityList;
	private String TAG="GridMenu";

  @Override
  public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	    
	    setContentView(R.layout.gridlayout);
	    getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
	    
	    selection=(TextView)findViewById(R.id.selection);
	    GridView g=(GridView) findViewById(R.id.grid);
	    ArrayList<String> activityList = getAllActivities();
	    
	    g.setAdapter(new CustomListAdapter(this,
	                       android.R.layout.simple_list_item_1,
	                       activityList));
	    
	   initHeader();
	  }

  /***Initializes header elements*/
	  private void initHeader() {
		  findViewById(id.header_layout).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri uri=Uri.parse("http://dotSlashA.in/wp/");
				Intent i=new Intent(Intent.ACTION_VIEW, uri);
				startActivity(i);
			}
		});
	
	  }

	private class CustomListAdapter extends ArrayAdapter {
	    Context ctxt;
	    ArrayList<String> listOfActivities;
	    CustomListAdapter(Context ctxt, int resource,
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
	      
	      stylizeView(label);
	      return(convertView);
	    }
	    
	    /***adds style to textview*/
		private void stylizeView(TextView label) {
			label.setTextSize(15);
			label.setTypeface(null,Typeface.BOLD);
		}
	  }
	  
	  View.OnClickListener clicklistener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
				launchActivity( v.getTag().toString());		
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
			}
		} catch (NameNotFoundException e) {
			
			Log.e(TAG, "Error",e);
			e.printStackTrace();
		}
		return activityList;
    }
    
    /**launchActuivity uses intent list to launch clicked activity*/
    public void launchActivity(String fullActivityName)
    {
    	Log.d(TAG , "in launchActivity2 of "+fullActivityName);
		Intent launcher = null;
		launcher = activityIntent(fullActivityName);
		startActivityForResult(launcher, 10);
    }
    
    
	}
