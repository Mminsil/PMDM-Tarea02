package mincarelli.silvero.mariobrosworld;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import mincarelli.silvero.mariobrosworld.databinding.FragmentCharacterDetailBinding;

public class CharacterDetailFragment extends Fragment {
    private FragmentCharacterDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() != null){
            int image = getArguments().getInt("image");
            String name = getArguments().getString("name");
            String description = getArguments().getString("description");
            String skills = getArguments().getString("skills");

            binding.image.setImageResource(image);
            binding.name.setText(name);
            binding.description.setText(description);
            binding.skills.setText(skills);
            String characterSelected = getString(R.string.character_selected) + getArguments().getString("name");;
            Toast.makeText(requireContext(), characterSelected, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.character_details);
        }
    }

}