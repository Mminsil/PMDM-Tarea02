package mincarelli.silvero.mariobrosworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mincarelli.silvero.mariobrosworld.databinding.CharacterCardviewBinding;

public class CharacterRecyclerViewAdapter extends RecyclerView.Adapter<CharacterViewHolder>  {
    private final ArrayList<Character> characters;
    private final Context context;

    public CharacterRecyclerViewAdapter(ArrayList<Character> characters, Context context) {
        this.characters = characters;
        this.context = context;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CharacterCardviewBinding binding = CharacterCardviewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new CharacterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {

        Character currentCharacter = this.characters.get(position);
        holder.bind(currentCharacter);

        holder.itemView.setOnClickListener(view -> {
            itemClicked(currentCharacter, view);
        });

    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    private void itemClicked(Character currentCharacter, View view) {
        ((MainActivity) context).characterClicked(currentCharacter, view);
    }
}

