package project.innoshop.kz.innoshop.screen.product;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.innoshop.kz.innoshop.R;
import project.innoshop.kz.innoshop.screen.add_products.AddProductsActivity;
import project.innoshop.kz.innoshop.utils.dialog.LoadingDialog;
import project.innoshop.kz.innoshop.utils.dialog.LoadingView;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment implements ProductsView{

    private LoadingView loadingView;
    private ProductsPresenter productsPresenter;
    private LifecycleHandler lifecycleHandler;

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleHandler = LoaderLifecycleHandler.create(getActivity(), getActivity().getSupportLoaderManager());
        productsPresenter = new ProductsPresenter(lifecycleHandler,this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadingView = LoadingDialog.view(getActivity().getFragmentManager());
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fba_add_product);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productsPresenter.onClickFab();
            }
        });
    }

    @Override
    public void openAddProductsActivity() {
        AddProductsActivity.start(getActivity());
    }

    @Override
    public void showLoading() {
        loadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        loadingView.hideLoading();
    }
}
