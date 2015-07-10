package com.cpns.notification;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AndroidNotification extends NotificationBase{
	private String collapse_key;
	private int ttl;
	private boolean delay_while_idle;
	private String regId;
	private String title;
	private String text;

	public AndroidNotification() {
		super();
	}
	public AndroidNotification(String title, String text, String regId)
	{
		super();
		this.delay_while_idle = true;
		this.regId = regId;
		this.title = title;
		this.text = text;
	}
	public AndroidNotification(String collapse_key, int ttl,
			boolean delay_while_idle, 
			String title, String text, String regId) {
		super();
		this.collapse_key = collapse_key;
		this.ttl = ttl;
		this.delay_while_idle = delay_while_idle;
		this.regId = regId;
		this.title = title;
		this.text = text;
	}

	public String getCollapse_key() {
		return collapse_key;
	}
	public void setCollapse_key(String collapse_key) {
		this.collapse_key = collapse_key;
	}

	public int getTtl() {
		return ttl;
	}
	public void setTtl(int ttl) {
		this.ttl = ttl;
	}

	public boolean isDelay_while_idle() {
		return delay_while_idle;
	}
	public void setDelay_while_idle(boolean delay_while_idle) {
		this.delay_while_idle = delay_while_idle;
	}

	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public String toJSON()
	{
		JSONObject notification = new JSONObject();
		try {
			notification.put("collapse_key", getCollapse_key());
			if(getTtl() > 0)
				notification.put("time_to_live", getTtl());
			notification.put("delay_while_idle", isDelay_while_idle());
			JSONObject data = new JSONObject();
			data.put("title", getTitle());
			data.put("text", getText());
			notification.put("data", data);
			JSONArray regIds = new JSONArray();
			regIds.put(getRegId());
			notification.put("registration_ids", regIds);
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
		return notification.toString();

		
	}

}
