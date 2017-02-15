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

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.cyberlite.scratchyguess.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
* controls level for different categories
* @author  Bennet Makwakwa
* @version 1.0
* @since   2016 
*/
 
public class LevelHandler
{

    private static final String TAG = "LevelManager";
    private int category;
    private Context context;
    private int currentLevelIndex;
    private Drawable currentLevelPicture;
    private Set hintedButtonIndices;
    private String levelItemNames[];
    private String levelItems[];
    private List levelLabels;
    private DataStorage dataStorage;
    private int numberOfHints;
    private Random randomGenerator;

    public LevelHandler(Context context1, DataStorage memorystorage, int i)
    {
        randomGenerator = new Random(Calendar.getInstance().getTimeInMillis());
        context = context1;
        dataStorage = memorystorage;
        setCategory(i);
        numberOfHints = memorystorage.getHintsNumberForToday();
        hintedButtonIndices = new HashSet();
    }

    private List getLevelNotPlayedIndexes()
    {
        Set set = dataStorage.getPlayedLevelIndexes(category);
        ArrayList arraylist = new ArrayList();
        int i = 0;
        while (i < levelItems.length) 
        {
            if (!set.contains(Integer.valueOf(i)))
            {
                arraylist.add(Integer.valueOf(i));
            }
            i++;
        }
        return arraylist;
    }

    public void addHintedButtonIndex(int i)
    {
        hintedButtonIndices.add(Integer.valueOf(i));
    }

    public boolean allHintsForLevelUsed()
    {
        return hintedButtonIndices.size() >= 4;
    }

    public boolean allLevelsPlayed()
    {
        return dataStorage.getLevelsPlayedNumberForCategory(category) == levelItems.length;
    }

    public boolean allLevelsPlayed(int i)
    {
        return dataStorage.getLevelsPlayedNumberForCategory(i) == ((String[])GameConfig.pictures.get(i)).length;
    }

    public String getCategoryName()
    {
        String as[] = context.getResources().getStringArray(R.array.categories);
        String s;
        try
        {
            s = as[category];
        }
        catch (Exception exception)
        {
            return context.getResources().getString(R.string.unknown);
        }
        return s;
    }

    public int getCurrentLevelIndex()
    {
        return currentLevelIndex;
    }

    public String getCurrentLevelLabel()
    {
        int i = 1 + dataStorage.getLevelsPlayedNumberForCategory(category);
        return (new StringBuilder()).append(i).append("/").append(levelItems.length).toString();
    }

    public List getGameButtonLabels()
    {
        ArrayList arraylist = new ArrayList();
        levelLabels = new ArrayList();
        Set set = dataStorage.getPlayedLevelIndexes(category);
        int i = 0;
        do
        {
            if (i >= levelItemNames.length)
            {
                break;
            }
            if (currentLevelIndex != i && !set.contains(Integer.valueOf(i)))
            {
                arraylist.add(Integer.valueOf(i));
            }
            i++;
        } while (true);
        Collections.shuffle(arraylist);
        List list = arraylist.subList(0, 5);
        list.add(Integer.valueOf(currentLevelIndex));
        Collections.shuffle(list);
        int j = 0;
        for (Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            int k = ((Integer)iterator.next()).intValue();
            levelLabels.add(levelItemNames[k]);
            if (levelItemNames[currentLevelIndex].equals(levelItemNames[k]))
            {
                addHintedButtonIndex(j);
            }
            j++;
        }

        return levelLabels;
    }

    public List getNextHintButtonIndices()
    {
        ArrayList arraylist = new ArrayList();
        if (allHintsForLevelUsed())
        {
            return arraylist;
        }
        numberOfHints = -1 + numberOfHints;
        dataStorage.saveHintsForToday(numberOfHints);
        ArrayList arraylist1 = new ArrayList();
        int i = 0;
        while (i < 6) 
        {
            if (!hintedButtonIndices.contains(Integer.valueOf(i)))
            {
                arraylist1.add(Integer.valueOf(i));
            }
            i++;
        }
        Collections.shuffle(arraylist1);
        arraylist.add(arraylist1.get(0));
        arraylist.add(arraylist1.get(1));
        hintedButtonIndices.addAll(arraylist);
        return arraylist;
    }

    public Drawable getPictureForCurrentLevel()
    {
        try
        {
            currentLevelPicture = Drawable.createFromStream(context.getAssets().open(levelItems[currentLevelIndex]), null);
        }
        catch (IOException ioexception)
        {
            Log.e("LevelManager", ioexception.getMessage(), ioexception);
        }
        return currentLevelPicture;
    }

    public String getSolutionLabel()
    {
        return levelItemNames[currentLevelIndex];
    }

    public boolean isButtonHinted(int i)
    {
        return hintedButtonIndices.contains(Integer.valueOf(i));
    }

    public void prepareNextLevel()
    {
        List list = getLevelNotPlayedIndexes();
        currentLevelIndex = ((Integer)list.get(randomGenerator.nextInt(list.size()))).intValue();
    }

    public void resetHintedButtonsList()
    {
        hintedButtonIndices = new HashSet();
    }

    public void setCategory(int i)
    {
        category = i;
        levelItems = (String[])GameConfig.pictures.get(i);
        int j = context.getResources().getIdentifier((new StringBuilder()).append("category").append(i).toString(), "array", context.getPackageName());
        if (j == 0)
        {
            levelItemNames = (String[])GameConfig.names.get(i);
            return;
        } else
        {
            levelItemNames = context.getResources().getStringArray(j);
            return;
        }
    }
}
