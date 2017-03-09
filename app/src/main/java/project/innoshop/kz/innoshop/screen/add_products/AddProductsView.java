package project.innoshop.kz.innoshop.screen.add_products;

import android.support.annotation.NonNull;
import android.widget.EditText;

import com.nguyenhoanglam.imagepicker.model.Image;

import java.util.List;

import project.innoshop.kz.innoshop.utils.dialog.LoadingView;

/**
 * Created by Andrey on 2/25/2017.
 */

public interface AddProductsView extends LoadingView {

    void clearEditText(@NonNull EditText editText);
    void initWidgets();
    void changeCheckImage(@NonNull EditText editText);
    void changeCheckImageDefault(@NonNull EditText editText);
    void visibileClearImage(@NonNull EditText editText);
    void invisibileClearImage(@NonNull EditText editText);
    void selectImage();
    void setAdapterImage(@NonNull List<Image> images);
    void setNumberTextWatcher(@NonNull EditText eTxtPrice,@NonNull EditText eTxtCount);
    void setBarCode(String scanContent);
    void errorBarCode();
}
