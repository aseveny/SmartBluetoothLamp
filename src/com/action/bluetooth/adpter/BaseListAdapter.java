package com.action.bluetooth.adpter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class BaseListAdapter<T> extends BaseAdapter {

	List<T> datas;
	Context context;

	/**
	 * datas 
	 * 
	 * @param datas
	 */
	void setDatas(List<T> datas) {
		this.datas = datas;
	}

	/**
	 * set the context
	 * 
	 * @param context
	 */
	void setContext(Context context) {
		this.context = context;
	}

	/**
	 * display the column
	 */
	@Override
	public int getCount() {
		return datas == null ? 0 : datas.size();
	}

	/**
	 * get the column
	 */
	@Override
	public T getItem(int position) {
		return (datas == null || datas.size() == 0) ? null : datas
				.get(position);
	}

	/**
	 * get the id
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * get the View
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return setItemView(position, convertView, parent);
	}

	/**
	 *set the view
	 * 
	 * @param position
	 * @param convertView
	 * @param parent
	 * @return
	 */
	abstract View setItemView(int position, View convertView, ViewGroup parent);
}
