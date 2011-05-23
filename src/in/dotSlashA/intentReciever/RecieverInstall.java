package in.dotSlashA.intentReciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


/***This Receiver receives the PACKAGE_REMOVED  , PACKAGE_ADDED actions here is the link to problem 
 * http://stackoverflow.com/questions/5217237/package-removed-then-package-added-are-fired-along-with-package-replaced-intent <br>
 * 
 * 
 * On each Replace we get 3 broadcasts with actions

	*First with PACKAGE_REMOVED which fires AppReciever
	*then after PACKAGE_ADDED which again fires AppReciever
	*And then after few seconds PACKAGE_REPLACED which again fires AppReciever
So how to catch only the Replace Action*/

/**<br>
 * Solution: Catch EXTRA_REPLACING key to know whether app is replaced or is actually installed**/
public class RecieverInstall extends BroadcastReceiver
{
	private String TAG = "RecieverInstall";
	@Override
	public void onReceive (final Context context, final Intent intent)
	{	
		Log.d(TAG, "Intent's action is "+intent.getAction());
		Log.d(TAG , "Intent.EXTRA_REPLACING is "+intent.getBooleanExtra(Intent.EXTRA_REPLACING, false));
	//Catch those actions as per your needs & update data as per Uninstall or replace
	
	}
	
}
