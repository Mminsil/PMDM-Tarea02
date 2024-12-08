package mincarelli.silvero.mariobrosworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mincarelli.silvero.mariobrosworld.databinding.CharacterCardviewBinding;

/**
 * A RecyclerView Adapter for displaying a list of characters in a card view layout.
 * It binds character data to the ViewHolder and handles click interactions.
 */
public class CharacterRecyclerViewAdapter extends RecyclerView.Adapter<CharacterViewHolder>  {
    private final ArrayList<Character> characters;
    private final Context context;

    /**
     * Constructor for CharacterRecyclerViewAdapter.
     * @param characters The list of Character objects to display in the RecyclerView.
     * @param context The Context in which the RecyclerView is used, usually an Activity.
     */
    public CharacterRecyclerViewAdapter(ArrayList<Character> characters, Context context) {
        this.characters = characters;
        this.context = context;
    }

    /**
     * Called when RecyclerView needs a new ViewHolder to represent an item.
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new CharacterViewHolder that holds a View for a character item.
     */
    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CharacterCardviewBinding binding = CharacterCardviewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new CharacterViewHolder(binding);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method updates the contents of the ViewHolder to reflect the character data.
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {

        Character currentCharacter = this.characters.get(position);
        holder.bind(currentCharacter);

        holder.itemView.setOnClickListener(view -> {
            itemClicked(currentCharacter, view);
        });

    }

    /**
     *  Returns the total number of items in the data set held by the adapter.
     * @return The number of characters in the list.
     */
    @Override
    public int getItemCount() {
        return characters.size();
    }

    /**
     * Handles the click event for a character item.
     * Delegates the action to the parent MainActivity to navigate to the character details.
     * @param currentCharacter The character that was clicked.
     * @param view The View that was clicked.
     */
    private void itemClicked(Character currentCharacter, View view) {
        ((MainActivity) context).characterClicked(currentCharacter, view);
    }
}

