package com.wong.voic2word.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

/**
 * @author 黄小天  1853955116@qq.com
 * @date 2018年8月21日 下午12:54:36
 * 剪贴板工具类
 */
public class ClipboardUitl {
	
	//获取系统剪贴板
	static Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	private static Robot robot = null;
    static {
    	if (robot == null) {
    		try {
				robot = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}
	}
	
    /**
     * 把文本设置到剪贴板
     */
    public static void setClipboardString(String text) {
    	// 封装文本内容
    	Transferable trans = new StringSelection(text);
        // 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);
    }

    /**
     * 从剪贴板中获取文本
     */
    public static String getClipboardString() {
        // 获取剪贴板中的内容
        Transferable trans = clipboard.getContents(null);
        if (trans != null) {
            // 判断剪贴板中的内容是否支持文本
            if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    // 获取剪贴板中的文本内容
                    String text = (String)trans.getTransferData(DataFlavor.stringFlavor);
                    return text;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    /**
     * 执行粘贴操作
     * @param text 需要粘贴的字符串，为空则粘贴系统已经存在的
     */
    public static void paste(String text) {
		if (!StringTool.isEmpty(text)) {
			setClipboardString(text);
		}
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
    }
}
