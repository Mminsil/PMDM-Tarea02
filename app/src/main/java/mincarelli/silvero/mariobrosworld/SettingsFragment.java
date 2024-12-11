package mincarelli.silvero.mariobrosworld;

import static androidx.core.app.ActivityCompat.invalidateOptionsMenu;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import java.util.Locale;

import mincarelli.silvero.mariobrosworld.databinding.FragmentSettingsBinding;

/**
 * Fragment that handles the settings screen of the application.
 * This fragment allows users to change the app's language dynamically.
 */
public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    /**
     * Inflates the fragment's layout and initializes its binding.
     *
     * @param inflater           The LayoutInflater object used to inflate views in the fragment.
     * @param container          The parent view that this fragment's UI will attach to.
     * @param savedInstanceState Saved state information from a previous instance of this fragment.
     * @return The root view of the inflated layout.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Called after the fragment's view has been created. Sets up listeners and other view-related logic.
     *
     * @param view               The root view of the fragment.
     * @param savedInstanceState Saved state information from a previous instance of this fragment.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Inicializamos el estado del RadioGroup según el idioma actual
        initializeLanguageSelection();
        binding.languageRadioGroup.setOnCheckedChangeListener(this::onLanguageSelected);
    }

    /**
     * Retrieves the saved language and selects the corresponding RadioButton.
     */
    private void initializeLanguageSelection() {
        // Obtenemos el idioma actual
        String currentLanguage = getCurrentLanguage();

        if ("en".equals(currentLanguage)) {
            binding.languageRadioGroup.check(R.id.englishRadioButton);
        } else if ("es".equals(currentLanguage)) {
            binding.languageRadioGroup.check(R.id.spanishRadioButton);
        }
    }

    /**
     * Retrieves the currently saved language from SharedPreferences.
     *
     * @return The saved language code (e.g., "en" for English, "es" for Spanish). Defaults to "en" if no language is saved.
     */
    private String getCurrentLanguage() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
        return prefs.getString("app_language", "en"); // "en" es el idioma predeterminado
    }

    /**
     * Listener triggered when the user selects a language in the RadioGroup.
     *
     * @param radioGroup The RadioGroup where the selection occurred.
     * @param checkedId  The ID of the selected RadioButton.
     */
    private void onLanguageSelected(RadioGroup radioGroup, int checkedId) {
        if (checkedId == R.id.englishRadioButton) {
            changeLanguage("en");
        } else if (checkedId == R.id.spanishRadioButton) {
            changeLanguage("es");
        }
    }

    /**
     * Saves the selected language to SharedPreferences.
     *
     * @param languageCode The language code to save (e.g., "en", "es").
     */
    private void saveCurrentLanguage(String languageCode) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
        prefs.edit().putString("app_language", languageCode).apply();
    }

    /**
     * Dynamically changes the app's language and updates the UI accordingly.
     *
     * @param codeLanguage The language code in ISO 639 format (e.g., "en" for English, "es" for Spanish).
     */
    private void changeLanguage(String codeLanguage) {
        // Cambiar el idioma de la app usando la configuración de la región
        Locale locale = new Locale(codeLanguage);
        locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

        // Actualiza la vista con el idioma seleccionado
        updateLanguageView();

        // Guarda el idioma seleccionado
        saveCurrentLanguage(codeLanguage);

        // Actualiza los títulos del menú si es necesario
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).updateMenuTitles();
        }

        // Invalida el menú para aplicar el cambio de idioma
        invalidateOptionsMenu();
    }

    /**
     * Updates the text displayed in the view based on the selected language.
     * This method should be called after the language has been changed.
     */
    private void updateLanguageView() {
        binding.languageTextView.setText(R.string.language);
        binding.englishRadioButton.setText(R.string.english);
        binding.spanishRadioButton.setText(R.string.spanish);
        binding.titleLanguageTextView.setText(R.string.titleLanguage);
    }

    /**
     * Invalidates the options menu to ensure the changes are reflected.
     */
    private void invalidateOptionsMenu() {
        if (getActivity() != null) {
            getActivity().invalidateOptionsMenu();
        }
    }
}