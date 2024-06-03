package com.nighter.nightspot.viewholder;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.nighter.nightspot.R;
import com.nighter.nightspot.fragments.HomeFragment;
import com.nighter.nightspot.fragments.HomeFragmentDirections;
import com.nighter.nightspot.fragments.LogInFragmentDirections;
import com.nighter.nightspot.models.Category;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private Category category;

    private TextView categoryTextView;


    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryTextView = itemView.findViewById(R.id.category);
    }

    public void setCategory(Category category) {

        itemView.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(itemView);
            NavDirections toHome = HomeFragmentDirections.homeToHome(category.getId());
            navController.navigate(toHome);
        });

        String underlinedText = "<u>" + category.getName() + "</u>";
        categoryTextView.setText(HtmlCompat.fromHtml(underlinedText, HtmlCompat.FROM_HTML_MODE_LEGACY));
    }
}
