package project.innoshop.kz.innoshop.screen.add_products;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nguyenhoanglam.imagepicker.model.Image;

import java.util.List;

import project.innoshop.kz.innoshop.R;
import project.innoshop.kz.innoshop.utils.BaseAdapter;

/**
 * Created by Andrey on 2/27/2017.
 */

public class AddProductAdapter extends BaseAdapter<AddProductsHolder,Image> {

    public AddProductAdapter(@NonNull List<Image> items) {
        super(items);
    }

    @Override
    public AddProductsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddProductsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.img_horizontal_add_products, parent, false));
    }

    @Override
    public void onBindViewHolder(AddProductsHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Image image = getItem(position);
        holder.bind(image);
        holder.imageButton.setOnClickListener(v -> {
            deleteItem(position);
        });
    }

    @Override
    public void setOnItemClickListener(@Nullable OnItemClickListener<Image> onItemClickListener) {
        super.setOnItemClickListener(onItemClickListener);
    }

    @NonNull
    @Override
    public void deleteItem(int position) {
        super.deleteItem(position);
    }
}
