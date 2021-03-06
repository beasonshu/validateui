package cxy.com.validateui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import cxy.com.validate.IValidateResult;
import cxy.com.validate.Validate;
import cxy.com.validate.ValidateAnimation;
import cxy.com.validate.annotation.Index;
import cxy.com.validate.annotation.MaxLength;
import cxy.com.validate.annotation.MinLength;
import cxy.com.validate.annotation.NotNull;
import cxy.com.validate.annotation.RE;
import cxy.com.validate.annotation.Repeat;
import cxy.com.validate.annotation.RepeatLast;


public class MainActivity extends AppCompatActivity implements IValidateResult {

    @Index(1)
    @NotNull(msg = "不能为空！")
    @Bind(R.id.et_notnull)
    EditText etNotnull;

    @Index(4)
    @NotNull(msg = "两次密码验证->密码一不为能空！")
    @Repeat(flag = "AA")
    @Bind(R.id.et_pw1)
    EditText etPw1;

    @Index(5)
    @NotNull(msg = "两次密码验证->密码二不为能空！")
    @RepeatLast(flag = "AA", msg = "两次密码不一致！！！")
    @Bind(R.id.et_pw2)
    EditText etPw2;

    @Index(6)
    @NotNull(msg = "多次密码验证->密码一不为能空！")
    @Repeat(flag = "BB")
    @Bind(R.id.et_pw_flag1)
    EditText etPwFlag1;
    @Index(7)
    @NotNull(msg = "多次密码验证->密码二不为能空！")
    @Repeat(flag = "BB")
    @Bind(R.id.et_pw_flag2)
    EditText etPwFlag2;
    @Index(8)
    @NotNull(msg = "多次密码验证->密码三不为能空！")
    @RepeatLast(flag = "BB", msg = "三次密码不同")
    @Bind(R.id.et_pw_flag3)
    EditText etPwFlag3;

    @Index(9)
    @NotNull(msg = "请填写邮箱")
    @Bind(R.id.et_re)
    @RE(re = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}", msg = "格式错误！")
    EditText etRe;

    @Index(2)
    @Bind(R.id.et_max)
    @MaxLength(length = 3, msg = "超出最大长度")
    EditText et_max;
    @Index(3)
    @Bind(R.id.et_min)
    @MinLength(length = 3, msg = "错误，字符数目不够")
    EditText et_min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Validate.reg(this);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validate.check(MainActivity.this, MainActivity.this);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Validate.unreg(this);
    }

    @Override
    public void onValidateSuccess() {
        Toast.makeText(this, "验证成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidateError(String msg, EditText editText) {
        editText.setFocusable(true);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Animation onValidateErrorAnno() {
        return ValidateAnimation.horizontalTranslate();
    }
}
