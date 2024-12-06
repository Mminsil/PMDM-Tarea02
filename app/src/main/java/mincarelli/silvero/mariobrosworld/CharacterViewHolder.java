package mincarelli.silvero.mariobrosworld;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import mincarelli.silvero.mariobrosworld.databinding.CharacterCardviewBinding;

public class CharacterViewHolder extends ViewHolder {
    private final CharacterCardviewBinding binding;

    public CharacterViewHolder(CharacterCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Character character){
        binding.image.setImageResource(character.getImage());
        binding.name.setText(character.getName());
    }
}
