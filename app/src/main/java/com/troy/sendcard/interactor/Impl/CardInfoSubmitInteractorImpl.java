package com.troy.sendcard.interactor.Impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.troy.sendcard.R;
import com.troy.sendcard.SendCardApp;
import com.troy.sendcard.bean.request.CardInfoSubmitParam;
import com.troy.sendcard.bean.respone.CardInfoSubmitResult;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.api.CardInfoSubmitApi;
import com.troy.sendcard.interactor.ICardInfoSubmitInteractor;
import com.troy.sendcard.util.SignUtil;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by chenlongfei on 16/4/20.
 */
public class CardInfoSubmitInteractorImpl implements ICardInfoSubmitInteractor {
    private ICardInfoSubmitInteractor.CardInfoSubmitListener mCardInfoSubmitListener;

    public CardInfoSubmitInteractorImpl(CardInfoSubmitListener cardInfoSubmitListener) {
        mCardInfoSubmitListener = cardInfoSubmitListener;
    }

    @Override
    public void requestCardInfoSubmit(final String applyId, final String cardnoList, final String sendno, List<String> pics) {
        final SendCardApp.UserInfo userInfo = new SendCardApp.UserInfo();
        final JSONArray picJsonArray = new JSONArray();
        Observable.from(pics)
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String filePath) {
                        return getBitmapFromPath(filePath);
                    }
                })
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {
                        CardInfoSubmitParam param = new CardInfoSubmitParam();
                        param.setApply_id(applyId);
                        param.setUser_id(userInfo.getUserId());
                        param.setSecret(userInfo.getSecret());
                        param.setImglist(picJsonArray.toString());
                        param.setSend_type("1");
                        param.setSend_no(sendno);
                        param.setCardlist(cardnoList);
                        param.setTime(System.currentTimeMillis() + "");
                        param.setSign(SignUtil.sign(param.createCardInfoSubmitParam()));
                        param.setImglist(picJsonArray.toString());
                        submit(param.createCardInfoSubmitParam());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
                        byte[] bytes = byteArrayOutputStream.toByteArray();
                        String base64 = Base64.encodeToString(bytes, 0);
                        picJsonArray.put(base64);
                        if (!bitmap.isRecycled()) {
                            bitmap.recycle();
                            bitmap = null;
                        }
                    }
                });
    }


    private void submit(Map<String, String> map) {
        CardInfoSubmitApi.getApi().getService().getResult(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CardInfoSubmitResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mCardInfoSubmitListener.onFail(SendCardApp.mContext.getString(R.string.network_error));
                    }

                    @Override
                    public void onNext(CardInfoSubmitResult cardInfoSubmitResult) {
                        if (cardInfoSubmitResult.getCode() == Constant.ResponeStatus.OK) {
                            mCardInfoSubmitListener.onSuccess();
                        } else {
                            mCardInfoSubmitListener.onFail(cardInfoSubmitResult.getMsg());
                        }
                    }
                });
    }

    private Bitmap getBitmapFromPath(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        return BitmapFactory.decodeFile(filePath, options);
    }
}
