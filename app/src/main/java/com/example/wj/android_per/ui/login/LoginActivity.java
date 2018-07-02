package com.example.wj.android_per.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;


import com.example.wj.android_per.R;
import com.example.wj.android_per.base.BaseActivity;
import com.example.wj.android_per.common.http.Api;
import com.example.wj.android_per.common.view.ToastSnackbarUtiles;
import com.example.wj.android_per.ui.MainActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.email_sign_in_button)
    Button mEmailSignInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        email = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {

                return true;
            }
            return false;
        });

        mEmailSignInButton.setOnClickListener(view -> {
            String phone = email.getText().toString();
            String password = mPasswordView.getText().toString();

            Api.getApiServiceInstance().login(phone, password).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(request -> {
                if (request.isSuccess()) {
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                    finish();
                }
                ToastSnackbarUtiles.show(request.getMsg());

            }, throwable -> {

            });

        });


    }


}

