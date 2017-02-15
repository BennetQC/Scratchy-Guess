/**
 *	Copyright [2016] [Bennet Makwakwa]
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	    http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
 */
package com.cyberlite.scratchyguess;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.cyberlite.scratchyguess.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
* provide main menu for game 
* @author  Bennet Makwakwa
* @version 1.0
* @since   2016 
*/
public class MainActivity extends Activity {

	private TextView mainScreenTotalPoints;
	DataStorage dataStorage;
	Button btnSound;
	private AdView mAdView;
	private InterstitialAd mInterstitial;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnSound=(Button)findViewById(R.id.sound);
		
		 mInterstitial = new InterstitialAd(getApplicationContext());
		 mInterstitial.setAdUnitId(getResources().getString(R.string.admob_intertestial_id));
		 mInterstitial.loadAd(new AdRequest.Builder().build());
		
		 mInterstitial.setAdListener(new AdListener() {
	    	  @Override
	    	public void onAdLoaded() {
	    		// TODO Auto-generated method stub
	    		super.onAdLoaded();
	    		if (mInterstitial.isLoaded()) {
		            mInterstitial.show();
			  }
	    	}
		});
		dataStorage = new DataStorage(getApplicationContext());
		mainScreenTotalPoints = (TextView)findViewById(R.id.mainScreenTotalPoints);
		Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/VarelaRound-Regular.ttf");
		mainScreenTotalPoints.setTypeface(typeface);
		mainScreenTotalPoints.setText(dataStorage.getTotalScoreLabel());
		
		mAdView = (AdView) findViewById(R.id.adView);
	    mAdView.loadAd(new AdRequest.Builder().build());
	    

		if (dataStorage.isSoundEnabled())
		{
			btnSound.setText(getString(R.string.mute));
			btnSound.setBackgroundResource(R.drawable.mute_01);
			return;
		} else
		{
			btnSound.setText(getString(R.string.sound));
			btnSound.setBackgroundResource(R.drawable.mute_02);
			return;
		}
	}

	public void onButtonClickPlay(View view)
	{
		startActivity(new Intent(this, CategoriesScreen.class));
	}

	public void onButtonClickHowToPlay(View view)
	{
		startActivity(new Intent(this, HowToPlay.class));
	}

	public void onButtonClickSound(View view)
	{
		if (dataStorage.isSoundEnabled())
		{
			btnSound.setText(getString(R.string.sound));
			btnSound.setBackgroundResource(R.drawable.mute_02);
			dataStorage.setStateSound(false);
			return;
		} else
		{
			btnSound.setText(getString(R.string.mute));
			btnSound.setBackgroundResource(R.drawable.mute_01);
			dataStorage.setStateSound(true);
			return;
		}
	}

	public void onButtonClickMoreApp(View view)
	{
		startActivity(new Intent(
				Intent.ACTION_VIEW,
				Uri.parse(getString(R.string.play_more_apps))));
	}

	public void onButtonClickRate(View view)
	{
		String s = getPackageName();
		startActivity(new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder()).append("https://play.google.com/store/apps/details?id=").append(s).toString())));
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mainScreenTotalPoints.setText(dataStorage.getTotalScoreLabel());
	}

}
