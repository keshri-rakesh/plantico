package com.example.splashscreen;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class SignupTabFragment extends Fragment implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private EditText email, mobile, pass, conpass;
    private ProgressBar progressBar;
    private Button signup;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container,false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        email = (EditText) view.findViewById(R.id.email);
        mobile = (EditText) view.findViewById(R.id.mobile);
        pass = (EditText) view.findViewById(R.id.pass);
        conpass = (EditText) view.findViewById(R.id.conpass);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        signup = (Button) view.findViewById(R.id.signup);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signup:
                registeruser();
                break;
        }
    }

    private void registeruser() {
        String emailR = email.getText().toString().trim();
        String mobileR = mobile.getText().toString().trim();
        String passR = pass.getText().toString().trim();
        String conpassR = conpass.getText().toString().trim();

        if(emailR.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }
        if(mobileR.isEmpty()){
            mobile.setError("Mobile is required!");
            mobile.requestFocus();
            return;
        }
        if(!(mobileR.length()==10)){
            mobile.setError("Please enter a valid mobile number!");
            mobile.requestFocus();
            return;
        }
        if(passR.isEmpty()){
            pass.setError("Password is required!");
            pass.requestFocus();
            return;
        }
        if(conpassR.isEmpty()){
            conpass.setError("Confirm Password is required!");
            conpass.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailR).matches()){
            email.setError("Please enter a valid email!");
            email.requestFocus();
            return;
        }
        if(passR.length() < 6){
            pass.setError("Password must be of 6 characters!");
            pass.requestFocus();
            return;
        }
        if(!passR.equals(conpassR)){
            conpass.setError("Password does not match!");
            conpass.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(emailR,passR)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(emailR,mobileR,passR,conpassR);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getActivity(),"User Registered Successfully!",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        email.setText("");
                                        pass.setText("");
                                        mobile.setText("");
                                        conpass.setText("");
                                        email.requestFocus();
                                    }
                                    else {
                                        Toast.makeText(getActivity(),"Failed to Register!",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        email.setText("");
                                        pass.setText("");
                                        mobile.setText("");
                                        conpass.setText("");
                                        email.requestFocus();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(getActivity(),"Failed to Register!",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            email.setText("");
                            pass.setText("");
                            mobile.setText("");
                            conpass.setText("");
                            email.requestFocus();
                        }

                    }
                });
    }
}
