package com.yougou.streetball;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

import com.yougou.enums.ImagePathEnum;

/**
 * 
 * @author Orange丶Athena
 *
 */
public class Im4JavaUtil {
	
	public static void main(String[] args) {
		changeCanvasSize();
		cropImage();
	}

	/**
	 * 给1364*768的图片加白色画布,画布大小为2048*1024
	 */
	public static void changeCanvasSize() {
		for(int i = 1;i < 7;i++) {
			IMOperation im = new IMOperation();
			im.addImage(ImagePathEnum.ORIGINAL_PATH.getDesc() + i + ".jpg");
			im.addRawArgs("-define","distort:viewport=2048x1024+0+0");
			im.addRawArgs("-virtual-pixel","white");
			im.addRawArgs("-distort","SRT","0","+repage");
			im.addImage(ImagePathEnum.CANVAS_PATH.getDesc() + i + ".jpg");
			ConvertCmd cmd = new ConvertCmd();
			cmd.setSearchPath(System.getenv("IM_HOME"));
			try {
				cmd.run(im);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("图片画布已经从1024*768变为2048*1024了");
	}
	
	/**
	 * 把2048*1024的图片切成大小相等的两张图片
	 */
	public static void cropImage() {
		for(int i = 1;i < 7;i++) {
			IMOperation im = new IMOperation();
			im.addRawArgs("-crop","1024x1024");
			im.addImage(ImagePathEnum.CANVAS_PATH.getDesc() + i + ".jpg");
			im.addImage(ImagePathEnum.CROP_PATH.getDesc() + i + ".jpg");
			ConvertCmd cmd = new ConvertCmd();
			cmd.setSearchPath(System.getenv("IM_HOME"));
			try {
				cmd.run(im);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("图片已经切好");
	}
}
