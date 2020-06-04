package com.capstone.moayo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CustomDialog extends Dialog {

    private Context mcontext;
    private TextView mPositiveButton, mNegativeButton;

    private TextView dialog_title, dialog_content;
    private String mTitle, mContent;

    private View.OnClickListener mPositiveListener;
    private View.OnClickListener mNegativeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //다이얼로그 밖의 화면은 흐리게
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        setContentView(R.layout.custom_dialog);

        //셋팅
        mPositiveButton=(TextView) findViewById(R.id.btn_ok);
        mNegativeButton=(TextView) findViewById(R.id.btn_no);
        dialog_title =(TextView) findViewById(R.id.dialog_title);
        dialog_content =(TextView) findViewById(R.id.dialog_content);


        //클릭 리스너 셋팅 (클릭버튼이 동작하도록 만들어줌.)
        mPositiveButton.setOnClickListener(mPositiveListener);
        mNegativeButton.setOnClickListener(mNegativeListener);
        dialog_title.setText(mTitle);
        dialog_content.setText(mContent);

    }

    //생성자 생성
    public CustomDialog(@NonNull Context context,
                        View.OnClickListener positiveListener,
                        View.OnClickListener negativeListener,
                        String title, String content
    )
    {
        super(context);
        this.mPositiveListener = positiveListener;
        this.mNegativeListener = negativeListener;
        this.mTitle = title;
        this.mContent = content;
    }
}
