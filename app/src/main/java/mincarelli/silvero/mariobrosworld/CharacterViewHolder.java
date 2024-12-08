package mincarelli.silvero.mariobrosworld;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import mincarelli.silvero.mariobrosworld.databinding.CharacterCardviewBinding;

/**
 * A ViewHolder for displaying individual character data within a RecyclerView.
 * This class uses ViewBinding to access and bind data to the views in the layout.
 */
public class CharacterViewHolder extends ViewHolder {
    private final CharacterCardviewBinding binding;

    /**
     * Constructs a new CharacterViewHolder.
     *
     * @param binding The ViewBinding object for the character card layout.
     */
    public CharacterViewHolder(CharacterCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    /**
     * Binds a Character object to the ViewHolder.
     * This method populates the card view with the character's image and name.
     *
     * @param character The Character object containing the data to display.
     */
    public void bind(Character character) {
        binding.image.setImageResource(character.getImage());
        binding.name.setText(character.getName());
    }
}
