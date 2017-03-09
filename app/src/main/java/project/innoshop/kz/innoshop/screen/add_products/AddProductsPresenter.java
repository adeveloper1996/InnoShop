package project.innoshop.kz.innoshop.screen.add_products;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nguyenhoanglam.imagepicker.activity.ImagePickerActivity;
import com.nguyenhoanglam.imagepicker.model.Image;

import java.util.ArrayList;

import project.innoshop.kz.innoshop.utils.NumberTextWatcher;
import ru.arturvasilov.rxloader.LifecycleHandler;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Andrey on 2/25/2017.
 */

public class AddProductsPresenter {
    private final LifecycleHandler mLifecycleHandler;
    private final AddProductsView view;
    private ArrayList<Image> images = new ArrayList<>();
    private  int REQUEST_CODE_PICKER = 2000;

    public AddProductsPresenter(@NonNull LifecycleHandler mLifecycleHandler,@NonNull AddProductsView view) {
        this.mLifecycleHandler = mLifecycleHandler;
        this.view = view;
    }

    public void textWatcher(final EditText editText){
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0){
                    view.visibileClearImage(editText);
                    view.changeCheckImage(editText);
                }
                if (s.length() == 0){
                    view.invisibileClearImage(editText);
                    view.changeCheckImageDefault(editText);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        editText.addTextChangedListener(textWatcher);
    }

    public void clearEditText(EditText editText){
        view.clearEditText(editText);
    }

    public void selectImage(){
        view.selectImage();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode ==  REQUEST_CODE_PICKER&& resultCode == RESULT_OK && data != null) {
            images = data.getParcelableArrayListExtra(ImagePickerActivity.INTENT_EXTRA_SELECTED_IMAGES);
            view.setAdapterImage(images);
            StringBuilder sb = new StringBuilder();
            for (int i = 0, l = images.size(); i < l; i++) {
                sb.append(images.get(i).getPath() + "\n");
            }
            Log.d("ssss",sb.toString());
        }
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            view.setBarCode(scanContent);
        }
        else{
            view.errorBarCode();

        }
    }

    public void numbertextWatcher(EditText editText){
        editText.addTextChangedListener(new NumberTextWatcher(editText));
    }

}
