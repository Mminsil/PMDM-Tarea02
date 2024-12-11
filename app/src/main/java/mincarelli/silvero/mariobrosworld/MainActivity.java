package mincarelli.silvero.mariobrosworld;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import mincarelli.silvero.mariobrosworld.databinding.ActivityMainBinding;
import mincarelli.silvero.mariobrosworld.databinding.NavHeaderBinding;

import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.Locale;

/**
 * MainActivity handles the main UI and navigation logic of the application.
 * It sets up the Toolbar, Navigation Drawer, and manages navigation between fragments.
 */
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    /**
     * Called when the activity is starting. Initializes the layout, navigation, and toolbar.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this contains the data it most recently supplied. Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Aplicar el lenguaje guardado a la app
        applySavedLanguage();

        // Configurar Toolbar
        setSupportActionBar(binding.toolbar);

        // Inicializar NavController y toolbar con navigation
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        appBarConfiguration = new AppBarConfiguration.Builder(R.id.charactersListFragment).
                setOpenableLayout(binding.drawerLayout).build();

        // Vincular Toolbar con NavController
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Configurar NavigationView con NavController
        NavigationUI.setupWithNavController(binding.navigationView, navController);

        // Configurar opciones del menú
        configureNavigationMenu();

        // Manejar los clics del botón Atrás
        handleBackPressed();

        //Actualizar los títulos de menu en base al idioma seleccionado
        updateMenuTitles();
    }

    /**
     * Updates the titles in the Toolbar and the navigation drawer menu to reflect the current language.
     */
    public void updateMenuTitles() {
        // Actualiza el título de la Toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.settings_app);
        }

        // Actualiza los títulos del menú del Drawer
        Menu menu = binding.navigationView.getMenu();
        menu.findItem(R.id.item_nav_home).setTitle(R.string.home);
        menu.findItem(R.id.item_nav_settings).setTitle(R.string.settings);
        // Encuentra y actualiza el texto del TextView en el NavHeader
        View headerView = binding.navigationView.getHeaderView(0); // Índice 0 si es el único header
        NavHeaderBinding headerBinding = NavHeaderBinding.bind(headerView);
        headerBinding.headerSubtitle.setText(R.string.app_name);
    }

    /**
     * Inflates the options menu and updates its items.
     *
     * @param menu The options menu in which items are placed.
     * @return true for the menu to be displayed; false otherwise.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_menu, menu);

        // Actualizar el título del item del menú
        MenuItem aboutItem = menu.findItem(R.id.about_item);
        if (aboutItem != null) {
            aboutItem.setTitle(R.string.about); // Toma el recurso correcto según el idioma
        }
        return true;
    }

    /**
     * Configures the actions of the navigation drawer menu items.
     */
    private void configureNavigationMenu() {
        binding.navigationView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.item_nav_home) {
                navController.navigate(R.id.charactersListFragment); // Navegar al fragmento de inicio
            } else {
                navController.navigate(R.id.settingsFragment);
            }

            binding.drawerLayout.closeDrawers(); // Cerrar el menú lateral
            return true;
        });

        // Configurar Header del menú
        ImageView headerImage = binding.navigationView.getHeaderView(0).findViewById(R.id.header_image);
        headerImage.setOnClickListener(v -> {
            navController.navigate(R.id.charactersListFragment);
            binding.drawerLayout.closeDrawers();
        });
    }

    /**
     * Handles navigation up actions using the NavController.
     *
     * @return true if the navigation up action was handled; false otherwise.
     */
    @Override
    public boolean onSupportNavigateUp() {
        // Utiliza el método navigateUp del NavController
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**
     * Handles back button presses. Closes the drawer if open or navigates back through the stack.
     */
    private void handleBackPressed() {
        // Maneja el evento de retroceso usando OnBackPressedDispatcher
        this.getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START); // Cierra el Drawer si está abierto
                } else {
                    if (!navController.popBackStack()) {
                        // Si no hay más destinos en el stack, cierra la actividad
                        finish();
                    }
                }
            }
        });
    }

    /**
     * Handles a character click event and navigates to the character detail fragment.
     *
     * @param character The character object containing the details to display.
     * @param view      The view that was clicked.
     */
    public void characterClicked(@NonNull Character character, View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("image", character.getImage());
        bundle.putString("name", character.getName());
        bundle.putString("description", character.getDescription());
        bundle.putString("skills", character.getSkills());

        Navigation.findNavController(view).navigate(R.id.characterDetailFragment, bundle);
    }

    /**
     * Handles the selection of options menu items.
     *
     * @param item The selected menu item.
     * @return true if the item selection was handled; false otherwise.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.about_item) {
            // Muestra el diálogo "Acerca de"
            new About().show(getSupportFragmentManager(), "AboutDialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Applies the saved language preference to the application's configuration.
     * The default language is English ("en") if no language has been saved.
     */
    private void applySavedLanguage() {
        //Recuperamos el lenguaje guardado
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Cargamos el lenguaje guardado
        String language = prefs.getString("app_language", "en");
        // Cambiar el idioma de la app usando la configuración de la región
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }
}