package vn.lequan.warrior.views;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.smile.studio.libsmilestudio.R;


public class DialogError extends Dialog implements
        android.view.View.OnClickListener {

    private int score;

    public DialogError(int score, Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.score = score;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_popup_thongbao);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
        dismiss();
    }
}