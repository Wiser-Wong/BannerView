package com.wiser.bannerpager;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.wiser.banner.BannerHolder;
import com.wiser.banner.BannerPagerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends FragmentActivity {

	private List<String> imageUrls = new ArrayList<>();

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		BannerPagerView bannerPagerView = findViewById(R.id.bpv_page_view);

		addData();

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
		}, imageUrls).isDot(true, Color.YELLOW, Color.RED, BannerPagerView.RIGHT_DOT).setCircle(true).startTurning(1200);

	}

	private void addData() {
		imageUrls.add("http://pic1.win4000.com/wallpaper/6/51e35bd76cd74.jpg");
		imageUrls.add("http://b-ssl.duitang.com/uploads/item/201706/10/20170610095055_G5LM8.jpeg");
		imageUrls.add("http://i0.hdslb.com/bfs/article/a22a162bca94cdf7438ea556dfa021b181bf3873.jpg");
	}
}
