package mincarelli.silvero.mariobrosworld;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import mincarelli.silvero.mariobrosworld.databinding.FragmentCharactersListBinding;

/**
 * A Fragment that displays a list of characters in a RecyclerView.
 * This fragment also manages first-launch preferences and shows a welcome Snackbar message.
 */
public class CharactersListFragment extends Fragment {
    private FragmentCharactersListBinding binding;
    private ArrayList<Character> characters;
    private CharacterRecyclerViewAdapter adapter;
    private PreferencesHelper prefs;

    /**
     * Inflates the fragment's view using ViewBinding.
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
        binding = FragmentCharactersListBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    /**
     * Called immediately after the fragment's view has been created.
     * Initializes the list of characters, manages the RecyclerView, and shows a welcome message if it's the first launch.
     *
     * @param view The View returned by {@link #onCreateView}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadCharacter();

        prefs = new PreferencesHelper(requireContext());
        //Show Snackbar only on first launch
        if (prefs.isFirstLaunch()) {
            String message = getString(R.string.welcome);
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
            prefs.setFirstLaunch(false);

        }

        adapter = new CharacterRecyclerViewAdapter(characters, getActivity());
        binding.charactersRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.charactersRecyclerview.setAdapter(adapter);
    }

    /**
     * Loads the list of characters into an ArrayList.
     * This data is used to populate the RecyclerView.
     */
    private void loadCharacter() {
        characters = new ArrayList<>();
        characters.add(new Character(R.drawable.mario, "Mario", "Fontanero italiano, hermano mayor de Luigi", "Salta alto, rompe bloques, usa power-ups (Flor de Fuego, Super Hoja)."));
        characters.add(new Character(R.drawable.luigi, "Luigi", "Hermano menor de Mario, más tímido pero igualmente valiente.", "Salta más alto que Mario, pero resbala más. También usa power-ups."));
        characters.add(new Character(R.drawable.princess_daisy, "Daisy", "Princesa del Reino Sarasaland, amiga cercana de Peach.", "Similar a Peach, con habilidades para deportes y carreras."));
        characters.add(new Character(R.drawable.princess_peach, "Peach", "Princesa del Reino Champiñón, a menudo rescatada por Mario.", "Puede flotar brevemente al saltar gracias a su vestido."));
        characters.add(new Character(R.drawable.yoshi, "Yoshi", "Dinosaurio amigo de Mario, lo ayuda en sus aventuras.", "Come enemigos, lanza huevos, puede dar un salto flotante."));
        characters.add(new Character(R.drawable.toad, "Toad", "Habitante del Reino Champiñón, leal ayudante de Peach.", "Es rápido y ágil, aunque no muy fuerte."));
        characters.add(new Character(R.drawable.wario, "Wario", "Un personaje avaricioso", "Usa su fuerza bruta y ataques corporales"));
        characters.add(new Character(R.drawable.waluigi, "Waluigi", "El eterno rival de Luigi", "Es ágil y utiliza trucos astutos e el campo"));
        characters.add(new Character(R.drawable.boo, "Boo", "Un fantasma travieso", "Se vuelve invisible y asusta a sus oponentes"));
        characters.add(new Character(R.drawable.bowser, "Bowser", "El rey de los Koopas", "Escupe fuego y tiene gran resistencia"));
        characters.add(new Character(R.drawable.goomba, "Goomba", "Un enemigo clásico de Mario", "Se lanza en línea recta para atacar"));
        characters.add(new Character(R.drawable.koopa_troopa, "Koopa Troopa", "Un soldado leal de Bowser", "Se esconde en su caparazón para protegerse"));
        characters.add(new Character(R.drawable.donkey_kong, "Donkey Kong", "Un gorila poderoso", "Utiliza su fuerza para romper barriles y obstáculos"));
    }

    /**
     * Updates the ActionBar title when the fragment starts.
     */
    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.character_list);
        }
    }
}