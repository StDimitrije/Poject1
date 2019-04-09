package com.example.poject1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poject1.R;
import com.example.poject1.adapter.ExpensesAdapter;
import com.example.poject1.model.Category;
import com.example.poject1.model.Expense;
import com.example.poject1.util.Util;
import com.example.poject1.viewmodels.MainViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class FirstFragment extends Fragment {

    private MainViewModel mainViewModel;
    private ExpensesAdapter expensesAdapter;
    private List<Category> mCategoryList;
    private Spinner spinner;
    private Button mAddBtn;
    private TextView mTitle;
    private EditText mEtTitle;
    private EditText mEtCost;


    public static FirstFragment newInstance(){
        return new FirstFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_first_add_expense, container, false);
        spinner =view.findViewById(R.id.second_spinner);
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, mCategoryList);
        spinner.setAdapter(adapter);

        mTitle=view.findViewById(R.id.first_tv_title);
        mEtTitle = view.findViewById(R.id.first_et_title);
        mEtCost = view.findViewById(R.id.first_et_cost);
        mAddBtn = view.findViewById(R.id.first_btn_add);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = mEtTitle.getText().toString();

                String cost = mEtCost.getText().toString();
                String date = Util.generateDate().toString();
                String category = spinner.getSelectedItem().toString();
                Expense expense = new Expense(Util.generateId(),title,category, cost ,date);
                mainViewModel.addExpense(expense);
                Toast.makeText(FirstFragment.this.getContext(), "Expense added: " + mEtCost + " for: " + mEtTitle, Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);


    }


}
