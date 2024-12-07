package mincarelli.silvero.mariobrosworld;

import static mincarelli.silvero.mariobrosworld.BR.character;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import mincarelli.silvero.mariobrosworld.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Configurar Toolbar
        setSupportActionBar(binding.toolbar);

//        NavController y toolbar
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                navController.getGraph()).build();

        NavigationUI.setupActionBarWithNavController(this, navController);



    }

    public void characterClicked(@NonNull Character character, View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("image", character.getImage());
        bundle.putString("name", character.getName());
        bundle.putString("description", character.getDescription());
        bundle.putString("skills", character.getSkills());




        Navigation.findNavController(view).navigate(R.id.characterDetailFragment , bundle);


    }

    @Override
    public boolean onSupportNavigateUp() {
        // Utiliza el método navigateUp del NavController
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.about_item) {
            // Muestra el diálogo "Acerca de"
            new About().show(getSupportFragmentManager(), "AboutDialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}