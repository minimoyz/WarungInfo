package com.pu.anonymous.mobileinformationcenter.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.pu.anonymous.mobileinformationcenter.R;

/**
 * Created by Anonymous on 08/09/2014.
 */
public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if(!isInEditMode()) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.helveticafont);

            int fontType = typedArray.getInt(R.styleable.helveticafont_fontType, 1);
            String font = null;
            if(fontType == 1)
                font = "cnn";
            else if(fontType == 2)
                font = "ltcn";
            else if(fontType == 3)
                font = "mdcn";
            else
                font = "roman";

            String asset = "font/helveticaneueltstd_" + font + ".otf";
            Typeface helvetica = Typeface.createFromAsset(getContext().getAssets(), asset);
            setTypeface(helvetica);
            typedArray.recycle();
        }
    }
}
