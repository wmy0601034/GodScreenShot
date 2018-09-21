package com.nanningzhuanqian.vscreenshot.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;


import com.nanningzhuanqian.vscreenshot.R;

import java.util.ArrayList;
import java.util.List;


public class NewActionSheetDialog {

	private Context context;

	protected NewActionSheetDialog(Context context) {
		//init(context);
		this.context = context;
	}

	public static class Builder {

		private Context context;
		private Dialog dialog;
		private TextView txt_title;
		private Button btn_cancel;
		private LinearLayout lLayout_content;
		private ScrollView sLayout_content;
		private boolean showTitle = false;
		private List<SheetItem> sheetItemList;
		private Display display;
		private View view;

		public Builder(Context context) {
			this.context = context;
			init(context);
		}

		//初始化界面的控件值
		private void init(Context context){
			context = context;
			WindowManager windowManager = (WindowManager) context
					.getSystemService(this.context.WINDOW_SERVICE);
			display = windowManager.getDefaultDisplay();

			// 获取Dialog布局
			view = LayoutInflater.from(this.context).inflate(
					R.layout.view_actionsheet, null);

			// 设置Dialog最小宽度为屏幕宽度
			view.setMinimumWidth(display.getWidth());

			// 获取自定义Dialog布局中的控件
			sLayout_content = (ScrollView) view.findViewById(R.id.sLayout_content);
			lLayout_content = (LinearLayout) view
					.findViewById(R.id.lLayout_content);
			txt_title = (TextView) view.findViewById(R.id.txt_title);
			btn_cancel = (Button) view.findViewById(R.id.btn_cancel);

			btn_cancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});

			// 定义Dialog布局和参数
			dialog = new Dialog(this.context, R.style.ActionSheetDialogStyle);
			dialog.setContentView(view);
		}



		public Dialog create() {

			Window dialogWindow = dialog.getWindow();
			dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
			WindowManager.LayoutParams lp = dialogWindow.getAttributes();
			lp.x = 0;
			lp.y = 0;

			dialogWindow.setAttributes(lp);

			/** 设置条目布局 */
			setSheetItems();

			return dialog;
		}

		//设置标题
		public Builder setTitle(String title) {
			showTitle = true;
			txt_title.setVisibility(View.VISIBLE);
			txt_title.setBackgroundResource(R.drawable.m08_actiondialog_text_selector);
			txt_title.setText(title);
			return this;
		}

		public Builder setCancelable(boolean cancel) {
			dialog.setCancelable(cancel);
			return this;

		}

		public Builder setCancelButtonVisiable(boolean cancel) {
//			dialog.setCancelable(cancel);
			if(cancel) {
				btn_cancel.setVisibility(View.VISIBLE);
			}else{
				btn_cancel.setVisibility(View.INVISIBLE);
			}
			return this;
		}

		public Builder setCanceledOnTouchOutside(boolean cancel) {
			//取消更换图片事件埋点设置
			dialog.setCanceledOnTouchOutside(cancel);
			return this;
		}

		/** 设置条目布局 */
		private void setSheetItems() {

			if (sheetItemList == null || sheetItemList.size() <= 0) {
				return;
			}

			int size = sheetItemList.size();

			// TODO 高度控制，非最佳解决办法
			// 添加条目过多的时候控制高度
			if (size >= 7) {
				LayoutParams params = (LayoutParams) sLayout_content
						.getLayoutParams();
				params.height = display.getHeight() / 2;
				sLayout_content.setLayoutParams(params);
			}

			// 循环添加条目
			if (size >= 1){
				for (int i = 1; i <= size; i++) {
					final int index = i;
					SheetItem sheetItem = sheetItemList.get(i - 1);
					String strItem = sheetItem.name;
					SheetItemColor color = sheetItem.color;
					final OnSheetItemClickListener listener = (OnSheetItemClickListener) sheetItem.itemClickListener;

					Button button = new Button(this.context);
					button.setText(strItem);
//					button.setTextSize(context.getResources().getDimensionPixelSize(R.dimen.common_textsize_title_small));
					button.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));// 不加粗

					button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
					button.setGravity(Gravity.CENTER);

					//button.setBackgroundResource(R.drawable.m08_actiondialog_button_selector);

					// 只有唯一一条数据，不处理分割线
					if (i == 1) {

						if(size ==1) {
							if(showTitle){
								button.setBackgroundResource(R.drawable.m08_actiondialog_bottom_button_selector);
							}else{
								button.setBackgroundResource(R.drawable.m08_actiondialog_button_selector);
							}

						}else {
							if(showTitle){
								button.setBackgroundResource(R.drawable.m08_actiondialog_top_button_selector_title);

							}
							else{
								button.setBackgroundResource(R.drawable.m08_actiondialog_top_button_selector);
							}
						}


						lLayout_content.addView(button);

					}else if (i == size) {

						// 最后一条，上分割线不显示
						View view = new View(this.context);
						view.setBackgroundColor(this.context.getResources().getColor(R.color.common_list_item_divider));
						lLayout_content.addView(view);
						view.getLayoutParams().height=1;

						button.setBackgroundResource(R.drawable.m08_actiondialog_bottom_button_selector);

						lLayout_content.addView(button);

					} else {
						// 不是第一条也不是最后一条
						// 上分割线不显示，下分割线设置maeginLeft

						View view = new View(this.context);
						view.setBackgroundColor(this.context.getResources().getColor(R.color.common_list_item_divider));
						lLayout_content.addView(view);
						view.getLayoutParams().height=1;
						button.setBackgroundResource(R.drawable.common_list_item_selector);
						lLayout_content.addView(button);

					}

					// 字体颜色
//					button.setTextColor(context.getResources().getColor(R.color.colorPrimary));
					button.setTextColor(Color.parseColor(color.getName()));
//					if (color == null) {
////						button.setTextColor(Color.parseColor(SheetItemColor.Blue
////								.getName()));
//						button.setTextColor(context.getResources().getColor(R.color.colorPrimary));
//					} else {
//						button.setTextColor(Color.parseColor(color.getName()));
//					}

					// 点击事件
					button.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							listener.onClick(index);
							dialog.dismiss();
						}
					});

				}
			}
		}

		public void show() {
			dialog.show();
		}


		/**
		 *
		 * @param strItem
		 *            条目名称
		 * @param color
		 *            条目字体颜色，设置null则默认蓝色
		 * @param listener
		 * @return
		 */
		public Builder addSheetItem(String strItem, SheetItemColor color,
									OnSheetItemClickListener listener) {
			if (sheetItemList == null) {
				sheetItemList = new ArrayList<SheetItem>();
			}
			sheetItemList.add(new SheetItem(strItem, color,listener));
			return this;


		}

		public interface OnSheetItemClickListener {
			void onClick(int which);
		}

		public class SheetItem {

			String name;
			SheetItemColor color;
			int textSize;

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public SheetItemColor getColor() {
				return color;
			}

			public void setColor(SheetItemColor color) {
				this.color = color;
			}

			public int getTextSize() {
				return textSize;
			}

			public void setTextSize(int textSize) {
				this.textSize = textSize;
			}
			OnSheetItemClickListener itemClickListener;
			public SheetItem(String name, SheetItemColor color,
							 OnSheetItemClickListener itemClickListener) {
				this.name = name;
				this.color = color;
//				this.textSize = textSize;
				this.itemClickListener = itemClickListener;
			}
		}

	}

		public enum SheetItemColor {

			Blue("#7db8de"),
			Red("#FD4A2E"),
			Black("#272727");

			private String name;

			private SheetItemColor(String name) {
				this.name = name;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {

				this.name = name;
			}

		}

}
