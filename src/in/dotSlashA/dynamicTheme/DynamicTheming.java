package in.dotSlashA.dynamicTheme;

import in.dotSlashA.R;
import in.dotSlashA.R.id;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**An activity to test themeing from Dialog to other Themes 
 * 
 * Solution to th problem mentioned here 
 * http://stackoverflow.com/questions/4967904/androidhow-to-programmatically-set-an-activitys-theme-to-theme-dialog/6093678#6093678
 * 
 * <br>
 * 
 * How to programmatically set an Activity's theme to Theme.Dialog?
 * */

/***<br>
 * Solution: Use super.setTheme in code & in manifest set Theme.Dialog*/
public class DynamicTheming extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/***randomly show the different views.
		 * You may change it as per your need say 
		 * if(isThemed)
		 * super.setTheme(R.style.MyTheme);*/

		if(System.currentTimeMillis() % 2 ==0)
		super.setTheme(android.R.style.Theme_Black_NoTitleBar_Fullscreen);
		super.onCreate(savedInstanceState);
		
		/**adding content*/
		setContentView(R.layout.title);
	   initLayout();
	}
	
	  /***Initializes header elements*/
	  private void initLayout() {
		  findViewById(id.header_layout).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri uri=Uri.parse("http://dotSlashA.in/wp/");
				Intent i=new Intent(Intent.ACTION_VIEW, uri);
				startActivity(i);
			}
		});
	
	  }
}
