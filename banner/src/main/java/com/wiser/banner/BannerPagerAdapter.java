package com.wiser.banner;

import java.util.List;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Wiser
 * @version 版本
 */
@SuppressWarnings("unchecked")
public class BannerPagerAdapter extends PagerAdapter {

	private LayoutInflater		mInflater;

	/**
	 * 数据
	 */
	private List				mItems;

	private FragmentActivity	activity;

	private BannerHolder		holder;

	private boolean				isCircle;

	private OnItemClickListener	onItemListener;

	BannerPagerAdapter(FragmentActivity activity, BannerHolder holder) {
		this.activity = activity;
		this.holder = holder;
		this.mInflater = LayoutInflater.from(activity);
	}

	public BannerPagerAdapter getAdapter() {
		return this;
	}

	public void setCircle(boolean isCircle) {
		this.isCircle = isCircle;
		notifyDataSetChanged();
	}

	public boolean isCircle() {
		return this.isCircle;
	}

	public void setOnItemListener(OnItemClickListener onItemListener) {
		this.onItemListener = onItemListener;
	}

	public void setItems(List mItems) {
		this.mItems = mItems;
		notifyDataSetChanged();
	}

	public List getItems() {
		return mItems;
	}

	public int getItemCounts() {
		return mItems == null ? 0 : mItems.size();
	}

	public Object getItem(int position) {
		return mItems.get(position);
	}

	@Override public int getCount() {
		if (mItems != null && mItems.size() > 0) {
			if (isCircle) return Integer.MAX_VALUE;
			else return mItems.size();
		}
		return 0;
	}

	@NonNull @Override public Object instantiateItem(@NonNull ViewGroup container, final int position) {
		if (holder != null) {
			View view = holder.createView(activity, mInflater, container);
			if (isCircle) holder.bindData(activity, position % mItems.size(), getItem(position % mItems.size()));
			else holder.bindData(activity, position, getItem(position));
			if (onItemListener != null && view != null) {
				view.setOnClickListener(new View.OnClickListener() {

					@Override public void onClick(View view) {
						if (isCircle) {
							onItemListener.setOnItemListener(view, position % mItems.size());
						} else {
							onItemListener.setOnItemListener(view, position);
						}
					}
				});
			}
			if (view != null) {
				container.addView(view);
				return view;
			} else {
				return super.instantiateItem(container, position);
			}
		} else {
			View view = new View(activity);
			container.addView(view);
			return view;
		}
	}

	@Override public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
		return view == o;
	}

	@Override public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
		container.removeView((View) object);
	}

	public interface OnItemClickListener {

		void setOnItemListener(View view, int position);
	}

}
