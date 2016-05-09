package com.troy.sendcard.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.troy.sendcard.R;
import com.troy.sendcard.bean.response.SendCardInfoResult;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.presenter.ICardInfoSubmitPresenter;
import com.troy.sendcard.presenter.ICardVaildatePresenter;
import com.troy.sendcard.presenter.impl.CardInfoSubmitPresenterImpl;
import com.troy.sendcard.presenter.impl.CardVaildatePresenterImpl;
import com.troy.sendcard.ui.adapter.PictureAdapter;
import com.troy.sendcard.ui.view.CardInfoSubmitView;
import com.troy.sendcard.util.StringUtil;
import com.troy.sendcard.view.CardVaildateView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by chenlongfei on 16/4/13.
 */
public class SendCardActivity extends BaseActivity implements CardVaildateView, CardInfoSubmitView {
    @Bind(R.id.tv_card_amount)
    TextView mCardAmount;
    @Bind(R.id.tv_name)
    TextView mName;
    @Bind(R.id.tv_phone_num)
    TextView mPhoneNum;
    @Bind(R.id.tv_province)
    TextView mProvince;
    @Bind(R.id.tv_city)
    TextView mCity;
    @Bind(R.id.tv_address)
    TextView mAddress;
    @Bind(R.id.ed_express_no)
    EditText mExpressno;
    @Bind(R.id.iv_qr_code)
    ImageView mQR_code;
    @Bind(R.id.gridview_pic_upload)
    GridView mGridViewPic;
    @Bind(R.id.btn_submit)
    Button mSubmit;
    @Bind(R.id.root_view)
    View mRootView;
    @Bind(R.id.layout_input_card_info)
    LinearLayout mCardInfoLayout;

    private PictureAdapter mPictureAdapter;
    private ProgressDialog mProgressDialog;
    private ICardVaildatePresenter mCardVaildatePresenter;
    private ICardInfoSubmitPresenter mCardInfoSubmitPresenter;
    private String mApplyId;
    private List<String> mPics;
    private SendCardInfoResult.SendCardInfoDataEntity mSendCardInfoDataEntity;

    private static final int REQUEST_CODE_IMAGE_SELECTOR = 0;
    private static final int REQUEST_CODE_EXPRESS_NO = 1;
    private static final int REQUEST_CODE_SUBMIT_RESULT = 2;
    private static final int MAX_SELECTOR_AMOUNT = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplyId = getIntent().getStringExtra(Constant.Intent.KEY_APPLY_ID);
        mSendCardInfoDataEntity = (SendCardInfoResult.SendCardInfoDataEntity) getIntent().getSerializableExtra(Constant.Intent.KEY_SENDCARDINFO);
        mCardVaildatePresenter = new CardVaildatePresenterImpl(this, this);
        mCardInfoSubmitPresenter = new CardInfoSubmitPresenterImpl(this, this);
        initView();
        showUserInfo(mSendCardInfoDataEntity);
    }

    private void initView() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);

        mQR_code.setOnClickListener(onClickListener);


        mSubmit.setOnClickListener(onClickListener);

        mPics = new ArrayList<String>();
        mPictureAdapter = new PictureAdapter(this, mPics);
        mGridViewPic.setAdapter(mPictureAdapter);
        mGridViewPic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == mPictureAdapter.getCount() - 1) {
                    showAddImageSheet();
                } else {
                    showDeleteImageSheet(position);
                }
            }
        });

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_qr_code:
                    Intent intent = new Intent(SendCardActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_EXPRESS_NO);
                    break;
                case R.id.btn_submit:
                    submitInfo();
                    break;
            }
        }
    };


    private void submitInfo() {
        boolean isCardNoCorrect = true;
        JSONArray cardnoJsonArray = new JSONArray();
        int childCount = mCardInfoLayout.getChildCount();
        for (int index = 0; index < childCount; index++) {
            View view = mCardInfoLayout.getChildAt(index);
            EditText cardno = (EditText) view.findViewById(R.id.et_card_num);
            cardnoJsonArray.put(cardno.getText().toString());
            ImageView check_correct = (ImageView) view.findViewById(R.id.iv_check);
            if (TextUtils.isEmpty(cardno.getText())) {
                isCardNoCorrect = false;
            }
            if (check_correct.getVisibility() == View.GONE) {
                isCardNoCorrect = false;
            }
        }
        if (!isCardNoCorrect) {
            showToast(getString(R.string.card_no_error));
            return;
        }
        if (TextUtils.isEmpty(mExpressno.getText())) {
            showToast(getString(R.string.empty_express_no));
            return;
        }
        if (mPics.size() < 2) {
            showToast(getString(R.string.tips_pic_amount));
            return;
        }

        mCardInfoSubmitPresenter.cardInfoSubmit(mApplyId, cardnoJsonArray.toString(), mExpressno.getText().toString(), mPics);
    }

    private void showDeleteImageSheet(final int position) {
        ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelableOnTouchOutside(false)
                .setCancelButtonTitle(getString(R.string.btn_cancle))
                .setOtherButtonTitles(getString(R.string.btn_delete_image))
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        switch (index) {
                            case 0:
                                mPics.remove(position);
                                mPictureAdapter.notifyDataSetChanged();
                                break;
                        }
                    }
                }).show();
    }

    private void showAddImageSheet() {
        ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelableOnTouchOutside(false)
                .setCancelButtonTitle(getString(R.string.btn_cancle))
                .setOtherButtonTitles(getString(R.string.btn_album_camera))
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        switch (index) {
                            case 0:
                                Intent intent = new Intent(SendCardActivity.this, MultiImageSelectorActivity.class);
                                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, MAX_SELECTOR_AMOUNT - mPics.size());
                                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
//                                intent.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, defaultDataArray);
                                startActivityForResult(intent, REQUEST_CODE_IMAGE_SELECTOR);
                                break;
                        }
                    }
                }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_IMAGE_SELECTOR:
                    List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                    mPics.addAll(path);
                    mPictureAdapter.notifyDataSetChanged();
                    break;
                case REQUEST_CODE_EXPRESS_NO:
                    if (null != data) {
                        Bundle bundle = data.getExtras();
                        String expressno = bundle.getString(Constant.KEY_EXTRA_EXPRESS_NO);
                        if (!TextUtils.isEmpty(expressno)) {
                            mExpressno.setText(expressno);
                        }
                    }
                    break;

            }
        } else if (resultCode == RESULT_CANCELED) {
            switch (requestCode) {
                case REQUEST_CODE_SUBMIT_RESULT:
                    setResult(RESULT_OK);
                    finish();
                    break;
            }
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_send_card;
    }

    @Override
    public void showProgressDialog(String tips) {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(tips);
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onSubmitSuccess() {
        Intent intent = new Intent(SendCardActivity.this, SubmitResultActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SUBMIT_RESULT);
    }

    @Override
    public void onSubmitFail(String msg) {
        showToast(msg);
    }

    public void showUserInfo(SendCardInfoResult.SendCardInfoDataEntity sendCardInfoDataEntity) {
        if (!TextUtils.isEmpty(sendCardInfoDataEntity.getCard_no_total())) {
            mCardAmount.setText(sendCardInfoDataEntity.getCard_no_total() + "张");
        }
        if (!TextUtils.isEmpty(sendCardInfoDataEntity.getReceive_name())) {
            mName.setText(sendCardInfoDataEntity.getReceive_name());
        }
        if (!TextUtils.isEmpty(sendCardInfoDataEntity.getPhone())) {
            mPhoneNum.setText(sendCardInfoDataEntity.getPhone());
        }
        if (!TextUtils.isEmpty(sendCardInfoDataEntity.getProvince())) {
            mProvince.setText(sendCardInfoDataEntity.getProvince());
        }
        if (!TextUtils.isEmpty(sendCardInfoDataEntity.getCity())) {
            mCity.setText(sendCardInfoDataEntity.getCity());
        }
        if (!TextUtils.isEmpty(sendCardInfoDataEntity.getAddress())) {
            mAddress.setText(sendCardInfoDataEntity.getAddress());
        }
        int cardTotal = StringUtil.parseInt(sendCardInfoDataEntity.getCard_no_total());
        for (int i = 0; i < cardTotal; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_card_info_input, mCardInfoLayout, false);
            TextView tips = (TextView) view.findViewById(R.id.tv_tips_card_num);
            EditText cardno = (EditText) view.findViewById(R.id.et_card_num);
            cardno.requestFocus();
            final TextView card_error = (TextView) view.findViewById(R.id.tv_card_tips);
            card_error.setVisibility(View.GONE);
            final ImageView check_correct = (ImageView) view.findViewById(R.id.iv_check);
            check_correct.setVisibility(View.GONE);
            final int finalI = i;
            cardno.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String str = s.toString();
                    if (str.length() == 19) {
                        if (str.matches("^[1][0][0][0]\\d{15}$")) {
                            mCardVaildatePresenter.cardVaildate(str, finalI);
                            card_error.setVisibility(View.GONE);
                        } else {
                            String error = getString(R.string.card_num_error);
                            card_error.setVisibility(View.VISIBLE);
                            check_correct.setVisibility(View.GONE);
                            card_error.setText(error);
                        }
                    }
                    if (str.length() == 0) {
                        card_error.setVisibility(View.GONE);
                        check_correct.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
            tips.setText(getString(R.string.card_num) + (i + 1));
            mCardInfoLayout.addView(view);
        }

    }

    @Override
    public void onCardVaildateSuccess(int index) {
        View view = mCardInfoLayout.getChildAt(index);
        if (null != view) {
            TextView card_error = (TextView) view.findViewById(R.id.tv_card_tips);
            card_error.setVisibility(View.GONE);
            ImageView check_correct = (ImageView) view.findViewById(R.id.iv_check);
            check_correct.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCardVaildateFail(int index, String msg) {
        View view = mCardInfoLayout.getChildAt(index);
        if (null != view) {
            EditText cardno = (EditText) view.findViewById(R.id.et_card_num);
            cardno.requestFocus();
            TextView card_error = (TextView) view.findViewById(R.id.tv_card_tips);
            card_error.setVisibility(View.VISIBLE);
            card_error.setText(msg);
            ImageView check_correct = (ImageView) view.findViewById(R.id.iv_check);
            check_correct.setVisibility(View.GONE);
        }
    }
}
