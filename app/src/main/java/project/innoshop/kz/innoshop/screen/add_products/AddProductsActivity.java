package project.innoshop.kz.innoshop.screen.add_products;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.nguyenhoanglam.imagepicker.activity.ImagePicker;
import com.nguyenhoanglam.imagepicker.model.Image;

import java.util.ArrayList;
import java.util.List;

import project.innoshop.kz.innoshop.R;
import project.innoshop.kz.innoshop.utils.dialog.LoadingDialog;
import project.innoshop.kz.innoshop.utils.dialog.LoadingView;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class AddProductsActivity extends AppCompatActivity implements AddProductsView,EditText.OnFocusChangeListener,View.OnClickListener{

    private LoadingView loadingView;
    private AddProductsPresenter presenter;
    private LifecycleHandler lifecycleHandler;

    private ImageView imgCheckName;
    private ImageView imgCheckPrice;
    private ImageView imgCheckCount;
    private ImageView imgCheckUnitTypeId;
    private ImageView imgCheckDescription;
    private ImageView imgCheckImage;
    private ImageView imgCheckQrCode;
    private ImageView imgClearName;
    private ImageView imgClearPrice;
    private ImageView imgClearCount;
    private ImageView imgClearUnitTypeId;
    private ImageView imgClearDescription;
    private EditText eTxtName;
    private EditText eTxtPrice;
    private EditText eTxtCount;
    private EditText eTxtunitTypeId;
    private EditText eTxtDescription;
    private EditText eTxtQrCode;
    private ImageButton iBtnQrCode;
    private Button iBtnSelectPhoto;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private ArrayList<Image> images = new ArrayList<>();
    private  int REQUEST_CODE_PICKER = 2000;

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, AddProductsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadingView = LoadingDialog.view(getFragmentManager());
        lifecycleHandler = LoaderLifecycleHandler.create(this,getSupportLoaderManager());
        presenter = new AddProductsPresenter(lifecycleHandler,this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initWidgets();

    }

    @Override
    public void initWidgets() {
        imgCheckName = (ImageView)findViewById(R.id.img_check_name);
        imgCheckPrice = (ImageView)findViewById(R.id.img_check_price);
        imgCheckCount = (ImageView)findViewById(R.id.img_check_count);
        imgCheckUnitTypeId = (ImageView)findViewById(R.id.img_check_unit_type_id);
        imgCheckDescription = (ImageView)findViewById(R.id.img_check_description);
        imgCheckImage = (ImageView)findViewById(R.id.img_check_choose_image);
        imgCheckQrCode = (ImageView)findViewById(R.id.img_check_qrcode);

        imgClearName = (ImageView)findViewById(R.id.img_clear_name);
        imgClearPrice = (ImageView)findViewById(R.id.img_clear_price);
        imgClearCount = (ImageView)findViewById(R.id.img_clear_count);
        imgClearDescription = (ImageView)findViewById(R.id.img_clear_description);
        imgClearUnitTypeId = (ImageView)findViewById(R.id.img_clear_unit_type_id);

        eTxtName = (EditText) findViewById(R.id.e_txt_name_product);
        eTxtPrice = (EditText) findViewById(R.id.e_txt_price_product);
        eTxtCount = (EditText) findViewById(R.id.e_txt_count_product);
        eTxtunitTypeId = (EditText) findViewById(R.id.e_txt_unit_type_id_product);
        eTxtDescription = (EditText) findViewById(R.id.e_txt_description_product);
        eTxtQrCode = (EditText) findViewById(R.id.e_txt_qrcode_product);

        imgCheckQrCode = (ImageButton) findViewById(R.id.i_btn_read_qr_code);
        iBtnSelectPhoto = (Button) findViewById(R.id.i_btn_take_photo);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_image_horizontal);

        eTxtName.setOnFocusChangeListener(this);
        eTxtPrice.setOnFocusChangeListener(this);
        eTxtCount.setOnFocusChangeListener(this);
        eTxtunitTypeId.setOnFocusChangeListener(this);
        eTxtDescription.setOnFocusChangeListener(this);

        imgClearName.setOnClickListener(this);
        imgClearPrice.setOnClickListener(this);
        imgClearCount.setOnClickListener(this);
        imgClearUnitTypeId.setOnClickListener(this);
        imgClearDescription.setOnClickListener(this);
        iBtnSelectPhoto.setOnClickListener(this);
        imgCheckQrCode.setOnClickListener(this);

        setNumberTextWatcher(eTxtPrice,eTxtCount);
    }

    @Override
    public void changeCheckImage(@NonNull EditText editText) {
        if (editText == eTxtName){
            imgCheckName.setImageResource(R.drawable.ic_check_circle_black_24dp);
        }
        if (editText == eTxtPrice){
            imgCheckPrice.setImageResource(R.drawable.ic_check_circle_black_24dp);
        }
        if (editText == eTxtCount){
            imgCheckCount.setImageResource(R.drawable.ic_check_circle_black_24dp);
        }
        if (editText == eTxtunitTypeId){
            imgCheckUnitTypeId.setImageResource(R.drawable.ic_check_circle_black_24dp);
        }
        if (editText == eTxtDescription){
            imgCheckDescription.setImageResource(R.drawable.ic_check_circle_black_24dp);
        }
    }

    @Override
    public void changeCheckImageDefault(@NonNull EditText editText) {
        if (editText == eTxtName){
            imgCheckName.setImageResource(R.drawable.ic_brightness_1_grey_400_24dp);
        }
        if (editText == eTxtPrice){
            imgCheckPrice.setImageResource(R.drawable.ic_brightness_1_grey_400_24dp);
        }
        if (editText == eTxtCount){
            imgCheckCount.setImageResource(R.drawable.ic_brightness_1_grey_400_24dp);
        }
        if (editText == eTxtunitTypeId){
            imgCheckUnitTypeId.setImageResource(R.drawable.ic_brightness_1_grey_400_24dp);
        }
        if (editText == eTxtDescription){
            imgCheckDescription.setImageResource(R.drawable.ic_brightness_1_grey_400_24dp);
        }
    }

    @Override
    public void visibileClearImage(@NonNull EditText editText) {
        if (editText == eTxtName) {
            imgClearName.setVisibility(View.VISIBLE);
        }
        if (editText == eTxtPrice) {
            imgClearPrice.setVisibility(View.VISIBLE);
        }
        if (editText == eTxtCount) {
            imgClearCount.setVisibility(View.VISIBLE);
        }
        if (editText == eTxtunitTypeId) {
            imgClearUnitTypeId.setVisibility(View.VISIBLE);
        }
        if (editText == eTxtDescription) {
            imgClearDescription.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void invisibileClearImage(@NonNull EditText editText) {
        if (editText == eTxtName) {
            imgClearName.setVisibility(View.GONE);
        }
        if (editText == eTxtPrice) {
            imgClearPrice.setVisibility(View.GONE);
        }
        if (editText == eTxtCount) {
            imgClearCount.setVisibility(View.GONE);
        }
        if (editText == eTxtunitTypeId) {
            imgClearUnitTypeId.setVisibility(View.GONE);
        }
        if (editText == eTxtDescription) {
            imgClearDescription.setVisibility(View.GONE);
        }
    }

    @Override
    public void selectImage() {
        ImagePicker.create(this)
                .folderMode(true) // set folder mode (false by default)
                .folderTitle("Folder") // folder selection title
                .imageTitle("Tap to select") // image selection title
                .single() // single mode
                .multi() // multi mode (default mode)
                .limit(8) // max images can be selected (999 by default)
                .showCamera(true) // show camera or not (true by default)
                .imageDirectory("Camera")   // captured image directory name ("Camera" folder by default)
                .origin(images) // original selected images, used in multi mode
                .start(REQUEST_CODE_PICKER); // start image picker activity with request code
    }

    @Override
    public void setAdapterImage(@NonNull List<Image> images) {
        if(images != null){
            imgCheckImage.setImageResource(R.drawable.ic_check_circle_black_24dp);
        }else {
            imgCheckImage.setImageResource(R.drawable.ic_brightness_1_grey_400_24dp);
        }
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new AddProductAdapter(images));
    }

    @Override
    public void setNumberTextWatcher(@NonNull EditText eTxtPrice, @NonNull EditText eTxtCount) {
        presenter.numbertextWatcher(eTxtPrice);
        presenter.numbertextWatcher(eTxtCount);
    }

    @Override
    public void setBarCode(String scanContent) {
        eTxtQrCode.setText(scanContent);
    }

    @Override
    public void errorBarCode() {
        Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
        toast.show();
    }


    @Override
    public void clearEditText(@NonNull EditText editText) {
        if(editText == eTxtName){
            eTxtName.getText().clear();
        }
        if(editText == eTxtPrice){
            eTxtPrice.getText().clear();
        }
        if(editText == eTxtCount){
            eTxtCount.getText().clear();
        }
        if(editText == eTxtunitTypeId){
            eTxtunitTypeId.getText().clear();
        }
        if(editText == eTxtDescription){
            eTxtDescription.getText().clear();
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.e_txt_name_product:
                presenter.textWatcher(eTxtName);
                break;
            case R.id.e_txt_price_product:
                presenter.textWatcher(eTxtPrice);
                break;
            case R.id.e_txt_count_product:
                presenter.textWatcher(eTxtCount);
                break;
            case R.id.e_txt_unit_type_id_product:
                presenter.textWatcher(eTxtunitTypeId);
                break;
            case R.id.e_txt_description_product:
                presenter.textWatcher(eTxtDescription);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_clear_name:
                presenter.clearEditText(eTxtName);
                break;
            case R.id.img_clear_price:
                presenter.clearEditText(eTxtPrice);
                break;
            case R.id.img_clear_count:
                presenter.clearEditText(eTxtCount);
                break;
            case R.id.img_clear_unit_type_id:
                presenter.clearEditText(eTxtunitTypeId);
                break;
            case R.id.img_clear_description:
                presenter.clearEditText(eTxtDescription);
                break;
            case R.id.i_btn_take_photo:
                presenter.selectImage();
                break;
            case R.id.i_btn_read_qr_code:
                IntentIntegrator integrator = new IntentIntegrator(this);
                integrator.initiateScan();
                break;
        }
    }

    @Override
    public void showLoading() {
        loadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        loadingView.hideLoading();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.onActivityResult(requestCode,resultCode,data);
    }
}
