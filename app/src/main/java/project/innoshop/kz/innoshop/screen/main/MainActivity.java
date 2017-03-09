package project.innoshop.kz.innoshop.screen.main;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import project.innoshop.kz.innoshop.R;
import project.innoshop.kz.innoshop.screen.main.fragments.ChangeWorkFragment;
import project.innoshop.kz.innoshop.screen.main.fragments.PersonalsFragment;
import project.innoshop.kz.innoshop.screen.main.fragments.ProductTimeEndFragment;
import project.innoshop.kz.innoshop.screen.product.ProductsFragment;
import project.innoshop.kz.innoshop.screen.main.fragments.ReportSalesFragment;
import project.innoshop.kz.innoshop.screen.main.fragments.ReportsFragment;
import project.innoshop.kz.innoshop.screen.main.fragments.RolesFragment;
import project.innoshop.kz.innoshop.screen.main.fragments.TypeOfEdinitsFragment;
import project.innoshop.kz.innoshop.screen.main.fragments.TypeOfZarplataFragment;
import project.innoshop.kz.innoshop.screen.main.fragments.TypeOplataFragment;
import project.innoshop.kz.innoshop.screen.main.fragments.ZarplataFragment;
import project.innoshop.kz.innoshop.utils.CircleTransform;
import project.innoshop.kz.innoshop.utils.dialog.LoadingDialog;
import project.innoshop.kz.innoshop.utils.dialog.LoadingView;

public class MainActivity extends AppCompatActivity implements MainView{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Fragment fragment;
    private NavigationView navigationView;
    private LoadingView loadingView;
    private ImageView avatar;

    private class DrawerItemClickListener implements NavigationView.OnNavigationItemSelectedListener{

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            selectItem(item);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadingView = LoadingDialog.view(getFragmentManager());

        initNavigationView(toolbar);
    }

    @Override
    public void initNavigationView(Toolbar toolbar) {
        navigationView = (NavigationView)findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        navigationView.setItemTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        initNavHeader();
        navigationView.inflateMenu(R.menu.menu_navigation);
        navigationView.setNavigationItemSelectedListener(new DrawerItemClickListener());
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public void initNavHeader() {
        View navHeader = navigationView.getHeaderView(0);
        avatar = (ImageView)navHeader.findViewById(R.id.img_avatar);
        Picasso.with(this).load(R.drawable.ava).transform(new CircleTransform()).into(avatar);
    }

    @Override
    public void selectItem(MenuItem item) {
        fragment = null;
        switch (item.getItemId()) {
            case R.id.menu_product:
                fragment = new ProductsFragment();
                break;
            case R.id.menu_report:
                fragment = new ReportsFragment();
                break;
            case R.id.menu_roles:
                fragment = new RolesFragment();
                break;
            case R.id.menu_personals:
                fragment = new PersonalsFragment();
                break;
            case R.id.menu_zarplata:
                fragment = new ZarplataFragment();
                break;
            case R.id.menu_type_zarplata:
                fragment = new TypeOfZarplataFragment();
                break;
            case R.id.menu_report_sales:
                fragment = new ReportSalesFragment();
                break;
            case R.id.menu_type_edinits:
                fragment = new TypeOfEdinitsFragment();
                break;
            case R.id.menu_type_oplata:
                fragment = new TypeOplataFragment();
                break;
            case R.id.menu_change_work:
                fragment = new ChangeWorkFragment();
                break;
            case R.id.menu_product_time_end:
                fragment = new ProductTimeEndFragment();
                break;
            default:
                break;
        }
        setFragment(fragment);
        setActionBarTitle(item.getTitle().toString());
    }

    @Override
    public void setFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        drawerLayout.closeDrawer(navigationView);
    }

    @Override
    public void setActionBarTitle(String item) {
        String title = item;
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
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
