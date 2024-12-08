package mincarelli.silvero.mariobrosworld;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Helper class for managing application preferences using SharedPreferences.
 * This class simplifies storing and retrieving application settings or flags.
 */
public class PreferencesHelper {
    private static final String PREFS_NAME = "app_prefs";
    private static final String KEY_FIRST_LAUNCH = "is_first_launch";
    private SharedPreferences sharedPreferences;

    /**
     * Constructs a new PreferencesHelper instance.
     *
     * @param context The application context used to access SharedPreferences.
     */
    public PreferencesHelper(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Checks whether the application is being launched for the first time.
     * This is determined by a value stored in SharedPreferences.
     *
     * @return {@code true} if this is the first launch, {@code false} otherwise.
     */
    public boolean isFirstLaunch() {
        return sharedPreferences.getBoolean(KEY_FIRST_LAUNCH, true);
    }

    /**
     * Updates the first launch status in SharedPreferences.
     *
     * @param isFirstLaunch {@code true} to indicate the app is being launched for the first time,
     *                      {@code false} otherwise.
     */
    public void setFirstLaunch(boolean isFirstLaunch) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_FIRST_LAUNCH, isFirstLaunch);
        editor.apply();
    }
}
