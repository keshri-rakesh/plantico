package com.example.splashscreen;

import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLOutput;

public class LoginTabFragment extends Fragment {

    EditText email,pass;
    Button login;
    TextView forgotpass;
    ProgressBar progressBar;
    float v=0;

    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container,false);

        mAuth = FirebaseAuth.getInstance();

        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.pass);
        forgotpass = root.findViewById(R.id.forgotpass);
        login = root.findViewById(R.id.login);
        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);

        email.setTranslationX(800);
        pass.setTranslationX(800);
        forgotpass.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        forgotpass.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgotpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String emailR = email.getText().toString().trim();
                String passR = pass.getText().toString().trim();

                if(emailR.isEmpty()){
                    email.setError("Email is required!");
                    email.requestFocus();
                    return;
                }
                if(passR.isEmpty()){
                    pass.setError("Password is required!");
                    pass.requestFocus();
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
                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(emailR,passR).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Toast.makeText(getActivity(),"Login Successfull!",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(),ProfileActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            //startActivity(new Intent(getActivity(),ProfileActivity.class));
                            progressBar.setVisibility(View.GONE);
                        }else{
                            Toast.makeText(getActivity(),"Failed to Login! Please check your credentials",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            email.setText("");
                            pass.setText("");
                            email.requestFocus();
                        }

                    }
                });


            }
        });

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
