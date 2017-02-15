package com.cyberlite.scratchyguess;
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
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
* Class Handles Memory Storage for writing preferences to user progress like score
* @author  Bennet Makwakwa
* @version 1.0
* @since   2016 
*/
public class DataStorage
{

    private static final String TAG = "Score";
    private Map data;
    DateFormat dateFormat;
    SharedPreferences preferences;

    public DataStorage(Context context)
    {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        data = new HashMap();
        refresh();
    }

    private void deserialize(String s)
    {
        if (s != null)
        {
            data.clear();
            String as[] = s.split("\\|");
            int i = as.length;
            int j = 0;
            while (j < i) 
            {
                String s1 = as[j];
                if (s1 != null && !"".equals(s1.trim()))
                {
                    String as1[] = s1.split("#");
                    if (as1.length == 3)
                    {
                        try
                        {
                            int k = Integer.valueOf(as1[0]).intValue();
                            int l = Integer.valueOf(as1[1]).intValue();
                            int i1 = Integer.valueOf(as1[2]).intValue();
                            if (data.get(Integer.valueOf(k)) == null)
                            {
                                data.put(Integer.valueOf(k), new HashMap());
                            }
                            ((Map)data.get(Integer.valueOf(k))).put(Integer.valueOf(l), Integer.valueOf(i1));
                        }
                        catch (NumberFormatException numberformatexception)
                        {
                            Log.e("Score", (new StringBuilder()).append("Score deserialize error [").append(s).append("]: ").append(s1).toString());
                        }
                    }
                }
                j++;
            }
        }
    }

    private void refresh()
    {
        deserialize(preferences.getString("Score", ""));
    }

    private String serialize()
    {
        String s = "";
        for (Iterator iterator = data.keySet().iterator(); iterator.hasNext();)
        {
            Integer integer = (Integer)iterator.next();
            Iterator iterator1 = ((Map)data.get(integer)).entrySet().iterator();
            while (iterator1.hasNext()) 
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
                s = (new StringBuilder()).append(s).append(integer).append("#").append(entry.getKey()).append("#").append(entry.getValue()).append("|").toString();
            }
        }

        return s;
    }

    public int getHintsNumberForToday()
    {
        String s = dateFormat.format(new Date());
        String s1 = preferences.getString("Hints", null);
        if (s1 != null)
        {
            String as[] = s1.split("#");
            if (as.length != 2)
            {
                return 0;
            }
            if (s.equals(as[0]))
            {
                return Integer.valueOf(as[1]).intValue();
            }
        }
        return 3;
    }

    public int getLevelsPlayedNumberForCategory(int i)
    {
        if (isCategoryNotPlayed(i))
        {
            return 0;
        } else
        {
            return ((Map)data.get(Integer.valueOf(i))).keySet().size();
        }
    }

    public String getNickname()
    {
        return preferences.getString("Nick", null);
    }

    public String getPassword()
    {
        return preferences.getString("Password", null);
    }

    public Set getPlayedLevelIndexes(int i)
    {
        if (isCategoryNotPlayed(i))
        {
            return new HashSet();
        } else
        {
            return ((Map)data.get(Integer.valueOf(i))).keySet();
        }
    }

    public Long getTotalScore()
    {
        refresh();
        long l = 0L;
        for (Iterator iterator = data.keySet().iterator(); iterator.hasNext();)
        {
            Integer integer = (Integer)iterator.next();
            Iterator iterator1 = ((Map)data.get(integer)).entrySet().iterator();
            while (iterator1.hasNext()) 
            {
                l += ((Integer)((java.util.Map.Entry)iterator1.next()).getValue()).intValue();
            }
        }

        return Long.valueOf(l);
    }

    public String getTotalScoreLabel()
    {
        return String.valueOf(getTotalScore());
    }

    public boolean isCategoryNotPlayed(int i)
    {
        return data.get(Integer.valueOf(i)) == null;
    }

    public boolean isCategoryPlayed(int i)
    {
        return !isCategoryNotPlayed(i);
    }

    public boolean isNicknameSet()
    {
        return preferences.getString("Nick", null) != null;
    }

    public boolean isPasswordSet()
    {
        return preferences.getString("Password", null) != null;
    }

    public boolean isSaveScoreDialogShowedForToday()
    {
        String s = dateFormat.format(new Date());
        return preferences.getString("ScoreSave", "").equals(s);
    }

    public boolean isSoundEnabled()
    {
        return preferences.getBoolean("Sound", true);
    }

    public boolean isTutorialAnimationNotPlayed()
    {
        return !isTutorialAnimationPlayed();
    }

    public boolean isTutorialAnimationPlayed()
    {
        return preferences.getBoolean("Animation", false);
    }

    public void resetScoreAndLevels()
    {
        data.clear();
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Score", serialize());
        editor.commit();
    }

    public void saveHintsForToday(int i)
    {
        if (i < 0)
        {
            i = 0;
        }
        String s = dateFormat.format(new Date());
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Hints", (new StringBuilder()).append(s).append("#").append(i).toString());
        editor.commit();
    }

    public void saveScore(int i, int j, int k)
    {
        if (isCategoryNotPlayed(i))
        {
            data.put(Integer.valueOf(i), new HashMap());
        }
        ((Map)data.get(Integer.valueOf(i))).put(Integer.valueOf(j), Integer.valueOf(k));
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Score", serialize());
        editor.commit();
    }

    public void setSaveScoreDialogShowedForToday()
    {
        String s = dateFormat.format(new Date());
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ScoreSave", s);
        editor.commit();
    }

    public void setStateSound(boolean flag)
    {
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("Sound", flag);
        editor.commit();
    }

    public void setTutorialAnimationPlayed()
    {
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("Animation", true);
        editor.commit();
    }

    public String toString()
    {
        return serialize();
    }

    public void updateNickname(String s)
    {
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Nick", s);
        editor.commit();
    }

    public void updatePassword(String s)
    {
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Password", s);
        editor.commit();
    }
}
