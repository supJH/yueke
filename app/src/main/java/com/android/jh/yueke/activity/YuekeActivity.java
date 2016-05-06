package com.android.jh.yueke.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.jh.yueke.R;
import com.android.jh.yueke.adapter.DrawerMenuRecyclerAdapter;
import com.android.jh.yueke.fragment.main.ExploreFragment;
import com.android.jh.yueke.fragment.main.FollowFragment;
import com.android.jh.yueke.fragment.main.HomeFragment;
import com.android.jh.yueke.utils.Colorful;
import com.android.jh.yueke.view.RoundImageHelper;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class YuekeActivity extends AppCompatActivity {
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavView;
    private String[] drawerItemNames = new String[]{"首页", "发现", "关注", "收藏", "草稿", "提问"};
    private String[] itemColor = new String[]{"#0011d9", "#1dc4f7", "#32bfbb", "#ef7521", "#6cc800", "#ffcc00"};
    private RecyclerView mDrawerRecycler;
    private ImageView portraitImage;
    private Colorful mColorful;
    private TextView changeThemeTv;

    private TextView settingsTv;

    private boolean hasPressedBack = false;
    private long lastPressBack = 0L;

    //使用第三方库Picasso，实现URL获取图片
    private Target portraitTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            //getRoundedConnerBitmap，绘制成圆形的Bitmap
            portraitImage.setImageBitmap(RoundImageHelper.getRoundedCornerBitmap(bitmap, 200));
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
//            Toast.makeText(getApplicationContext(),"失败。。。",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mNavView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerRecycler = (RecyclerView) findViewById(R.id.drawer_menu_recycler);
        RecyclerView.Adapter mAdapter = new DrawerMenuRecyclerAdapter(this, drawerItemNames, mDrawerLayout);
        mDrawerRecycler.setAdapter(mAdapter);
        mDrawerRecycler.setLayoutManager(new LinearLayoutManager(this));

        mDrawerRecycler.setHasFixedSize(true);


        portraitImage = (ImageView) findViewById(R.id.round_portrait_image);
        Picasso.with(this).load(getResources().getString(R.string.base_url) + "/images/portrait.png").into(portraitTarget);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.main_fragment_container, new HomeFragment()).commit();

        settingsTv = (TextView) findViewById(R.id.zhihu_settings);
        settingsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YuekeActivity.this, YuekeSettingActivity.class);
                mDrawerLayout.closeDrawers();
                startActivity(intent);
            }
        });

        changeThemeTv = (TextView) findViewById(R.id.change_theme_text);
        changeThemeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("Config", Context.MODE_PRIVATE);
                boolean isNight = sp.getBoolean("night_theme", false);

                if (!isNight) {
                    mColorful.setTheme(R.style.NightTheme);

                } else {
                    mColorful.setTheme(R.style.DayTheme);
                }
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("night_theme", !isNight);
                editor.commit();
            }
        });

        setupColorful();
        SharedPreferences sp = getSharedPreferences("Config", Context.MODE_PRIVATE);
        boolean isNight = sp.getBoolean("night_theme", false);
        if (isNight) {
            mColorful.setTheme(R.style.NightTheme);

        } else {
            mColorful.setTheme(R.style.DayTheme);
        }

    }

    /**
     * 设置各个视图与颜色属性的关联
     */
    private void setupColorful() {

        // 构建Colorful对象来绑定View与属性的对象关系
        mColorful = new Colorful.Builder(this)
                .textColor(R.id.change_theme_text, R.attr.text_color)
                .create(); // 设置文本颜色
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zhihu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(YuekeActivity.this, YuekeSettingActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_about) {
            Intent intent = new Intent(YuekeActivity.this, YuekeAboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    public void selectDrawerItem(MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass = null;
        Intent intent = null;

        switch (item.getItemId()) {
            case R.id.drawer_item_home:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.drawer_item_explore:
                fragmentClass = ExploreFragment.class;
                break;
            case R.id.drawer_item_follow:
                fragmentClass = FollowFragment.class;
                break;
            case R.id.drawer_item_collect:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.drawer_item_draft:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.drawer_item_question:
                intent = new Intent(this, AddQuestionActivity.class);

                break;

            default:
                return;
        }

        if (null != fragmentClass) {
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.main_fragment_container, fragment).commit();

            item.setChecked(true);
            setTitle(item.getTitle());
            mDrawerLayout.closeDrawers();
            return;
        } else {
            startActivity(intent);
        }
    }

    /**
     * 按两次返回键才会退出，activity
     */
    @Override
    public void onBackPressed() {
        long currentPressBack = System.currentTimeMillis();
        if (hasPressedBack) {
            lastPressBack = currentPressBack;
            showExitTips();
        } else {
            if (currentPressBack - 2000 > lastPressBack) {
                lastPressBack = currentPressBack;
                showExitTips();
            } else {
                finish();
            }
        }
    }

    public void showExitTips() {
        Toast.makeText(this, "再按一次返回键退出应用", Toast.LENGTH_SHORT).show();
    }
}
