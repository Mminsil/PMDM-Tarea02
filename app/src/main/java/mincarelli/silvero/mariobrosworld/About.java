package mincarelli.silvero.mariobrosworld;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * Class representing a dialog box "Acerca de" using a {@link DialogFragment}.
 * This dialog displays information about the application and includes a button to close it.
 */
public class About extends DialogFragment {
    /**
     * Creates and configures the "About as" dialog.
     * @param savedInstanceState The last saved instance state of the Fragment,
     * or null if this is a freshly created Fragment.
     *
     * @return Instance of {@link Dialog} representing the configured dialog.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Crear un costructor para el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Configurar el título y el mensaje
        builder.setTitle("About as...");
        builder.setMessage(R.string.about_as);
        //Configurar el botón Aceptar que cierra el diálogo
        AlertDialog.Builder accept = builder.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción al pulsar "accept"
                dialog.dismiss();
            }
        });

        //Devuelve el diálogo configurado
        return builder.create();
    }
}
