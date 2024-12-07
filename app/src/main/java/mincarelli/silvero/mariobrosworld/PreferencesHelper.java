package mincarelli.silvero.mariobrosworld;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Helper class for managing application preferences using SharedPreferences.
 */
public class PreferencesHelper {
    private static final String PREFS_NAME = "app_prefs";
    private static final String KEY_FIRST_LAUNCH = "is_first_launch";

    private SharedPreferences sharedPreferences;

    public PreferencesHelper(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Checks whether the application is being launched for the first time.
     * This is determined by a value stored in SharedPreferences.
     * @return true if this is the first launch, false otherwise.
     */
    public boolean isFirstLaunch() {
        return sharedPreferences.getBoolean(KEY_FIRST_LAUNCH, true);
    }

    public void setFirstLaunch(boolean isFirstLaunch) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_FIRST_LAUNCH, isFirstLaunch);
        editor.apply();
    }


}
