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
    private boolean isSelecteAll = false; // 是否全选状态

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

    /**
     * 禁止焦点处于HintStr中
     *
     * @param selStart
     * @param selEnd
     */
    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        if (!TextUtils.isEmpty(hintStr) && selStart < hintStr.length()) {
            setSelection(hintStr.length(), getLength());
        }
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        boolean consumed = super.onTextContextMenuItem(id);
        switch (id) {
            case android.R.id.selectAll:
                isSelecteAll = true;
                setSelection(hintStr.length(), getLength());
                break;
            default:
                isSelecteAll = false;
                break;

        }
        return consumed;
    }

    /**
     * 保证hintStr不被删除的意向就是 选择焦点（包括 gSelectionStart和 SelectionEnd）处于与hintStr的长度相等的位置
     * 但是要排除全选的情况
     *
     * @param v
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            if (getSelectionStart() == hintStr
                    .length() && getSelectionEnd() == getSelectionStart()
                    && !isSelecteAll) {
                return true;
            }
            return false;
        }
        return false;
    }

    private int getLength() {
        return getText().toString().trim().length();
    }
}
