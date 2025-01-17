package com.sahanya.dogfoodapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.sahanya.dogfoodapp.R;
import com.sahanya.dogfoodapp.fragment.AddProduct;
import com.sahanya.dogfoodapp.fragment.OrderViewFragment;
import com.sahanya.dogfoodapp.fragment.ProductViewFragment;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    private FirebaseAuth mAuth;

    TextView textView24;

    public void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            textView24 = findViewById(R.id.textView24);
            textView24.setText("NutriPaws Admin");

            boolean emailVerified = user.isEmailVerified();
            String uid = user.getUid();
        }else {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView;

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        displayFragment(new AddProduct());
    }

    @Override
    public boolean onNavigationItemSelected (@NonNull MenuItem item){
        Fragment fragment;
        if (R.id.AddProduct == item.getItemId()) {
            fragment = new AddProduct();
        }else if (R.id.Product == item.getItemId()) {
            fragment = new ProductViewFragment();
        } else if (R.id.orders == item.getItemId()) {
            fragment = new OrderViewFragment();
        }else {
            fragment = new ProductViewFragment();
        }
        displayFragment(fragment);
        return true;
    }
    private void displayFragment(Fragment fragment){

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();

    }

}