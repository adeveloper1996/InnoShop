package project.innoshop.kz.innoshop.screen.add_products;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.nguyenhoanglam.imagepicker.model.Image;
import com.squareup.picasso.Picasso;

import project.innoshop.kz.innoshop.R;

/**
 * Created by Andrey on 2/27/2017.
 */
public class AddProductsHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    ImageButton imageButton;

    public AddProductsHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.image_horizontal_add_products);
        imageButton = (ImageButton) itemView.findViewById(R.id.i_btn_delete_image);
    }

    public void bind(@NonNull Image image) {
        imageView.setImageURI(Uri.parse(image.getPath()));
    }
}
