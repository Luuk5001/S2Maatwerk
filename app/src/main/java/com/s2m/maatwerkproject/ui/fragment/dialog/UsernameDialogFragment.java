package com.s2m.maatwerkproject.ui.fragment.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.s2m.maatwerkproject.R;

import java.util.List;


public class UsernameDialogFragment extends DialogFragment implements DialogInterface.OnClickListener, Validator.ValidationListener, TextWatcher {

    public interface UsernameDialogListener {
        void onUsernameDialogPositiveClick(String username);
    }

    public static final String TAG = UsernameDialogFragment.class.getSimpleName();
    public static final String INTERFACE_TAG = UsernameDialogListener.class.getSimpleName();

    @Length(min = 3, max = 50, message = "Must be between 3 en 50 characters long")
    private EditText editTextUsername;

    private UsernameDialogListener listener;
    private Validator validator;
    private boolean buttonClicked = false;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (UsernameDialogListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement " + INTERFACE_TAG);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        validator = new Validator(this);
        validator.setValidationListener(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_username, null))
                .setTitle("Please enter a username")
                .setMessage("Other users will find you with this name.")
                .setPositiveButton("Ok", this)
                .setNegativeButton("Cancel", this);
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        editTextUsername = getDialog().findViewById(R.id.editTextDialogUsername);
        editTextUsername.addTextChangedListener(this);
        ((AlertDialog)getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == DialogInterface.BUTTON_POSITIVE){
            buttonClicked = true;
            validator.validate();
        }
    }

    @Override
    public void onValidationSucceeded() {
        ((AlertDialog)getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
        if(buttonClicked){
            listener.onUsernameDialogPositiveClick(editTextUsername.getText().toString());
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            buttonClicked = false;
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getDialog().getContext());
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
                ((AlertDialog)getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        validator.validate();
    }
}
