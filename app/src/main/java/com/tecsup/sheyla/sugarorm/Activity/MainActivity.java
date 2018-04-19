package com.tecsup.sheyla.sugarorm.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tecsup.sheyla.sugarorm.R;
import com.tecsup.sheyla.sugarorm.adapter.UserAdapter;
import com.tecsup.sheyla.sugarorm.models.User;
import com.tecsup.sheyla.sugarorm.repositories.UserRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REGISTER_FORM_REQUEST = 100;
    private RecyclerView usersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Configure ReciclerView
        usersList = (RecyclerView) findViewById(R.id.user_list);
        usersList.setLayoutManager(new LinearLayoutManager(this));

        // Set Data Adapter to ReciclerView
        List<User> users = UserRepository.list();
        usersList.setAdapter(new UserAdapter(users));
    }
    public void callRegisterForm(View view){
        startActivityForResult(new Intent(this, RegisterActivity.class), REGISTER_FORM_REQUEST);
    }
    // return from RegisterActivity


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // refresh data
        UserAdapter adapter = (UserAdapter)usersList.getAdapter();

        List<User> users = UserRepository.list();
        adapter.setUsers(users);
        adapter.notifyDataSetChanged();

    }
}
