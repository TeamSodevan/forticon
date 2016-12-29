package sodevan.com.forticon;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.SimpleLineIconsIcons;
import com.joanzapata.iconify.fonts.SimpleLineIconsModule;

public class MainActivity extends AppCompatActivity {

    EditText User_Phoneno ;
    EditText password ;
    Button btn  ;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        btn = (Button) findViewById(R.id.login);




        Iconify.with(new SimpleLineIconsModule());
        setContentView(R.layout.activity_main);

        User_Phoneno = (EditText)findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password) ;



        Drawable user = new IconDrawable(this , SimpleLineIconsIcons.icon_user).colorRes(R.color.white).actionBarSize() ;
        Drawable passkey = new IconDrawable(this , SimpleLineIconsIcons.icon_key).colorRes(R.color.white).actionBarSize() ;

        User_Phoneno.setCompoundDrawablesWithIntrinsicBounds(user,null , null , null);
        password.setCompoundDrawablesWithIntrinsicBounds(passkey , null , null , null);


    }

}
