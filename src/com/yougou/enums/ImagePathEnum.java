package com.yougou.enums;

public enum ImagePathEnum {
	ORIGINAL_PATH("E:\\美化包原始图片\\","ORIGINAL"),
	CANVAS_PATH("E:\\美化包改变画布后的图片\\","CANVAS"),
	CROP_PATH("E:\\美化包切图后的图片\\","CROP");
	
	private String key;
	private String desc;
	
	private ImagePathEnum(String key, String desc) {
		this.key = key;
		this.desc = desc;
	}
	
	/**
	 * 传入key值,获取对应的描述
	 */
	public static String getDescByKey(String keyValue) {
		for (ImagePathEnum path : ImagePathEnum.values()) {
			String key = path.getKey();
			if(key == keyValue) {
				return path.getDesc();
			}
		}
		return null;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
