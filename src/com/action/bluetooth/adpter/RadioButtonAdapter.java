package com.action.bluetooth.adpter;

import java.util.List;

import com.action.bluetooth.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

public class RadioButtonAdapter extends BaseListAdapter<String> {
	private List<Boolean> checkStates;
	private int last = -1;// save items
	private CheckStateUpdateListener checkStateUpdateListener;

	public RadioButtonAdapter(Context context, List<String> datas) {
		setContext(context);
		setDatas(datas);
	}

	public void setCheckStates(List<Boolean> checkStates) {
		this.checkStates = checkStates;
	}

	public void setCheckStateUpdateListener(
			CheckStateUpdateListener checkStateUpdateListener) {
		this.checkStateUpdateListener = checkStateUpdateListener;
	}

	@Override
	View setItemView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_radiobutton, null);
			holder.tv = (TextView) convertView.findViewById(R.id.tv);
			holder.rb = (RadioButton) convertView.findViewById(R.id.rb);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.clear();
		holder.tv.setText(getItem(position));
		holder.rb.setChecked(checkStates.get(position));
		// if checked
		if (checkStates.get(position)) {
			// save the checked
			last = position;
		}
		holder.rb.setOnClickListener(new MyClickListener(position));
		return convertView;
	}

	class ViewHolder {
		TextView tv;
		RadioButton rb;

		void clear() {
			tv.setText("");
			rb.setChecked(false);
		}
	}

	private class MyClickListener implements View.OnClickListener {
		private int position;

		public MyClickListener(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			checkStateUpdateListener.checkStateUpdate(position);
			checkStates.set(position, true);
			if (last != -1) {
				checkStates.set(last, false);
			}
			last = position;

			for (int i = -1; i < position; i++) {
				i += position;
				checkStates.set(last, true);
			}
			RadioButtonAdapter.this.notifyDataSetChanged();
		}
	}

	public interface CheckStateUpdateListener {
		void checkStateUpdate(int position);
	}
}
