package com.example.wander.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.wander.R;
import com.example.wander.settings.Activity;
import com.example.wander.settings.Privacy;
import com.example.wander.settings.Recovery;
import com.example.wander.settings.Theme;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment implements ProfileView{

    private FriendsAdapter adapter;
    private List<FriendsItem> friendsList;
    private RecyclerView recyclerView;
    private Toolbar mTopToolbar;
    private ToggleButton btn_edit;
    private ProfilePresenter presenter;
    private ProgressBar progressBar;
    private ViewPager settings;

    private TextView name;
    private TextView age;
    private TextView gender;
    private EditText et_name;
    private EditText et_age;
    private RadioGroup rd_gender;
    private TextView errorText;
    private Button save;
    private int person_gender = -1; //0->no gender, 1->male, 2->female, -1->no option selected(invalid)

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        presenter = new ProfilePresenter(this, new ProfileInteractor());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mTopToolbar =  view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mTopToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = view.findViewById(R.id.recycler_view);

        name = view.findViewById(R.id.tv_name);
        age = view.findViewById(R.id.tv_age);
        gender = view.findViewById(R.id.tv_gender);
        et_name = view.findViewById(R.id.name);
        et_age = view.findViewById(R.id.age);
        rd_gender = view.findViewById(R.id.gender);
        btn_edit = view.findViewById(R.id.btn_edit_profile);
        progressBar = view.findViewById(R.id.progress);
        errorText = view.findViewById(R.id.errorText);
        save = view.findViewById(R.id.btn_save);


        btn_edit.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked){
                makeVisible();
            }else {
                makeInvisible();
            }
        });

        rd_gender.setOnCheckedChangeListener((radioGroup, checkedID) -> {
            switch (checkedID) {
                case R.id.male:
                    person_gender = 1;
                    break;
                case R.id.female:
                    person_gender = 2;
                    break;
                case R.id.no_gender:
                    person_gender = 0;
                    break;
            }
        });

        save.setOnClickListener(v->updateProfileInfo());

        fillfriendsList();
        setUpRecyclerView();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Settings View Pager
        List<Fragment> fragments = getFragments();
        MyPageAdapter pageAdapter = new MyPageAdapter(getChildFragmentManager(), fragments);
        settings = view.findViewById(R.id.other_settings);
        settings.setAdapter(pageAdapter);
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<>();
        fList.add(new Activity());
        fList.add(new Privacy());
        fList.add(new Recovery());
        fList.add(new Theme());
        return fList;
    }

    private class MyPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments ) {
            super(fm);
            this.fragments = fragments;
        }
        @Override
        public Fragment getItem(int position)
        {
            return this.fragments.get(position);
        }

        @Override
        public int getCount()
        {
            return this.fragments.size();
        }
    }


    private void fillfriendsList() {
        friendsList = new ArrayList<>();
        friendsList.add(new FriendsItem(R.drawable.wander_logo, "One"));
        friendsList.add(new FriendsItem(R.drawable.ic_username, "Two"));
        friendsList.add(new FriendsItem(R.drawable.wander_logo, "Three"));
        friendsList.add(new FriendsItem(R.drawable.ic_username, "Four"));
        friendsList.add(new FriendsItem(R.drawable.wander_logo, "Five"));
        friendsList.add(new FriendsItem(R.drawable.ic_username, "Six"));
        friendsList.add(new FriendsItem(R.drawable.wander_logo, "Seven"));
        friendsList.add(new FriendsItem(R.drawable.ic_username, "Eight"));
        friendsList.add(new FriendsItem(R.drawable.wander_logo, "Nine"));
    }

    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new FriendsAdapter(friendsList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.friends_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                recyclerView.setVisibility(View.VISIBLE);
                break;
            case R.id.close_search:
                recyclerView.setVisibility(View.GONE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void makeVisible() {
        name.setVisibility(View.VISIBLE);
        age.setVisibility(View.VISIBLE);
        gender.setVisibility(View.VISIBLE);
        et_name.setVisibility(View.VISIBLE);
        et_age.setVisibility(View.VISIBLE);
        rd_gender.setVisibility(View.VISIBLE);
        save.setVisibility(View.VISIBLE);
    }

    private void makeInvisible() {
        name.setVisibility(View.GONE);
        age.setVisibility(View.GONE);
        gender.setVisibility(View.GONE);
        et_name.setVisibility(View.GONE);
        et_age.setVisibility(View.GONE);
        rd_gender.setVisibility(View.GONE);
        save.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setNameError() {
        et_name.setError("Name field cannot be empty!");
    }

    @Override
    public void setAgeError() {
        et_age.setError("Age field cannot be empty!");
    }

    @Override
    public void setGenderError() {
        gender.setError("Gender not specified!");
    }


    @Override
    public void showSuccessfulUpdate() {
        Toast.makeText(getContext(),"Successfully updated to the database!",Toast.LENGTH_SHORT).show();
        makeInvisible();
        btn_edit.setChecked(false);

    }

    @Override
    public void showErrorText() {
        errorText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorText() {
        errorText.setVisibility(View.GONE);
    }

    private void updateProfileInfo() {
        presenter.update(et_name.getText().toString().trim(),et_age.getText().toString().trim(),person_gender);
    }

}
