# BannerView
Banner轮播图

## 截图
![images](https://github.com/Wiser-Wong/BannView/blob/master/images/banner.gif)

## 环境配置

    allprojects {
        repositories {
          ...
          maven { url 'https://jitpack.io' }
        }
      }

        dependencies {
              implementation 'com.github.Wiser-Wong:BannerView:1.0.0'
      }

## 使用方法
    bannerPagerView.setPages(this, new BannerHolder<String>() {

          private AppCompatImageView ivBanner;

          @Override public View createView(Context context, LayoutInflater inflater, ViewGroup container) {
            View view = inflater.inflate(R.layout.banner_layout, container, false);
            ivBanner = view.findViewById(R.id.iv_banner);
            return view;
          }

          @Override public void bindData(Context context, int position, String data) {
            if (!TextUtils.isEmpty(data)) Glide.with(context).load(data).thumbnail(0.1f).into(ivBanner);
          }
        }, imageUrls).isDot(true, Color.YELLOW, Color.RED, BannerPagerView.RIGHT_DOT).setOnItemClickListener(new BannerPagerAdapter.OnItemClickListener() {
          @Override
          public void setOnItemListener(View view, int position) {//点击监听
            Toast.makeText(MainActivity.this,"点击位置：-->>" + position,Toast.LENGTH_LONG).show();
          }
        }).addOnPageChangeListener(new BannerPagerView.OnPageChangeListener() {//滚动监听
          @Override
          public void onPageScrolled(int i, float v, int i1) {

          }

          @Override
          public void onPageSelected(int i) {

          }

          @Override
          public void onPageScrollStateChanged(int i) {

          }
        }).startTurning(2200);
        
        <com.wiser.banner.BannerPagerView
            android:id="@+id/bpv_page_view"
            android:layout_width="match_parent"
            android:layout_height="250dp" />

