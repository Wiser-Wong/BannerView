package com.wiser.banner;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author Wiser
 * @version 版本
 */
@SuppressLint("RtlHardcoded")
public class BannerPagerView extends FrameLayout implements ViewPager.OnPageChangeListener {

	private BannerView				bannerView;

	private LinearLayout			dotLayout;

	public static final int			LEFT_DOT		= Gravity.LEFT;		// 左侧指示点

	public static final int			CENTER_DOT		= Gravity.CENTER;	// 中间指示点

	public static final int			RIGHT_DOT		= Gravity.RIGHT;	// 右侧指示点

	private int						unSelectColor	= Color.GRAY;

	private int						selectColor		= Color.WHITE;

	private int						unSelectRes;

	private int						selectRes;

	private boolean					isDot			= false;

	private boolean					isColor			= true;

	private boolean					isFirstLoad		= true;

	private OnPageChangeListener	onPageChangeListener;

	public BannerPagerView(Context paramContext) {
		super(paramContext);
		init();
	}

	public BannerPagerView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		init();
	}

	private void init() {
		bannerView = (BannerView) LayoutInflater.from(getContext()).inflate(R.layout.baner_view, this, false);
		addView(bannerView);
		if (bannerView != null) bannerView.addOnPageChangeListener(this);
	}

	/**
	 * start auto scroll, first scroll delay time is {@link #()}
	 */
	public void startScroll() {
		if (bannerView != null) bannerView.startScroll();
	}

	/**
	 * start auto scroll
	 *
	 * @param delayTimeInMills
	 *            first scroll delay time
	 */
	public void startScroll(long delayTimeInMills) {
		if (bannerView != null) bannerView.startScroll(delayTimeInMills);
	}

	/**
	 * stop auto scroll
	 */
	public void stopScroll() {
		if (bannerView != null) bannerView.stopScroll();
	}

	/**
	 * set the factor by which the duration of sliding animation will change while
	 * swiping
	 */
	public void setSwipeScrollDurationFactor(double scrollFactor) {
		if (bannerView != null) bannerView.setSwipeScrollDurationFactor(scrollFactor);
	}

	/**
	 * set the factor by which the duration of sliding animation will change while
	 * auto scrolling
	 */
	public void setAutoScrollDurationFactor(double scrollFactor) {
		if (bannerView != null) bannerView.setAutoScrollDurationFactor(scrollFactor);
	}

	/**
	 * set auto scroll direction
	 */
	public BannerPagerView setDirection(int direction) {
		if (bannerView != null) bannerView.setDirection(direction);
		return this;
	}

	/**
	 * set whether stop auto scroll when touching, default is true
	 *
	 * @param stopScrollWhenTouch
	 */
	public BannerPagerView setStopScrollWhenTouch(boolean stopScrollWhenTouch) {
		if (bannerView != null) bannerView.setStopScrollWhenTouch(stopScrollWhenTouch);
		return this;
	}

	/**
	 * set how to process when sliding at the last or first item
	 *
	 * @param slideBorderMode
	 */
	public BannerPagerView setSlideBorderMode(int slideBorderMode) {
		if (bannerView != null) bannerView.setSlideBorderMode(slideBorderMode);
		return this;
	}

	/**
	 * set whether animating when auto scroll at the last or first item, default is
	 * true
	 *
	 * @param isBorderAnimation
	 */
	public BannerPagerView setBorderAnimation(boolean isBorderAnimation) {
		if (bannerView != null) bannerView.setBorderAnimation(isBorderAnimation);
		return this;
	}

	/**
	 * 启动加载Banner
	 *
	 * @param activity
	 * @param holder
	 * @param list
	 * @return
	 */
	public BannerPagerView setPages(FragmentActivity activity, BannerHolder holder, List list) {
		isFirstLoad = true;
		if (bannerView != null) bannerView.setPages(activity, holder, list);
		return this;
	}

	/**
	 * 启动定时滑动 定时滑动默认是循环的
	 *
	 * @param interval
	 * @return
	 */
	public void startTurning(long interval) {
		if (bannerView != null) bannerView.startTurning(interval);
	}

	/**
	 * Banner是否循环 默认循环
	 *
	 * @param isCircle
	 * @return
	 */
	public BannerPagerView setCircle(boolean isCircle) {
		if (bannerView != null) {
			bannerView.setCircle(isCircle);
		}
		if (isDot && isFirstLoad && dotLayout != null && dotLayout.getChildCount() > 0) {
			isFirstLoad = false;
			if (isColor) {
				((PointView) dotLayout.getChildAt(0)).setColor(selectColor);
			} else {
				((AppCompatImageView) dotLayout.getChildAt(0)).setImageResource(selectRes);
			}
		}
		return this;
	}

	/**
	 * Banner默认加载数量
	 *
	 * @param limit
	 * @return
	 */
	public BannerPagerView setOffsetPageLimit(int limit) {
		if (bannerView != null) bannerView.setOffsetPageLimit(limit);
		return this;
	}

	// Banner适配器实例
	public BannerPagerAdapter adapter() {
		if (bannerView != null) {
			return bannerView.adapter();
		}
		return null;
	}

	// BannerView实例
	public BannerView bannerView() {
		return bannerView;
	}

	/**
	 * 设置拦截ScrollView
	 *
	 * @param isInterceptTouch
	 * @return
	 */
	public BannerPagerView isInterceptTouch(boolean isInterceptTouch) {
		if (bannerView != null) bannerView.setInterceptTouch(isInterceptTouch);
		return this;
	}

	/**
	 * 设置点击事件
	 *
	 * @param onItemClickListener
	 * @return
	 */
	public BannerPagerView setOnItemClickListener(BannerPagerAdapter.OnItemClickListener onItemClickListener) {
		if (bannerView != null) bannerView.setOnItemClickListener(onItemClickListener);
		return this;
	}

	/**
	 * 创建指示点
	 */
	private void createDot() {
		this.isDot = true;
		if (adapter() != null && adapter().getItemCounts() > 1) {
			dotLayout = new LinearLayout(getContext());
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			params.gravity = Gravity.BOTTOM;
			dotLayout.setGravity(CENTER_DOT);
			dotLayout.setLayoutParams(params);
			for (int i = 0; i < adapter().getItemCounts(); i++) {
				View view;
				if (isColor) {
					PointView pointView = new PointView(getContext());
					view = pointView;
					if (i == 0) {
						pointView.setColor(selectColor);
					} else {
						pointView.setColor(unSelectColor);
					}
				} else {
					ImageView ivView = new ImageView(getContext());
					view = ivView;
					if (i == 0) {
						ivView.setImageResource(selectRes);
					} else {
						ivView.setImageResource(unSelectRes);
					}
				}
				view.setPadding(5, 5, 5, 10);
				dotLayout.addView(view);
			}
			addView(dotLayout);
		}
	}

	/**
	 * 设置指示器方向位置
	 *
	 * @param dotDirection
	 */
	private void setDotDirection(int dotDirection) {
		if (dotLayout != null) {
			dotLayout.setGravity(dotDirection);
			switch (dotDirection) {
				case LEFT_DOT:
					dotLayout.setPadding(15, 0, 0, 0);
					break;
				case RIGHT_DOT:
					dotLayout.setPadding(0, 0, 15, 0);
					break;
			}
		}
	}

	/**
	 * 设置指示器是否显示
	 *
	 * @param isDot
	 * @return
	 */
	public BannerPagerView isDot(boolean isDot) {
		if (!isDot) return this;
		this.isColor = true;
		createDot();
		return this;
	}

	/**
	 * 设置指示器是否显示 以及显示位置
	 *
	 * @param isDot
	 * @param dotDirection
	 * @return
	 */
	public BannerPagerView isDot(boolean isDot, int dotDirection) {
		if (!isDot) return this;
		this.isColor = true;
		createDot();
		setDotDirection(dotDirection);
		return this;
	}

	/**
	 * 设置指示器是否显示 以及指示器变化颜色值 整型
	 *
	 * @param isDot
	 * @param unSelectColor
	 * @param selectColor
	 * @return
	 */
	public BannerPagerView isDot(boolean isDot, int unSelectColor, int selectColor) {
		if (!isDot) return this;
		this.unSelectColor = unSelectColor;
		this.selectColor = selectColor;
		this.isColor = true;
		createDot();
		return this;
	}

	/**
	 * 设置指示器是否显示 以及指示器变化颜色值 整型 和 位置
	 *
	 * @param isDot
	 * @param unSelectColor
	 * @param selectColor
	 * @param dotDirection
	 * @return
	 */
	public BannerPagerView isDot(boolean isDot, int unSelectColor, int selectColor, int dotDirection) {
		if (!isDot) return this;
		this.unSelectColor = unSelectColor;
		this.selectColor = selectColor;
		this.isColor = true;
		createDot();
		setDotDirection(dotDirection);
		return this;
	}

	/**
	 * 设置指示器是否显示 以及指示器变化颜色值 整型
	 *
	 * @param isDot
	 * @param firstRes
	 * @param secondRes
	 * @return
	 */
	public BannerPagerView isDotRes(boolean isDot, int firstRes, int secondRes) {
		if (!isDot) return this;
		this.unSelectRes = firstRes;
		this.selectRes = secondRes;
		this.isColor = false;
		createDot();
		return this;
	}

	/**
	 * 设置指示器是否显示 以及指示器变化颜色值 整型
	 *
	 * @param isDot
	 * @param firstRes
	 * @param secondRes
	 * @return
	 */
	public BannerPagerView isDotRes(boolean isDot, int firstRes, int secondRes, int dotDirection) {
		if (!isDot) return this;
		this.unSelectRes = firstRes;
		this.selectRes = secondRes;
		this.isColor = false;
		createDot();
		setDotDirection(dotDirection);
		return this;
	}

	/**
	 * 改变指示器颜色
	 *
	 * @param index
	 */
	private void changeDot(int index) {
		if (adapter() != null) {
			if (dotLayout != null && dotLayout.getChildCount() > 0) {
				if (bannerView.isCircle()) {
					int position = index % dotLayout.getChildCount();
					for (int i = 0; i < dotLayout.getChildCount(); i++) {
						if (isColor) {
							if (position == i) {
								((PointView) dotLayout.getChildAt(position)).setColor(selectColor);
							} else {
								((PointView) dotLayout.getChildAt(i)).setColor(unSelectColor);
							}
						} else {
							if (position == i) {
								((ImageView) dotLayout.getChildAt(position)).setImageResource(selectRes);
							} else {
								((ImageView) dotLayout.getChildAt(i)).setImageResource(unSelectRes);
							}
						}
					}
				} else {
					for (int i = 0; i < dotLayout.getChildCount(); i++) {
						if (isColor) {
							if (index == i) {
								((PointView) dotLayout.getChildAt(index)).setColor(selectColor);
							} else {
								((PointView) dotLayout.getChildAt(i)).setColor(unSelectColor);
							}
						} else {
							if (index == i) {
								((ImageView) dotLayout.getChildAt(index)).setImageResource(selectRes);
							} else {
								((ImageView) dotLayout.getChildAt(i)).setImageResource(unSelectRes);
							}
						}
					}
				}
			}
		}
	}

	@Override public void onPageScrolled(int i, float v, int i1) {
		if (onPageChangeListener != null) onPageChangeListener.onPageScrolled(i, v, i1);
	}

	@Override public void onPageSelected(int i) {
		if (isDot) changeDot(i);
		if (onPageChangeListener != null) {
			if (adapter() != null && adapter().getItemCounts() > 0) {
				if (bannerView.isCircle()) {
					onPageChangeListener.onPageSelected(i % adapter().getItemCounts());
				} else {
					onPageChangeListener.onPageSelected(i);
				}
			} else {
				onPageChangeListener.onPageSelected(i);
			}
		}
	}

	@Override public void onPageScrollStateChanged(int i) {
		if (onPageChangeListener != null) onPageChangeListener.onPageScrollStateChanged(i);
	}

	/**
	 * 添加页面改变监听
	 *
	 * @param onPageChangeListener
	 * @return
	 */
	public BannerPagerView addOnPageChangeListener(BannerPagerView.OnPageChangeListener onPageChangeListener) {
		this.onPageChangeListener = onPageChangeListener;
		return this;
	}

	public interface OnPageChangeListener {

		void onPageScrolled(int i, float v, int i1);

		void onPageSelected(int i);

		void onPageScrollStateChanged(int i);
	}

	@Override protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		stopScroll();
		onPageChangeListener = null;
		dotLayout = null;
	}

}
