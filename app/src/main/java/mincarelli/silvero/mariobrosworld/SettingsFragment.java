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

public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container,false);
        return binding.getRoot();



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.languageRadioGroup.setOnCheckedChangeListener(this::onLanguageSelected);
    }

    private void onLanguageSelected(RadioGroup radioGroup, int checkedId) {
        if(checkedId == R.id.englishRadioButton){
            changeLanguage("en");
        }else{
            changeLanguage("es");
        }
    }

    private void changeLanguage(String codeLanguage) {
        //Cambiar el idioma de la app usando el sistema
        Locale locale = new Locale(codeLanguage);
        locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        updateLanguageView();
    }

    private void updateLanguageView() {
        binding.languageTextView.setText(R.string.language);
        binding.englishRadioButton.setText(R.string.english);
        binding.spanishRadioButton.setText(R.string.spanish);
        binding.tittleLanguageTextView.setText(R.string.titleLanguage);
    }

}