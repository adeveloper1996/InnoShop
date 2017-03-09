package project.innoshop.kz.innoshop.screen.main;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import project.innoshop.kz.innoshop.utils.dialog.LoadingView;

/**
 * Created by Andrey on 2/24/2017.
 */

public interface MainView extends LoadingView {

    void initNavigationView(Toolbar toolbar);
    void initNavHeader();
    void selectItem(MenuItem item);
    void setFragment(Fragment fragment);
    void setActionBarTitle(String item);
}
