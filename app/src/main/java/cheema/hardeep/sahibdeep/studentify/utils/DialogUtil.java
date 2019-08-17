package cheema.hardeep.sahibdeep.studentify.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtil {

    private static final String CHOOSE_SEMESTER = "Choose Semester";

    public static void createTermDialog(Context context, CharSequence[] items, DialogInterface.OnClickListener listener){
        new AlertDialog.Builder(context)
                .setTitle(CHOOSE_SEMESTER)
                .setSingleChoiceItems(items, -1, listener)
                .show();
    }
}
