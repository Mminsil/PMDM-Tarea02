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
     * Creates and configures the "About us" dialog.
     *
     * @param savedInstanceState The last saved instance state of the Fragment,
     *                           or null if this is a freshly created Fragment.
     * @return Instance of {@link Dialog} representing the configured dialog.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Crear un costructor para el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Configurar el título y el mensaje
        builder.setTitle(R.string.about);
        builder.setMessage(R.string.about_text);
        //Configurar el botón Aceptar que cierra el diálogo
        AlertDialog.Builder accept = builder.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
            /**
             * Handles the click event for a dialog button.
             * This method is triggered when a button in the dialog is clicked.
             * @param dialog The dialog interface that received the click.
             * @param which The button that was clicked.
             */
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
