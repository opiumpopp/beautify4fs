package com.yougou.streetball;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

/**
 * 
 * @author Orange丶Athena
 *
 */
public class Im4JavaUtil {
	//美化包原始图片存放的路径
	public static final String ORIGINAL_PATH = "E:\\美化包原始图片\\";
	//把1024*768的图片放大为1364*768后的图片路径
	public static final String ENLARGE_PATH = "E:\\美化包放大后的图片\\";
	//给1364*768的图片增加白色画布,画布扩大为2048*1024后的图片路径
	public static final String CANVAS_PATH = "E:\\美化包改变画布后的图片\\";
	//把2048*1024切成两张为1024*1024后图片的路径
	public static final String CROP_PATH = "E:\\美化包切图后的图片\\";

	public static void main(String[] args) {
		//可以直接从网上下载1366*768大小的图片,那么此步骤就不需要进行了
		changeImageSize();
		changeCanvasSize();
		cropImage();
	}

	/**
	 * 把1024*768的图片放大为1364*768的图片
	 */
	public static void changeImageSize() {
		for(int i = 1;i < 7;i++) {
			IMOperation im = new IMOperation();
			im.addImage(ORIGINAL_PATH + i + ".jpg");
			im.resize(1364, 768, "!");
			im.addImage(ENLARGE_PATH + i + ".jpg");
			ConvertCmd cmd = new ConvertCmd();
			cmd.setSearchPath(System.getenv("IM_HOME"));
			try {
				cmd.run(im);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("图片已经从1024*768变为1364*768了");
	}
	
	/**
	 * 给1364*768的图片加白色画布,画布大小为2048*1024
	 */
	public static void changeCanvasSize() {
		for(int i = 1;i < 7;i++) {
			IMOperation im = new IMOperation();
			im.addImage(ENLARGE_PATH + i + ".jpg");
			im.addRawArgs("-define","distort:viewport=2048x1024+0+0");
			im.addRawArgs("-virtual-pixel","white");
			im.addRawArgs("-distort","SRT","0","+repage");
			im.addImage(CANVAS_PATH + i + ".jpg");
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
			im.addImage(CANVAS_PATH + i + ".jpg");
			im.addImage(CROP_PATH + i + ".jpg");
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
