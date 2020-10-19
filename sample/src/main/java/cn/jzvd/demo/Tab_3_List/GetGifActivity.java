package cn.jzvd.demo.Tab_3_List;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;

import java.util.LinkedHashMap;

import cn.jzvd.JZDataSource;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import cn.jzvd.demo.ApplicationDemo;
import cn.jzvd.demo.CustomJzvd.JzvdStdGetGif;
import cn.jzvd.demo.R;
import cn.jzvd.demo.Urls;

/**
 * @author dl
 * @time 2020-03-30
 * @des
 */
public class GetGifActivity extends AppCompatActivity {

    JzvdStdGetGif jzvdStdGetGif;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setTitle(getString(R.string.get_gif));
        setContentView(R.layout.activity_get_gif);

        //checkPermission
        int permission = ActivityCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    "android.permission.READ_EXTERNAL_STORAGE",
                    "android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        }

        jzvdStdGetGif = findViewById(R.id.jz_video);

        LinkedHashMap map = new LinkedHashMap();
        String proxyUrl = ApplicationDemo.getProxy(getBaseContext()).getProxyUrl(Urls.clarities[0]);
        map.put("高清", proxyUrl);
        map.put("标清", Urls.clarities[1]);
        map.put("普清", Urls.clarities[2]);
        JZDataSource jzDataSource = new JZDataSource(map, "饺子会拼图");
        jzDataSource.looping = true;
        jzDataSource.currentUrlIndex = 2;
        jzDataSource.headerMap.put("key", "value");//header
        jzvdStdGetGif.setUp(jzDataSource
                , JzvdStd.SCREEN_NORMAL);
        Glide.with(this).load(Urls.videoPosterList[0]).into(jzvdStdGetGif.posterImageView);



//        String url = "http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4";
//        jzvdStdGetGif.setUp(url, "饺子会拼图");
//        Glide.with(this).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").into(jzvdStdGetGif.posterImageView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
