package mincarelli.silvero.mariobrosworld;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
     * Called to inflate the fragment's layout and initialize its binding.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return
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
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.languageRadioGroup.setOnCheckedChangeListener(this::onLanguageSelected);
    }

    /**
     * Listener triggered when a new language is selected from the radio group.
     * Dynamically updates the app's language based on the selected option.
     *
     * @param radioGroup The RadioGroup containing the language options.
     * @param checkedId  The ID of the selected RadioButton.
     */
    private void onLanguageSelected(RadioGroup radioGroup, int checkedId) {
        if (checkedId == R.id.englishRadioButton) {
            changeLanguage("en");
        } else {
            changeLanguage("es");
        }
    }

    /**
     * Changes the app's language dynamically by updating the configuration.
     *
     * @param codeLanguage The language code in ISO 639 format ("en" for English, "es" for Spanish).
     */
    private void changeLanguage(String codeLanguage) {
        //Cambiar el idioma de la app usando el sistema
        Locale locale = new Locale(codeLanguage);
        locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        updateLanguageView();
    }

    /**
     * Updates the text of the views to reflect the newly selected language.
     * This method should be called after the app's language has been changed.
     */
    private void updateLanguageView() {
        binding.languageTextView.setText(R.string.language);
        binding.englishRadioButton.setText(R.string.english);
        binding.spanishRadioButton.setText(R.string.spanish);
        binding.tittleLanguageTextView.setText(R.string.titleLanguage);
    }
}