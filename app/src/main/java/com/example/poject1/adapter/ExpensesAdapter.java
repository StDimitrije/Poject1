package com.example.poject1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.poject1.R;
import com.example.poject1.model.Category;
import com.example.poject1.model.Expense;
import com.example.poject1.util.ExpensesDiffCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ExpensesHolder> {

    private List<Expense> mExpensesList;

    public ExpensesAdapter(){
        mExpensesList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ExpensesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.second_fragment_list_item, parent, false);

        return new ExpensesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesHolder holder, int position) {
        Expense expense = mExpensesList.get(position);
        holder.mTitle.setText(expense.getmTitle());
        holder.mCost.setText(expense.getmCost());
        holder.mCategory.setText(expense.getmCategory());
        holder.mDate.setText(expense.getmDate());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setData(List<Expense> expense) {

        ExpensesDiffCallback callback = new ExpensesDiffCallback(mExpensesList,expense );

        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        mExpensesList.clear();

        mExpensesList.addAll(expense);

        result.dispatchUpdatesTo(this);
    }

    public class ExpensesHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mCost;
        private TextView mCategory;
        private TextView mDate;
        private Button mRemove;
        private Button mDetails;


        public ExpensesHolder(@NonNull View itemView) {

            super(itemView);
            mTitle.findViewById(R.id.list_item_name);
            mCost.findViewById(R.id.list_item_cost);
            mCategory.findViewById(R.id.list_item_category);
            mDate.findViewById(R.id.list_item_date);
            mRemove.findViewById(R.id.list_item_btn_remove);
            mRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mExpensesList.remove();
                }
            });
            mDetails.findViewById(R.id.list_item_btn_details);


        }
    }
}
