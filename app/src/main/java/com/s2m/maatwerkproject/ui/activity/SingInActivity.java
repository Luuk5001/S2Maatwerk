package com.s2m.maatwerkproject.ui.activity;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.models.User;
import com.s2m.maatwerkproject.data.Firebase;
import com.s2m.maatwerkproject.data.repository.RepositoryCallback;
import com.s2m.maatwerkproject.data.repository.UserRepository;
import com.s2m.maatwerkproject.ui.fragment.dialog.UsernameDialogFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingInActivity extends AppCompatActivity implements RepositoryCallback<User>, UsernameDialogFragment.UsernameDialogListener {

    public static final int RC_SING_IN = 101;

    private UserRepository userRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseUser firebaseUser = Firebase.getAuthInstance().getCurrentUser();

        userRepo = new UserRepository(this);

        if(firebaseUser == null){
            setContentView(R.layout.activity_sing_in);
            ButterKnife.bind(this);
        }
        else{
            checkIfUserExists();
        }
    }

    @Override
    public void single(User user, String callbackKey) {
        if(callbackKey.equals(UserRepository.KEY_USER)){
            if(user == null){
                UsernameDialogFragment dialogUsernameFragment = new UsernameDialogFragment();
                dialogUsernameFragment.show(getFragmentManager(), DialogFragment.class.getSimpleName());
            }
            else{
                startMainActivity();
            }
        }
    }

    @Override
    public void list(List<User> users, String callbackKey) {

    }

    @Override
    public void error(String errorMessage) {

    }

    @Override
    public void onUsernameDialogPositiveClick(String username) {
        userRepo.addUser(new User(Firebase.getAuthInstance().getCurrentUser().getUid(), username));
        startMainActivity();
    }

    @OnClick(R.id.buttonSingIn)
    public void onClickButtonSingIn(View view){
        startActivityForResult(AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(
                                Arrays.asList(
                                        new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build()))
                        .build(),
                RC_SING_IN);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SING_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            // Successfully signed in
            if (resultCode == RESULT_OK) {
                checkIfUserExists();
            }
            else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Toast.makeText(this, "cancelled", Toast.LENGTH_LONG).show();
                }
                else{
                    switch (response.getErrorCode()){
                        case ErrorCodes.NO_NETWORK:
                            Toast.makeText(this, "No network", Toast.LENGTH_LONG).show();
                            break;
                        case ErrorCodes.UNKNOWN_ERROR:
                            Toast.makeText(this, "Unknown error", Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Toast.makeText(this, "Unknown response", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }
        }
    }

    private void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    private void checkIfUserExists(){
        userRepo.getById(Firebase.getAuthInstance().getCurrentUser().getUid(), UserRepository.KEY_USER);
    }
}
