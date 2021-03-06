package codersit.co.kr.jejugo.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.activity.coupon.CouponFragment;
import codersit.co.kr.jejugo.activity.festival.FestivalFragment;
import codersit.co.kr.jejugo.activity.festival.JejuArtcenterFragment;
import codersit.co.kr.jejugo.activity.festival.JejuArtcenterSearchFragment;
import codersit.co.kr.jejugo.activity.festival.JejuCultureStreetFragment;
import codersit.co.kr.jejugo.activity.festival.JejuFestivalFragment;
import codersit.co.kr.jejugo.activity.festival.SeogwipoCultureFragment;
import codersit.co.kr.jejugo.activity.food.FoodDetailFragment;
import codersit.co.kr.jejugo.activity.food.FoodFragment;
import codersit.co.kr.jejugo.activity.hotplace.HotplaceDetailFragment;
import codersit.co.kr.jejugo.activity.hotplace.HotplaceFragment;
import codersit.co.kr.jejugo.activity.partnerstore.PartnerStoreFragment;
import codersit.co.kr.jejugo.activity.setting.AboutFragment;
import codersit.co.kr.jejugo.activity.setting.InfoFragment;
import codersit.co.kr.jejugo.activity.stampbook.StampBookFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int isQuit;

    String LOG = "MainActivity";

    public static Context mContext;
    public static int RENEW_GPS = 1;
    public static int SEND_PRINT = 2;

    FrameLayout frame_layout;

    Fragment curFragment;
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        frame_layout = (FrameLayout)findViewById(R.id.frame_layout);
        mContext = this.getApplicationContext();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        callFragmentPage(new MainFragment());



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if(curFragment instanceof HotplaceDetailFragment)
            {
                callFragmentPage(new HotplaceFragment());
                return;
            }
            else if(curFragment instanceof FoodDetailFragment)
            {
                callFragmentPage(new FoodFragment());
                return;
            }
            else if(curFragment instanceof CouponFragment)
            {
                callFragmentPage(new StampBookFragment());
                return;
            }
            else if(curFragment instanceof PartnerStoreFragment)
            {
                callFragmentPage(new StampBookFragment());
                return;
            }
            else if(curFragment instanceof JejuArtcenterSearchFragment)
            {
                callFragmentPage(new JejuArtcenterFragment());
                return;
            }
            else if(curFragment instanceof JejuArtcenterFragment
                    || curFragment instanceof JejuCultureStreetFragment
                    || curFragment instanceof JejuFestivalFragment
                    || curFragment instanceof SeogwipoCultureFragment)
            {
                callFragmentPage(new FestivalFragment());
                return;
            }


            if(isQuit==2)
            {
                callFragmentPage(new MainFragment());
                isQuit=1;
            }
            else if(isQuit==1)
            {
                Toast.makeText(getApplicationContext(),"뒤로가기 버튼을 한번 더 누르면 앱이 종료됩니다.",Toast.LENGTH_SHORT).show();
                isQuit=0;
            }
            else if(isQuit==0)
                super.onBackPressed();


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            callFragmentPage(new InfoFragment());
        }

        if (id == R.id.action_about) {
            callFragmentPage(new AboutFragment());
        }



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_stamp_get) {
            callFragmentPage(new StampGetFragment());
        } else if (id == R.id.nav_hotplace) {
            callFragmentPage(new HotplaceFragment());
        } else if (id == R.id.nav_weather) {
            callFragmentPage(new WeatherFragment());
        } else if (id == R.id.nav_festival) {
            callFragmentPage(new FestivalFragment());
        } else if (id == R.id.nav_food) {
            callFragmentPage(new FoodFragment());
        } else if (id == R.id.nav_stamp_book) {
            callFragmentPage(new StampBookFragment());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setToolbarText(Fragment fragment)
    {
        String toolbarText="";
        if(fragment instanceof MainFragment)
            toolbarText = "";
        else if(fragment instanceof StampBookFragment)
            toolbarText = "스탬프 북";
        else if(fragment instanceof FoodFragment)
            toolbarText = "모범 음식점 추천";
        else if(fragment instanceof FestivalFragment)
            toolbarText = "공연행사정보";
        else if(fragment instanceof JejuArtcenterFragment)
            toolbarText = "제주아트센터";
        else if(fragment instanceof JejuCultureStreetFragment)
            toolbarText = "제주문화축제의거리";
        else if(fragment instanceof JejuFestivalFragment)
            toolbarText = "제주시 축제/행사";
        else if(fragment instanceof SeogwipoCultureFragment)
            toolbarText = "서귀포 문화예술행사";
        else if(fragment instanceof JejuArtcenterSearchFragment)
            toolbarText = "제주아트센터 검색결과";
        else if(fragment instanceof CouponFragment)
            toolbarText = "사용쿠폰 현황";
        else if(fragment instanceof PartnerStoreFragment)
            toolbarText = "제휴업체";
        else if(fragment instanceof HotplaceFragment)
            toolbarText = "제주 HOT 플레이스";
        else if(fragment instanceof AboutFragment)
            toolbarText = "개발자 정보";
        else if(fragment instanceof InfoFragment)
            toolbarText = "메뉴 설명서";
        else if(fragment instanceof StampGetFragment)
            toolbarText = "스탬프 모으기";
        else if(fragment instanceof WeatherFragment)
            toolbarText = "날씨정보";
        else if(fragment instanceof HotplaceDetailFragment)
            toolbarText = "제주 HOT플레이스 검색결과";
        else if(fragment instanceof FoodDetailFragment)
            toolbarText = "모범 음식점 검색결과";

        toolbar.setTitle(toolbarText);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    public void callFragmentPage(Fragment fragment)
    {
        setToolbarText(fragment);

        isQuit=2;

        curFragment=fragment;

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(frame_layout.getId(),fragment);
        fragmentTransaction.commit();
    }

    public void callFragmentPageWithData(Fragment fragment, ArrayList<String> strings)
    {
        setToolbarText(fragment);

        isQuit=2;

        curFragment=fragment;

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle(strings.size());
        for(int i = 0 ; i <strings.size();i++)
            bundle.putStringArrayList("DATA",strings);

        fragment.setArguments(bundle);

        fragmentTransaction.replace(frame_layout.getId(),fragment);
        fragmentTransaction.commit();
    }



}
