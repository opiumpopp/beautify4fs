package com.yougou.streetball;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
		File dir = new File(ImagePathEnum.CROP_PATH.getDesc());
		if(!dir.exists()) {
			dir.mkdir();
		}
		cropImage();
		//list集合的使用
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		//两个整数求和
		int a = 3;
		int b = 2;
		int sum = a + b;
		System.out.println(sum);
	}

	/**
	 * 给1364*768的图片加上白色画布,画布大小为2048*1024,然后切成大小为1024*1024的两张图片
	 */
	public static void cropImage() {
		for(int i = 1;i < 7;i++) {
			IMOperation im = new IMOperation();
			im.addImage(ImagePathEnum.ORIGINAL_PATH.getDesc() + i + ".jpg");
			im.addRawArgs("-define","distort:viewport=2048x1024+0+0");
			im.addRawArgs("-virtual-pixel","white");
			im.addRawArgs("-distort","SRT","0","+repage");
			im.addRawArgs("-crop","1024x1024");
			im.addImage(ImagePathEnum.CROP_PATH.getDesc() + i + ".jpg");
			ConvertCmd cmd = new ConvertCmd();
			cmd.setSearchPath(System.getenv("IM_HOME"));
			try {
				cmd.run(im);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("图片已经切好了");
	}
	
}
