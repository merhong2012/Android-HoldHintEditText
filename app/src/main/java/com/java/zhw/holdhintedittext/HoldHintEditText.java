package com.java.zhw.holdhintedittext;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;


/**
 * Author: Zhw
 * Description:将Hint保持在EditText顶部，不会被编辑、删除、选中包括全选
 * 后期计划 增加设置Hint color、size、image
 */
public class HoldHintEditText extends EditText implements View.OnKeyListener {
    private String hintStr;

    public HoldHintEditText(Context context) {
        super(context);
        init();
    }


    public HoldHintEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HoldHintEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setHoldHint(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.hintStr = str;
    }

    private void init() {
        hintStr = getText().toString().trim();
        setSelection(hintStr.length());
        setOnKeyListener(this);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        if (!TextUtils.isEmpty(hintStr) && selStart < hintStr.length()) {
            setSelection(hintStr.length(), getText().toString().trim().length());
        }
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        boolean consumed = super.onTextContextMenuItem(id);
        switch (id) {
            case android.R.id.selectAll:
                setSelection(hintStr.length(), getText().toString().trim().length());
                break;
            default:
                break;

        }
        return consumed;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            if (getSelectionStart() < hintStr
                    .length()) {
                return true;
            }
        }
        return false;
    }
}
