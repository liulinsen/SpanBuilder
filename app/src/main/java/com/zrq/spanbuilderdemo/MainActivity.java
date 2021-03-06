package com.zrq.spanbuilderdemo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ScaleXSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zrq.spanbuilder.SpanBuilder;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

        // 1.单个样式
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder()
                .append(new SpanBuilder("字体30Sp\n").setTextSize(30))
                .append(new SpanBuilder("字体红色\n").setTextColor(Color.RED))
                .append(new SpanBuilder("字体背景绿色\n").setBackgroundColor(Color.GREEN))
                .append(new SpanBuilder("粗斜体\n").setTextStyle(Typeface.BOLD_ITALIC))
                .append(new SpanBuilder("自定义Style样式\n").
                        setTextAppearance(getApplicationContext(), android.R.style.TextAppearance_Small))
                .append(new SpanBuilder("可点击\n").setClick(textView, new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        // ！！点击了内容，但是会触发TextView的点击事件
                        Toast.makeText(getApplicationContext(), "ClickSpan点击了", Toast.LENGTH_LONG).show();
                    }
                }))
                .append(new SpanBuilder("删除线\n").setDeleteLine())
                .append(new SpanBuilder("下划线\n").setUnderLine())
                .append(new SpanBuilder("此内容无效，会被图片给替换\n").setImage(drawable))
                .append(new SpanBuilder("字体类型为serif\n").setTypeface("serif"))
                .append(new SpanBuilder("设置蓝色的引用线\n").setQuote(Color.BLUE))
                .append(new SpanBuilder("设置此内容的对齐方式为相反\n").setAlignment(Layout.Alignment.ALIGN_OPPOSITE))
                .append(new SpanBuilder("设置字体大小为之前的1.2倍\n").setRelativeSize(1.2f))
                .append("这是")
                .append(new SpanBuilder("上标\n").setUpLabel())
                .append("这是")
                .append(new SpanBuilder("下标\n").setUnderLabel())
                .append(new SpanBuilder("X轴缩放3倍\n").setScaleX(3f));

        // 2.混合样式
        SpanBuilder spanBuilder = new SpanBuilder("\n\n此为混合样式应用于部分,设置字体15sp、红色、背景绿色、粗斜体\n\n\n")
                .setTextSize(15)
                .setTextColor(Color.RED)
                .setBackgroundColor(Color.GREEN)
                .setTextStyle(Typeface.BOLD_ITALIC);
        spannableStringBuilder.append(spanBuilder);

        // 3.给混合样式部分内容添加（或替换）新样式
        SpanBuilder oldSpan = new SpanBuilder("给混合样式部分内容添加（或替换）新样式,设置其为蓝色、背景红色\n")
                .setTextSize(15)
                .setTextColor(Color.RED)
                .setBackgroundColor(Color.GREEN)
                .setTextStyle(Typeface.BOLD_ITALIC);

        // 新样式的容器
        SpanBuilder newSpanStyle = new SpanBuilder()
                .setTextColor(Color.BLUE)
                .setBackgroundColor(Color.RED);
        // newSpanStyle里面的样式会添加（或替换）原来oldSpan的样式
        oldSpan.addNewSpanStyle(5, 16, newSpanStyle);

        spannableStringBuilder.append(oldSpan);
        // 4.添加其它样式
        // 4.1 作用于所有
        new SpanBuilder("X轴缩放3倍\n").setSpanAll(
                new ForegroundColorSpan(Color.RED),//字体红色
                new BackgroundColorSpan(Color.GREEN), //删除线
                new ScaleXSpan(2.5f));
        // 4.1 作用于部分
        new SpanBuilder("X轴缩放3倍\n").setSpanPart(1, 5,
                new ForegroundColorSpan(Color.RED),//字体红色
                new BackgroundColorSpan(Color.GREEN), //删除线
                new ScaleXSpan(2.5f));

        // 5.常用组合
        spannableStringBuilder
                .append(new SpanBuilder("8").setTextSize(45).setTextColor(Color.RED))
                .append(new SpanBuilder(".88").setTextSize(28).setTextColor(Color.RED))
                .append(new SpanBuilder("%\n").setTextSize(16).setTextColor(Color.BLACK));

        spannableStringBuilder
                .append(new SpanBuilder("10").setTextSize(50).setTextColor(Color.RED).setTextStyle(Typeface.BOLD_ITALIC))
                .append(new SpanBuilder("元\n").setTextSize(16).setTextColor(Color.BLACK));

        spannableStringBuilder
                .append(new SpanBuilder("￥149").setTextSize(24).setTextColor(Color.RED))
                .append(new SpanBuilder(".9  ").setTextSize(16).setTextColor(Color.RED))
                .append(new SpanBuilder("￥259.00").setTextSize(20).setTextColor(Color.BLACK).setDeleteLine())
                .append(new SpanBuilder("   4738").setTextSize(20).setTextColor(Color.RED))
                .append(new SpanBuilder("件已售\n").setTextSize(20).setTextColor(Color.BLACK));


        textView.setText(spannableStringBuilder);

    }
}
