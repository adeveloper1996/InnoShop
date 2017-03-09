package project.innoshop.kz.innoshop.screen.product;

import android.support.annotation.NonNull;

import project.innoshop.kz.innoshop.screen.add_products.AddProductsActivity;
import project.innoshop.kz.innoshop.screen.main.MainActivity;
import ru.arturvasilov.rxloader.LifecycleHandler;

/**
 * Created by Andrey on 2/24/2017.
 */

public class ProductsPresenter {

    private final LifecycleHandler mLifecycleHandler;
    private final ProductsView productsView;

    public ProductsPresenter(@NonNull LifecycleHandler mLifecycleHandler, @NonNull ProductsView productsView) {
        this.mLifecycleHandler = mLifecycleHandler;
        this.productsView = productsView;
    }

    public void onClickFab(){
        productsView.openAddProductsActivity();
    }
}
