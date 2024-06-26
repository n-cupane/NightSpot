package com.nighter.nightspot.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.nighter.nightspot.R;
import com.nighter.nightspot.fragments.MySpotsFragmentDirections;
import com.nighter.nightspot.models.Product;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.Spot_Product;

public class MenuViewHolder extends RecyclerView.ViewHolder{

    private Spot_Product product;
    private TextView productPrice;

    private TextView productName;
    private ImageView menu_image;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        productName = itemView.findViewById(R.id.productName);
        productPrice = itemView.findViewById(R.id.productPrice);
        menu_image = itemView.findViewById(R.id.menu_immagine);



    }

    public void setProduct(Spot_Product product) {
        this.product = product;
        productName.setText(product.getProduct().getName());
        menu_image.setImageResource(R.drawable.drink);
        productPrice.setText(String.valueOf(product.getPrice()));

    }

}
