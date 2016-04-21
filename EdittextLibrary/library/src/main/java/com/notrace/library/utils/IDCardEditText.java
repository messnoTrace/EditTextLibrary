package com.notrace.library.utils;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.notrace.library.TextWatch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by abc on 2016/4/21.
 */
public class IDCardEditText extends EditText {
    private TextWatch mTextWatch;

    private KeyboardView keyboardView;

    public CharSequence addSpace(String str) {

        if (str == null) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'x' || str.charAt(i) == 'X') {
                if (i == 17) {
                    buffer.append(str.charAt(i));
                }
                break;
            }
            buffer.append(str.charAt(i));

            if ((i + 2) % 4 == 3 && i != str.length() - 1) {
                buffer.append(' ');
            }
        }
        return buffer.toString();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        disableShowSoftInput();
//        int tempInputType = getInputType();
//        this.setInputType(InputType.TYPE_NULL);
        boolean superResult = super.onTouchEvent(event);
//        this.setInputType(tempInputType);
//        this.setSelection(this.getText().length());
        hideSoftInputMethod(this);
        if (keyboardView != null) {
            keyboardView.setVisibility(View.VISIBLE);
        }

        return superResult;
    }


    /**
     * 隐藏系统键盘
     *
     * @param ed
     */
    public void hideSoftInputMethod(EditText ed) {
        ((Activity) getContext()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        int currentVersion = android.os.Build.VERSION.SDK_INT;
        String methodName = null;
        if (currentVersion >= 16) {
            // 4.2
            methodName = "setShowSoftInputOnFocus";
        } else if (currentVersion >= 14) {
            // 4.0
            methodName = "setSoftInputShownOnFocus";
        }

        if (methodName == null) {
            ed.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus;
            try {
                setShowSoftInputOnFocus = cls.getMethod(methodName, boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(ed, false);
            } catch (NoSuchMethodException e) {
                ed.setInputType(InputType.TYPE_NULL);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }


    private void bankCardNumAddSpace() {
        addTextChangedListener(new TextWatcher() {
            boolean isIgnore = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isIgnore) {
                    isIgnore = false;
                    return;
                }
                CharSequence chars = addSpace(getIDCardNo());
                isIgnore = true;
                setText(chars);
                setSelection(chars.length());

                if (getText() == null || getText().length() == 0) {
                    setSelection(0);
                    return;
                }
                setSelection(getText().length());
                if (mTextWatch != null) {
                    mTextWatch.onTextChanged(getIDCardNo());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public IDCardEditText(Context context) {
        super(context);
        init(context, null);

    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return super.getSuggestedMinimumHeight();
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return super.getSuggestedMinimumWidth();
    }

    public void init(Context context, AttributeSet attrs) {

        bankCardNumAddSpace();

        if (attrs == null) {
            return;
        }

    }

    public IDCardEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public IDCardEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public String getIDCardNo() {
        Editable editable = super.getText();
        if (editable == null) {
            return null;
        }
        String str = editable.toString();
        return str.replace(" ", "");
    }

    public TextWatch getTextWatch() {
        return mTextWatch;
    }

    public void setTextWatch(TextWatch textWatch) {
        this.mTextWatch = textWatch;
    }

    public KeyboardView getKeyboardView() {
        return keyboardView;
    }

    public void setKeyboardView(KeyboardView keyboardView) {
        this.keyboardView = keyboardView;
    }
}
