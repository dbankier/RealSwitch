package com.yydigital.realswitch;

import org.appcelerator.titanium.view.TiUIView;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.titanium.TiC;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.util.TiUIHelper;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class RealSwitch extends TiUIView implements OnCheckedChangeListener {

	private boolean oldValue = false;

	public RealSwitch(TiViewProxy proxy) {
		super(proxy);
	}

	@Override
	public void processProperties(KrollDict d) {
		super.processProperties(d);

		// Create Real Switch
		CompoundButton button = new Switch(proxy.getActivity()) {
			@Override
			protected void onLayout(boolean changed, int left, int top,
					int right, int bottom) {
				super.onLayout(changed, left, top, right, bottom);
				TiUIHelper.firePostLayoutEvent(proxy);
			}
		};

		setNativeView(button);
		updateButton(button, proxy.getProperties());
		button.setOnCheckedChangeListener(this);

		if (d.containsKey(TiC.PROPERTY_VALUE)) {
			oldValue = TiConvert.toBoolean(d, TiC.PROPERTY_VALUE);
		}

		View nativeView = getNativeView();
		if (nativeView != null) {
			updateButton((CompoundButton) nativeView, d);
		}
	}

	protected void updateButton(CompoundButton cb, KrollDict d) {
		if (d.containsKey(TiC.PROPERTY_TITLE_OFF)) {
			((Switch) cb).setTextOff(TiConvert.toString(d,
					TiC.PROPERTY_TITLE_OFF));
		}
		if (d.containsKey(TiC.PROPERTY_TITLE_ON)) {
			((Switch) cb).setTextOn(TiConvert.toString(d,
					TiC.PROPERTY_TITLE_ON));
		}
		if (d.containsKey(TiC.PROPERTY_VALUE)) {
			cb.setChecked(TiConvert.toBoolean(d, TiC.PROPERTY_VALUE));
		}
		if (d.containsKey(TiC.PROPERTY_COLOR)) {
			cb.setTextColor(TiConvert.toColor(d, TiC.PROPERTY_COLOR));
		}
		if (d.containsKey(TiC.PROPERTY_FONT)) {
			TiUIHelper.styleText(cb, d.getKrollDict(TiC.PROPERTY_FONT));
		}
		if (d.containsKey(TiC.PROPERTY_TEXT_ALIGN)) {
			String textAlign = d.getString(TiC.PROPERTY_TEXT_ALIGN);
			TiUIHelper.setAlignment(cb, textAlign, null);
		}
		if (d.containsKey(TiC.PROPERTY_VERTICAL_ALIGN)) {
			String verticalAlign = d.getString(TiC.PROPERTY_VERTICAL_ALIGN);
			TiUIHelper.setAlignment(cb, null, verticalAlign);
		}
		cb.invalidate();
	}

	@Override
	public void propertyChanged(String key, Object oldValue, Object newValue,
			KrollProxy proxy) {


		CompoundButton cb = (CompoundButton) getNativeView();
		if (key.equals(TiC.PROPERTY_TITLE_OFF)) {
			((Switch) cb).setTextOff((String) newValue);
		} else if (key.equals(TiC.PROPERTY_TITLE_ON)) {
			((Switch) cb).setTextOff((String) newValue);
		} else if (key.equals(TiC.PROPERTY_VALUE)) {
			cb.setChecked(TiConvert.toBoolean(newValue));
		} else if (key.equals(TiC.PROPERTY_COLOR)) {
			cb.setTextColor(TiConvert.toColor(TiConvert.toString(newValue)));
		} else if (key.equals(TiC.PROPERTY_FONT)) {
			TiUIHelper.styleText(cb, (KrollDict) newValue);
		} else if (key.equals(TiC.PROPERTY_TEXT_ALIGN)) {
			TiUIHelper.setAlignment(cb, TiConvert.toString(newValue), null);
			cb.requestLayout();
		} else if (key.equals(TiC.PROPERTY_VERTICAL_ALIGN)) {
			TiUIHelper.setAlignment(cb, null, TiConvert.toString(newValue));
			cb.requestLayout();
		} else {
			super.propertyChanged(key, oldValue, newValue, proxy);
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton btn, boolean value) {
		KrollDict data = new KrollDict();

		proxy.setProperty(TiC.PROPERTY_VALUE, value);
		// if user triggered change, we fire it.
		if (oldValue != value) {
			data.put(TiC.PROPERTY_VALUE, value);
			fireEvent(TiC.EVENT_CHANGE, data);
			oldValue = value;
		}
	}

}
