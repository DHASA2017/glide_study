package com.dl.glidesourcedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class GlideBaseUseActivity extends AppCompatActivity {

    public static final String PIC_URL = "http://c.hiphotos.baidu.com/image/h%3D200/sign=38dbb7b714178a82d13c78a0c602737f/4e4a20a4462309f72aafe069780e0cf3d6cad68d.jpg";
    public static final String GIF_URL = "http://p1.pstatp.com/large/166200019850062839d3";
//    public static final String GIF_URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496975336&di=fccd262d5f6684ad3216a2c0ac315914&imgtype=jpg&er=1&src=http%3A%2F%2Fi.zeze.com%2Fattachment%2Fforum%2F201608%2F26%2F191212b6ssqcbpgn2zs26s.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glidebaseuse);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Glide基本使用");
        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                loadImgBase();//基本加载方式
//                loadImgDiskCache();//设置缓存方式
//                loadImgWithPlaceHolder();//设置占位图和错误图
//                loadImgGif();//加载gif格式图片
//                loadImgSpecFormat();//指定加载的图片格式
                loadImgSpecSize();//指定加载图片的尺寸--单位像素
            }
        });
    }

    /**
     * 最基本的加载图片的方式
     * 三步走
     */
    private void loadImgBase() {
        Glide.with(this).load(PIC_URL).into((ImageView) findViewById(R.id.iv_img));
    }

    /**
     * diskCacheStrategy是硬盘缓存，你如果想直观看出效果，需要把app杀掉再重新加载，这样才能看出效果。
     * 直接重新点按钮，发现图片立刻就显示出来了，这是因为Glide还有一套内存缓存机制，
     */
    private void loadImgDiskCache(){//默认就有硬盘缓存
        Glide.with(this).load(PIC_URL).diskCacheStrategy(DiskCacheStrategy.NONE).into((ImageView) findViewById(R.id.iv_img));
    }

    /**
     * 增加网络请求时的
     * 占位图和加载失败图
     */
    private void loadImgWithPlaceHolder() {
        Glide.with(this).load(PIC_URL).placeholder(R.drawable.img_default).error(R.drawable.loading_fail).diskCacheStrategy(DiskCacheStrategy.NONE).into((ImageView) findViewById(R.id.iv_img));
    }

    /**
     * Glide是支持加载GIF图片的。这一点确实非常牛逼，因为相比之下Jake Warton曾经明确表示过，Picasso是不会支持加载GIF图片的。
     * 使用Glide加载GIF图并不需要编写什么额外的代码，Glide内部会自动判断图片格式
     */
    private void loadImgGif() {
        Glide.with(this).load(GIF_URL).placeholder(R.drawable.img_default).error(R.drawable.loading_fail).diskCacheStrategy(DiskCacheStrategy.NONE).into((ImageView) findViewById(R.id.iv_img));
    }

    /**
     * 指定加载图片的格式
     * 如果是gif图自动是gif类型，可以指定加载静态图片
     * 但是如果是静态图片，指定gif类型，则会加载错误。
     */
    private void loadImgSpecFormat(){
//        Glide.with(this).load(GIF_URL).asBitmap().placeholder(R.drawable.img_default).error(R.drawable.loading_fail).diskCacheStrategy(DiskCacheStrategy.NONE).into((ImageView) findViewById(R.id.iv_img));
        Glide.with(this).load(PIC_URL).asGif().placeholder(R.drawable.img_default).error(R.drawable.loading_fail).diskCacheStrategy(DiskCacheStrategy.NONE).into((ImageView) findViewById(R.id.iv_img));
    }

    /**
     * 指定大小
     * 在加载图片的时候很容易会造成内存浪费。什么叫内存浪费呢？
     * 比如说一张图片的尺寸是1000*1000像素，但是我们界面上的ImageView可能只有200*200像素，
     * 这个时候如果你不对图片进行任何压缩就直接读取到内存中，这就属于内存浪费了，因为程序中根本就用不到这么高像素的图片。
     *
     * 使用Glide，我们就完全不用担心图片内存浪费，甚至是内存溢出的问题。因为Glide从来都不会直接将图片的完整尺寸全部加载到内存中，而是用多少加载多少。
     * Glide会自动判断ImageView的大小，然后只将这么大的图片像素加载到内存当中，帮助我们节省内存开支。
     * 实现机制就是google官方文档中介绍的方法，郭霖译文：http://blog.csdn.net/guolin_blog/article/details/9316683
     */
    private void loadImgSpecSize(){
        Glide.with(this).load(PIC_URL).placeholder(R.drawable.img_default).error(R.drawable.loading_fail).diskCacheStrategy(DiskCacheStrategy.NONE).override(500,500).into((ImageView) findViewById(R.id.iv_img));
    }









    /**
     * glide存在很多load的重载方法
     */
//    // 加载本地图片
//    File file = new File(getExternalCacheDir() + "/image.jpg");
//    Glide.with(this).load(file).into(imageView);
//
//    // 加载应用资源
//    int resource = R.drawable.image;
//    Glide.with(this).load(resource).into(imageView);
//
//    // 加载二进制流
//    byte[] image = getImageBytes();
//    Glide.with(this).load(image).into(imageView);
//
//    // 加载Uri对象
//    Uri imageUri = getImageUri();
//    Glide.with(this).load(imageUri).into(imageView);
}
