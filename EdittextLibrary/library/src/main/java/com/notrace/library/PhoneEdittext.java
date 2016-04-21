package com.notrace.library;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.notrace.library.utils.DisplayUtils;

/**
 * Created by abc on 2016/4/21.
 */
public class PhoneEdittext extends EditText implements View.OnFocusChangeListener,TextWatcher{

    private Drawable mClearDrawable;

    private boolean hasFocus;
    private boolean isIgnore;
    private TextWatch textWatcher;



    public PhoneEdittext(Context context) {
        this(context,null);
    }

    public PhoneEdittext(Context context, AttributeSet attrs) {
        this(context,attrs,android.R.attr.editTextStyle);
    }

    public PhoneEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mClearDrawable=getCompoundDrawables()[2];
        if(mClearDrawable ==null)
        {
            mClearDrawable=getResources().getDrawable(R.drawable.ic_edit_clear);
        }
        int size= DisplayUtils.dip2px(getContext(),20);
        mClearDrawable.setBounds(0,0,size,size);
        setIconVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action=event.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_UP:
                if(getCompoundDrawables()[2]!=null){
                    boolean touch=event.getX()>(getWidth()-getTotalPaddingRight())
                            && (event.getX()<(getWidth()-getPaddingRight()));
                    if(touch)
                    this.setText("");
                }
                break;
        }
        return super.onTouchEvent(event);

    }

    private void setIconVisible(boolean visible)
    {
        Drawable right=visible ? mClearDrawable :null;
        setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1],right,getCompoundDrawables()[3]);

    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
            this.hasFocus=hasFocus;
        if(hasFocus)
        {
            setIconVisible(getText().length()>0);
        }else {
            setIconVisible(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if(isIgnore)
        {
            isIgnore=false;
            return;
        }
        CharSequence charSequence=addSpace(getPhoneNumber());
        isIgnore = true;
        setText(charSequence);

        if (getText() == null || getText().length() == 0) {
            setSelection(0);
            return;
        }
        setSelection(getText().length());
        if (textWatcher != null) {
            textWatcher.onTextChanged(getPhoneNumber());
        }
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if(hasFocus)
        {
            setIconVisible(text.length()>0);

        }
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    public CharSequence addSpace(String str){
        if (TextUtils.isEmpty(str))
        {
            return null;
        }
        StringBuffer buffer=new StringBuffer();
        for (int i=0;i<str.length();i++)
        {
            buffer.append(str.charAt(i));
            if((i+1)%4 ==3 && i!=str.length()-1)
            {
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    public String getPhoneNumber()
    {
        Editable editable=getText();
        if (editable==null)
            return null;

        String str=editable.toString();
        return str.replace(" ","");
    }

    public void setTextWatcher(TextWatch textWatcher)
    {
        this.textWatcher=textWatcher;
    }

    public void setShakeAnimation() {
        this.setAnimation(shakeAnimation(5));
    }

    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }
}
