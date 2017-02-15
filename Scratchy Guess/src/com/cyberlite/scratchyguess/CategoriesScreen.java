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
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.HashSet;
import java.util.Set;
import com.cyberlite.scratchyguess.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
* Class Handles category selection
* @author  Bennet Makwakwa
* @version 1.0
* @since   2016 
*/

public class CategoriesScreen extends Activity
{

    private TextView categoriesTotalPoints;
    private Set inactiveCategories;
    private LevelHandler levelHandler;
    private DataStorage dataStorage;

    public CategoriesScreen()
    {
    }

    private void refreshScore()
    {
        PreferenceManager.getDefaultSharedPreferences(this);
        categoriesTotalPoints.setText(dataStorage.getTotalScoreLabel());
    }

    private void showResetGameButtonIfAllCategoriesFinished()
    {
        if (inactiveCategories.size() == GameConfig.names.size())
        {
            findViewById(R.id.btn_reset).setVisibility(0);
        }
    }

    public void onButtonClickCategory(View view)
    {
        int i = Integer.valueOf((String)view.getTag()).intValue();
        if (inactiveCategories.contains(Integer.valueOf(i)))
        {
            return;
        } else
        {
            Intent intent = new Intent(this,GameActivity.class);
            intent.putExtra("category", i);
            startActivity(intent);
            return;
        }
    }

    public void onButtonClickRate(View view)
    {
        String s = getPackageName();
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder()).append("https://play.google.com/store/apps/details?id=").append(s).toString())));
    }
    
    public void onButtonClickShare(View view)
    {
    	Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
    	sharingIntent.setType("text/plain");
    	String shareBody = "Hey, You Have To Download This Game, it's FUN , Scratchy Guess, PlayStore : https://play.google.com/store/apps/details?id=com.cyberlite.scratchyguess";
    	sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Scratchy Guess Share ");
    	sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
    	startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_category);
        dataStorage = new DataStorage(getApplicationContext());
        levelHandler = new LevelHandler(getApplicationContext(), dataStorage, 0);
        categoriesTotalPoints = (TextView)findViewById(R.id.categoriesTotalPoints);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/VarelaRound-Regular.ttf");
        categoriesTotalPoints.setTypeface(typeface);
        categoriesTotalPoints.setText(dataStorage.getTotalScoreLabel());
        ((TextView)findViewById(R.id.btn_rate)).setTypeface(typeface);
        for (int i = 0; i < 6; i++)
        {
            ((Button)findViewById(getResources().getIdentifier((new StringBuilder()).append("categoryBtn_").append(i).toString(), "id", getPackageName()))).setTypeface(typeface);
        }

       ((AdView)findViewById(R.id.adView)).loadAd((new AdRequest.Builder()).build());
    }

    protected void onResume()
    {
        super.onResume();
        refreshScore();
        inactiveCategories = new HashSet();
        for (int i = 0; i < GameConfig.names.size(); i++)
        {
            Button button = (Button)findViewById(getResources().getIdentifier((new StringBuilder()).append("categoryBtn_").append(i).toString(), "id", getPackageName()));
            int j = Integer.valueOf((String)button.getTag()).intValue();
            if (levelHandler.allLevelsPlayed(j))
            {
                button.setBackgroundResource(R.drawable.inactive_btn);
                inactiveCategories.add(Integer.valueOf(j));
            }
        }

        showResetGameButtonIfAllCategoriesFinished();
    }

    public void resetGame(View view)
    {
        dataStorage.resetScoreAndLevels();
        finish();
        startActivity(getIntent());
    }
}
